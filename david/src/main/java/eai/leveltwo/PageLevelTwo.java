package eai.leveltwo;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.david.dto.Constants;
import com.example.david.dto.Progress;
import com.example.david.model.LevelOne;
import com.example.david.model.LevelTwo;
import com.example.david.service.LevelOneService;
import com.example.david.service.LevelTwoService;

import eai.page.Page;
import eai.utils.UtilPage;

public class PageLevelTwo{
    private final UtilPage utilPage;
    private final Page page;
    private Progress progress;
    
    public PageLevelTwo(){
    	utilPage = new UtilPage();
        page = new Page();
        progress = Progress.getSingletonInstance();
    }
    
    public void levelTwo(LevelOne levelOne, LevelOneService levelOneService, LevelTwoService levelTwoService){
    	
		try {
			Element firstDiv = page.element(levelOne.getUrl(), "refinementList");
			
    		if(firstDiv != null){
    			Elements articles = firstDiv.getElementsByClass("a-link-normal");
    			List<LevelTwo> levelTwoList = new ArrayList<>();
    			
    			 for(Element article : articles){
    				 data(article, levelTwoList, levelOne.getIdLevel1());
    			 }
    			     
    			 levelTwoService.save(levelTwoList);
    			 levelOne.setStatus(Constants.SUCCESS.val());            
    			 progress.setCountSuccess(progress.calculateCountSuccess());
    		}else {
    			levelOne.setStatus(Constants.FAILURE.val());
    			progress.setCountFail(progress.calculateCountFail());
    		}
		}catch (Exception ex) {
			progress.setCountFail(progress.calculateCountFail());
    		levelOne.setStatus(Constants.ERROR.val());
    	}
    
		levelOneService.save(levelOne);
    }
    
    private void data(Element article, List<LevelTwo> levelTwoList, Integer id) {
    	Elements span = article.getElementsByTag("span");
    	if(!span.isEmpty()) {
    		String name = span.get(0).text().replaceAll(Constants.REPLACE.val(), "").trim();
			 
            int amount = Integer.parseInt(span.get(1).text().replaceAll(Constants.REPLACE.val(), "").trim());
            
            StringBuilder url = new StringBuilder();
            url.append(Constants.BASEURL.val())
            .append(article.attr("href"));
            
            LevelTwo levelTwo = new LevelTwo(name, amount, url.toString(), Constants.PENDING.val(), id);
  	                 
            List<String> urlPages = utilPage.urlPage(levelTwo);
  	                 
            if(urlPages.size() == 1) {
            	levelTwoList.add(levelTwo);
            }else {
            	for (String urls : urlPages) {
            		levelTwoList.add(new LevelTwo(name, amount, urls, Constants.PENDING.val(), id));
            	}
            }
    	}	                 
    }
}

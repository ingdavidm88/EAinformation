package eai.levelthree;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.david.dto.Constants;
import com.example.david.dto.Progress;
import com.example.david.model.LevelThree;
import com.example.david.model.LevelTwo;
import com.example.david.model.ViewLevelTwo;
import com.example.david.service.LevelThreeService;
import com.example.david.service.LevelTwoService;

import eai.page.Page;

public class PageLevelThree{
    private final Page page;
    private Progress progress;
    
    public PageLevelThree(){
    	page = new Page();
        progress = Progress.getSingletonInstance();
    }
    
    public void levelThree(List<ViewLevelTwo> viewLevelTwoList, LevelTwoService levelTwoService, LevelThreeService levelThreeService){
    	for(ViewLevelTwo viewLevelTwo : viewLevelTwoList){
    		LevelTwo levelTwo = levelTwoService.findOne(viewLevelTwo.getIdLevel2());
    		
    		try {
    			Element firstDiv = page.element(viewLevelTwo.getUrl(), "rightResultsATF");
	    		List<LevelThree> levelThreeList = new ArrayList<>();
	    		
	    		if(firstDiv != null){
	    			data(firstDiv, levelThreeList, viewLevelTwo.getIdLevel2());
	    		}
	    		
	    		if(!levelThreeList.isEmpty()){
	    			progress.setCountSuccess(progress.calculateCountSuccess());
	    			levelThreeService.save(levelThreeList);
	    			levelTwo.setStatus(Constants.SUCCESS.val());
	    		}else {
	    			progress.setCountFail(progress.calculateCountFail());
	    			levelTwo.setStatus(Constants.FAILURE.val());
	    		}
    		}catch (Exception ex) {
				progress.setCountFail(progress.calculateCountFail());
    			levelTwo.setStatus(Constants.ERROR.val());
        	}
    		
    		levelTwoService.save(levelTwo);
    	}
    }
    
    private void data(Element firstDiv, List<LevelThree> levelThreeList, Integer id) {
    	Elements articles = firstDiv.getElementsByClass("s-item-container");
		
    	for (Element article : articles) {
          if(!article.getElementsByTag("a").isEmpty()){
          	StringBuilder url = new StringBuilder();
      		url.append(article.getElementsByTag("a").get(0).attr("href"));
      		
      		StringBuilder name = new StringBuilder();
      		name.append(article.getElementsByTag("a").get(0).getElementsByTag("img").attr("alt"));
      		
      		if(!name.toString().isEmpty() && !url.toString().isEmpty())
      			levelThreeList.add(new LevelThree(name.toString(), url.toString(), Constants.ERROR.val(), id));
          	}
		}  
    }
}

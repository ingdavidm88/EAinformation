package eai.levelfour;

import java.math.BigDecimal;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.david.dto.Constants;
import com.example.david.dto.Progress;
import com.example.david.model.LevelFour;
import com.example.david.model.LevelThree;
import com.example.david.model.ViewLevelThree;
import com.example.david.service.LevelFourService;
import com.example.david.service.LevelThreeService;

import eai.page.Page;

public class PageLevelFour{
    private final Page page;
    private Progress progress;
    
    public PageLevelFour(){
    	page = new Page();
        progress = Progress.getSingletonInstance();
    }
    
    public void levelFour(
    		String userAgent,
    		List<ViewLevelThree> viewLevelThreeList, 
    		LevelThreeService levelThreeService, 
    		LevelFourService levelFourService, 
    		BigDecimal usdToCop, 
    		int numberOfRetries, 
    		BigDecimal shippingUsd, 
    		BigDecimal shippingCop){
    	
    	for(ViewLevelThree viewLevelThree : viewLevelThreeList){
    		LevelThree levelThree = levelThreeService.findOne(viewLevelThree.getIdLevel3());
    		
    		try {
    			Element firstDiv = page.element(viewLevelThree.getUrl()+Constants.ES_US.val(), "ppd", numberOfRetries, userAgent);
	    		
    			if(firstDiv != null){
	    			data(firstDiv, usdToCop, levelFourService, levelThree, shippingUsd, shippingCop, viewLevelThree.getIdLevel3());
	    		}else {
	    			progress.setCountFail(progress.calculateCountFail());
	        		levelThree.setStatus(Constants.FAILURE.val());
	    		}
    		}catch (Exception ex) {
    			progress.setCountError(progress.calculateCountError());
        		levelThree.setStatus(Constants.ERROR.val());
        	}
    		
        	levelThreeService.save(levelThree);
    	}
    }
    
    private void data(Element firstDiv, BigDecimal usdToCop, LevelFourService levelFourService, LevelThree levelThree, BigDecimal shippingUsd, BigDecimal shippingCop, Integer id) {
    	StringBuilder priceUsd = new StringBuilder();
        priceUsd.append(firstDiv.getElementById("priceblock_ourprice") == null ? "0" : firstDiv.getElementById("priceblock_ourprice").text().replaceAll("\\$","").replaceAll("US","").replaceAll(",", ""));
        StringBuilder priceCop = new StringBuilder();
        priceCop.append(page.priceValue(usdToCop, priceUsd.toString(), shippingUsd, shippingCop));
        
        Element centerCol = firstDiv.getElementById("centerCol");
        Elements ul = centerCol.getElementById("feature-bullets").getElementsByTag("li");
        StringBuilder description = new StringBuilder();
        for (Element li : ul)
        	description.append(li.text().trim()).append("|");
        
        Element leftDiv = firstDiv.getElementById("altImages");
		Elements articles = leftDiv.getElementsByClass("a-button-text");
		StringBuilder attachments = new StringBuilder();
		
        for (Element article : articles) {
        	if(!article.getElementsByTag("img").isEmpty()){
        		attachments
        			.append(article.getElementsByTag("img").attr("src"))
        			.append("|");
        	}
        }
        
        if(!priceUsd.toString().equals("0")) {
	        levelFourService
	        	.save(new LevelFour(
	        		description.toString(), 
					priceUsd.toString(), 
					priceCop.toString(), 
					attachments.toString(), 
					Constants.PENDING.val(),
					id
	        	));
	        
	        progress.setCountSuccess(progress.calculateCountSuccess());
	        levelThree.setStatus(Constants.SUCCESS.val());
        }else {
        	progress.setCountError(progress.calculateCountError());
    		levelThree.setStatus(Constants.ERROR.val());
        }
    }
}

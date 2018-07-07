package eai.levelfour;

import java.math.BigDecimal;
import java.util.List;

import com.example.david.model.ViewLevelThree;
import com.example.david.service.LevelFourService;
import com.example.david.service.LevelThreeService;

public class ThradLevelFour extends  Thread{
    private final PageLevelFour pageLevelFour;
    
    private final String userAgent;
    private final List<ViewLevelThree> viewLevelThreeList;
    private final LevelThreeService levelThreeService;
    private final LevelFourService levelFourService;
    private final BigDecimal usdToCop;
    private final int numberOfRetries;
    private final BigDecimal shippingUsd;
    private final BigDecimal shippingCop;

    public ThradLevelFour(
    		String userAgent,
    		List<ViewLevelThree> viewLevelThreeList,
    		LevelThreeService levelThreeService,
    		LevelFourService levelFourService,
    		BigDecimal usdToCop,
    		int numberOfRetries,
    		BigDecimal shippingUsd,
    		BigDecimal shippingCop) {
    	
    	pageLevelFour = new PageLevelFour();
    	this.userAgent = userAgent;
    	this.viewLevelThreeList = viewLevelThreeList;
    	this.levelThreeService = levelThreeService;
    	this.levelFourService = levelFourService;
    	this.usdToCop = usdToCop;
    	this.numberOfRetries = numberOfRetries;
    	this.shippingUsd = shippingUsd;
    	this.shippingCop = shippingCop;
    	this.setDaemon(true);
    }
       
    @Override
    public void run(){
        pageLevelFour.levelFour(
        		userAgent,
        		viewLevelThreeList, 
        		levelThreeService, 
        		levelFourService, 
        		usdToCop, 
        		numberOfRetries, 
        		shippingUsd, 
        		shippingCop);
    }
}

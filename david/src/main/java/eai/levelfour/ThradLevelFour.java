package eai.levelfour;

import java.math.BigDecimal;
import java.util.List;

import com.example.david.model.ViewLevelThree;
import com.example.david.service.LevelFourService;
import com.example.david.service.LevelThreeService;

public class ThradLevelFour extends  Thread{
    private final PageLevelFour pageLevelFour;
    
    private final List<ViewLevelThree> viewLevelThreeList;
    private final LevelThreeService levelThreeService;
    private final LevelFourService levelFourService;
    private final BigDecimal usdToCop;
    
    public ThradLevelFour(
    		List<ViewLevelThree> viewLevelThreeList,
    		LevelThreeService levelThreeService,
    		LevelFourService levelFourService,
    		BigDecimal usdToCop) {
    	
    	pageLevelFour = new PageLevelFour();
    	this.viewLevelThreeList = viewLevelThreeList;
    	this.levelThreeService = levelThreeService;
    	this.levelFourService = levelFourService;
    	this.usdToCop = usdToCop;
    }
       
    @Override
    public void run(){
        pageLevelFour.levelFour(viewLevelThreeList, levelThreeService, levelFourService, usdToCop);
    }
}

package eai.levelfive;

import java.util.List;

import com.example.david.model.ViewLevelFour;
import com.example.david.service.LevelFourService;

public class ThradLevelFive extends  Thread{
    private final PageLevelFive pageLevelFive;
    
    private final List<ViewLevelFour> viewLevelFourList;
    private final LevelFourService levelFourService;
    
    public ThradLevelFive(
    		List<ViewLevelFour> viewLevelFourList,
    		LevelFourService levelFourService) {
    	
    	pageLevelFive = new PageLevelFive();
    	this.viewLevelFourList = viewLevelFourList;
    	this.levelFourService = levelFourService;
    }
       
    @Override
    public void run(){
        pageLevelFive.levelFive(viewLevelFourList, levelFourService);
    }
}

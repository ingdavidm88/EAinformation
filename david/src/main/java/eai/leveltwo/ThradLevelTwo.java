package eai.leveltwo;

import com.example.david.model.LevelOne;
import com.example.david.service.LevelOneService;
import com.example.david.service.LevelTwoService;

public class ThradLevelTwo extends  Thread{
    private final PageLevelTwo pageLevelTwo;
    
    private final LevelOne levelOne;
    private final LevelOneService levelOneService;
    private final LevelTwoService levelTwoService;
    
    public ThradLevelTwo(
    		LevelOne levelOne,
    		LevelOneService levelOneService,
    		 LevelTwoService levelTwoService) {
    	
    	pageLevelTwo = new PageLevelTwo();
    	this.levelOne = levelOne;
    	this.levelOneService = levelOneService;
    	this.levelTwoService = levelTwoService;
    }
       
    @Override
    public void run(){
    	pageLevelTwo.levelTwo(levelOne, levelOneService, levelTwoService);
    }
}

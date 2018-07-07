package eai.leveltwo;

import com.example.david.model.LevelOne;
import com.example.david.service.LevelOneService;
import com.example.david.service.LevelTwoService;

public class ThradLevelTwo extends  Thread{
    private final PageLevelTwo pageLevelTwo;
    
    private final String userAgent;
    private final LevelOne levelOne;
    private final LevelOneService levelOneService;
    private final LevelTwoService levelTwoService;
    private final int numberOfRetries;
    
    public ThradLevelTwo(
    		String userAgent,
    		LevelOne levelOne,
    		LevelOneService levelOneService,
    		LevelTwoService levelTwoService,
    		int numberOfRetries) {
    	
    	pageLevelTwo = new PageLevelTwo();
    	this.userAgent = userAgent;
    	this.levelOne = levelOne;
    	this.levelOneService = levelOneService;
    	this.levelTwoService = levelTwoService;
    	this.numberOfRetries = numberOfRetries;
    	this.setDaemon(true);
    }
       
    @Override
    public void run(){
    	pageLevelTwo.levelTwo(
    			userAgent,
    			levelOne, 
    			levelOneService, 
    			levelTwoService, 
    			numberOfRetries);
    }
}

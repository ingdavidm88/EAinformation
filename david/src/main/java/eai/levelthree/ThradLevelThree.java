package eai.levelthree;

import java.util.List;

import com.example.david.model.ViewLevelTwo;
import com.example.david.service.LevelThreeService;
import com.example.david.service.LevelTwoService;

public class ThradLevelThree extends  Thread{
    private final PageLevelThree pageLevelThree;
    
    private final String userAgent;
    private final List<ViewLevelTwo> viewLevelTwoList;
    private final LevelTwoService levelTwoService;
    private final LevelThreeService levelThreeService;
    private final int numberOfRetries;
    
    public ThradLevelThree(
    		String userAgent,
    		List<ViewLevelTwo> viewLevelTwoList,
    		LevelTwoService levelTwoService,
    		LevelThreeService levelThreeService,
    		int numberOfRetries) {
    	
    	pageLevelThree = new PageLevelThree();
    	this.userAgent = userAgent;
    	this.viewLevelTwoList = viewLevelTwoList;
        this.levelTwoService = levelTwoService;
        this.levelThreeService = levelThreeService;
        this.numberOfRetries = numberOfRetries;
        this.setDaemon(true);
    }
       
    @Override
    public void run(){
    	pageLevelThree.levelThree(
    			userAgent,
    			viewLevelTwoList, 
    			levelTwoService, 
    			levelThreeService, 
    			numberOfRetries);
    }
}

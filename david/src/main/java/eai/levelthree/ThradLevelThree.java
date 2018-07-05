package eai.levelthree;

import java.util.List;

import com.example.david.model.ViewLevelTwo;
import com.example.david.service.LevelThreeService;
import com.example.david.service.LevelTwoService;

public class ThradLevelThree extends  Thread{
    private final PageLevelThree pageLevelThree;
    
    private final List<ViewLevelTwo> viewLevelTwoList;
    private final LevelTwoService levelTwoService;
    private final LevelThreeService levelThreeService;
    
    public ThradLevelThree(
    		List<ViewLevelTwo> viewLevelTwoList,
    		LevelTwoService levelTwoService,
    		LevelThreeService levelThreeService) {
    	
    	pageLevelThree = new PageLevelThree();
    	this.viewLevelTwoList = viewLevelTwoList;
        this.levelTwoService = levelTwoService;
        this.levelThreeService = levelThreeService;
        this.setDaemon(true);
    }
       
    @Override
    public void run(){
    	pageLevelThree.levelThree(viewLevelTwoList, levelTwoService, levelThreeService);
    }
}

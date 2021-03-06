package eai.levelone;

import com.example.david.model.LevelOne;
import com.example.david.service.LevelOneService;

public class ThradLevelOne extends  Thread{
	private final PageLevelOne pageLevelOne;
	private final LevelOne levelOne;
	private final LevelOneService levelOneService;
    

    public ThradLevelOne(LevelOne levelOne, LevelOneService levelOneService) {
        pageLevelOne = new PageLevelOne();
        this.levelOne = levelOne;
    	this.levelOneService = levelOneService;
    	this.setDaemon(true);
    }
       
    @Override
    public void run(){ 
    	pageLevelOne.levelOne(levelOne, levelOneService);
    }
}

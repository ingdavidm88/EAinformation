package eai.levelone;

import com.example.david.dto.Constants;
import com.example.david.dto.Progress;
import com.example.david.model.LevelOne;
import com.example.david.service.LevelOneService;

import eai.utils.UtilPage;

public class PageLevelOne {
	
	private final UtilPage utilPage;
	private Progress progress;
    
    public PageLevelOne(){
    	utilPage = new UtilPage();
        progress = Progress.getSingletonInstance();
    }

	public void levelOne(LevelOne levelOne, LevelOneService levelOneService){
		try{
        	String url = utilPage.url(levelOne.getAlphabet());
            
            if(levelOne.getIdLevel1() == null) {
            	levelOne = new LevelOne(levelOne.getAlphabet(), url, Constants.PENDING.val());
            }
            
           	progress.setCountSuccess(progress.calculateCountSuccess());           
        }catch(Exception ex){
        	levelOne.setStatus(Constants.ERROR.val());
        	progress.setCountFail(progress.calculateCountFail());
        }
        
        levelOneService.save(levelOne);
    }
}

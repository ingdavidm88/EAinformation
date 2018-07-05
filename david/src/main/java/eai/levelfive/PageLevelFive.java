package eai.levelfive;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.example.david.dto.Constants;
import com.example.david.dto.Progress;
import com.example.david.model.LevelFour;
import com.example.david.model.ViewLevelFour;
import com.example.david.service.LevelFourService;

public class PageLevelFive{
    private Progress progress;
    
    public PageLevelFive(){
    	progress = Progress.getSingletonInstance();
    }
    
    public void levelFive(List<ViewLevelFour> viewLevelFourList, LevelFourService levelFourService){
    	for(ViewLevelFour viewLevelFour : viewLevelFourList){
    		LevelFour levelFour = levelFourService.findOne(viewLevelFour.getIdLevel4());
    		
    		try {
	    		File folder = new File("..\\images\\"+viewLevelFour.getIdLevel4());
				folder.mkdir();
				int name = 1;
	    		
	    		for(String url : viewLevelFour.getAttachments().split("\\|")){
	    			URL website = new URL(url.replace("38,50", "50").replace("US40_", "500"));
	    			try (InputStream in = website.openStream()) {
	    				Files.copy(in, Paths.get(folder.getPath()+"\\"+viewLevelFour.getIdLevel4()+"_"+name+".jpg"));
	    				name += 1;
	    			}
	    		}
	    		
	    		progress.setCountSuccess(progress.calculateCountSuccess());
	    		levelFour.setStatus(Constants.SUCCESS.val());
    		}catch (Exception exception) {
    			progress.setCountError(progress.calculateCountError());
    			levelFour.setStatus(Constants.ERROR.val());
        	}
    		
    		levelFourService.save(levelFour);
    	}
    }
}

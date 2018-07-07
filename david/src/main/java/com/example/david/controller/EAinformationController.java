package com.example.david.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.david.dto.Constants;
import com.example.david.dto.MessageResponse;
import com.example.david.dto.Progress;
import com.example.david.dto.TransactionPage;
import com.example.david.model.LevelOne;
import com.example.david.model.LogError;
import com.example.david.model.ViewLevelFour;
import com.example.david.model.ViewLevelThree;
import com.example.david.model.ViewLevelTwo;
import com.example.david.service.LevelFourService;
import com.example.david.service.LevelOneService;
import com.example.david.service.LevelThreeService;
import com.example.david.service.LevelTwoService;
import com.example.david.service.LogErrorService;
import com.example.david.service.MenuService;
import com.example.david.service.ParentMenuService;
import com.example.david.service.SystemParametersService;
import com.example.david.service.UserService;
import com.example.david.utils.TransactionUtilities;

import eai.levelfive.ThradLevelFive;
import eai.levelfour.ThradLevelFour;
import eai.levelone.ThradLevelOne;
import eai.levelthree.ThradLevelThree;
import eai.leveltwo.ThradLevelTwo;
import eai.page.Page;

@Controller
public class EAinformationController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ParentMenuService parentMenuService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	LevelOneService levelOneService;
	
	@Autowired
	LevelTwoService levelTwoService;
	
	@Autowired
	LevelThreeService levelThreeService;
	
	@Autowired
	LevelFourService levelFourService;
	
	@Autowired
	LogErrorService logErrorService;
	
	@Autowired
	SystemParametersService systemParametersService;
	
	TransactionUtilities transactionUtilities = new TransactionUtilities();
	TransactionPage transactionPage = new TransactionPage();
	MessageResponse message = new MessageResponse();
	ResourceBundle rb = ResourceBundle.getBundle("messages_en_US");
	
	public static final String PATTH_EAINFORMATION = "/eainformation";
	public static final String PATTH_STEP1 = "/step1";
	public static final String PATTH_STEP2 = "/step2";
	public static final String PATTH_STEP3 = "/step3";
	public static final String PATTH_STEP4 = "/step4";
	public static final String PATTH_STEP5 = "/step5";
	public static final String PATTH_PROGRESS = "/progress";
	
	@RequestMapping(path = PATTH_EAINFORMATION, method = RequestMethod.GET)
    public String page(Model model, HttpServletRequest request) {
		try {
			transactionPage = transactionUtilities.getTransactionPage(request, PATTH_EAINFORMATION);
			model.addAttribute("tp", transactionPage);
		} catch (Exception exception) {
			logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_EAINFORMATION));
	    }
		
    	return  PATTH_EAINFORMATION;
	}
	
	@RequestMapping(path = PATTH_STEP1, method = RequestMethod.POST)
	public ResponseEntity<MessageResponse> step1(Model model, HttpServletRequest request) {
		Progress progress = Progress.getSingletonInstance();
		progressStarting(progress);
		
		try {
			transactionPage = transactionUtilities.getTransactionPage(request, PATTH_EAINFORMATION);
			
			ThradLevelOne thradLevelOne;
			List<LevelOne> levelOneList = levelOneService.findPendingOrFail();
			
			int length = (levelOneList.isEmpty())? Constants.ALPHABET.val().split(",").length : levelOneList.size();
			
			int successLength = levelOneService.findSuccess();
			
			progress.setLength(length);
			
			if(levelOneList.isEmpty() && successLength != Constants.ALPHABET.val().split(",").length) {
				for(String abc : Constants.ALPHABET.val().split(",")){
		        	thradLevelOne = new ThradLevelOne(new LevelOne(abc, null, null), levelOneService);
		            thradLevelOne.start();
		            thradLevelOne.join();
		        }        
			}else {
				if(Constants.ALPHABET.val().split(",").length == successLength) {
					progressEnding(progress, length);			
				}else {
					for(LevelOne levelOne : levelOneList){
			        	thradLevelOne = new ThradLevelOne(levelOne, levelOneService);
			            thradLevelOne.start();
			            thradLevelOne.join();
			        }
				}
			}
			
		} catch (Exception exception) {
			message = logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_STEP1));
        }
		
        return  message.responseEntity();
	}
	
	@RequestMapping(path = PATTH_STEP2, method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> step2(Model model , HttpServletRequest request) {
		
		Progress progress = Progress.getSingletonInstance();
		progressStarting(progress);
		
		try {
			transactionPage = transactionUtilities.getTransactionPage(request, PATTH_EAINFORMATION);
			String numberOfRetries = systemParametersService.findById(2).getValue();
			String userAgent = systemParametersService.findById(6).getValue();
			
			List<LevelOne> levelOneList = levelOneService.findPendingOrFail();
	        
			if(!levelOneList.isEmpty()) {
				int length = levelOneList.size();
				
				progress.setLength(length);
		        
		        ThradLevelTwo thradLevelTwo;
		        for(LevelOne levelOne : levelOneList){
		        	thradLevelTwo = new 
		        			ThradLevelTwo(
		        					userAgent,
		        					levelOne, 
		        					levelOneService, 
		        					levelTwoService, 
		        					Integer.parseInt(numberOfRetries));
		        	
		        	thradLevelTwo.start();
		        }
	        }else {
	        	int successLength = Constants.ALPHABET.val().split(",").length;
	        	progressEnding(progress, successLength);
	        }
		 } catch (Exception exception) {
			 message = logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_STEP2));
         }
        
        return message.responseEntity();
	}
	
	@RequestMapping(path = PATTH_STEP3, method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> step3(Model model, HttpServletRequest request) {
		
		Progress progress = Progress.getSingletonInstance();
		progressStarting(progress);
		
		try {
			transactionPage = transactionUtilities.getTransactionPage(request, PATTH_EAINFORMATION);
			String numberOfRetries = systemParametersService.findById(2).getValue();
			String numberOfThreads = systemParametersService.findById(3).getValue();
			String userAgent = systemParametersService.findById(6).getValue();
			
			int levelTwoLength = levelTwoService.findPendingOrFail();
			
			if(levelTwoLength > 0) {
				
		        List<ThradLevelThree> thradLevelThreeList = new ArrayList<>();
		        ThradLevelThree thradLevelThree;
		        List<ViewLevelTwo> viewLevelTwoList;
		        int numberThreads = (levelTwoLength > 40)? Integer.parseInt(numberOfThreads) : 1;
		        int from = 0;
		        int to = (int)Math.ceil((float)levelTwoLength/numberThreads);
		        boolean valid = true;
		        
		        while(valid) {
		        	viewLevelTwoList = levelTwoService.findPendingOrFail(from, to);
		        	
		        	if(viewLevelTwoList.isEmpty()) {
		        		progress.setLength(levelTwoLength);
		        		valid = false;
		        	}else {
		        		progress.setLength(from);
		        		thradLevelThree = new 
		        				ThradLevelThree(
		        						userAgent,
		        						viewLevelTwoList, 
		        						levelTwoService, 
		        						levelThreeService, 
		        						Integer.parseInt(numberOfRetries));
		        		
			        	thradLevelThreeList.add(thradLevelThree);
		        	}
		        	
		        	from += to;
		        }
		        
		        for (ThradLevelThree th : thradLevelThreeList) {
	            	th.start();
		        }
	        }else {
	        	int successLength = levelTwoService.findSuccess();
	        	progressEnding(progress, successLength);
	        }
		 } catch (Exception exception) {
			 message = logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_STEP3));
         }
        
        return message.responseEntity();
	}
	
	@RequestMapping(path = PATTH_STEP4, method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> step4(Model model, HttpServletRequest request) {
		
		Progress progress = Progress.getSingletonInstance();
		progressStarting(progress);
		
		try {
			transactionPage = transactionUtilities.getTransactionPage(request, PATTH_EAINFORMATION);
			String numberOfRetries = systemParametersService.findById(2).getValue();
			String numberOfThreads = systemParametersService.findById(3).getValue();
			String shippingUsd = systemParametersService.findById(4).getValue();
			String shippingCop = systemParametersService.findById(5).getValue();
			String userAgent = systemParametersService.findById(6).getValue();
			
			int levelThreeLength = levelThreeService.findPendingOrFail();
	        
			if(levelThreeLength > 0) {
				List<ThradLevelFour> thradLevelFourList = new ArrayList<>();
				ThradLevelFour thradLevelFour;
		        List<ViewLevelThree> viewLevelThreeList;
		        int numberThreads = (levelThreeLength > 40)? Integer.parseInt(numberOfThreads) : 1;
		        int from = 0;
		        int to = (int)Math.ceil((float)levelThreeLength/numberThreads);
		        boolean valid = true;
		        Page page = new Page();
		        BigDecimal usdToCop = page.convertTo(logErrorService, transactionPage.getUserName());
		        
		        while(valid) {
		        	viewLevelThreeList = levelThreeService.findPendingOrFail(from, to);
		    	   
		        	if(viewLevelThreeList.isEmpty()) {
		        		progress.setLength(levelThreeLength);
		        		valid = false;
		        	}else {
		        		progress.setLength(from);
		        		
		        		thradLevelFour = new 
		        				ThradLevelFour(
		        						userAgent,
		        						viewLevelThreeList, 
		        						levelThreeService, 
		        						levelFourService, 
		        						usdToCop, 
		        						Integer.parseInt(numberOfRetries), 
		        						new BigDecimal(shippingUsd),
		        						new BigDecimal(shippingCop));
		        		
			        	thradLevelFourList.add(thradLevelFour);
		        	}
		        	
		        	from += to;
		        }
		        
		        for (ThradLevelFour th : thradLevelFourList) {
	            	th.start();
		        }
	        }else {
	        	int successLength = levelThreeService.findSuccess();
	        	progressEnding(progress, successLength);
	        }
		 }catch (Exception exception) {
			 message = logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_STEP4));
         }
        
        return message.responseEntity();
	}
	
	@RequestMapping(path = PATTH_STEP5, method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> step5(Model model, HttpServletRequest request) {
		
		Progress progress = Progress.getSingletonInstance();
		progressStarting(progress);
		
		try {
			transactionPage = transactionUtilities.getTransactionPage(request, PATTH_EAINFORMATION);
			String numberOfThreads = systemParametersService.findById(3).getValue();
			
			int levelFourLength = levelFourService.findPendingOrFail();
	        
			if(levelFourLength > 0) {
				File folder = new File("..\\images");
				if (!folder.exists()) 
					folder.mkdir();
				
				List<ThradLevelFive> thradLevelFiveList = new ArrayList<>();
				ThradLevelFive thradLevelFive;
		        List<ViewLevelFour> viewLevelFourList;
		        int numberThreads = (levelFourLength > 40)? Integer.parseInt(numberOfThreads) : 1;
		        int from = 0;
		        int to = (int)Math.ceil((float)levelFourLength/numberThreads);
		        boolean valid = true;
		        
		        while(valid) {
		        	viewLevelFourList = levelFourService.findPendingOrFail(from, to);
		    	   
		        	if(viewLevelFourList.isEmpty()) {
		        		progress.setLength(levelFourLength);
		        		valid = false;
		        	}else {
		        		progress.setLength(from);
		        		
		        		thradLevelFive = new ThradLevelFive(viewLevelFourList, levelFourService);
		        		thradLevelFiveList.add(thradLevelFive);
		        	}
		        	
		        	from += to;
		        }
		        
		        for (ThradLevelFive th : thradLevelFiveList) {
	            	th.start();
		        }
	        }else {
	        	int successLength = levelFourService.findSuccess();
	        	progressEnding(progress, successLength);
	        }
		 }catch (Exception exception) {
			 message = logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_STEP5));
         }
        
        return message.responseEntity();
	}
	
	@RequestMapping(path = PATTH_PROGRESS, method = RequestMethod.POST)
    public ResponseEntity<Progress> progress(Model model, HttpServletRequest request, HttpServletResponse response) {
		
        Progress progress = Progress.getSingletonInstance();
       
        try {
        	transactionPage = transactionUtilities.getTransactionPage(request, PATTH_EAINFORMATION);
            String mens = (progress.getPercentage() == 100)?Constants.FINISHED.val():Constants.PROCESSING.val();
	        progress.setStatus(mens);
        } catch (Exception exception) {
        	logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_PROGRESS));
        }
        
        return  ResponseEntity.ok(progress);
	}
	
	public void progressStarting(Progress progress) {
		progress.setPercentage(0);
        progress.setLength(1);
        progress.setCount(0);
        progress.setCountSuccess(0);
        progress.setCountFail(0);
        progress.setCountError(0);
        progress.setStatus(Constants.STARTING.val());
	}
	
	public void progressEnding(Progress progress, int length) {
		progress.setPercentage(100);
        progress.setLength(length);
        progress.setCount(length);
        progress.setCountSuccess(length);
        progress.setCountFail(0);
        progress.setCountError(0);
        progress.setStatus(Constants.FINISHED.val());
	}
}
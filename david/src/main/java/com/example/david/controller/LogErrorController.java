package com.example.david.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.david.dto.Constants;
import com.example.david.dto.MessageResponse;
import com.example.david.dto.Pagination;
import com.example.david.dto.TransactionPage;
import com.example.david.model.LogError;
import com.example.david.service.LogErrorService;
import com.example.david.service.MenuService;
import com.example.david.service.ParentMenuService;
import com.example.david.service.UserService;
import com.example.david.utils.TransactionUtilities;

@Controller
public class LogErrorController {
	
	MessageResponse message;
	
	TransactionUtilities transactionUtilities = new TransactionUtilities();
	TransactionPage transactionPage = new TransactionPage();
	
	@Autowired
	UserService userService;
	
	@Autowired
	ParentMenuService parentMenuService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	LogErrorService logErrorService;
		
	public static final String PATTH_LOGERROR = "/logerror";
	public static final String PATTH_SEARCH = "/searchlogerror";
	
	@RequestMapping(path = PATTH_LOGERROR, method = RequestMethod.GET)
    public String page(Model model, HttpServletRequest request) {
		try {
			transactionPage = transactionUtilities.getTransactionPage(request, PATTH_LOGERROR);
			model.addAttribute("pagination", new Pagination());
			model.addAttribute("tp",transactionPage);
		} catch (Exception exception) {
			logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_LOGERROR));
	    }
		
    	return  PATTH_LOGERROR;
	}
	
	@RequestMapping(path = PATTH_SEARCH, method = RequestMethod.POST)
    public String search(
    		Model model,
    		HttpServletRequest request,
    		@ModelAttribute("Pagination") Pagination pagination,
    		BindingResult bindingResult) {
		
        try {
        	transactionPage = transactionUtilities.getTransactionPage(request, PATTH_LOGERROR);
        	logErrorService.findAll(pagination, transactionPage.getPageSize());
        } catch (Exception exception) {
        	message = logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_SEARCH));
        }
                
    	model.addAttribute("tp", transactionPage); 
    	model.addAttribute("pagination", pagination); 
        model.addAttribute(Constants.MESSAGESRESPONSE.val(), message);
        
        return  PATTH_LOGERROR;
	}
}
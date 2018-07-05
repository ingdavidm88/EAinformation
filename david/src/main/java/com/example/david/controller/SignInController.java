package com.example.david.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.david.dto.Constants;
import com.example.david.dto.TransactionPage;
import com.example.david.model.LogError;
import com.example.david.model.User;
import com.example.david.service.LogErrorService;
import com.example.david.service.MenuService;
import com.example.david.service.ParentMenuService;
import com.example.david.service.UserService;
import com.example.david.utils.TransactionUtilities;

@Controller
public class SignInController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ParentMenuService parentMenuService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	LogErrorService logErrorService;
	
	TransactionUtilities transactionUtilities = new TransactionUtilities();
	TransactionPage transactionPage = new TransactionPage();
	
	public static final String PATTH_SIGNIN = "/signin";
	public static final String PATTH_INDEX = "/index";
	
	@RequestMapping(path = PATTH_SIGNIN, method = RequestMethod.GET)
    public String page(Model model, HttpServletRequest request) {
		try {
	        model.addAttribute("user", new User());
	        
	        Object messageRequest = request.getSession().getAttribute(Constants.MESSAGESRESPONSE.val());
	        model.addAttribute(Constants.MESSAGESRESPONSE.val(), messageRequest);
	        request.getSession().removeAttribute(Constants.MESSAGESRESPONSE.val());
	    } catch (Exception exception) {
	    	Object user = request.getSession().getAttribute("user");
			logErrorService.save(new LogError(exception, user.toString(), PATTH_SIGNIN));
	    }
		
        return PATTH_SIGNIN;
    }
	
	@RequestMapping(path = PATTH_INDEX, method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
		try {
			transactionPage = transactionUtilities.getData(request, userService, parentMenuService, menuService);
			request.getSession().setAttribute("TransactionPage", transactionPage);
			model.addAttribute("tp", transactionPage);
		} catch (Exception exception) {
			logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_INDEX));
	    }
		
    	return PATTH_INDEX;
	}

    @RequestMapping("/access_denied")
    public String accessDenied(Model model, HttpServletRequest request) {
    	return "access_denied";
    }
}
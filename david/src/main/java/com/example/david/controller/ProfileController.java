package com.example.david.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.david.dto.TransactionPage;
import com.example.david.model.LogError;
import com.example.david.model.User;
import com.example.david.model.UserDetail;
import com.example.david.service.LogErrorService;
import com.example.david.service.MenuService;
import com.example.david.service.ParentMenuService;
import com.example.david.service.UserService;
import com.example.david.utils.TransactionUtilities;

@Controller
public class ProfileController {
	
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
	
	public static final String PATTH_PROFILE = "/profile";
	
	@RequestMapping(path = PATTH_PROFILE, method = RequestMethod.GET)
    public String page(Model model, HttpServletRequest request) {
		try {
			transactionPage = transactionUtilities.getTransactionPage(request, PATTH_PROFILE);
			
			User user = userService.findByUserName(transactionPage.getUserName());

			UserDetail userDetail = user.getUserDetail();
			userDetail.setUserName(user.getUsername());
			userDetail.setRepeatPassword(user.getRole().getName());
			
			model.addAttribute("userDetail", userDetail);
			model.addAttribute("tp", transactionPage);
		} catch (Exception exception) {
			logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_PROFILE));
	    }
		
    	return  PATTH_PROFILE;
	}
}
package com.example.david.utils;

import java.util.List;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;

import com.example.david.dto.TransactionPage;
import com.example.david.model.ParentMenu;
import com.example.david.service.MenuService;
import com.example.david.service.ParentMenuService;
import com.example.david.service.UserService;

public class TransactionUtilities {

	public TransactionPage getData(
			HttpServletRequest request, 
			UserService userService, 
			ParentMenuService parentMenuService,
			MenuService menuService) {
		
		Object user = request.getSession().getAttribute("user");
		TransactionPage transactionPage =  new TransactionPage();
		
		if(user != null) {
			CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
			
			Integer idRole = userService.findByUserName(user.toString()).getRole().getIdRole();
			
			List<ParentMenu> parentMenuList = parentMenuService.parentMenuList(idRole);
			for (ParentMenu parentMenu : parentMenuList) {
				parentMenu.setMenuList(menuService.menuList(parentMenu.getIdParentMenu(), idRole));
			}
			
			Predicate<ParentMenu> parentMenuListPredicate = p-> p.getMenuList().isEmpty();
			parentMenuList.removeIf(parentMenuListPredicate);
			
			transactionPage.setUserName(user.toString());
			transactionPage.setTitle("Home");
			transactionPage.setIdRole(idRole);
			transactionPage.setParentMenuList(parentMenuList);
			transactionPage.setHeaderName(token.getHeaderName());
			transactionPage.setToken(token.getToken());
		}
		
		return transactionPage;
	}
	
	public TransactionPage getTransactionPage(HttpServletRequest request, String path) {
		TransactionPage transactionPage = (TransactionPage) request.getSession().getAttribute("TransactionPage");
		transactionPage.getParentMenuList().forEach(parentMenu -> {
			parentMenu.getMenuList().forEach(menu -> {
				if(menu.getUrlName().equals(path))
					transactionPage.setTitle(menu.getMenuName());
			});
		});
		
		return transactionPage;
	}
}

package com.example.david.serviceimple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.david.model.Menu;
import com.example.david.repository.MenuRepository;
import com.example.david.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
    
	@Autowired
    private MenuRepository menuRepository;
    
    @Override
    @Transactional
    public List<Menu> menuList(Integer idParentMenu, Integer idRole){
        
        return menuRepository.menuList(idParentMenu, idRole);
    }

	@Override
	@Transactional
	public Menu findByUrlName(String urlName) {
		return menuRepository.findByUrlName(urlName);
	}

}
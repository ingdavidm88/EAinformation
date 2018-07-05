package com.example.david.serviceimple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.david.model.ParentMenu;
import com.example.david.repository.ParentMenuRepository;
import com.example.david.service.ParentMenuService;

@Service
public class ParentMenuServiceImpl implements ParentMenuService {
    
	@Autowired
    private ParentMenuRepository parentMenuRepository;
    
    @Override
    @Transactional
    public List<ParentMenu> parentMenuList(Integer idRole){
        
        return parentMenuRepository.parentMenuList(idRole);
    }

}
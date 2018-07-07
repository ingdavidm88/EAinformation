package com.example.david.serviceimple;

import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.david.dto.Pagination;
import com.example.david.model.SystemParameters;
import com.example.david.repository.SystemParametersRepository;
import com.example.david.service.SystemParametersService;

@Service
public class SystemParametersServiceImpl implements SystemParametersService {
	
	ResourceBundle rb = ResourceBundle.getBundle("messages_en_US");
	
	@PersistenceContext
    private EntityManager manager;
	
	@Autowired
	SystemParametersRepository systemParametersRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void findAll(Pagination pagination, Long pageSize) {
		StringBuilder sql = new StringBuilder();
		SystemParameters systemParameters = pagination.getSystemParameters();
		
    	sql.append("SELECT * FROM system_parameters s WHERE 1=1 ");
    	
    	if(systemParameters.getIdSystemParameters() != null) {
    		sql.append("and s.ID_SYSTEM_PARAMETERS = '")
    		   .append(systemParameters.getIdSystemParameters())
    		   .append("' ");
    	}
    	
    	if(!systemParameters.getName().equals("")) {
    		sql.append("and s.NAME = '")
    		   .append(systemParameters.getName())
    		   .append("' ");
    	}
    	
    	if(!systemParameters.getValue().equals("")) {
    		sql.append("and s.VALUE = '")
    		   .append(systemParameters.getValue())
    		   .append("' ");
    	}
    	
    	if(!systemParameters.getDescription().equals("")) {
    		sql.append("and s.DESCRIPTION = '")
    		   .append(systemParameters.getDescription())
    		   .append("' ");
    	}
    	
    	if(!systemParameters.getUserName().equals("")) {
    		sql.append("and s.USER_NAME = '")
    		   .append(systemParameters.getUserName())
    		   .append("' ");
    	}
    	
    	if(!systemParameters.getDate().equals("")) {
    		sql.append("and s.DATE = '")
    		   .append(systemParameters.getDate())
    		   .append("' ");
    	}
    	
    	Query query = manager.createNativeQuery(sql.toString(), SystemParameters.class);
    	
    	pagination.getPage(query.getResultList().size(), pageSize);
    	
    	sql.append("limit ")
    	.append(""+pagination.getPager().getPaginaton()*(Math.abs(pagination.getPager().getPage()-1)))
		.append(", ")
		.append(pagination.getPager().getPaginaton());
    	
    	Query query1 = manager.createNativeQuery(sql.toString(), SystemParameters.class);    	
    	pagination.setSystemParametersList(query1.getResultList());
	}

	@Override
	@Transactional
	public SystemParameters findById(Integer idSystemParameters) {
		return systemParametersRepository.findOne(idSystemParameters);	
	}

	@Override
	@Transactional
	public SystemParameters save(SystemParameters systemParameters) {
		return systemParametersRepository.save(systemParameters);
	}
}
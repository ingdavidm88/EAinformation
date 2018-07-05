package com.example.david.serviceimple;

import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.david.dto.Constants;
import com.example.david.dto.MessageResponse;
import com.example.david.dto.Pagination;
import com.example.david.model.LogError;
import com.example.david.repository.LogErrorRepository;
import com.example.david.service.LogErrorService;
import com.example.david.service.SystemParametersService;

@Service
public class LogErrorServiceImpl implements LogErrorService {
	
	ResourceBundle rb = ResourceBundle.getBundle("messages_en_US");
	
	@PersistenceContext
    private EntityManager manager;
	
	@Autowired
	LogErrorRepository logErrorRepository;
	
	@Autowired
	SystemParametersService systemParametersService;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void findAll(Pagination pagination) {
		StringBuilder sql = new StringBuilder();
		LogError logError = pagination.getLogError();
		
    	sql.append("SELECT * FROM log_error l WHERE 1=1 ");
    	
    	if(logError.getIdLogError() != null) {
    		sql.append("and l.ID_LOG_ERROR = '")
    		   .append(logError.getIdLogError())
    		   .append("' ");
    	}
    	
    	if(!logError.getUserName().equals("")) {
    		sql.append("and l.USER_NAME = '")
    		   .append(logError.getUserName())
    		   .append("' ");
    	}
    	
    	if(!logError.getPath().equals("")) {
    		sql.append("and l.PATH = '")
    		   .append(logError.getPath())
    		   .append("' ");
    	}
    	
    	if(!logError.getDate().equals("")) {
    		sql.append("and l.DATE = '")
    		   .append(logError.getDate())
    		   .append("' ");
    	}
    	
    	Query query = manager.createNativeQuery(sql.toString(), LogError.class);
    	
    	pagination.getPage(query.getResultList().size(), systemParametersService);
    	
    	sql.append("limit ")
    	.append(""+pagination.getPager().getPaginaton()*(Math.abs(pagination.getPager().getPage()-1)))
		.append(", ")
		.append(pagination.getPager().getPaginaton());
    	
    	Query query1 = manager.createNativeQuery(sql.toString(), LogError.class);    	
    	pagination.setLogErrorList(query1.getResultList());
	}
	
	@Override
    @Transactional
    public MessageResponse save(LogError logError){
		Integer idLogError = logErrorRepository.save(logError).getIdLogError();
		
		MessageResponse message = new MessageResponse();
		message.setMessage(rb.getString(Constants.INTERNALERROR.val()) + idLogError, Constants.ERROR.val());
		message.setStatus(Constants.ERROR.val());
		
		return message;
    }	
}
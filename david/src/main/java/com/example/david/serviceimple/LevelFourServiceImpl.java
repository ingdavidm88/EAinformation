package com.example.david.serviceimple;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.david.model.LevelFour;
import com.example.david.model.ViewLevelFour;
import com.example.david.repository.LevelFourRepository;
import com.example.david.repository.ViewLevelFourRepository;
import com.example.david.service.LevelFourService;

@Service
public class LevelFourServiceImpl implements LevelFourService {
	
	@PersistenceContext
    private EntityManager manager;
	
	@Autowired
	LevelFourRepository levelFourRepository;
	
	@Autowired
    private ViewLevelFourRepository viewLevelFourRepository;
	
	@Override
    @Transactional
    public void save(LevelFour levelFour){
		levelFourRepository.save(levelFour);
    }	

	@Override
	@Transactional
	public int findPendingOrFail() {
		return viewLevelFourRepository.findPendingOrFail();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ViewLevelFour> findPendingOrFail(int from, int to) {
		
		StringBuilder sql = new StringBuilder();
    	sql
    		.append("SELECT * FROM view_level_four l WHERE l.status IN ('P','F') limit ")
    		.append(from)
    		.append(", ")
    		.append(to);
    	
    	Query query = manager.createNativeQuery(sql.toString(), ViewLevelFour.class);
    	
		return query.getResultList();
	}

	@Override
	@Transactional
	public int findSuccess() {
		return viewLevelFourRepository.findSuccess();
	}
	
	@Override
	@Transactional
	public LevelFour findOne(Integer id) {
		return levelFourRepository.findOne(id);
	}
}
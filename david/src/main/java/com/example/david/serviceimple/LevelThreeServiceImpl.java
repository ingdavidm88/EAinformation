package com.example.david.serviceimple;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.david.model.LevelThree;
import com.example.david.model.ViewLevelThree;
import com.example.david.repository.LevelThreeRepository;
import com.example.david.repository.ViewLevelThreeRepository;
import com.example.david.service.LevelThreeService;

@Service
public class LevelThreeServiceImpl implements LevelThreeService {
	
	@PersistenceContext
    private EntityManager manager;
	
	@Autowired
    private LevelThreeRepository levelThreeRepository;
	
	@Autowired
    private ViewLevelThreeRepository viewLevelThreeRepository;
    
	@Override
    @Transactional
    public void save(LevelThree levelThree){
		levelThreeRepository.save(levelThree);
    }
	
    @Override
    @Transactional
    public void save(List<LevelThree> levelThreeList){
    	levelThreeRepository.save(levelThreeList);
    }
    
    @Override
	@Transactional
	public int findPendingOrFail() {
		return viewLevelThreeRepository.findPendingOrFail();
	}
    
    @SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ViewLevelThree> findPendingOrFail(int from, int to) {
    	
    	StringBuilder sql = new StringBuilder();
    	sql
    		.append("SELECT * FROM view_level_three l WHERE l.status IN ('P','F') limit ")
    		.append(from)
    		.append(", ")
    		.append(to);
    	
    	Query query = manager.createNativeQuery(sql.toString(), ViewLevelThree.class);
    	
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public int findSuccess() {
		return viewLevelThreeRepository.findSuccess();
	}
	
	@Override
	@Transactional
	public LevelThree findOne(Integer id) {
		return levelThreeRepository.findOne(id);
	}
}
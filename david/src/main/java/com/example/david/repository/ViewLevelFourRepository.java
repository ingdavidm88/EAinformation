package com.example.david.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.david.model.ViewLevelFour;

@Repository
public interface ViewLevelFourRepository extends CrudRepository<ViewLevelFour, Integer> {
	
	@Query("SELECT COUNT(l.idLevel4) FROM ViewLevelFour l WHERE l.status IN ('P','F')")
	public int findPendingOrFail();

	@Query("SELECT COUNT(l.idLevel4) FROM ViewLevelFour l WHERE l.status IN ('S')")
	public int findSuccess();
	
}
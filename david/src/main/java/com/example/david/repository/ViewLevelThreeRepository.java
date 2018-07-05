package com.example.david.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.david.model.ViewLevelThree;

@Repository
public interface ViewLevelThreeRepository extends CrudRepository<ViewLevelThree, Integer> {
	
	@Query("SELECT COUNT(l.idLevel3) FROM ViewLevelThree l WHERE l.status IN ('P','F')")
	public int findPendingOrFail();

	@Query("SELECT COUNT(l.idLevel3)FROM ViewLevelThree l WHERE l.status IN ('S')")
	public int findSuccess();
	
}
package com.demo.repository;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Medicine;

@Repository
public interface MedicineRepository  extends MongoRepository<Medicine, String>{


}

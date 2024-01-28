package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Byes;
import com.main.entity.ByesId;

public interface ByesRepository extends JpaRepository<Byes, ByesId>{

}

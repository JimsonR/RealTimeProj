package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Event;
import com.main.entity.Teams;
@Repository
public interface TeamsRepository extends JpaRepository<Teams, Integer> {

}

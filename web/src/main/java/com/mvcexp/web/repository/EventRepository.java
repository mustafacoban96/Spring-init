package com.mvcexp.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvcexp.web.models.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

}

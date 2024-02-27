package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.model.ToDo;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository("toDoRepository")
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}

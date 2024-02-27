package com.example.service;

import java.util.List;

import com.example.model.ToDo;

public interface ToDoService {
	public List<ToDo> getAllToDos();
	public ToDo getTodo(long id);
	public ToDo saveToDo(ToDo todo);
	public void deleteToDo (ToDo todo);
}

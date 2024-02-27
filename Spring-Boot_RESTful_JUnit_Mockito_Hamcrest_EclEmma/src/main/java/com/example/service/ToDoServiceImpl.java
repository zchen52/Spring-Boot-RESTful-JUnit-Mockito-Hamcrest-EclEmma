package com.example.service;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.ToDo;
import com.example.repository.ToDoRepository;

@Service("toDoService")
public class ToDoServiceImpl implements ToDoService {
	
	@Autowired 
	private ToDoRepository todoRespository;

	@Override
	public List<ToDo> getAllToDos() {
		return todoRespository.findAll();
	}

	@Override
	public ToDo getTodo(long id) {
;
		return todoRespository.findById(id).isPresent()? todoRespository.findById(id).get():null;
	}

	@Override
	public ToDo saveToDo(ToDo todo) {
		return todoRespository.save(todo);
	}

	@Override
	public void deleteToDo(ToDo todo) {
		todoRespository.delete(todo);
	}

}

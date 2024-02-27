package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ToDoException;
import com.example.model.Response;
import com.example.model.ToDo;
import com.example.service.ToDoService;

@RestController
public class ToDoController {
	private static final Logger logger = LoggerFactory.getLogger(ToDoController.class);
	
	@Autowired
	private ToDoService todoService;
	
	@GetMapping("/")
	public String hello() {
		return "Hello";
	}
	
	@GetMapping("/todo")
	public ResponseEntity<List<ToDo>> getAllToDo(){
    	logger.info("Returning all the ToDo´s");
		return new ResponseEntity<List<ToDo>>(todoService.getAllToDos(), HttpStatus.OK);
	}
	
	@GetMapping("/todo/{id}")
	public ResponseEntity<ToDo> getToDoById(@PathVariable("id") long id) throws ToDoException{
    	logger.info("ToDo id to return " + id);
    	ToDo toDo = todoService.getTodo(id);
    	if (toDo == null || toDo.getId() <= 0){
            throw new ToDoException("ToDo doesn´t exist");
    	}
		return new ResponseEntity<ToDo>(todoService.getTodo(id), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/todo/{id}")
	public ResponseEntity<Response> removeToDoById(@PathVariable("id") long id) throws ToDoException{
    	logger.info("ToDo id to remove " + id);
    	ToDo toDo = todoService.getTodo(id);
    	if (toDo == null || toDo.getId() <= 0){
            throw new ToDoException("ToDo to delete doesn´t exist");
    	}
    	todoService.deleteToDo(toDo);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "ToDo has been deleted"), HttpStatus.OK);
	}
    
    @PostMapping(value = "/todo")
   	public ResponseEntity<ToDo> saveToDo(@RequestBody ToDo payload) throws ToDoException{
    	logger.info("Payload to save " + payload);
    	if (!PayloadValidator.validateCreatePayload(payload)){
            throw new ToDoException("Payload malformed, id must not be defined");
    	}
		return new ResponseEntity<ToDo>(todoService.saveToDo(payload), HttpStatus.OK);
   	}
    
    @PatchMapping(value = "/todo")
   	public ResponseEntity<ToDo> updateToDo(@RequestBody ToDo payload) throws ToDoException{
    	logger.info("Payload to update " + payload);
    	ToDo toDo = todoService.getTodo(payload.getId());
    	if (toDo == null || toDo.getId() <= 0){
            throw new ToDoException("ToDo to update doesn´t exist");
    	}
		return new ResponseEntity<ToDo>(todoService.saveToDo(payload), HttpStatus.OK);
   	}
}

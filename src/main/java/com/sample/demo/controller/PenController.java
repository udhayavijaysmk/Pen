package com.sample.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.demo.dao.PenDao;
import com.sample.demo.model.Pen;

@Controller
public class PenController {

	@Autowired
	PenDao pd;
	
	@RequestMapping(value="/")
	public String Show(){
		return "index";
	}
	
	@RequestMapping(value="/all")
	public ResponseEntity<List<Pen>> getAllItems()
	{
		System.out.println("GetAll Method");
		return new ResponseEntity<>(pd.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getone",method=RequestMethod.GET)
	public Pen get(@RequestParam("itemId") int id)
	{
		return pd.getIndividualItem(id);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public ResponseEntity<Object> addDetails(@RequestBody Pen p)
	{
		String message = null;
		System.out.println("inside insert method");
		if(pd.addItem(p.getName(),p.getCategory())>=1)
		{
			message = "Data Saved Successfully";
		}
		else
		{
			message = "Please Check";
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.DELETE)
	public String DeleteDetails(@RequestParam("itemId") int id)
	{
		if(pd.deleteItem(id)>=1)
		{
			return "Data removed Successfully";
		}
		else
		{
			return "Please Check";
		}
	}
	
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public String updateDetails(@RequestParam("itemId") int id, @RequestBody Pen p)
	{
		if(pd.updateItem(id, p.getName(), p.getCategory())>=1)
		{
			return "Data updated Successfully";
		}
		else
		{
			return "Please Check";
		}
	}
}

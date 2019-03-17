package com.sample.demo.model;

public class Pen {

	private int id;
	private String name;
	private String category;

	public Pen() {

	}
	
	public Pen(int id, String name, String category) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}

package com.sample.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.sample.demo.model.Pen;

@Repository
public class PenDao {

	@Autowired
	JdbcTemplate temp;
	
	public List<Pen> getAll(){
		System.out.println("Reached Get method");
		List<Pen> li = new ArrayList<Pen>();
		temp.query("select id,Name,category from pen", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				li.add(new Pen(rs.getInt("id"), rs.getString("name"), rs.getString("category")));
			}
		});
		return li;
	}
	
	public Pen getIndividualItem(int itemId)
	{
		String query="select * from pen where ID=?";
		Pen p=temp.queryForObject(query, new Object[]{itemId},new BeanPropertyRowMapper<>(Pen.class));
		return p;
	}
	
	public int addItem(String name, String category) {
		String query="insert into pen(Name,category)values(?,?)";
		return temp.update(query,name,category);
	}
	
	public int deleteItem(int id)
	{
		String query="delete from pen where id=?";
		return temp.update(query,id);
	}

	public int updateItem(int id,String name,String category)
	{
		String query="update pen set name=?, category=? where id=?";
		return temp.update(query,name,category,id);
	}	
}

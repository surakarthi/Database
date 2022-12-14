package com.student.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.student.student.mapper.studentMapper;
import com.student.student.model.students;


@Service
public class studentService {
	
	@Autowired
	private studentMapper stuMapper;
	//print all
	public List<students> getAllStus(){
		return stuMapper.getAll();
	}
	//print the selected student value
	public students getStuId(int id) {
		return stuMapper.getStuId(id);
	}
	
	//insert
	public void addStu(students stu) {
		 stuMapper.insert(stu);
	}
	
	//update
	public void updateStu(students stu) {
		 stuMapper.update(stu);
	}
	
	//delete
	public void deleteStu(int id) {
    	 stuMapper.delete(id);
	}
		
}

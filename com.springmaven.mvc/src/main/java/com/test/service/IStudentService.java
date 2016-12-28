package com.test.service;

import java.util.List;

import com.test.model.Student;

public interface IStudentService {
	    public Student getUserInfo(int sID);  
	    public List<Student> getAllUser();  
       
	    public int insertStudent(Student student);  
	    
	    public int updateStudent(Student student);  
	       
	    public int deleteStudent(int sID); 
	    
	    public void testTransaction(Student insert,Student update);
}

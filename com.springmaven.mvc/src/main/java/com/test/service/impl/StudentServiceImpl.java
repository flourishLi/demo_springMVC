package com.test.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.StudentMapper;
import com.test.model.Student;
import com.test.service.IStudentService;


@Service
public class StudentServiceImpl implements IStudentService{
	
	@Autowired
	private StudentMapper IStudentMapper;
	public Student getUserInfo(int sID) {
		return IStudentMapper.selectByPrimaryKey(sID);
	}

	public int insertStudent(Student student) {
		return IStudentMapper.insert(student);
	}

	public int updateStudent(Student student) {
        return IStudentMapper.updateByPrimaryKey(student);
	}

	public int deleteStudent(int sID) {
        return IStudentMapper.deleteByPrimaryKey(sID);
	}

	public List<Student> getAllUser() {
		System.out.println("========Begin In getAllUser========");
		return IStudentMapper.selectAll();
	}

	@Transactional
	public void testTransaction(Student insert, Student update) {
		// TODO Auto-generated method stub
		System.out.println("========Begin In testTransaction========");
        insertStudent(insert);
        updateStudent(update);
		System.out.println("========End In testTransaction========");
		
	}


}

package com.test.controller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.model.Student;
import com.test.service.IStudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	protected Logger log = Logger.getLogger(StudentController.class);

	@Autowired
	IStudentService studentService;
	@RequestMapping(value = "/select")
	@ResponseBody
	public void PrintStudentInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		log.info("============begin in get all users===========");
		List<Student> students=GetAllUsers();
		OutputStream outputStream = response.getOutputStream();//获取OutputStream输出流
		response.setCharacterEncoding("UTF-8"); //设置编码格式
        response.setContentType("text/html");   //设置数据格式      
        
        JSONArray jsonList = JSONArray.fromObject(students);
        byte[] dataByteArr = (jsonList.toString()).getBytes("UTF-8");//将字符转换成字节数组，指定以UTF-8编码进行转换
        outputStream.write(dataByteArr);//使用OutputStream流向客户端输出字节数组
    	log.info("============end in get all users===========");
     
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public void DeleteStudentByID(HttpServletRequest request,HttpServletResponse response) throws IOException{
	    String dID=request.getParameter("ID");
		
		int no=studentService.deleteStudent(Integer.parseInt(dID));
		PrintStudentInfo(request,response);
	}
	
	@RequestMapping(value = "/insert")
	@ResponseBody
	public void InsertStudent(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String dID=request.getParameter("ID");
		
		Student student=new Student();
		student.setAge(Integer.parseInt(dID));
		student.setName("小韩");
		student.setSex("女");
		student.setSid(Integer.parseInt(dID));
		
		int no=studentService.insertStudent(student);
		PrintStudentInfo(request,response);
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public void UpdateStudentByID(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String dID=request.getParameter("ID");
		
		Student student=new Student();
		student.setAge(Integer.parseInt(dID));
		student.setName("小小"+dID);
		student.setSex("女");
		student.setSid(Integer.parseInt(dID));
		
		int no=studentService.updateStudent(student);
		
		PrintStudentInfo(request,response);
	}
	
	public List<Student> GetAllUsers() {
		List<Student> students=studentService.getAllUser();
		return students;
	}

	
	@RequestMapping(value = "/transaction")
	@ResponseBody
	public void transactionTest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String dID=request.getParameter("ID");
		
		Student insertStudent=new Student();
		insertStudent.setAge(Integer.parseInt(dID));
		insertStudent.setName("小韩Insert");
		insertStudent.setSex("女");
		insertStudent.setSid(Integer.parseInt(dID));
		
		Student updateStudent=new Student();
		updateStudent.setAge(Integer.parseInt(dID));
		updateStudent.setName("小韩update");
		updateStudent.setSex("女");
		updateStudent.setSid(Integer.parseInt(dID)-1);
		
		studentService.testTransaction(insertStudent, updateStudent);
		
		//PrintStudentInfo(request,response);
     
	}
	
	
}

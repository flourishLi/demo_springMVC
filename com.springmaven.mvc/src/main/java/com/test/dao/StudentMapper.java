package com.test.dao;


import java.util.List;

import com.test.model.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer sid);
    List<Student> selectAll();

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}
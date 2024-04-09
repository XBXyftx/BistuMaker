package com.bistu.ckkj.service;

import com.bistu.ckkj.pojo.Enrollment;

import java.util.List;

public interface EnrollmentService {
    //查询全部报名
    List<Enrollment> selectAllEnrollment();
    //根据用户查询
    Enrollment selectEnrollmentById(Integer id);

    //删除报名
    void deleteEnrollment(Integer id);

    //添加报名
    void addEnrollment(Enrollment enrollment);

    //修改报名
    void updateEnrollment(Enrollment enrollment);

    //


}

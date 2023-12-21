package com.example.demo.mapper;

import com.example.demo.pojo.Enrollment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EnrollmentMapper {
    /*
     *  查
     */
    @Select("select * from enrollments")
    List<Enrollment> selectAll();

    /*
     *  删
     */
    @Select("delete from enrollments where id = #{id}")
    void deleteById(Integer id);

    /*
     *  增
     */
    @Select("insert into enrollments(name,student_id,signup_time,signup_reason,department,email,hobbies) values(#{name}, #{studentId},now(),#{signupReason}, #{department},#{email},#{hobbies})")
    void insert(Enrollment enrollment);

    /*
     *  改
     */
    @Select("update enrollments set name=#{name},student_id=#{studentId},signup_time=#{signupTime},signup_reason=#{signupReason},department=#{department},email=#{email},hobbies=#{hobbies} where id=#{id}")
    void update(Enrollment enrollment);

}

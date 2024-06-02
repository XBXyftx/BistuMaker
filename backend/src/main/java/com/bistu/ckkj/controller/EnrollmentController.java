package com.bistu.ckkj.controller;


import com.bistu.ckkj.aop.MyLog;
import com.bistu.ckkj.pojo.Enrollment;
import com.bistu.ckkj.pojo.Result;
import com.bistu.ckkj.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    @MyLog(value = "添加报名")  //这里添加了AOP的自定义注解
    @PostMapping("/add")
    public Result addEnrollment(@RequestBody Enrollment enrollment){

        enrollmentService.addEnrollment(enrollment);
        return Result.success("添加成功");
    }
    @MyLog(value = "根据id查询报名")  //这里添加了AOP的自定义注解
    @GetMapping("/enrollmentInfo")
    public Result<Enrollment> selectEnrollmentById(Integer id){
        return Result.success(enrollmentService.selectEnrollmentById(id));
    }
    @MyLog(value = "查询全部报名")  //这里添加了AOP的自定义注解
    @GetMapping("/enrollmentAllInfo")
    public Result selectAllEnrollment(){
        return Result.success(enrollmentService.selectAllEnrollment());
    }

}

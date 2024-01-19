package com.example.demo.controller;


import com.example.demo.pojo.Enrollment;
import com.example.demo.pojo.Result;
import com.example.demo.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/add")
    public Result addEnrollment(@RequestBody Enrollment enrollment){
        enrollmentService.addEnrollment(enrollment);
        return Result.success("添加成功");
    }
    @GetMapping("/enrollmentInfo")
    public Result<Enrollment> selectEnrollmentById(Integer id){
        return Result.success(enrollmentService.selectEnrollmentById(id));
    }

    @GetMapping("/enrollmentAllInfo")
    public Result selectAllEnrollment(){
        return Result.success(enrollmentService.selectAllEnrollment());
    }

}

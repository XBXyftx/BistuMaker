package com.bistu.ckkj.service.impl;

import com.bistu.ckkj.mapper.EnrollmentMapper;
import com.bistu.ckkj.pojo.Enrollment;
import com.bistu.ckkj.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl  implements EnrollmentService {

    private final EnrollmentMapper enrollmentMapper;

    @Override
    public List<Enrollment> selectAllEnrollment() {
        return enrollmentMapper.selectAll();
    }

    @Override
    public Enrollment selectEnrollmentById(Integer id) {
        return enrollmentMapper.selectById(id);
    }


    //删
    @Override
    public void deleteEnrollment(Integer id) {
        enrollmentMapper.deleteById(id);
    }

    @Override
    public void addEnrollment(Enrollment enrollment) {
            enrollmentMapper.insert(enrollment);
    }

    @Override
    public void updateEnrollment(Enrollment enrollment) {
        enrollmentMapper.update(enrollment);
    }
}

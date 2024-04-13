package com.bistu.ckkj.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Enrollment {
    private Integer id; // ID
    private String name; // 姓名
    private String studentId; // 学号
    private String department; // 系别
    private String phoneNumber; // 电话
    private String email; // 邮箱
    private String reason;//原因
    private String customReason;//其他原因
    private String desiredPosition;//意向职位
    private String introduction;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime signupTime; // 报名时间

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                ", department='" + department + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", reason='" + reason + '\'' +
                ", customReason='" + customReason + '\'' +
                ", desiredPosition='" + desiredPosition + '\'' +
                ", introduction='" + introduction + '\'' +
                ", signupTime=" + signupTime +
                '}';
    }
}

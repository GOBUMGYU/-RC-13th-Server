package com.example.demo.src.user.model;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserReq {
    private String pass;
    private String name;
    private String nickName;
    private String phoneNumber;
    @Column
    @Enumerated(value = EnumType.STRING)
    private MyForgn myForgn;

    @Timestamp
    private LocalDateTime localDateTime;

    @UpdateTimestamp // UPDATE 시 자동으로 값을 채워줌
    private LocalDateTime updatedDate = LocalDateTime.now();
    private String email;


}

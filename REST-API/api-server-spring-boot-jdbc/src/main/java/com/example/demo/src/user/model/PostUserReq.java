package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserReq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger userId;
    private String pass;
    private String name;
    private String profileImg;
    private String nickName;
    private String phoneNumber;
    private String myForgn;
    private LocalDateTime createdAt;
    @PrePersist // DB에 insert되기 직전에 실행됨
    public void createdAt(){
        this.createdAt = LocalDateTime.now();
    }

    @UpdateTimestamp // UPDATE 시 자동으로 값을 채워줌
    private LocalDateTime updateAt = LocalDateTime.now();
    private String email;
    private String role;
    private String status;


}

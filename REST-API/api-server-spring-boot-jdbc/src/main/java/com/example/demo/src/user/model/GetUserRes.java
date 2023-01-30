package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.PrePersist;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRes {
    private BigInteger userId;
    private String name;
    private String email;
    private String pass;
    private String status;
    private String jwt;

    public GetUserRes(BigInteger userId, String name, String email, String pass, String status) {


    }
}

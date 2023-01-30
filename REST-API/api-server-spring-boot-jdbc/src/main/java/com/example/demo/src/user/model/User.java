package com.example.demo.src.user.model;

import lombok.*;

import javax.persistence.Id;
import java.math.BigInteger;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private BigInteger userId;
    private String name;
    private String email;
    private String pass;
}

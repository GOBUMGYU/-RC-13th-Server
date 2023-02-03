package com.example.demo.src.store.model;

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
@AllArgsConstructor
@NoArgsConstructor
public class PostStoreReq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger storeId;
    private String deliveryType;
    private String categories;
    private String storeName;
    private String content;
    private String deliveryArea;
    private String storeAddress;
    private String phoneNumber;
    private String operatingTime;
    private String holiday;
    private String storeImgUrl;
    private String deliveryTips;
    private String minimumOrderAmount;
    private String wishCount;
    private String estimatedDeliveryTime;
    private LocalDateTime createdAt;
    @PrePersist // DB에 insert되기 직전에 실행됨
    public void createdAt(){
        this.createdAt = LocalDateTime.now();
    }

    @UpdateTimestamp // UPDATE 시 자동으로 값을 채워줌
    private LocalDateTime updateAt = LocalDateTime.now();
    private String status;
}

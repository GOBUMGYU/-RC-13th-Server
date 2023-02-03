package com.example.demo.src.store;
import com.example.demo.src.store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;


@Repository
public class StoreDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int createStore(PostStoreReq postStoreReq) {
        String createStoreQuery = "insert into store (storeId, deliveryType, categories, storeName, content, deliveryArea" +
                ", storeAddress, phoneNumber, operatingTime, holiday, storeImgUrl, deliveryTips, minimumOrderAmount" +
                ", wishCount, estimatedDeliveryTime, createdAt, updateAt, status)" +
                " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        Object[] createStoreParams = new Object[] {
                postStoreReq.getStoreId(), postStoreReq.getDeliveryType(), postStoreReq.getCategories(), postStoreReq.getStoreName()
                , postStoreReq.getContent(), postStoreReq.getDeliveryArea(), postStoreReq.getStoreAddress(), postStoreReq.getPhoneNumber()
                , postStoreReq.getOperatingTime(), postStoreReq.getHoliday(), postStoreReq.getStoreImgUrl(), postStoreReq.getDeliveryTips()
                , postStoreReq.getMinimumOrderAmount(), postStoreReq.getWishCount(), postStoreReq.getEstimatedDeliveryTime(), postStoreReq.getCreatedAt()
                , postStoreReq.getUpdateAt(), postStoreReq.getStatus()
        };

        this.jdbcTemplate.update(createStoreQuery,createStoreParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    //특정 유저 조회
    public GetStoreRes getStore(BigInteger storeId){
        String getStoreQuery = "select storeId, storeName, content, storeImgURL from store where storeId = ?";
        BigInteger getStoreParams = storeId;
        return this.jdbcTemplate.queryForObject(getStoreQuery,
                (rs, rowNum) -> new GetStoreRes(
                        new BigInteger(rs.getString( "storeId")),
                        rs.getString("storeName"),
                        rs.getString("content"),
                        rs.getString("storeImgURL")),
                getStoreParams);
    }

    public List<GetStoreRes> getStores(){
        String getStoresQuery = "select * from store";
        return this.jdbcTemplate.query(getStoresQuery,
                (rs,rowNum) -> new GetStoreRes(
                        new BigInteger(rs.getString("storeId")),
                        rs.getString("storeName"),
                        rs.getString("content"),
                        rs.getString("storeImgUrl"))
        );
    }

    public List<GetStoreRes> getStoresByStoreName(String storeName){
        String getStoresByNameQuery = "select * from store where storeName =?";
        String getStoreByNameParams = storeName;
        return this.jdbcTemplate.query(getStoreByNameParams,
                (rs, rowNum) -> new GetStoreRes(
                        new BigInteger(rs.getString("storeId")),
                        rs.getString("storeName"),
                        rs.getString("content"),
                        rs.getString("storeImgUrl")),
                getStoreByNameParams);
    }

    public int modifyStoreName(PatchStoreReq patchStoreReq){
        String modifyStoreNameQuery = "update store set storeName = ? where storeId = ? ";
        Object[] modifyStoreNameParams = new Object[]{patchStoreReq.getStoreName(), patchStoreReq.getStoreId()};

        return this.jdbcTemplate.update(modifyStoreNameQuery,modifyStoreNameParams);
    }

    public int deleteStore(BigInteger storeId) {
        String deleteStoreQuery = "update store set status = 'N' where storeId = ?";
        BigInteger deleteStoreParams = storeId;

        return this.jdbcTemplate.update(deleteStoreQuery, deleteStoreParams);
    }

}

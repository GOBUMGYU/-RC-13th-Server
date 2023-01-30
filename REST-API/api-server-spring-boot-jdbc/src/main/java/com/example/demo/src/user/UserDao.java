package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //특정 유저 조회
    public GetUserRes getUser(BigInteger userId){
        String getUserQuery = "select userId, pass, name, email, status from user where userId = ?";
        BigInteger getUserParams = userId;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        new BigInteger(rs.getString( "userId")),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("pass"),
                        rs.getString("status")),

                getUserParams);
    }
    //유저 전체 조회
    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from user";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        new BigInteger(rs.getString("userId")),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("pass"),
                        rs.getString("status"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from user where email = ?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        new BigInteger(rs.getString("userId")),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("pass"),
                        rs.getString("status")),
                getUsersByEmailParams);
    }
    //회원가입
    public BigInteger createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into user (pass, name, profileImg, nickName" +
                ", phoneNumber, myForgn, createdAt, updateAt, email, role, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] createUserParams = new Object[]{postUserReq.getPass(), postUserReq.getName()
                , postUserReq.getProfileImg(), postUserReq.getNickName(), postUserReq.getPhoneNumber()
                , postUserReq.getMyForgn(), postUserReq.getCreatedAt().toLocalDate(), postUserReq.getUpdateAt().toLocalDate()
                , postUserReq.getEmail(), postUserReq.getRole(), postUserReq.getStatus()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,BigInteger.class);
    }

    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from user where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update user set name = ? where userId = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getName(), patchUserReq.getUserId()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }


    public User getPwd(PostLoginReq postLoginReq) {
        String getPwdQuery = "select userId, pass, name, email from user where userId = ?";
        BigInteger getPwdParams = postLoginReq.getUserId();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs, rowNum) -> User.builder()
                        .userId(new BigInteger(rs.getString("userId")))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .pass(rs.getString("pass"))
                        .build(), getPwdParams
        );
    }

    public int deleteUser(BigInteger userId) {
        String deleteUserQuery = "update user set status = 'N' where userId = ?";
        BigInteger deleteUserParams = userId;

        return this.jdbcTemplate.update(deleteUserQuery, deleteUserParams);
    }


}

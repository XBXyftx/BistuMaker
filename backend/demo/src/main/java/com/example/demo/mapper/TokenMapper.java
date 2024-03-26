package com.example.demo.mapper;

import com.example.demo.pojo.Token;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TokenMapper {

    @Insert("insert into token (token_id, token, expiration_time,user_name) values (#{tokenId},#{token},#{expirationTime},#{userName})")
    void insertToken(Token token);

    @Delete("delete from token where token_id = #{token}")
    void deleteToken(String token);

    @Select("select * from token")
    List<Token> selectAllToken();

    @Select("select * from token where token_id = #{token}")
    Token selectTokenById(String token);

    @Update("update token set token_id = #{tokenId} ,token = #{token},expiration_time = #{expirationTime},user_name = #{userName} where user_name = #{userName}")
    void updateToken(Token token);

    @Select("select * from token where user_name = #{user}")
    Token selectTokenByUser(String user);

}

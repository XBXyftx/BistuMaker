package com.example.demo.mapper;

import com.example.demo.pojo.Token;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {

    @Insert("insert into token (token_id, token, expiration_time) values (#{tokenId},#{token},#{expirationTime})")
    void insertToken(Token token);

}

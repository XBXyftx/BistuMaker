package com.example.demo.service;

import com.example.demo.pojo.Token;

import java.util.List;

public interface TokenService  {

    void addToken(Token token);

    void deleteToken(String token);

    List<Token> selectAllToken();


    Token selectTokenById(String token);

    void updateTokenById(Token token);
    Token selectTokenByUser(String user);
}

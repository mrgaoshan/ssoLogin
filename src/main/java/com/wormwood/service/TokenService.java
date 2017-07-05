package com.wormwood.service;

import com.wormwood.DTO.TokenDTO;
import com.wormwood.dao.TokenDao;
import com.wormwood.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kasimodo on 2017-07-05.
 */
public class TokenService {

    @Autowired
    private TokenDao tokenDao;

    public void insert(TokenDTO tokenDTO) {
        tokenDao.insert(tokenDTO);
    }


    public TokenDTO findByName(String systemName) {
        return tokenDao.findByName(systemName);
    }


    public int checkToken(String token) {
        return tokenDao.checkToken(token);
    }

    public void updateToken(String token,String systemName){
        tokenDao.updateToken(token,systemName);
    }

    public void updateOrInsert(String token,String systemName){
        TokenDTO existToken = findByName(systemName);
        if(null!=existToken){
            updateToken(token,systemName);
        }else{
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setSystemName(systemName);
            tokenDTO.setToken(token);
            insert(tokenDTO);
        }
    }
}

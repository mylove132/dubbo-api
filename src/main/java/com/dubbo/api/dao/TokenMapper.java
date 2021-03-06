package com.dubbo.api.dao;

import com.dubbo.api.vo.Token;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Token record);

    int insertSelective(Token record);

    Token selectByPrimaryKey(Integer id);

    Token selectByToken(String token);

    Token selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);
}

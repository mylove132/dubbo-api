package com.dubbo.api.dao;

import com.dubbo.api.vo.Protocol;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProtocolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Protocol record);

    int insertSelective(Protocol record);

    Protocol selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Protocol record);

    int updateByPrimaryKey(Protocol record);

    List<Protocol> listProtocol();
}
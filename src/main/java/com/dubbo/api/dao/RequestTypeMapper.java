package com.dubbo.api.dao;

import com.dubbo.api.vo.RequestType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RequestTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RequestType record);

    int insertSelective(RequestType record);

    RequestType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RequestType record);

    int updateByPrimaryKey(RequestType record);

    List<RequestType> requestTypeList();
}
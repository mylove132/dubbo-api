package com.dubbo.api.dao;

import com.dubbo.api.vo.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(Integer id);

    History selectByMd5(String md5);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);

    List listHistory();

    List listHistoryByScriptId(Integer scriptId);

    List searchScriptByKeyWord(@Param("keyword") String keyword);
}

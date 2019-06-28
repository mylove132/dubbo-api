package com.dubbo.api.dao;

import com.dubbo.api.vo.Script;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScriptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Script record);

    int insertSelective(Script record);

    Script selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Script record);

    int updateByPrimaryKey(Script record);
}

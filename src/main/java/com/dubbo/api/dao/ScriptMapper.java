package com.dubbo.api.dao;

import com.dubbo.api.vo.Script;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScriptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Script record);

    int insertSelective(Script record);

    Script selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Script record);

    int updateByPrimaryKey(Script record);

    List<Script> listScript();

    List<Script> listScriptByProjectId(Integer projectId);

}
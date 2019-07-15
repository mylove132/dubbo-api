package com.dubbo.api.dao;

import com.dubbo.api.vo.Script;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScriptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Script record);

    int insertSelective(Script record);

    Script selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Script record);

    int updateByPrimaryKey(Script record);

    List listScript();

    List searchScriptByKeyWord(@Param("keyword") String keyword);

    List listScriptByProjectId(Integer projectId);

    List filterScriptListByUserId(Integer userId);

    List filterScriptListByProtocolId(Integer protocolId);

    List filterScriptListByProjectId(Integer projectId);

    List filterScriptListByUserIdAndProtocolId(@Param("userId") Integer userId,@Param("protocolId") Integer protocolId);

    List filterScriptListByUserIdAndProjectId(@Param("userId") Integer userId,@Param("projectId") Integer projectId);

    List filterScriptListByProjectIdAndProtocolId(@Param("projectId") Integer projectId,@Param("protocolId") Integer protocolId);

    List filterScriptListByProjectIdAndProtocolIdAndUserId(@Param("projectId") Integer projectId,@Param("protocolId") Integer protocolId,@Param("userId") Integer userId);

}
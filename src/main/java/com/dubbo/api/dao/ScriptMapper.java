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

    List<Script> listScript();

    List<Script> searchScriptByKeyWord(@Param("keyword") String keyword);

    List<Script> listScriptByProjectId(Integer projectId);

    List<Script> filterScriptListByUserId(Integer userId);

    List<Script> filterScriptListByProtocolId(Integer protocolId);

    List<Script> filterScriptListByProjectId(Integer projectId);

    List<Script> filterScriptListByUserIdAndProtocolId(@Param("userId") Integer userId,@Param("protocolId") Integer protocolId);

    List<Script> filterScriptListByUserIdAndProjectId(@Param("userId") Integer userId,@Param("projectId") Integer projectId);

    List<Script> filterScriptListByProjectIdAndProtocolId(@Param("projectId") Integer projectId,@Param("protocolId") Integer protocolId);

    List<Script> filterScriptListByProjectIdAndProtocolIdAndUserId(@Param("projectId") Integer projectId,@Param("protocolId") Integer protocolId,@Param("userId") Integer userId);

}
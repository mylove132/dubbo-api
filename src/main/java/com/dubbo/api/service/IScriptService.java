package com.dubbo.api.service;


import com.dubbo.api.vo.Script;

import java.util.List;
import java.util.Map;

public interface IScriptService {

    List searchScriptByKeyWord(String keyword,Integer pageNum, Integer pageSize);

    List listScriptService(Integer pageNum, Integer pageSize);

    Script getScriptByIdService(Integer scriptId);

    List listScriptByProjectIdService(Integer projectId,Integer pageNum, Integer pageSize);

    int addScriptService(Script script);

    int updateScriptService(Script script);

    int deleteScriptService(Integer scriptId);

    List filterScriptListByUserIdService(Integer userId,Integer pageNum, Integer pageSize);

    List filterScriptListByProtocolIdService(Integer protocolId,Integer pageNum, Integer pageSize);

    List filterScriptListByProjectIdService(Integer projectId,Integer pageNum, Integer pageSize);

    List filterScriptListByUserIdAndProtocolIdService(Integer userId, Integer protocolId,Integer pageNum, Integer pageSize);

    List filterScriptListByUserIdAndProjectIdService(Integer userId, Integer projectId,Integer pageNum, Integer pageSize);

    List filterScriptListByProjectIdAndProtocolIdService(Integer projectId, Integer protocolId,Integer pageNum, Integer pageSize);

    List filterScriptListByProjectIdAndProtocolIdAndUserIdService(Integer projectId, Integer protocolId, Integer userId,Integer pageNum, Integer pageSize);
}

package com.dubbo.api.service;


import com.dubbo.api.vo.Script;

import java.util.List;

public interface IScriptService {

    List<Script> searchScriptBYKeyWordService(String keyword);

    List<Script> listScriptService();

    Script getScriptByIdService(Integer scriptId);

    List<Script> listScriptByProjectIdService(Integer projectId);

    int addScriptService(Script script);

    int updateScriptService(Script script);

    int deleteScriptService(Integer scriptId);

    List<Script> filterScriptListByUserIdService(Integer userId);

    List<Script> filterScriptListByProtocolIdService(Integer protocolId);

    List<Script> filterScriptListByProjectIdService(Integer projectId);

    List<Script> filterScriptListByUserIdAndProtocolIdService(Integer userId, Integer protocolId);

    List<Script> filterScriptListByUserIdAndProjectIdService(Integer userId, Integer projectId);

    List<Script> filterScriptListByProjectIdAndProtocolIdService(Integer projectId, Integer protocolId);

    List<Script> filterScriptListByProjectIdAndProtocolIdAndUserIdService(Integer projectId, Integer protocolId, Integer userId);
}

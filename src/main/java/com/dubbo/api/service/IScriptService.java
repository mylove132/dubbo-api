package com.dubbo.api.service;


import com.dubbo.api.vo.Script;

import java.util.List;

public interface IScriptService {

    List<Script> listScriptService();

    List<Script> listScriptByProjectIdService(Integer projectId);

    int addScriptService(Script script);

    int updateScriptService(Script script);

    int deleteScriptService(Integer scriptId);
}

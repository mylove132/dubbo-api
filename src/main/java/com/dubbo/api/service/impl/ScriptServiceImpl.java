package com.dubbo.api.service.impl;

import com.dubbo.api.dao.ScriptMapper;
import com.dubbo.api.service.IScriptService;
import com.dubbo.api.vo.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScriptServiceImpl implements IScriptService {

    @Autowired
    private ScriptMapper scriptMapper;

    @Override
    public List<Script> listScriptService() {
        return scriptMapper.listScript();
    }

    @Override
    public List<Script> listScriptByProjectIdService(Integer projectId) {
        return scriptMapper.listScriptByProjectId(projectId);
    }

    @Transactional
    @Override
    public int addScriptService(Script script) {
        return scriptMapper.insertSelective(script);
    }

    @Transactional
    @Override
    public int updateScriptService(Script script) {
        return scriptMapper.updateByPrimaryKeySelective(script);
    }

    @Transactional
    @Override
    public int deleteScriptService(Integer scriptId) {
        return scriptMapper.deleteByPrimaryKey(scriptId);
    }
}

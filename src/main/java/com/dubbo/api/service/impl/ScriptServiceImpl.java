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
    public List<Script> searchScriptBYKeyWordService(String keyword) {
        return scriptMapper.searchScriptByKeyWord(keyword);
    }

    @Override
    public List<Script> listScriptService() {
        return scriptMapper.listScript();
    }

    @Override
    public Script getScriptByIdService(Integer scriptId) {
        return scriptMapper.selectByPrimaryKey(scriptId);
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

    @Override
    public List<Script> filterScriptListByUserIdService(Integer userId) {
        return scriptMapper.filterScriptListByUserId(userId);
    }

    @Override
    public List<Script> filterScriptListByProtocolIdService(Integer protocolId) {
        return scriptMapper.filterScriptListByProtocolId(protocolId);
    }

    @Override
    public List<Script> filterScriptListByProjectIdService(Integer projectId) {
        return scriptMapper.filterScriptListByProjectId(projectId);
    }

    @Override
    public List<Script> filterScriptListByUserIdAndProtocolIdService(Integer userId, Integer protocolId) {
        return scriptMapper.filterScriptListByUserIdAndProtocolId(userId, protocolId);
    }

    @Override
    public List<Script> filterScriptListByUserIdAndProjectIdService(Integer userId, Integer projectId) {
        return scriptMapper.filterScriptListByUserIdAndProjectId(userId, projectId);
    }

    @Override
    public List<Script> filterScriptListByProjectIdAndProtocolIdService(Integer projectId, Integer protocolId) {
        return scriptMapper.filterScriptListByProjectIdAndProtocolId(projectId, protocolId);
    }

    @Override
    public List<Script> filterScriptListByProjectIdAndProtocolIdAndUserIdService(Integer projectId, Integer protocolId, Integer userId) {
        return scriptMapper.filterScriptListByProjectIdAndProtocolIdAndUserId(projectId, protocolId, userId);
    }

}

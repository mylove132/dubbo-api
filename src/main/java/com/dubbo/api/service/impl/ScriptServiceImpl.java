package com.dubbo.api.service.impl;

import com.dubbo.api.dao.ScriptMapper;
import com.dubbo.api.service.IScriptService;
import com.dubbo.api.vo.Script;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ScriptServiceImpl implements IScriptService {

    @Autowired
    private ScriptMapper scriptMapper;

    @Override
    public List searchScriptByKeyWord(String keyword,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return scriptMapper.searchScriptByKeyWord(keyword);
    }

    @Override
    public List listScriptService(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return scriptMapper.listScript();
    }

    @Override
    public Script getScriptByIdService(Integer scriptId) {
        return scriptMapper.selectByPrimaryKey(scriptId);
    }

    @Override
    public List listScriptByProjectIdService(Integer projectId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
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
    public List<Script> filterScriptListByUserIdService(Integer userId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return scriptMapper.filterScriptListByUserId(userId);
    }

    @Override
    public List<Script> filterScriptListByProtocolIdService(Integer protocolId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return scriptMapper.filterScriptListByProtocolId(protocolId);
    }

    @Override
    public List<Script> filterScriptListByProjectIdService(Integer projectId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return scriptMapper.filterScriptListByProjectId(projectId);
    }

    @Override
    public List filterScriptListByUserIdAndProtocolIdService(Integer userId, Integer protocolId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return scriptMapper.filterScriptListByUserIdAndProtocolId(userId, protocolId);
    }


    @Override
    public List<Script> filterScriptListByUserIdAndProjectIdService(Integer userId, Integer projectId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return scriptMapper.filterScriptListByUserIdAndProjectId(userId, projectId);
    }

    @Override
    public List<Script> filterScriptListByProjectIdAndProtocolIdService(Integer projectId, Integer protocolId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return scriptMapper.filterScriptListByProjectIdAndProtocolId(projectId, protocolId);
    }

    @Override
    public List<Script> filterScriptListByProjectIdAndProtocolIdAndUserIdService(Integer projectId, Integer protocolId, Integer userId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return scriptMapper.filterScriptListByProjectIdAndProtocolIdAndUserId(projectId, protocolId, userId);
    }

}

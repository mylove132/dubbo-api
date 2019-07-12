package com.dubbo.api.dao;

import com.dubbo.api.vo.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Mapper
public interface ScheduleJobMapper {

	/**
	 * 新增
	 *
	 * @param record
	 * @return
	 */
	int insertSelective(ScheduleJob record);

	/**
	 * 根据ID查询
	 *
	 * @param id
	 * @return
	 */
	ScheduleJob selectById(Integer id);

	/**
	 * 根据ID更新
	 *
	 * @param job
	 * @return
	 */
	int updateById(ScheduleJob job);

	/**
	 * 批量更新状态
	 *
	 * @param ids
	 * @param status
	 * @return
	 */
	int updateBatch(@Param("ids") List<Integer> ids, @Param("status") Integer status);

	/**
	 * 批量删除
	 *
	 * @param ids
	 */
	 void deleteBatch(List<Integer> ids);

	/**
	 * 根据ID查询
	 *
	 * @param ids
	 * @return
	 */
	 List<ScheduleJob> selectJobListByIds(List<Integer> ids);

	/**
	 * 获取全部
	 *
	 * @return
	 */
	List<ScheduleJob> selectAll();

	/**
	 * 根据条件查询
	 *
	 * @return
	 */
	List<ScheduleJob> selectByParams();
}

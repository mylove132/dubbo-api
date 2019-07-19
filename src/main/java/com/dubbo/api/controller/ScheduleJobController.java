package com.dubbo.api.controller;

import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.constant.PermissionConstant;
import com.dubbo.api.config.AuthPermission;
import com.dubbo.api.vo.ScheduleJob;
import com.dubbo.api.service.IScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 */
@Controller
@RequestMapping("/api/scheduleJob")
public class ScheduleJobController {

	@Autowired
	private IScheduleJobService scheduleJobService;

	/**
	 * 查询列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse list(@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
							 @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
		return scheduleJobService.selectByQuery(pageNum,pageSize);
	}

	/**
	 * 新增定时任务
	 *
	 * @param scheduleJob
	 * @return
	 */
	@AuthPermission(PermissionConstant.VIP)
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse add(@RequestBody ScheduleJob scheduleJob){
		return scheduleJobService.add(scheduleJob);
	}

	/**
	 * 修改定时任务
	 *
	 * @param id
	 * @param scheduleJob
	 * @return
	 */
	@AuthPermission(PermissionConstant.VIP)
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public BaseResponse update(@PathVariable int id, @RequestBody ScheduleJob scheduleJob){
		return scheduleJobService.update(id, scheduleJob);
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AuthPermission(PermissionConstant.VIP)
	@RequestMapping(value = "/batchDel/{ids}",method = RequestMethod.DELETE)
	@ResponseBody
	public BaseResponse delete(@PathVariable List<Integer> ids){
		return scheduleJobService.deleteBatch(ids);
	}

	/**
	 * 立即执行任务
	 *
	 * @param ids
	 * @return
	 */
	@AuthPermission(PermissionConstant.VIP)
	@RequestMapping(value = "/run/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse run(@PathVariable List<Integer> ids){
		return scheduleJobService.run(ids);
	}

	/**
	 * 暂停定时任务
	 *
	 * @param ids
	 * @return
	 */
	@AuthPermission(PermissionConstant.VIP)
	@RequestMapping(value = "/pause/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse pause(@PathVariable List<Integer> ids){
		return scheduleJobService.pause(ids);
	}

	/**
	 * 恢复定时任务
	 *
	 * @param ids
	 * @return
	 */
	@AuthPermission(PermissionConstant.VIP)
	@RequestMapping(value = "/resume/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse resume(@PathVariable List<Integer> ids){
		return scheduleJobService.resume(ids);
	}

}

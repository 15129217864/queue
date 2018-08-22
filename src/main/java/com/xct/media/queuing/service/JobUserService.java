package com.xct.media.queuing.service;

import com.xct.media.queuing.pojo.bo.JobUserBo;
import com.xct.media.queuing.util.RestApiMsg;

import java.util.List;

/**
 * @Author: YouGX.
 * @Date: Create in 2018/4/30 20:18.
 * @Description:
 */
public interface JobUserService {

    /**
     * 查询所有工作人员信息 再循环查询出 对应的屏ip 业务名称
     * 转换为json字符串供页面使用
     */
    List<JobUserBo> queryAllJobUserInfo();

    /**
     * 查询出所有工作人员信息
     * 下拉框选择使用
     *
     * @return
     */
    List<JobUserBo> selectBaseJobUserInfo();

    /**
     * 用户登录
     *
     * @return
     */
    boolean userLogin(String username, String password);

    /**
     * 添加工作人员
     *
     * @return 返回执行信息
     */
    RestApiMsg addJobUser(JobUserBo jobUserBo);

    /**
     * 修改工作人员
     *
     * @param jobUserBo
     * @return
     */
    RestApiMsg updateJobUser(JobUserBo jobUserBo);

    /**
     * 删除工作人员
     *
     * @param jobUserId
     * @return
     */
    RestApiMsg clearJobUser(String jobUserId);
}

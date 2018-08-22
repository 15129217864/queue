package com.xct.media.queuing.mapper;

import com.xct.media.queuing.pojo.bo.JobUserBo;

import java.util.List;

/**
 * Created by Chris on 2018/4/29.
 */
public interface JobUserMapper {

    /**
     * 保存业务数据记录表
     *
     * @param jobUser
     * @return
     */
    int setJobUser(JobUserBo jobUser);


    /**
     * 查询业务记录数据
     *
     * @param jobUserId
     * @return
     */
    JobUserBo queryByJobUser(int jobUserId);


    /**
     * 查询业务记录数据集
     *
     * @param jobUser
     * @return
     */
    List<JobUserBo> queryJobUserSet(JobUserBo jobUser);

    /**
     * 修改业务记录数据
     *
     * @param jobUser
     * @return
     */
    int updateJobUser(JobUserBo jobUser);
}
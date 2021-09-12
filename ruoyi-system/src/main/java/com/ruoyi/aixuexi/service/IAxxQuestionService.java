package com.ruoyi.aixuexi.service;

import java.util.List;
import com.ruoyi.aixuexi.domain.AxxQuestion;

/**
 * 问题详情Service接口
 * 
 * @author tianhuiying
 * @date 2021-07-16
 */
public interface IAxxQuestionService 
{
    /**
     * 查询问题详情
     * 
     * @param questionId 问题详情ID
     * @return 问题详情
     */
    public AxxQuestion selectAxxQuestionById(Long questionId);

    /**
     * 查询问题详情列表
     * 
     * @param axxQuestion 问题详情
     * @return 问题详情集合
     */
    public List<AxxQuestion> selectAxxQuestionList(AxxQuestion axxQuestion);

    /**
     * 新增问题详情
     * 
     * @param axxQuestion 问题详情
     * @return 结果
     */
    public int insertAxxQuestion(AxxQuestion axxQuestion);

    /**
     * 修改问题详情
     * 
     * @param axxQuestion 问题详情
     * @return 结果
     */
    public int updateAxxQuestion(AxxQuestion axxQuestion);

    /**
     * 批量删除问题详情
     * 
     * @param questionIds 需要删除的问题详情ID
     * @return 结果
     */
    public int deleteAxxQuestionByIds(Long[] questionIds);

    /**
     * 删除问题详情信息
     * 
     * @param questionId 问题详情ID
     * @return 结果
     */
    public int deleteAxxQuestionById(Long questionId);
}

package com.ruoyi.aixuexi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.aixuexi.mapper.AxxQuestionMapper;
import com.ruoyi.aixuexi.domain.AxxQuestion;
import com.ruoyi.aixuexi.service.IAxxQuestionService;

/**
 * 问题详情Service业务层处理
 * 
 * @author tianhuiying
 * @date 2021-07-16
 */
@Service
public class AxxQuestionServiceImpl implements IAxxQuestionService 
{
    @Autowired
    private AxxQuestionMapper axxQuestionMapper;

    /**
     * 查询问题详情
     * 
     * @param questionId 问题详情ID
     * @return 问题详情
     */
    @Override
    public AxxQuestion selectAxxQuestionById(Long questionId)
    {
        return axxQuestionMapper.selectAxxQuestionById(questionId);
    }

    /**
     * 查询问题详情列表
     * 
     * @param axxQuestion 问题详情
     * @return 问题详情
     */
    @Override
    public List<AxxQuestion> selectAxxQuestionList(AxxQuestion axxQuestion)
    {
        return axxQuestionMapper.selectAxxQuestionList(axxQuestion);
    }

    /**
     * 新增问题详情
     * 
     * @param axxQuestion 问题详情
     * @return 结果
     */
    @Override
    public int insertAxxQuestion(AxxQuestion axxQuestion)
    {
        return axxQuestionMapper.insertAxxQuestion(axxQuestion);
    }

    /**
     * 修改问题详情
     * 
     * @param axxQuestion 问题详情
     * @return 结果
     */
    @Override
    public int updateAxxQuestion(AxxQuestion axxQuestion)
    {
        return axxQuestionMapper.updateAxxQuestion(axxQuestion);
    }

    /**
     * 批量删除问题详情
     * 
     * @param questionIds 需要删除的问题详情ID
     * @return 结果
     */
    @Override
    public int deleteAxxQuestionByIds(Long[] questionIds)
    {
        return axxQuestionMapper.deleteAxxQuestionByIds(questionIds);
    }

    /**
     * 删除问题详情信息
     * 
     * @param questionId 问题详情ID
     * @return 结果
     */
    @Override
    public int deleteAxxQuestionById(Long questionId)
    {
        return axxQuestionMapper.deleteAxxQuestionById(questionId);
    }
}

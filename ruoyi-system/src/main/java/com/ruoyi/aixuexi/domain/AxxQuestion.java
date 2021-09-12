package com.ruoyi.aixuexi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 问题详情对象 axx_question
 * 
 * @author tianhuiying
 * @date 2021-07-16
 */
public class AxxQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 问题分类id */
    private Long questionId;

    /** 研发部门id */
    @Excel(name = "研发部门id")
    private Long departmentId;

    /** 问题分类id */
    @Excel(name = "问题分类id")
    private Long serviceId;

    /** 业务线id */
    @Excel(name = "业务线id")
    private Long categoryId;

    /** 问题概述 */
    @Excel(name = "问题概述")
    private String questionDesc;

    /** 问题答案 */
    @Excel(name = "问题答案")
    private String questionAnswer;

    /** 问题关键字 */
    @Excel(name = "问题关键字")
    private String questionKey;

    public void setQuestionId(Long questionId) 
    {
        this.questionId = questionId;
    }

    public Long getQuestionId() 
    {
        return questionId;
    }
    public void setDepartmentId(Long departmentId) 
    {
        this.departmentId = departmentId;
    }

    public Long getDepartmentId() 
    {
        return departmentId;
    }
    public void setServiceId(Long serviceId) 
    {
        this.serviceId = serviceId;
    }

    public Long getServiceId() 
    {
        return serviceId;
    }
    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setQuestionDesc(String questionDesc) 
    {
        this.questionDesc = questionDesc;
    }

    public String getQuestionDesc() 
    {
        return questionDesc;
    }
    public void setQuestionAnswer(String questionAnswer) 
    {
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionAnswer() 
    {
        return questionAnswer;
    }
    public void setQuestionKey(String questionKey) 
    {
        this.questionKey = questionKey;
    }

    public String getQuestionKey() 
    {
        return questionKey;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("questionId", getQuestionId())
            .append("departmentId", getDepartmentId())
            .append("serviceId", getServiceId())
            .append("categoryId", getCategoryId())
            .append("questionDesc", getQuestionDesc())
            .append("questionAnswer", getQuestionAnswer())
            .append("questionKey", getQuestionKey())
            .append("remark", getRemark())
            .toString();
    }
}

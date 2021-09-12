package com.ruoyi.aixuexi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 业务线对象 axx_serviceline
 *
 * @author tianhuiying
 * @date 2021-07-16
 */
public class AxxServiceline extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 业务线id */
    private Long servicelineId;

    /** 研发部门id */
    @Excel(name = "研发部门id")
    private Long departmentId;

    /** 业务线名称 */
    @Excel(name = "业务线名称")
    private String servicelineName;

    public void setServicelineId(Long servicelineId)
    {
        this.servicelineId = servicelineId;
    }

    public Long getServicelineId()
    {
        return servicelineId;
    }
    public void setDepartmentId(Long departmentId)
    {
        this.departmentId = departmentId;
    }

    public Long getDepartmentId()
    {
        return departmentId;
    }
    public void setServicelineName(String servicelineName)
    {
        this.servicelineName = servicelineName;
    }

    public String getServicelineName()
    {
        return servicelineName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("servicelineId", getServicelineId())
                .append("departmentId", getDepartmentId())
                .append("servicelineName", getServicelineName())
                .append("remark", getRemark())
                .toString();
    }
}

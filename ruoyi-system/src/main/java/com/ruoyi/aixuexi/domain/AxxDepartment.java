package com.ruoyi.aixuexi.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 研发部门对象 axx_department
 * 
 * @author tianhuiying
 * @date 2021-07-16
 */
public class AxxDepartment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 研发部门id */
    private Long departmentId;

    /** 研发部门名称 */
    @Excel(name = "研发部门名称")
    private String departmentName;

    /** 业务线信息 */
    private List<AxxServiceline> axxServicelineList;

    public void setDepartmentId(Long departmentId) 
    {
        this.departmentId = departmentId;
    }

    public Long getDepartmentId() 
    {
        return departmentId;
    }
    public void setDepartmentName(String departmentName) 
    {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() 
    {
        return departmentName;
    }

    public List<AxxServiceline> getAxxServicelineList()
    {
        return axxServicelineList;
    }

    public void setAxxServicelineList(List<AxxServiceline> axxServicelineList)
    {
        this.axxServicelineList = axxServicelineList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("departmentId", getDepartmentId())
            .append("departmentName", getDepartmentName())
            .append("remark", getRemark())
            .append("axxServicelineList", getAxxServicelineList())
            .toString();
    }
}

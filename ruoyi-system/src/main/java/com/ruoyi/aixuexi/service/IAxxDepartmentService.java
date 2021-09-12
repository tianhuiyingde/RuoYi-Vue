package com.ruoyi.aixuexi.service;

import java.util.List;
import com.ruoyi.aixuexi.domain.AxxDepartment;

/**
 * 研发部门Service接口
 * 
 * @author tianhuiying
 * @date 2021-07-16
 */
public interface IAxxDepartmentService 
{
    /**
     * 查询研发部门
     * 
     * @param departmentId 研发部门ID
     * @return 研发部门
     */
    public AxxDepartment selectAxxDepartmentById(Long departmentId);

    /**
     * 查询研发部门列表
     * 
     * @param axxDepartment 研发部门
     * @return 研发部门集合
     */
    public List<AxxDepartment> selectAxxDepartmentList(AxxDepartment axxDepartment);

    /**
     * 新增研发部门
     * 
     * @param axxDepartment 研发部门
     * @return 结果
     */
    public int insertAxxDepartment(AxxDepartment axxDepartment);

    /**
     * 修改研发部门
     * 
     * @param axxDepartment 研发部门
     * @return 结果
     */
    public int updateAxxDepartment(AxxDepartment axxDepartment);

    /**
     * 批量删除研发部门
     * 
     * @param departmentIds 需要删除的研发部门ID
     * @return 结果
     */
    public int deleteAxxDepartmentByIds(Long[] departmentIds);

    /**
     * 删除研发部门信息
     * 
     * @param departmentId 研发部门ID
     * @return 结果
     */
    public int deleteAxxDepartmentById(Long departmentId);
}

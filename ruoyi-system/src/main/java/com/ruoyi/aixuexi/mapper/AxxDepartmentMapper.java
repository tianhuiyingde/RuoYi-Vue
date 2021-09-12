package com.ruoyi.aixuexi.mapper;

import java.util.List;
import com.ruoyi.aixuexi.domain.AxxDepartment;
import com.ruoyi.aixuexi.domain.AxxServiceline;

/**
 * 研发部门Mapper接口
 * 
 * @author tianhuiying
 * @date 2021-07-16
 */
public interface AxxDepartmentMapper 
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
     * 删除研发部门
     * 
     * @param departmentId 研发部门ID
     * @return 结果
     */
    public int deleteAxxDepartmentById(Long departmentId);

    /**
     * 批量删除研发部门
     * 
     * @param departmentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAxxDepartmentByIds(Long[] departmentIds);

    /**
     * 批量删除业务线
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAxxServicelineByDepartmentIds(Long[] departmentIds);
    
    /**
     * 批量新增业务线
     * 
     * @param axxServicelineList 业务线列表
     * @return 结果
     */
    public int batchAxxServiceline(List<AxxServiceline> axxServicelineList);
    

    /**
     * 通过研发部门ID删除业务线信息
     * 
     * @param departmentId 研发部门ID
     * @return 结果
     */
    public int deleteAxxServicelineByDepartmentId(Long departmentId);
}

package com.ruoyi.aixuexi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.aixuexi.domain.AxxServiceline;
import com.ruoyi.aixuexi.mapper.AxxDepartmentMapper;
import com.ruoyi.aixuexi.domain.AxxDepartment;
import com.ruoyi.aixuexi.service.IAxxDepartmentService;

/**
 * 研发部门Service业务层处理
 * 
 * @author tianhuiying
 * @date 2021-07-16
 */
@Service
public class AxxDepartmentServiceImpl implements IAxxDepartmentService 
{
    @Autowired
    private AxxDepartmentMapper axxDepartmentMapper;

    /**
     * 查询研发部门
     * 
     * @param departmentId 研发部门ID
     * @return 研发部门
     */
    @Override
    public AxxDepartment selectAxxDepartmentById(Long departmentId)
    {
        return axxDepartmentMapper.selectAxxDepartmentById(departmentId);
    }

    /**
     * 查询研发部门列表
     * 
     * @param axxDepartment 研发部门
     * @return 研发部门
     */
    @Override
    public List<AxxDepartment> selectAxxDepartmentList(AxxDepartment axxDepartment)
    {
        return axxDepartmentMapper.selectAxxDepartmentList(axxDepartment);
    }

    /**
     * 新增研发部门
     * 
     * @param axxDepartment 研发部门
     * @return 结果
     */
    @Transactional
    @Override
    public int insertAxxDepartment(AxxDepartment axxDepartment)
    {
        int rows = axxDepartmentMapper.insertAxxDepartment(axxDepartment);
        insertAxxServiceline(axxDepartment);
        return rows;
    }

    /**
     * 修改研发部门
     * 
     * @param axxDepartment 研发部门
     * @return 结果
     */
    @Transactional
    @Override
    public int updateAxxDepartment(AxxDepartment axxDepartment)
    {
        axxDepartmentMapper.deleteAxxServicelineByDepartmentId(axxDepartment.getDepartmentId());
        insertAxxServiceline(axxDepartment);
        return axxDepartmentMapper.updateAxxDepartment(axxDepartment);
    }

    /**
     * 批量删除研发部门
     * 
     * @param departmentIds 需要删除的研发部门ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAxxDepartmentByIds(Long[] departmentIds)
    {
        axxDepartmentMapper.deleteAxxServicelineByDepartmentIds(departmentIds);
        return axxDepartmentMapper.deleteAxxDepartmentByIds(departmentIds);
    }

    /**
     * 删除研发部门信息
     * 
     * @param departmentId 研发部门ID
     * @return 结果
     */
    @Override
    public int deleteAxxDepartmentById(Long departmentId)
    {
        axxDepartmentMapper.deleteAxxServicelineByDepartmentId(departmentId);
        return axxDepartmentMapper.deleteAxxDepartmentById(departmentId);
    }

    /**
     * 新增业务线信息
     * 
     * @param axxDepartment 研发部门对象
     */
    public void insertAxxServiceline(AxxDepartment axxDepartment)
    {
        List<AxxServiceline> axxServicelineList = axxDepartment.getAxxServicelineList();
        Long departmentId = axxDepartment.getDepartmentId();
        if (StringUtils.isNotNull(axxServicelineList))
        {
            List<AxxServiceline> list = new ArrayList<AxxServiceline>();
            for (AxxServiceline axxServiceline : axxServicelineList)
            {
                axxServiceline.setDepartmentId(departmentId);
                list.add(axxServiceline);
            }
            if (list.size() > 0)
            {
                axxDepartmentMapper.batchAxxServiceline(list);
            }
        }
    }
}

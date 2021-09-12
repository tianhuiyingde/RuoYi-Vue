package com.ruoyi.aixuexi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.aixuexi.mapper.AxxServicelineMapper;
import com.ruoyi.aixuexi.domain.AxxServiceline;
import com.ruoyi.aixuexi.service.IAxxServicelineService;

/**
 * 业务线Service业务层处理
 * 
 * @author tianhuiying
 * @date 2021-07-16
 */
@Service
public class AxxServicelineServiceImpl implements IAxxServicelineService 
{
    @Autowired
    private AxxServicelineMapper axxServicelineMapper;

    /**
     * 查询业务线
     * 
     * @param servicelineId 业务线ID
     * @return 业务线
     */
    @Override
    public AxxServiceline selectAxxServicelineById(Long servicelineId)
    {
        return axxServicelineMapper.selectAxxServicelineById(servicelineId);
    }

    /**
     * 查询业务线列表
     * 
     * @param axxServiceline 业务线
     * @return 业务线
     */
    @Override
    public List<AxxServiceline> selectAxxServicelineList(AxxServiceline axxServiceline)
    {
        return axxServicelineMapper.selectAxxServicelineList(axxServiceline);
    }

    /**
     * 新增业务线
     * 
     * @param axxServiceline 业务线
     * @return 结果
     */
    @Override
    public int insertAxxServiceline(AxxServiceline axxServiceline)
    {
        return axxServicelineMapper.insertAxxServiceline(axxServiceline);
    }

    /**
     * 修改业务线
     * 
     * @param axxServiceline 业务线
     * @return 结果
     */
    @Override
    public int updateAxxServiceline(AxxServiceline axxServiceline)
    {
        return axxServicelineMapper.updateAxxServiceline(axxServiceline);
    }

    /**
     * 批量删除业务线
     * 
     * @param servicelineIds 需要删除的业务线ID
     * @return 结果
     */
    @Override
    public int deleteAxxServicelineByIds(Long[] servicelineIds)
    {
        return axxServicelineMapper.deleteAxxServicelineByIds(servicelineIds);
    }

    /**
     * 删除业务线信息
     * 
     * @param servicelineId 业务线ID
     * @return 结果
     */
    @Override
    public int deleteAxxServicelineById(Long servicelineId)
    {
        return axxServicelineMapper.deleteAxxServicelineById(servicelineId);
    }
}

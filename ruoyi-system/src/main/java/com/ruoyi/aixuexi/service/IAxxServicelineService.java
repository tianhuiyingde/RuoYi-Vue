package com.ruoyi.aixuexi.service;

import java.util.List;
import com.ruoyi.aixuexi.domain.AxxServiceline;

/**
 * 业务线Service接口
 * 
 * @author tianhuiying
 * @date 2021-07-16
 */
public interface IAxxServicelineService 
{
    /**
     * 查询业务线
     * 
     * @param servicelineId 业务线ID
     * @return 业务线
     */
    public AxxServiceline selectAxxServicelineById(Long servicelineId);

    /**
     * 查询业务线列表
     * 
     * @param axxServiceline 业务线
     * @return 业务线集合
     */
    public List<AxxServiceline> selectAxxServicelineList(AxxServiceline axxServiceline);

    /**
     * 新增业务线
     * 
     * @param axxServiceline 业务线
     * @return 结果
     */
    public int insertAxxServiceline(AxxServiceline axxServiceline);

    /**
     * 修改业务线
     * 
     * @param axxServiceline 业务线
     * @return 结果
     */
    public int updateAxxServiceline(AxxServiceline axxServiceline);

    /**
     * 批量删除业务线
     * 
     * @param servicelineIds 需要删除的业务线ID
     * @return 结果
     */
    public int deleteAxxServicelineByIds(Long[] servicelineIds);

    /**
     * 删除业务线信息
     * 
     * @param servicelineId 业务线ID
     * @return 结果
     */
    public int deleteAxxServicelineById(Long servicelineId);
}

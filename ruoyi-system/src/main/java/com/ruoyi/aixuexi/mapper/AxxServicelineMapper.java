package com.ruoyi.aixuexi.mapper;

import java.util.List;
import com.ruoyi.aixuexi.domain.AxxServiceline;

/**
 * 业务线Mapper接口
 * 
 * @author tianhuiying
 * @date 2021-07-16
 */
public interface AxxServicelineMapper 
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
     * 删除业务线
     * 
     * @param servicelineId 业务线ID
     * @return 结果
     */
    public int deleteAxxServicelineById(Long servicelineId);

    /**
     * 批量删除业务线
     * 
     * @param servicelineIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAxxServicelineByIds(Long[] servicelineIds);
}

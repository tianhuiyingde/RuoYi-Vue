package com.ruoyi.web.controller.aixuexi;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.aixuexi.domain.AxxServiceline;
import com.ruoyi.aixuexi.service.IAxxServicelineService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 业务线Controller
 * 
 * @author tianhuiying
 * @date 2021-07-16
 */
@RestController
@RequestMapping("/aixuexi/serviceline")
public class AxxServicelineController extends BaseController
{
    @Autowired
    private IAxxServicelineService axxServicelineService;

    /**
     * 查询业务线列表
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:serviceline:list')")
    @GetMapping("/list")
    public AjaxResult list(AxxServiceline axxServiceline)
    {
        List<AxxServiceline> list = axxServicelineService.selectAxxServicelineList(axxServiceline);
        return AjaxResult.success(list);
    }

    /**
     * 导出业务线列表
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:serviceline:export')")
    @Log(title = "业务线", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AxxServiceline axxServiceline)
    {
        List<AxxServiceline> list = axxServicelineService.selectAxxServicelineList(axxServiceline);
        ExcelUtil<AxxServiceline> util = new ExcelUtil<AxxServiceline>(AxxServiceline.class);
        return util.exportExcel(list, "业务线数据");
    }

    /**
     * 获取业务线详细信息
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:serviceline:query')")
    @GetMapping(value = "/{servicelineId}")
    public AjaxResult getInfo(@PathVariable("servicelineId") Long servicelineId)
    {
        return AjaxResult.success(axxServicelineService.selectAxxServicelineById(servicelineId));
    }

    /**
     * 新增业务线
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:serviceline:add')")
    @Log(title = "业务线", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AxxServiceline axxServiceline)
    {
        return toAjax(axxServicelineService.insertAxxServiceline(axxServiceline));
    }

    /**
     * 修改业务线
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:serviceline:edit')")
    @Log(title = "业务线", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AxxServiceline axxServiceline)
    {
        return toAjax(axxServicelineService.updateAxxServiceline(axxServiceline));
    }

    /**
     * 删除业务线
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:serviceline:remove')")
    @Log(title = "业务线", businessType = BusinessType.DELETE)
	@DeleteMapping("/{servicelineIds}")
    public AjaxResult remove(@PathVariable Long[] servicelineIds)
    {
        return toAjax(axxServicelineService.deleteAxxServicelineByIds(servicelineIds));
    }
}

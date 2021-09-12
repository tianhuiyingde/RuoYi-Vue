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
import com.ruoyi.aixuexi.domain.AxxDepartment;
import com.ruoyi.aixuexi.service.IAxxDepartmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 研发部门Controller
 * 
 * @author tianhuiying
 * @date 2021-07-16
 */
@RestController
@RequestMapping("/aixuexi/department")
public class AxxDepartmentController extends BaseController
{
    @Autowired
    private IAxxDepartmentService axxDepartmentService;

    /**
     * 查询研发部门列表
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:department:list')")
    @GetMapping("/list")
    public TableDataInfo list(AxxDepartment axxDepartment)
    {
        startPage();
        List<AxxDepartment> list = axxDepartmentService.selectAxxDepartmentList(axxDepartment);
        return getDataTable(list);
    }

    /**
     * 导出研发部门列表
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:department:export')")
    @Log(title = "研发部门", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AxxDepartment axxDepartment)
    {
        List<AxxDepartment> list = axxDepartmentService.selectAxxDepartmentList(axxDepartment);
        ExcelUtil<AxxDepartment> util = new ExcelUtil<AxxDepartment>(AxxDepartment.class);
        return util.exportExcel(list, "研发部门数据");
    }

    /**
     * 获取研发部门详细信息
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:department:query')")
    @GetMapping(value = "/{departmentId}")
    public AjaxResult getInfo(@PathVariable("departmentId") Long departmentId)
    {
        return AjaxResult.success(axxDepartmentService.selectAxxDepartmentById(departmentId));
    }

    /**
     * 新增研发部门
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:department:add')")
    @Log(title = "研发部门", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AxxDepartment axxDepartment)
    {
        return toAjax(axxDepartmentService.insertAxxDepartment(axxDepartment));
    }

    /**
     * 修改研发部门
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:department:edit')")
    @Log(title = "研发部门", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AxxDepartment axxDepartment)
    {
        return toAjax(axxDepartmentService.updateAxxDepartment(axxDepartment));
    }

    /**
     * 删除研发部门
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:department:remove')")
    @Log(title = "研发部门", businessType = BusinessType.DELETE)
	@DeleteMapping("/{departmentIds}")
    public AjaxResult remove(@PathVariable Long[] departmentIds)
    {
        return toAjax(axxDepartmentService.deleteAxxDepartmentByIds(departmentIds));
    }
}

package com.renqing.feel.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.renqing.common.annotation.Log;
import com.renqing.common.core.controller.BaseController;
import com.renqing.common.core.domain.AjaxResult;
import com.renqing.common.core.page.TableDataInfo;
import com.renqing.common.enums.BusinessType;
import com.renqing.common.utils.poi.ExcelUtil;
import com.renqing.feel.entity.HumanFeel;
import com.renqing.feel.service.IHumanFeelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author renqing
 * @since 2022-06-17
 */
@RestController
@RequestMapping("/feel")
public class HumanFeelController extends BaseController {

    @Autowired
    private IHumanFeelService humanFeelService;

    /**
     * 分页查询
     */
    @PreAuthorize("@ss.hasPermi('human:feel:list')")
    @GetMapping("/list")
    @ApiOperation("分页查询")
    public TableDataInfo list(HumanFeel humanFeel) {

        IPage<HumanFeel> list = humanFeelService.queryPage(humanFeel);
        return getDataTable(list);
    }

    /**
     * 导出人情单列表
     */
    @PreAuthorize("@ss.hasPermi('human:feel:export')")
    @Log(title = "人情单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HumanFeel humanFeel) {
        List<HumanFeel> list = humanFeelService.queryPage(humanFeel).getRecords();
        ExcelUtil<HumanFeel> util = new ExcelUtil<HumanFeel>(HumanFeel.class);
        util.exportExcel(response, list, "人情单数据");
    }

    /**
     * 获取人情单详细信息
     */
    @PreAuthorize("@ss.hasPermi('human:feel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(humanFeelService.getById(id));
    }

    /**
     * 新增人情单
     */
    @PreAuthorize("@ss.hasPermi('human:feel:add')")
    @Log(title = "人情单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HumanFeel humanFeel) {

        humanFeel.setId(UUID.randomUUID().toString());
        humanFeel.setCreateTime(LocalDateTime.now());
        return toAjax(humanFeelService.save(humanFeel));
    }

    /**
     * 修改人情单
     */
    @PreAuthorize("@ss.hasPermi('human:feel:edit')")
    @Log(title = "人情单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HumanFeel humanFeel) {

        return toAjax(humanFeelService.updateById(humanFeel));
    }

    /**
     * 删除人情单
     */
    @PreAuthorize("@ss.hasPermi('human:feel:remove')")
    @Log(title = "人情单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(humanFeelService.removeBatchByIds(Arrays.asList(ids)));
    }

}

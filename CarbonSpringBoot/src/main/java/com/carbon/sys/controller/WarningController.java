package com.carbon.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.common.vo.Result;
import com.carbon.sys.entity.Warning;
import com.carbon.sys.service.IWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang
 * @since 2023-08-10
 */
@RestController
@RequestMapping("/sys")
@CrossOrigin
public class WarningController {
    @Autowired
    private IWarningService warningService;

    @GetMapping("/all")
    public Result<List<Warning>> getAllData(){
        List<Warning> list = warningService.list();
        return Result.success(list,"查询成功");
    }

    @GetMapping("/list")
    public  Result<Map<String,Object>> getCarbonList(@RequestParam(value = "monitorName",required = false) String monitorName,
                                                 @RequestParam(value = "monitorAddr",required = false) String monitorAddr,
                                                 @RequestParam(value = "pageNo") Long pageNo,
                                                 @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<Warning> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(monitorName),Warning::getMonitorName,monitorName);
        wrapper.eq(StringUtils.hasLength(monitorAddr),Warning::getMonitorAddr,monitorAddr);

        Page<Warning> page = new Page<>(pageNo,pageSize);
        warningService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);

    }

    @PostMapping
    public Result<?> addCarbon(@RequestBody Warning warning){
        warningService.save(warning);
        return Result.success("新增成功");
    }

    @PutMapping
    public Result<?> updataCarbon(@RequestBody Warning warning){
        warningService.updateById(warning);
        return Result.success("修改成功");

    }

    @GetMapping("/{warningId}")
    public Result<Warning> getCarbonById(@PathVariable("warningId") Integer warningId){
        Warning warning = warningService.getById(warningId);
        return  Result.success(warning);
    }

    @DeleteMapping("/{warningId}")
    public Result<Warning> deleteCarbonById(@PathVariable("warningId") Integer warningId) {
        warningService.removeById(warningId);
        return Result.success("删除用户成功");
    }
}

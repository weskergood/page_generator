package com.foundersc.pg.controllers;

import com.foundersc.pg.pojos.DataGridResult;
import com.foundersc.pg.services.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 通用Controller，其中提供了通用查询、新增、修改、查询多个操作
 */
@RestController
@RequestMapping("/pg/admin/")
@Slf4j
public class CommonController {

    @Resource(type = CommonService.class)
    private CommonService commonService;

    /**
     * 通用查询，此版本不存在田间查询
     * @param eleId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/doQuery/{eleId}")
    public DataGridResult doQuery(@PathVariable("eleId") String eleId, Integer page, Integer rows) {
        return commonService.commonQuery(eleId, page, rows);
    }
}

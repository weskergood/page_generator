package com.foundersc.pg.controllers;

import com.foundersc.pg.pojos.DataGridResult;
import com.foundersc.pg.services.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pg/admin/")
@Slf4j
public class CommonController {

    @Resource(type = CommonService.class)
    private CommonService commonService;

    @RequestMapping("/doQuery/{eleId}")
    public DataGridResult doQuery(@PathVariable("eleId") String eleId, Integer page, Integer rows) {
        return commonService.commonQuery(eleId, page, rows);
    }
}

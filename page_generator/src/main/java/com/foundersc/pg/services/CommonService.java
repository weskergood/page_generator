package com.foundersc.pg.services;

import com.foundersc.pg.pojos.DataGridResult;

public interface CommonService {

    DataGridResult commonQuery(String eleId, Integer page, Integer rows);
}

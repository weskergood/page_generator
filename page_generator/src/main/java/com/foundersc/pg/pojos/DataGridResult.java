package com.foundersc.pg.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 返回前端数据表格的对象  (obj-->json)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataGridResult {

    private Long total;
    private List<?> rows;
}

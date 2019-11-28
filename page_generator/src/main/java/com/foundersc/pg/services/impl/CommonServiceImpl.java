package com.foundersc.pg.services.impl;

import com.foundersc.pg.PageGenerator;
import com.foundersc.pg.pojos.DataGridResult;
import com.foundersc.pg.services.CommonService;
import com.foundersc.pg.views.ColumnView;
import com.foundersc.pg.views.TableView;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    @Resource(type = JdbcTemplate.class)
    private JdbcTemplate jdbcTemplate;

    /**
     * 通用查询，先分页，后续扩展加条件查询
     *
     * @param eleId
     * @param page
     * @param rows
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public DataGridResult commonQuery(String eleId, Integer page, Integer rows) {
        DataGridResult dataGridResult = new DataGridResult();
        Map<String, TableView> parsedTables = PageGenerator.getParsedTables();
        Map<String, List<ColumnView>> parsedTableColumns = PageGenerator.getParsedTableColumns();
        List<ColumnView> columnViews = parsedTableColumns.get(eleId);
        StringBuilder stringBuilder = new StringBuilder();
        for (ColumnView view : columnViews
        ) {
            String fieldName = view.getFieldName();
            stringBuilder.append(fieldName).append(",");
        }
        String columns = stringBuilder.substring(0, stringBuilder.lastIndexOf(","));
        TableView tableViews = parsedTables.get(eleId);
        String tableName = tableViews.getTableName();
        String sql = "SELECT " + columns + " FROM " + tableName + " LIMIT " + (page - 1) * rows + "," + rows;
        log.info(sql);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        dataGridResult.setRows(maps);
        dataGridResult.setTotal(100L);
        return dataGridResult;
    }
}

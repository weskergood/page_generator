package com.foundersc;

import com.foundersc.pg.annotations.MenuItem;
import com.foundersc.pg.annotations.Table;
import com.foundersc.pg.annotations.TableColumn;

@MenuItem(title = "部门")
@Table(tableName = "tb_dept")
public class Dept {

    @TableColumn(msg = "部门ID", isPrimaryKey = true)
    private Integer id;

    @TableColumn(msg = "部门名称")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

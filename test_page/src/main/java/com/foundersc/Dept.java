package com.foundersc;

import com.foundersc.pg.annotations.MenuItem;
import com.foundersc.pg.annotations.TableColumn;

@MenuItem(title = "部门")
public class Dept {

    @TableColumn(msg = "部门名称")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

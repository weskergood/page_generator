package com.foundersc;

import com.foundersc.pg.annotations.IsCondition;
import com.foundersc.pg.annotations.MenuItem;
import com.foundersc.pg.annotations.Table;
import com.foundersc.pg.annotations.TableColumn;
import com.foundersc.pg.utils.QueryType;

@Table(tableName = "tb_user")
@MenuItem(title = "用户")
public class User {

    @TableColumn(msg = "用户Id", isPrimaryKey = true)
    private Integer id;
    @TableColumn(msg = "姓名")
    @IsCondition(queryType = QueryType.EQUALS)
    private String name;
    @TableColumn(msg = "年龄")
    @IsCondition(queryType = QueryType.BETWEEN_AND)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

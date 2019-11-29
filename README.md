项目名：PageGenerator
完成进度：目前仅完成了分页查询操作
后续更新：
  1、增加
  2、删除
  3、修改
  4、带条件查询
  5、代码优化

核心用法：
```java
// 初始化数据源 指定连接数据库信息 
PageGenerator.initDataSource("jdbc:mysql://localhost:3306/test?useSSL=false", "com.mysql.jdbc.Driver", "root",
    "123456");
// 初始化基本信息，设置对那些实体操作
PageGenerator.initBaseData(User.class, Dept.class, Role.class);
// 运行内置web容器
PageGenerator.run();
```

实体类配置：
```java
// 指定该类对应的数据库表名，其中字段名称与属性名称默认保持一致
@Table(tableName = "tb_user")
// 指定主页中左侧菜单显示文字
@MenuItem(title = "用户")
public class User {

    // 指定该属性与数据库字段的对应关系，msg表示在查询后显示在数据表格中的列名称，isPersistence表示该属性是不是数据库表中的字段(默认为true)，isPrimaryKey表示该属性对应的数据库表字段是不是主键(默认为false)
    @TableColumn(msg = "用户Id", isPersistence = true,isPrimaryKey = true)
    private Integer id;
    
    @TableColumn(msg = "姓名")
    // 如果配置了该注解，说明该属性将作为条件来查询数据库，queryType表示查询类型，例如：等值查询、范围查询、模糊查询等
    
    @IsCondition(queryType = QueryType.EQUALS)
    private String name;
    
    @TableColumn(msg = "年龄")
    @IsCondition(queryType = QueryType.BETWEEN_AND)
    private int age;

}
```
# page_generator
```java
View view = new DefaultView(Class<T> clazz);
// 创建视图属性(比如：条件查询、分页信息)
view.setQueryConditions(Map<String queryName,String queryExpression>);
view.setPagination(page,pageSize);

PageGenerator pg = new PageGenerator(view);

// 调用run()方法，会启动spring容器，这时用户可以访问
pg.run();
```

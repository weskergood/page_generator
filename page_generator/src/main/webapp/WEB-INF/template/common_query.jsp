<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(function () {
        $(".queryTable").datagrid({
            fitColumns: true,
            title: "检索列表",
            striped: true,
            loadMsg: "正在加载数据中...",
            remoteSort: false,
            pagination: true,
            rownumbers: false,
            pageNumber: 1,
            pageSize: 50,
            fit: true,
        });
    });

</script>

<table class="easyui-datagrid queryTable" style="width:400px;height:250px"
       data-options="url:'/pg/admin/doQuery'">
    <thead>
    <tr>
        <c:forEach items="${tableColumns}" var="col">
            <th data-options="field:'${col.fieldName}',width:100,align:'center',halign:'center'">${col.columnName}</th>
        </c:forEach>
    </tr>
    </thead>
</table>
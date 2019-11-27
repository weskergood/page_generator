<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../../common/common.jsp"/>
    <title>管理后台首页</title>
    <link href="../../css/default.css" rel="stylesheet" type="text/css"/>
</head>
<body class="easyui-layout">
<div data-options="region:'west',title:'导航菜单',split:true,collapsible:false,minWidth:200,maxWidth:250"
     style="width:200px;">
    <div class="easyui-accordion" fit="true" border="false">
        <c:forEach items="${menuItems}" var="menu">
            <div title="${menu.title}" style="overflow:auto;" id="div_eleId_${menu.eleId}">
                <ul>
                    <li>
                        <div><a href="javascript:void(0)" role="query">${menu.title}列表</a></div>
                    </li>
                    <li>
                        <div><a href="javascript:void(0)" role="add">新增${menu.title}</a></div>
                    </li>
                    <li>
                        <div><a href="javascript:void(0)" role="update">修改${menu.title}</a></div>
                    </li>
                </ul>
            </div>
        </c:forEach>
    </div>
</div>
<div data-options="region:'center',title:null" style="padding:5px;overflow: hidden;">
    <div id="tabs">
        <div title="首页" style="display: block;"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $(".panel-title").css("text-align", "center");
        InitLeftMenu();
        $("#tabs").tabs({
            fit: true,
            justified: false,
            tabWidth: 150,
            onClose(title, index) {
                let $div = $('.easyui-accordion li div>a');
                if ($div.length > 1) {
                    $($div).each(function (i, n) {
                        if ($(n).text() === title) {
                            if ($(n).parent().hasClass("selected")) {
                                $(n).parent().removeClass("selected");
                            }
                        }
                    })
                }
            },
            onSelect(title, index) {
                if (title !== "首页") {
                    let $div = $('.easyui-accordion li div>a');
                    if ($div.length > 1) {
                        $($div).each(function (i, n) {
                            if ($(n).text() === title) {
                                if (!$(n).parent().hasClass("selected")) {
                                    $(n).parent().addClass("selected");
                                }
                            } else {
                                $(n).parent().removeClass("selected");
                            }
                        })
                    }
                }
            }
        });
        $(".easyui-accordion div ul li div a").click(function () {
            let $tabs = $('#tabs');
            let aName = $(this).html();
            let aId = $(this).parents("div[id^='div_eleId_']").attr('id');
            let role = $(this).attr("role");
            aId = aId.substring(aId.lastIndexOf("_") + 1);
            if ($tabs.tabs("exists", aName)) {
                $tabs.tabs("select", aName);
            } else {
                $tabs.tabs('add', {
                    title: aName,
                    selected: true,
                    closable: true,
                    href: "/pg/views/" + role + "?id=" + aId
                });
            }
        });
    });

    function InitLeftMenu() {
        $('.easyui-accordion li a').click(function () {
            $('.easyui-accordion li div').removeClass("selected");
            $(this).parent().addClass("selected");
        }).hover(function () {
            $(this).parent().addClass("hover");
        }, function () {
            $(this).parent().removeClass("hover");
        });
    }
</script>
</html>
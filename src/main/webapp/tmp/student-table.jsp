<%@ page import="java.util.List" %>
<%@ page import="demo0908.entity.Student" %>
<%@ page import="demo0908.dao.StudentDao" %><%--
  Created by IntelliJ IDEA.
  User: 86131
  Date: 2021/10/12
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh">
<head>
    <title>Title</title>
    <script src="../js/jquery-3.6.0.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../css/bulma.min.css">
</head>
<body>

<div class="section">
    <div class="container is-full">
        <div class="columns">
            <div class="column">
                <table class="table is-striped is-bordered container">
                    <thead>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>年龄</th>
                    <th>号码</th>
                    <th>操作</th>
                    </thead>
                    <tbody>

                    <c:forEach items="<%=(new StudentDao()).queryAllStudent()%>" var="student">
                        <tr>
                            <td class="has-text-centered is-vcentered">${student.studentId}</td>
                            <td class="has-text-centered is-vcentered">${student.name}</td>
                            <td class="has-text-centered is-vcentered">${student.age}</td>
                            <td class="has-text-centered is-vcentered">${student.telephone}</td>
                            <td>
                                <div class="field is-grouped is-grouped-centered">
                                    <div class="control">
                                        <button class="button is-info" id="edit">修改</button>
                                    </div>
                                    <div class="control">
                                        <button class="button is-danger" id="delete">删除</button>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script>

    $(document).on("click", "#edit", function () {
        if ($(this).text() == "确定") {
            var student = {};
            student["studentId"] = $(this).parents("tr").children('td').eq(0).text();
            student["name"] = $(this).parents("tr").children('td').eq(1).children('input').val();
            student["age"] = $(this).parents("tr").children('td').eq(2).children('input').val();
            student["telephone"] = $(this).parents("tr").children('td').eq(3).children('input').val();
            // console.log(student);
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/edit",
                data: student,
                dataType: "json",
                success: function (res) {
                    alert(res);
                }
            });
        }


        // 修改样式 以及添加和删除输入框
        str = $(this).text() == "修改" ? "确定" : "修改";
        if (str == "确定") {
            $(this).addClass("is-success");
        } else {
            $(this).removeClass("is-success");
        }
        $(this).text(str);   // 按钮被点击后，在“编辑”和“确定”之间切换

        var inputName = ['studentId','name','age','telephone'];
        $(this).parents("td").eq(0).siblings("td").each(function (i) {  // 获取当前行的其他单元格
            if (i > 0) {
                var obj_text = $(this).find("input:text");    // 判断单元格下是否有文本框
                // console.log(obj_text.text())
                // console.log(obj_text.val())
                if (!obj_text.length)   // 如果没有文本框，则添加文本框使之可以编辑
                    $(this).html("<input type='text' class='input' name=" + inputName[i] + " value='" + $(this).text() + "'>");
                else
                    $(this).html(obj_text.val());
            }
        });

    })

    $(document).on("click", "#delete", function () {
        // console.log($(this).text());
        var student = {};
        student["studentId"] = $(this).parents("tr").children("td").eq(0).text();
        console.log(student["studentId"])
        // console.log($(this).parents("tr").find(".student").text());
        // console.log($(this).parents("tr").eq(1));
        var msg = "您确定要删除该学生吗？";
        if (confirm(msg) == true) {
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/delete",
                data: student,
                dataType: "json",
                success: function (res) {
                    alert(res);
                    location.reload();
                }
            });
        }
    })
</script>

</body>
</html>

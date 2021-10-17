<%@ page language="java" import="java.util.*" contentType="text/html; UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ page import="demo0908.entity.User" %>
<% User user = (User) session.getAttribute("user"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../css/bulma.min.css">

    <title>Sunrise</title>
</head>
<body style="background: #f8f8f8">

<nav class="navbar">
    <div class="container">
        <div class="navbar-brand">
            <a class="navbar-item">
                <img src="https://bulma.io/images/bulma-type-white.png" alt="Logo">
            </a>
            <span class="navbar-burger" data-target="navbarMenuHeroA">
                        <span></span>
                        <span></span>
                        <span></span>
                    </span>
        </div>
        <div id="navbarMenuHeroA" class="navbar-menu">
            <div class="navbar-end">
                <a class="navbar-item">
                    Home
                </a>
                <a class="navbar-item">
                    Examples
                </a>
                <a class="navbar-item" href="profile.jsp">
                    用户信息
                </a>
                <span class="navbar-item">
                            <a class="button is-primary" href="../signout">
                                <span class="icon">
                                   <svg t="1632733295260" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                        xmlns="http://www.w3.org/2000/svg" p-id="2731" width="64" height="64"><path
                                           d="M42.663751 53.911623V801.423209l341.310011 167.028587V202.381477l-341.310011-148.469854zM8.276768 4.464335A21.331876 21.331876 0 0 1 22.313142 0.027305H618.624394a21.331876 21.331876 0 0 1 21.331876 21.331875V298.673564a21.331876 21.331876 0 1 1-42.663752 0V42.691056H123.85287l289.942854 126.156713a21.331876 21.331876 0 0 1 12.799125 19.539998V767.974828h170.655006v-255.982507a21.331876 21.331876 0 1 1 42.663751 0v277.314383a21.331876 21.331876 0 0 1-21.331876 21.331876H426.637513v191.986881a21.331876 21.331876 0 0 1-30.675237 19.198688l-383.973762-187.933825a21.331876 21.331876 0 0 1-11.94585-19.156024V21.35918C0 14.276998 3.327773 8.304073 8.276768 4.464335z"
                                           p-id="2732"></path><path
                                           d="M490.63314 426.664818h511.965016a21.331876 21.331876 0 1 0 0-42.663751h-511.965016a21.331876 21.331876 0 1 0 0 42.663751z"
                                           p-id="2733"></path><path
                                           d="M972.434884 405.332942l-198.258453 198.215789a21.331876 21.331876 0 0 0 30.163272 30.205936l213.318757-213.318757a21.331876 21.331876 0 0 0 0-30.205936l-213.318757-213.318756a21.331876 21.331876 0 0 0-30.163272 30.205936L972.434884 405.332942z"
                                           p-id="2734"></path></svg>
                                </span>
                                <span>登出</span>
                            </a>
                        </span>
            </div>
        </div>
    </div>
</nav>


<div class="section">
    <div class="container">
        <div class="tile is-ancestor">
            <div class="tile is-parent is-2" style="max-height: 150px">
                <article class="tile is-child box">
                    <figure class="image container is-64x64">
                        <img src="<%=request.getContextPath()%>/userAvatarServlet">
                    </figure>
                    <label class="label has-text-centered">
                        <%= user.getUserName()%>
                    </label>
                </article>
            </div>
            <div class="tile is-parent">
                <div class="box">
                    <div class="media">
                        <div class="media-content">
                            <form name="thisform" method="post" enctype="multipart/form-data">
                                <div class="content" style="border-bottom: 2px solid #e2d8d8">
                                    <p class="label">个人资料</p>
                                </div>
                                <div class="level">
                                    <figure class="image is-128x128">
                                        <img id="avatar" src="<%=request.getContextPath()%>/userAvatarServlet">
                                    </figure>
                                    <div class="file" style="margin-left: 5px">
                                        <label class="file-label">
                                            <input class="file-input" type="file" name="avatar"
                                                   onchange="refresh(this)">
                                            <span class="file-cta">
                                                <span class="file-icon">
                                                <svg t="1633921397809" class="icon" viewBox="0 0 1024 1024"
                                                     version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2459"
                                                     width="64" height="64"><path
                                                        d="M782.72512 327.9104l-242.54464-242.54464a35.84512 35.84512 0 0 0-13.76256-10.7008c-0.22016-0.09728-0.44032-0.2048-0.67072-0.30208a33.97632 33.97632 0 0 0-13.34272-2.73408l-0.08704 0.00512-0.32256-0.01536c-0.09216 0-0.1792 0.01536-0.27136 0.01536-0.09728 0-0.18944-0.01536-0.2816-0.01536a33.7664 33.7664 0 0 0-20.21376 6.66624 35.96288 35.96288 0 0 0-6.79936 6.27712l-243.3536 243.36384c-6.97856 6.97856-10.85952 15.97952-11.0848 25.4208a35.4304 35.4304 0 0 0 9.3696 24.9344l0.512 0.512a34.49344 34.49344 0 0 0 24.01792 9.57952 36.5568 36.5568 0 0 0 26.33728-11.29472L476.16 191.14496v499.99872a35.84 35.84 0 1 0 71.68 0v-499.8144l185.73312 185.73312c6.97856 6.97856 15.97952 10.85952 25.41568 11.0848l0.86016 0.01024c9.1136 0 17.60768-3.43552 24.07936-9.37984l0.512-0.512c13.30176-13.83424 12.76928-36.38272-1.7152-50.3552z"
                                                        fill="#666666" p-id="2460"></path><path
                                                        d="M880.64 747.57632v61.44c0 39.58784-32.09216 71.68-71.68 71.68H215.04c-39.58784 0-71.68-32.09216-71.68-71.68v-61.44a35.84 35.84 0 1 0-71.68 0v61.44c0 79.17568 64.18432 143.36 143.36 143.36h593.92c79.17568 0 143.36-64.18432 143.36-143.36v-61.44a35.84 35.84 0 1 0-71.68 0z"
                                                        fill="#666666" p-id="2461"></path></svg>
                                                </span>
                                                <span class="file-label">
                                                    更新头像
                                                </span>
                                            </span>
                                        </label>
                                    </div>
                                </div>
                                <div class="content" style="border-bottom: 2px solid #e2d8d8">
                                    <p class="label">基本信息</p>
                                </div>
                                <div class="field">
                                    <div class="level">
                                        <label class="content">账号</label>
                                        <div class="control">
                                            <input class="input" type="text" placeholder="username"
                                                   value="${user.userId}" disabled>
                                            <input name="userid" value="${user.userId}" type="hidden">
                                        </div>
                                    </div>
                                </div>
                                <div class="field">
                                    <div class="level">
                                        <label class="content">用户名</label>
                                        <div class="control">
                                            <input class="input" type="text" name="username" placeholder="username"
                                                   value="${user.userName}">
                                        </div>
                                    </div>
                                </div>
                                <div class="field">
                                    <div class="level">
                                        <label class="content">更新密码</label>
                                        <div class="control">
                                            <input class="input" type="password" name="password" placeholder="password"
                                                   value="${user.password}">
                                        </div>
                                    </div>
                                </div>
                                <div class="field">
                                    <div class="level">
                                        <label class="content">确认密码</label>
                                        <div class="control">
                                            <input class="input" type="password" name="repassword"
                                                   placeholder="repassword" value="${user.password}">
                                        </div>
                                    </div>
                                </div>
                                <div class="field is-grouped is-grouped-centered">
                                    <div class="control">
                                        <button class="button is-success"
                                                onclick="formSub('<%=request.getContextPath()%>/UpdataInfoServlet');">更新
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>


<script>
    function formSub(url) {
        var password = document.getElementsByName("password")[0].value;
        var repassword = document.getElementsByName("repassword")[0].value;
        if (password.length == 0 || repassword.length == 0) {
            alert("密码不能为空");
        } else if (password == repassword) {
            document.thisform.action = url;
            document.thisform.submit();
        } else {
            alert("两次密码不一致")
        }
    }

    function refresh(obj) {
        var rd = new FileReader();
        var files = obj.files[0];
        rd.readAsDataURL(files);
        rd.onloadend = function (e) {
            document.getElementById("avatar").src = rd.result;
        }
    }
</script>

</html>
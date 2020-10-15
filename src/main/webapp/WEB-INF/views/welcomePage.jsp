<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 03.10.2020
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="components/header.jsp" %>
<div class="main">
    <div class="container">
        <div class="content add-post">
            <form method="post" class="update">
                <h2>Редактирование аккаунта</h2>
                <div class="form-group">
                    <label for="update-login">Логин</label>
                    <input type="text" id="update-login" name="login" value="" />

                </div>
                <div class="form-group">
                    <label for="update-password">Пароль</label>
                    <input type="password" id="update-password" name="password" value="" />
                </div>
                <div class="form-group">
                    <label for="update-password-confirm">Повтори пароль</label>
                    <input type="password" id="update-password-confirm" name="passwordConfirm" value="" />
                </div>
                <input type="hidden" id="user-id" name="userId" value="">

                <div class="buttons">
                    <button class="update-button">Редактировать</button>
                </div>
            </form>

        </div>

<div class="sidebar">
        <%@ include file="components/sidebar.jsp" %>
    </div>
    </div>
</div>
</body>

</html>

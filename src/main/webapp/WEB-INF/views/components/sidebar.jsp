<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="register" style="display: none;">
                    <h2>Регистрация</h2>
                    <div class="form-group">
                        <label for="nameSignUp">Имя</label>
                        <input type="text" id="nameSignUp" name="name" class="form-control" placeholder="Name" required>
                    </div>
                    <div class="form-group">
                        <label for="emailSignUp">E-mail</label>
                        <input type="email" id="emailSignUp" name="email" class="form-control" placeholder="Email" />
                    </div>
                    <div class="form-group">
                        <label for="phoneSignUp">Телефон</label>
                        <input type="text" id="phoneSignUp" name="phone" class="form-control" placeholder="Number" />
                    </div>
                    <div class="form-group">
                        <label for="passwordSignUp">Пароль</label>
                        <input type="password" id="passwordSignUp" name="password" class="form-control"placeholder="Password" />
                    </div>
                    <div class="buttons">
                        <button class="switch-button secondary-inverse">Войти</button>
                        <button class="btn btn-primary btn-block" id="signUpBtn"
                                onclick="signUp()">Регистрация
                        </button>
                    </div>
                </div>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a href="/train/all">Управление поездами????</a>
                    <a href="/account/current">Account</a>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    user   <a href="/ticket">Билеты</a>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <div class="box auth" >
                    <form id="loginForm"  action="/j_spring_security_check" method="post" class="login">
                        <h2>Вход</h2>
                        <div class="form-group">
                            <label for="email">E-mail</label>
                            <input type="email" id="email" name="email"  placeholder="Email" />
                        </div>
                        <div class="form-group">
                            <label for="password">Пароль</label>
                            <input type="password" id="password" name="password"  placeholder="Password" />
                        </div>
                        <c:if test="${not empty error}">
                            <div id="loginMistake"><p class='alert alert-danger' role='alert'>${error}</p></div>
                        </c:if>

                        <div class="buttons">
                            <button class="switch-button secondary-inverse">Регистрация</button>
                            <button class="btn btn-primary btn-block" id="loginBtn"
                                    type="submit">Войти
                            </button>
                        </div>
                    </form>
                </sec:authorize>
            </div>



<script>
    $(function() {
        function removeErrors() {
            $('form.update p.error, form.login p.error, form.register p.error').remove();
            $('form.update input, form.login input, form.register input').removeClass('error');
        }

        var flag = true;
        $('.switch-button').on('click', function (e) {
            e.preventDefault();

            $('input').val('');
            removeErrors();

            if (flag) {
                flag = false;
                $('.register').show('slow');
                $('.login').hide();
            } else {
                flag = true;
                $('.login').show('slow');
                $('.register').hide();
            }
        });
    })

    function signUp() {
        var name = $('#nameSignUp').val();
        var phone = $('#phoneSignUp').val();
        var email = $('#emailSignUp').val();
        var password = $('#passwordSignUp').val();
        var accountDTO = ({
            "name": name,
            "phone": phone,
            "email": email,
            "password": password
        });
        $.ajax({
            type: "Post",
            url: "/account/registration",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(accountDTO),
            success: function (res) {
                alert("cool")
                $(location).attr('href', '/ticket');
            },
            error: function (res) {
                // if (res.responseJSON === "Unique fields error") {
                //     $('#uniqueFieldSignUpMistake').show();
                // } else if (res.responseJSON === "Empty fields error") {
                //     $('#emptyFieldSignUpMistake').show();
                // } else {
                //     $("#signUpModal").modal("hide");
                //     $("#errorForm")[0].reset();
                //     $("#errorModal").modal();
                // }
                alert("error");
            }
        })
    };
</script>

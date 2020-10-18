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
    <div class="container" style="    justify-content: center;">
        <div class="content add-post">
            <h2>Поиск поездов</h2>
            <form method="post" class="search">
                <div class="form-group">
                    <label for="fromPoint">Откуда</label>
                    <input type="text" id="fromPoint" name="fromPoint" value="Минск"/>

                </div>
                <div class="form-group">
                    <label for="toPoint">Куда</label>
                    <input type="text" id="toPoint" name="toPoint" value="Москва"/>
                </div>
                <div class="form-group">
                    <label for="exitDate">Дата отправления</label>
                    <input type="date" id="exitDate" name="exitDate" value=""/>
                </div>
                <div class="buttons" style="align-items: flex-end; margin-bottom: 16px ">
                    <button class="update-button" onclick="search(); return false;">Поиск</button>
                </div>
            </form>
            <hr>
            <br>
            <div id="table"></div>
        </div>

        <div class="sidebar">
            <%@ include file="components/sidebar.jsp" %>
        </div>
    </div>
</div>
<script>
    function search() {
        var fromPoint = $('#fromPoint').val();
        var toPoint = $('#toPoint').val();
        var exitDate = $('#exitDate').val();
        $.ajax({
            type: "GET",
            url: "/train/search?from=" + fromPoint + "&to=" + toPoint + "&date=" + exitDate,
            success: function (data) {
                $('#table')
                var html = "            <table  class=\"table\">\n" +
                    "                <thead>\n" +
                    "                <tr>\n" +
                    "                    <th scope=\"col\">Откуда</th>\n" +
                    "                    <th scope=\"col\">Куда</th>\n" +
                    "                    <th scope=\"col\">Дата отправления</th>\n" +
                    "                    <th scope=\"col\">Дата прибытия</th>\n" +
                    "                    <th scope=\"col\">Тип</th>\n" +
                    "                    <th scope=\"col\">Стоимость</th>\n" +
                    "                    <th scope=\"col\">Количество мест</th>\n" +
                    "                    <th scope=\"col\">Маршрут</th>\n" +
                    "                    <th scope=\"col\">Длительность</th>";
                <sec:authorize access="hasRole('ROLE_USER')">
                html += " <th scope=\"col\">Забронировать</th>";
                </sec:authorize>
                html += "  <tbody id=\"tBody\">"
                for (var i = 0; i < data.length; i++) {
                    html += "<tr id='row_" + data[i].id + "'><td>" + data[i].fromPoint + "</td><td>" + data[i].toPoint + "</td><td>" +
                        data[i].exitDate + " " + data[i].exitTime + "</td><td>" + data[i].arrivalDate + " " + data[i].arrivalTime + "</td><td>" +
                        data[i].type + "</td><td>" + data[i].cost + "</td><td>" +
                        data[i].seatingCapacity + "</td><td>" + data[i].routePoints + "</td><td>" + data[i].duration;
                    <sec:authorize access="hasRole('ROLE_USER')">
                    html += "  <th>\n" +
                        "                                                <a style='color: #333333' class=\"btn btn-primary btn-block\" id=\"signUpBtn\"\n" +
                        "                                                        href='/ticket/new?trainId=" + data[i].id + "'>Забронировать\n" +
                        "                                                </a>\n" +
                        "                                            </th>";
                    </sec:authorize>
                    html += "  </tr>";
                }
                html += "</tbody> </table>";

                $('#table').append(html);
            },
            error: function (res) {
                alert("К сожалению, по вашему запросу ничгео не найдено");
            }
        })
    };

</script>
</body>

</html>

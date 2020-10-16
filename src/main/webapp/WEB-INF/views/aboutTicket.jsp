<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 16.10.2020
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="components/header.jsp" %>
<div class="main">
    <div class="container" style="    justify-content: center;" >
        <div class="content add-post" >
            <h2 style="color: #333333; padding-left: 80px">Ваш билет:</h2>
            <div  style="display:flex;  justify-content: space-around">
            <div>
                <h4><b>Откуда:</b></h4>
                <h4><b>Куда:</b></h4>
                <h4><b>Дата отправления:</b></h4>
                <h4><b>Дата прибытия:</b></h4>
                <h4><b>ФИ:</b></h4>
                <h4><b>Паспорт:</b></h4>
                <h4><b>Номер вагона:</b></h4>
                <h4><b>Номер вашего места:</b></h4>
            </div>
            <div>
                <h4>Минск</h4>
                <h4>Москва</h4>
                <h4>22.10.2020 9.00</h4>
                <h4>23.10.2020 10.00</h4>
                <h4>Лещинская Дарья</h4>
                <h4>MP389202</h4>
                <h4>2</h4>
                <h4>5</h4>
            </div>



            </div>
        </div>
        <div class="sidebar">
            <%@ include file="components/sidebar.jsp" %>
        </div>
    </div>
</div>
</body>
</html>

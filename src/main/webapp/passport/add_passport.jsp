<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить паспорт</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .form-container {
            background-color: #f4f4f4;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            max-width: 90%;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }

        input[type="text"],
        input[type="date"] {
            width: calc(100% - 20px);
            padding: 10px;
            border: 1px solid #000000;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }


        button {
            background-color: #259925;
            color: #ffffff;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #218721;
        }
    </style>
</head>
<body>
<div class="form-container">
    <div class="form-group">
        <h2>Введите данные о паспорте:</h2>
    </div>
    <form action="<%=ConstantAction.ADD_PASSPORT%>"
          method="post">
        <div class="form-group">
            <label for="series">Серия:</label>
            <input type="text"
                   id="series"
                   name="<%=ConstantParamAndAttribute.SERIES%>"
                   maxlength="2"
                   required>
        </div>
        <div class="form-group">
            <label for="number">Номер:</label>
            <input type="text"
                   id="number"
                   name="<%=ConstantParamAndAttribute.NUMBER%>"
                   required>
        </div>
        <div class="form-group">
            <label for="identification_number">Идентификационный номер:</label>
            <input type="text"
                   id="identification_number"
                   name="<%=ConstantParamAndAttribute.IDENTIFICATION_NUMBER%>"
                   maxlength="14"
                   required>
        </div>
        <div class="form-group">
            <label for="registration_city">Адрес регистрации(населенный пункт):</label>
            <input type="text"
                   id="registration_city"
                   name="<%=ConstantParamAndAttribute.REGISTRATION_CITY%>">
        </div>
        <div class="form-group">
            <label for="registration_street">Адрес регистрации(улица):</label>
            <input type="text"
                   id="registration_street"
                   name="<%=ConstantParamAndAttribute.REGISTRATION_STREET%>">
        </div>
        <div class="form-group">
            <label for="registration_house">Адрес регистрации(дом):</label>
            <input type="text"
                   id="registration_house"
                   name="<%=ConstantParamAndAttribute.REGISTRATION_HOUSE%>">
        </div>
        <div class="form-group">
            <label for="registration_apartment">Адрес регистрации(квартира):</label>
            <input type="text"
                   id="registration_apartment"
                   name="<%=ConstantParamAndAttribute.REGISTRATION_APARTMENT%>">
        </div>
        <div class="form-group">
            <label for="date_issue">Дата выдачи:</label>
            <input type="date"
                   id="date_issue"
                   name="<%=ConstantParamAndAttribute.DATE_ISSUE%>"
                   required>
        </div>
        <div class="form-group">
            <label for="date_end_action">Дата окончания действия:</label>
            <input type="date"
                   id="date_end_action"
                   name="<%=ConstantParamAndAttribute.DATE_END_ACTION%>"
                   required>
        </div>
        <div class="form-group">
            <label for="publisher">Кем выдан:</label>
            <input type="text"
                   id="publisher"
                   name="<%=ConstantParamAndAttribute.PUBLISHER%>"
                   required>
        </div>
        <div class="form-group">
            <button name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.ID)%>">Сохранить
            </button>
        </div>
        <div class="form-group">
            <a href=<%= ConstantAction.EMPLOYEE %>> Вернуться к просмотру сотрудника </a><br/>
        </div>
    </form>
</div>
</body>
</html>

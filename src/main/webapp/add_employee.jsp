<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление сотрудника</title>
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
        <h2>Введите данные о новом сотруднике:</h2>
    </div>
    <form action="<%= ConstantAction.ADD_EMPLOYEE %>" method="post">
        <div class="form-group">
            <label for="surname">Фамилия:</label>
            <input type="text" id="surname" name="<%= ConstantParamAndAttribute.SURNAME %>" required>
        </div>
        <div class="form-group">
            <label for="name">Имя:</label>
            <input type="text" id="name" name="<%= ConstantParamAndAttribute.NAME %>" required>
        </div>
        <div class="form-group">
            <label for="patronymic">Отчество:</label>
            <input type="text" id="patronymic" name="<%= ConstantParamAndAttribute.PATRONYMIC %>">
        </div>
        <div class="form-group">
            <label for="birthday">Дата рождения:</label>
            <input type="date" id="birthday" name="<%= ConstantParamAndAttribute.BIRTHDAY %>" required>
        </div>
        <div class="form-group">
            <label for="residence_city">Город, в котором проживает:</label>
            <input type="text" id="residence_city" name="<%= ConstantParamAndAttribute.RESIDENCE_CITY %>">
        </div>
        <div class="form-group">
            <label for="residence_street">Улица, на которой проживает:</label>
            <input type="text" id="residence_street" name="<%= ConstantParamAndAttribute.RESIDENCE_STREET %>">
        </div>
        <div class="form-group">
            <label for="residence_house">Дом, в котором проживает:</label>
            <input type="text" id="residence_house" name="<%= ConstantParamAndAttribute.RESIDENCE_HOUSE %>">
        </div>
        <div class="form-group">
            <label for="residence_apartment">Квартира, в которой проживает:</label>
            <input type="text" id="residence_apartment" name="<%= ConstantParamAndAttribute.RESIDENCE_APARTMENT %>">
        </div>
        <div class="form-group">
            <label for="phone">Номер телефона:</label>
            <input type="text" id="phone" name="<%= ConstantParamAndAttribute.PHONE %>">
        </div>
        <div class="form-group">
            <button type="submit">Сохранить</button>
        </div>
        <div class="form-group">
            <a href=<%= ConstantAction.LIST_EMPLOYEES %>> Вернуться к списку сотрудников </a><br/>
        </div>
    </form>
</div>
</body>
</html>

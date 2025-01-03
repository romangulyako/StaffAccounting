<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Обновить семейное положение</title>
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
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .button-add {
            background-color: #259925;
            color: #ffffff;
        }

        .button-add:hover {
            background-color: #218721;
        }

        .tabs {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }

        .tab {
            margin: 0 10px;
            padding: 10px 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            background-color: #f9f9f9;
            cursor: pointer;
            text-decoration: none;
            color: #333;
            transition: background-color 0.3s ease;
        }

        .tab:hover {
            background-color: #e9e9e9;
        }
    </style>
</head>
<body>
<div class="form-container">
    <div class="form-group">
        <h2>Заполните информацию:</h2>
    </div>
    <form id="save"
          action="<%= ConstantAction.ADD_MARITAL_STATUS %>"
          method="post">
        <div class="form-group">
            <label for="marital_status">Семейное положение:</label>
            <input type="text"
                   id="marital_status"
                   name="<%= ConstantParamAndAttribute.STATUS %>"
                   required>
        </div>
        <div class="form-group">
            <label for="registration_date">Дата регистрации:</label>
            <input type="date"
                   id="registration_date"
                   name="<%= ConstantParamAndAttribute.REGISTRATION_DATE %>"
                   required>
        </div>
        <div class="form-group">
            <label for="document">Подтверждающий документ:</label>
            <input type="text"
                   id="document"
                   name="<%= ConstantParamAndAttribute.DOCUMENT %>"
                   required>
        </div>
    </form>
    <form class="tabs" action="<%=ConstantAction.MARITAL_STATUSES%>" method="get">
        <div class="form-group">
            <button class="tab, button-add" form="save"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                Сохранить
            </button>
            <input form="save"
                   type="hidden"
                   name="<%=ConstantParamAndAttribute.IS_CURRENT%>"
                   value="true"
                   required/>
        </div>
        <div class="form-group">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_ID)%>">
                Вернуться к просмотру семейного положения
            </button>
        </div>
    </form>
</div>
</body>
</html>

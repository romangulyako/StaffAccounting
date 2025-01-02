<%@ page import="by.itacademy.jd2.dto.RelativeDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить родственника</title>
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
<% RelativeDTO relative = (RelativeDTO) request.getAttribute(ConstantParamAndAttribute.RELATIVE); %>
<div class="form-container">
    <div class="form-group">
        <h2>Введите новые данные о родственнике:</h2>
    </div>
    <form id="save" action="<%= ConstantAction.UPDATE_RELATIVE%>"
          method="post">
        <input name="<%= ConstantParamAndAttribute.ID %>"
               type="hidden"
               value="<%= relative.getId() %>"
               required/>
        <input name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
               type="hidden"
               value="<%= relative.getEmployeeId() %>"
               required/>
        <div class="form-group">
            <label for="surname">Фамилия:</label>
            <input type="text"
                   id="surname"
                   name="<%= ConstantParamAndAttribute.SURNAME %>"
                   value="<%= relative.getPersonData().getSurname() %>"
                   required>
        </div>
        <div class="form-group">
            <label for="name">Имя:</label>
            <input type="text"
                   id="name"
                   name="<%= ConstantParamAndAttribute.NAME %>"
                   value="<%= relative.getPersonData().getName() %>"
                   required>
        </div>
        <div class="form-group">
            <label for="patronymic">Отчество:</label>
            <input type="text"
                   id="patronymic"
                   name="<%= ConstantParamAndAttribute.PATRONYMIC %>"
                   value="<%= relative.getPersonData().getPatronymic() %>">
        </div>
        <div class="form-group">
            <label for="birthday">Дата рождения:</label>
            <input type="date"
                   id="birthday"
                   name="<%= ConstantParamAndAttribute.BIRTHDAY %>"
                   value="<%= relative.getPersonData().getBirthday() %>"
                   required>
        </div>
        <div class="form-group">
            <label for="type_kinship">Тип родства:</label>
            <input type="text"
                   id="type_kinship"
                   name="<%= ConstantParamAndAttribute.TYPE_KINSHIP %>"
                   value="<%= relative.getTypeKinship()%>"
                   required>
        </div>
    </form>
    <form class="tabs" action="<%= ConstantAction.RELATIVES %>" method="get">
        <div class="form-group">
            <button class="tab, button-add" form="save">
                Сохранить
            </button>
        </div>
        <div class="form-group">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.EMPLOYEE_ID%>"
                    value="<%=relative.getEmployeeId()%>">
                Вернуться к просмотру родственников
            </button>
        </div>
    </form>
</div>
</body>
</html>

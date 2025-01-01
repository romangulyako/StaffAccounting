<%@ page import="by.itacademy.jd2.dto.PassportDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Паспортные данные</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: 20px auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .passport-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }

        .passport-header h2 {
            margin: 0;
        }


        .passport-info {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); /* Адаптивная колоночная сетка */
            gap: 15px;
            margin-bottom: 20px;

        }

        .passport-info-item {
            margin-bottom: 10px;
            padding: 10px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 4px;
        }


        .passport-info-item label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
            color: #333;
        }

        .passport-info-item span {
            color: #666;
            font-size: 1.1em;
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

        button {
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .button-add {
            background-color: #259925; /* Зеленый цвет */
            color: #ffffff;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .button-add:hover {
            background-color: #218721; /* Более темный зеленый цвет при наведении */
        }

        .button-edit {
            background-color: #eac431; /* Зеленый цвет */
            color: #000000;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .button-edit:hover {
            background-color: #d1ad1b;
        }

        .button-delete {
            background-color: #e41c1c; /* Зеленый цвет */
            color: #ffffff;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .button-delete:hover {
            background-color: #cd1313; /* Более темный зеленый цвет при наведении */
        }
    </style>
</head>
<body>
<div class="container">
    <% PassportDTO passportDTO = (PassportDTO) request.getAttribute(ConstantParamAndAttribute.PASSPORT);
        if (passportDTO == null) { %>
    <div class="passport-header">
        <h2>Информация о паспорте отсутствует</h2>
        <form name="add_passport"
              method="get"
              action=<%=ConstantAction.ADD_PASSPORT%>>
            <button class="button-add"
                    name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.ID)%>">
                Добавить
            </button>
        </form>
    </div>
    <% } else { %>
    <div class="passport-info">
        <div class="passport-info-item">
            <label>Серия:</label>
            <span><%=passportDTO.getSeries()%></span>
        </div>
        <div class="passport-info-item">
            <label>Номер:</label>
            <span><%=passportDTO.getNumber()%></span>
        </div>
        <div class="passport-info-item">
            <label>Идентификационный номер:</label>
            <span><%=passportDTO.getIdentificationNumber()%></span>
        </div>
        <div class="passport-info-item">
            <label>Адрес регистрации:</label>
            <span><%=passportDTO.getRegistrationAddress().getCity() + ", ул. "
                    + passportDTO.getRegistrationAddress().getStreet() + ", д. "
                    + passportDTO.getRegistrationAddress().getHouse() + ", кв. "
                    + passportDTO.getRegistrationAddress().getApartment()%>
            </span>
        </div>
        <div class="passport-info-item">
            <label>Дата выдачи:</label>
            <span><%=passportDTO.getDateIssue()%></span>
        </div>
        <div class="passport-info-item">
            <label>Дата окончания действия:</label>
            <span><%=passportDTO.getDateEndAction()%></span>
        </div>
        <div class="passport-info-item">
            <label>Кем выдан:</label>
            <span><%=passportDTO.getPublisher()%></span>
        </div>
    </div>
    <div>
        <form action="<%=ConstantAction.UPDATE_PASSPORT%>" method="get">
            <button class="button-edit"
                    name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=passportDTO.getId()%>">
                Изменить паспортные данные
            </button>
        </form>
        <form action="<%=ConstantAction.DELETE_PASSPORT%>" method="post">
            <button class="button-delete" name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=passportDTO.getId()%>">
                Удалить паспорт
            </button>
        </form>
    </div>
    <% } %>
    <div class="tabs">
        <form action="<%=ConstantAction.EMPLOYEE%>" method="get">
            <button class="tab"
                    name="<%=ConstantParamAndAttribute.ID%>"
                    value="<%=request.getAttribute(ConstantParamAndAttribute.ID)%>">
                Вернуться к просмотру сотрудника
            </button>
        </form>
    </div>
</div>
</body>
</html>

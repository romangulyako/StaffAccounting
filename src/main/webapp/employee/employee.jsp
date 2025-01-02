<%@ page import="by.itacademy.jd2.dto.EmployeeDTO" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.repository.embedded.PersonData" %>
<%@ page import="by.itacademy.jd2.repository.embedded.Address" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<% EmployeeDTO employee = (EmployeeDTO) request.getAttribute(ConstantParamAndAttribute.EMPLOYEE); %>
<% PersonData personData = employee.getPersonData(); %>
<% Address homeAddress = employee.getHomeAddress(); %>
<head>
    <title><%=personData.getSurname() + " "
            + personData.getName() + " "
            + personData.getPatronymic() %>
    </title>
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

        .employee-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }

        .employee-header h2 {
            margin: 0;
        }


        .employee-info {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); /* Адаптивная колоночная сетка */
            gap: 15px;
            margin-bottom: 20px;

        }

        .employee-info-item {
            margin-bottom: 10px;
            padding: 10px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 4px;
        }


        .employee-info-item label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
            color: #333;
        }

        .employee-info-item span {
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
    </style>
</head>
<body>
<div class="container">
    <div class="employee-header">
        <h2>Информация о сотруднике</h2>
        <form name="update_employee"
              method="get"
              action="<%= ConstantAction.UPDATE_EMPLOYEE %>">
            <button class="button-edit"
                    name="<%= ConstantParamAndAttribute.ID %>"
                    value="<%= employee.getId() %>">
                Изменить
            </button>
        </form>
    </div>
    <div class="employee-info">
        <div class="employee-info-item">
            <label>Фамилия:</label>
            <span><%=personData.getSurname()%></span>
        </div>
        <div class="employee-info-item">
            <label>Имя:</label>
            <span><%=personData.getName()%></span>
        </div>
        <div class="employee-info-item">
            <label>Отчество:</label>
            <span><%=personData.getPatronymic()%></span>
        </div>
        <div class="employee-info-item">
            <label>Дата рождения:</label>
            <span><%=personData.getBirthday()%></span>
        </div>
        <div class="employee-info-item">
            <label>Домашний адрес:</label>
            <span><%=homeAddress.getCity()%>, ул. <%=homeAddress.getStreet()%>, д. <%=homeAddress.getHouse()%>, кв. <%=homeAddress.getApartment()%></span>
        </div>
        <div class="employee-info-item">
            <label>Мобильный телефон:</label>
            <span><%=employee.getPhone()%></span>
        </div>
    </div>

    <div class="tabs">
        <form action="passport" method="get">
            <button class="tab" name="<%= ConstantParamAndAttribute.ID %>"
                    value="<%= employee.getId() %>">Паспортные данные
            </button>
        </form>
        <form action="relatives" method="get">
            <button class="tab"
                    name="<%= ConstantParamAndAttribute.EMPLOYEE_ID %>"
                    value="<%= employee.getId() %>">Родственники</button>
        </form>
        <form action="marital_status" method="post">
            <button class="tab" >Семейное положение</button>
        </form>
        <form action="education" method="post">
            <button class="tab" >Образование</button>
        </form>
        <form action="career" method="post">
            <button class="tab" >Карьера</button>
        </form>
    </div>
    <div class="tab">
        <a href=<%= ConstantAction.LIST_EMPLOYEES %>> Вернуться к списку сотрудников </a>
    </div>
</div>
</body>
</html>

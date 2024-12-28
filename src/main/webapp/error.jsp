<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ошибка</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .error-container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 30px;
            text-align: center;
            max-width: 600px;
            width: 90%;
        }
        .error-icon{
            font-size: 50px;
            color: #dc3545; /* Красный цвет */
            margin-bottom: 10px;
        }


        h1 {
            color: #dc3545; /* Красный цвет для заголовка */
            margin-bottom: 10px;
        }

        p {
            color: #555;
            font-size: 1.2em;
        }
    </style>
</head>
<body>
<div class="error-container">
    <div class="error-icon">
        &#9888;
    </div>
    <h1>Произошла ошибка!</h1>
    <p>
        <%= request.getAttribute("errorMessage") %>
    </p>
    <a href=<%= ConstantAction.LIST_EMPLOYEES %>> Вернуться к списку сотрудников </a>
</div>
</body>
</html>

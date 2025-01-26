<%@ page import="by.itacademy.jd2.dto.EmployeeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page import="by.itacademy.jd2.service.PageInfo" %>
<%@ page import="by.itacademy.jd2.dto.EmployeeFilterData" %>
<%@ page import="by.itacademy.jd2.dto.DepartmentItemDTO" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Сотрудники</title>
    <style>
        <%@include file="../resources/css/styles.css"%>
    </style>
</head>
<body>
<%@include file="../header.jsp" %>
<main>
    <%@include file="../menu.jsp" %>
    <div>
        <h2>Все сотрудники организации:</h2>
        <div class="filter-container">
            <%EmployeeFilterData filterData = (EmployeeFilterData) request.getAttribute(ConstantParamAndAttribute.EMPLOYEE_FILTER_DATA);%>
            <div class="filter-form">
                <form id="filter"
                      action="<%=ConstantAction.LIST_EMPLOYEES%>"
                      method="get">
                    <div class="filter-inputs">
                        <fieldset>
                            <legend>Фамилия</legend>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.SURNAME%>"
                                    <%if (filterData.getSurname() != null) { %>
                                   value="<%=filterData.getSurname()%>"
                                    <% } %>
                                   placeholder="Введите фамилию"/>
                        </fieldset>
                        <fieldset>
                            <legend>Имя</legend>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.NAME%>"
                                    <%if (filterData.getName() != null) { %>
                                   value="<%=filterData.getName()%>"
                                    <% } %>
                                   placeholder="Введите имя"/>
                        </fieldset>
                        <fieldset>
                            <legend>Отчество</legend>
                            <input type="text"
                                   name="<%=ConstantParamAndAttribute.PATRONYMIC%>"
                                    <%if (filterData.getPatronymic() != null) { %>
                                   value="<%=filterData.getPatronymic()%>"
                                    <% } %>
                                   placeholder="Введите отчество"/>
                        </fieldset>
                        <fieldset>
                            <legend>Минимальный возраст</legend>
                            <input type="number"
                                   name="<%=ConstantParamAndAttribute.AGE%>"
                                    <%if (filterData.getAge() != null) { %>
                                   value="<%=filterData.getAge()%>"
                                    <% } %>
                                   placeholder="Введите возраст"/>
                        </fieldset>
                        <fieldset>
                            <%List<DepartmentItemDTO> departments = (List<DepartmentItemDTO>) request.getAttribute(ConstantParamAndAttribute.DEPARTMENT_ITEMS);%>
                            <legend>Отдел</legend>
                            <select name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                                    required>
                                <%if (filterData.getDepartmentId() != null) { %>
                                <option value="<%=filterData.getDepartmentId()%>"
                                        selected>
                                    <%=departments.stream()
                                            .filter(department -> Objects.equals(department.getId(),
                                                    filterData.getDepartmentId())).findFirst()
                                            .get().getName()%>
                                </option>
                                <% } else { %>
                                <option value="null">
                                    Выберите отдел
                                </option>
                                <% } %>
                                <%
                                    for (DepartmentItemDTO department : departments) { %>
                                <option value="<%=department.getId()%>">
                                    <%=department.getName()%>
                                </option>
                                <% } %>
                            </select>
                        </fieldset>
                    </div>
                </form>
                <div class="apply-filter-buttons">
                    <button class="apply-filter-button"
                            type="submit"
                            form="filter">Применить
                    </button>
                    <form id="clear" action="<%=ConstantAction.CLEAR_FILTER%>"
                          method="post">
                        <input type="hidden"
                               name="<%=ConstantParamAndAttribute.IS_FIRED%>"
                               value="<%=request.getAttribute(ConstantParamAndAttribute.IS_FIRED)%>"/>
                        <button class="clear-filter-button"
                                type="submit"
                                form="clear">
                            Очистить фильтр
                        </button>
                    </form>

                </div>
            </div>
        </div>

        <table>
            <thead>
            <tr>
                <th>ФИО</th>
                <th>Дата рождения</th>
                <th>Должность</th>
                <th colspan="2">Действие</th>
            </tr>
            </thead>
            <tbody>
            <% PageInfo<EmployeeDTO> pageInfo = (PageInfo<EmployeeDTO>) request.getAttribute(ConstantParamAndAttribute.PAGE_INFO);
                List<EmployeeDTO> employees = pageInfo.getItems();
                for (EmployeeDTO employee : employees) {
            %>
            <tr>
                <td>
                    <%= employee.getPersonData().getSurname() + " "
                            + employee.getPersonData().getName()%>
                    <%if (employee.getPersonData().getPatronymic() != null) { %>
                    <%=" " + employee.getPersonData().getPatronymic()%>
                    <% } %>
                </td>
                <td><%= employee.getPersonData().getBirthday() %>
                </td>
                <td>
                    <%if (employee.getPositionName() != null) { %>
                    <%=employee.getPositionName()%>
                    <% } %>
                </td>
                <td>
                    <form name="employee"
                          method="get"
                          action="<%= ConstantAction.EMPLOYEE %>">
                        <button class="button-show"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= employee.getId() %>">
                            Просмотреть
                        </button>
                    </form>
                </td>
                <td>
                    <form name="delete_employee"
                          method="post"
                          action="<%= ConstantAction.DELETE_EMPLOYEE %>">
                        <button class="button-delete"
                                name="<%= ConstantParamAndAttribute.ID %>"
                                value="<%= employee.getId() %>">
                            Удалить
                        </button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <form action="<%=ConstantAction.LIST_EMPLOYEES%>"
              method="get">
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.IS_FIRED%>"
                   value="<%=request.getAttribute(ConstantParamAndAttribute.IS_FIRED)%>"/>
            <%if (filterData.getSurname() != null) {%>
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.SURNAME%>"
                   value="<%=filterData.getSurname()%>"/>
            <% } %>
            <%if (filterData.getName() != null) {%>
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.NAME%>"
                   value="<%=filterData.getName()%>"/>
            <% } %>
            <%if (filterData.getPatronymic() != null) { %>
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.PATRONYMIC%>"
                   value="<%=filterData.getPatronymic()%>"/>
            <% } %>
            <%if (filterData.getAge() != null) { %>
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.AGE%>"
                   value="<%=filterData.getAge()%>"/>
            <% } %>
            <%if (filterData.getDepartmentId() != null) { %>
            <input type="hidden"
                   name="<%=ConstantParamAndAttribute.DEPARTMENT_ID%>"
                   value="<%=filterData.getDepartmentId()%>"/>
            <% } %>
            <% request.setAttribute(ConstantParamAndAttribute.PAGE_NUMBER, pageInfo.getPageNumber());
                request.setAttribute(ConstantParamAndAttribute.PAGE_SIZE, pageInfo.getPageSize());
                request.setAttribute(ConstantParamAndAttribute.TOTAL_PAGES, pageInfo.getTotalPages());%>
            <%@include file="../paginator.jsp" %>
        </form>
        <%
            Boolean isFired = (Boolean) request.getAttribute(ConstantParamAndAttribute.IS_FIRED);
            if (isFired == null || !isFired) {
        %>
        <form name="add_employee"
              method="get"
              action="<%= ConstantAction.ADD_EMPLOYEE %>">
            <button class="button-add">Добавить нового сотрудника</button>
        </form>
        <% } %>
    </div>
</main>
<%@include file="../footer.jsp" %>
</body>
</html>

<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page pageEncoding="UTF-8" %>
<nav class="menu-panel">
    <ul class="menu-list">
        <li>
            <form action="<%=ConstantAction.LIST_EMPLOYEES%>"
                  method="get">
                <button class="menu-item">Сотрудники</button>
            </form>
        </li>
        <li>
            <form action="<%=ConstantAction.LIST_EMPLOYEES%>"
                  method="get">
                <button class="menu-item">Штат</button>
            </form>
        </li>
        <li>
            <form action="<%=ConstantAction.LIST_EMPLOYEES%>"
                  method="get">
                <button class="menu-item">Назначение</button>
            </form>
        </li>
        <li>
            <form action="<%=ConstantAction.LIST_EMPLOYEES%>"
                  method="get">
                <button class="menu-item">Увольнение</button>
            </form>
        </li>
    </ul>
</nav>

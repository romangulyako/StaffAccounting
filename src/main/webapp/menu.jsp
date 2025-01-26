<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page pageEncoding="UTF-8" %>
<aside>
    <nav class="menu-panel">
        <ul class="menu-list">
            <li>
                <form action="<%=ConstantAction.LIST_EMPLOYEES%>"
                      method="get">
                    <button class="menu-item"
                            name="<%=ConstantParamAndAttribute.IS_FIRED%>"
                            value="false">
                        Действующие сотрудники организации
                    </button>
                </form>
            </li>
            <li>
                <form action="<%=ConstantAction.LIST_DEPARTMENTS%>"
                      method="get">
                    <button class="menu-item"
                            name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                            value="true">
                        Структура организации
                    </button>
                </form>
            </li>
            <li>
                <form action="<%=ConstantAction.APPOINTMENT%>"
                      method="get">
                    <button class="menu-item">Назначение на должности</button>
                </form>
            </li>
            <li>
                <form action="<%=ConstantAction.DISMISS%>"
                      method="get">
                    <button class="menu-item">Увольнение сотрудников</button>
                </form>
            </li>
            <li>
                <form action="<%=ConstantAction.LIST_EMPLOYEES%>"
                      method="get">
                    <button class="menu-item"
                            name="<%=ConstantParamAndAttribute.IS_FIRED%>"
                            value="true">
                        Просмотр уволенных сотрудников
                    </button>
                </form>
            </li>
            <li>
                <form action="<%=ConstantAction.LIST_DEPARTMENTS%>"
                      method="get">
                    <button class="menu-item"
                            name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                            value="false">
                        Сокращенные подразделения/должности
                    </button>
                </form>
            </li>
        </ul>
    </nav>
</aside>
<%@ page import="by.itacademy.jd2.constant.ConstantAction" %>
<%@ page pageEncoding="UTF-8" %>
<aside>
    <%Locale localeMenu = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
    <nav class="menu-panel">
        <ul class="menu-list">
            <li>
                <form action="<%=ConstantAction.LIST_EMPLOYEES%>"
                      method="get">
                    <button class="menu-item"
                            name="<%=ConstantParamAndAttribute.IS_FIRED%>"
                            value="false">
                        <%=LocalizationUtil.getMessage("current_employees", localeMenu)%>
                    </button>
                </form>
            </li>
            <li>
                <form action="<%=ConstantAction.LIST_DEPARTMENTS%>"
                      method="get">
                    <button class="menu-item"
                            name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                            value="true">
                        <%=LocalizationUtil.getMessage("structure", localeMenu)%>
                    </button>
                </form>
            </li>
            <li>
                <form action="<%=ConstantAction.APPOINTMENT%>"
                      method="get">
                    <button class="menu-item">
                        <%=LocalizationUtil.getMessage("appointment", localeMenu)%>
                    </button>
                </form>
            </li>
            <li>
                <form action="<%=ConstantAction.DISMISS%>"
                      method="get">
                    <button class="menu-item">
                        <%=LocalizationUtil.getMessage("dismiss", localeMenu)%>
                    </button>
                </form>
            </li>
            <li>
                <form action="<%=ConstantAction.LIST_EMPLOYEES%>"
                      method="get">
                    <button class="menu-item"
                            name="<%=ConstantParamAndAttribute.IS_FIRED%>"
                            value="true">
                        <%=LocalizationUtil.getMessage("view_dismissed", localeMenu)%>
                    </button>
                </form>
            </li>
            <li>
                <form action="<%=ConstantAction.LIST_DEPARTMENTS%>"
                      method="get">
                    <button class="menu-item"
                            name="<%=ConstantParamAndAttribute.IS_ACTUAL%>"
                            value="false">
                        <%=LocalizationUtil.getMessage("view_abbreviated", localeMenu)%>
                    </button>
                </form>
            </li>
        </ul>
    </nav>
</aside>
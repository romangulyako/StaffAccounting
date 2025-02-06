<%@ page import="java.util.Locale" %>
<%@ page import="by.itacademy.jd2.utils.LocalizationUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!--%Locale locale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %-->
<header>
    <%Locale localeHeader = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE);%>
    <div class="header-container">
        <div class="header-left">
            <img src="resources/logo.png" alt="Логотип" class="logo">
            <h1><%=LocalizationUtil.getMessage("app_header", localeHeader)%>
            </h1>
        </div>
        <div class="header-right">
            <form action="set_language" method="post">
                <select name="language" onchange="this.form.submit()">
                    <option value="<%=request.getAttribute("locale")%>">
                        <%=LocalizationUtil.getMessage("select_language", localeHeader)%>
                    </option>
                    <option value="ru"><%=LocalizationUtil.getMessage("russian", localeHeader)%>
                    </option>
                    <option value="en"><%=LocalizationUtil.getMessage("english", localeHeader)%>
                    </option>
                </select>
            </form>
            <span class="user-name">Администратор</span>
            <button class="logout-button"><%=LocalizationUtil.getMessage("logout", localeHeader)%>
            </button>
        </div>
    </div>
</header>

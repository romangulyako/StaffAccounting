<%@ page contentType="text/html;charset=UTF-8" %>
<footer>
  <% Locale footerLocale = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE); %>
  <div><%=LocalizationUtil.getMessage("footer_title", footerLocale)%></div>
  <div><%=LocalizationUtil.getMessage("author", footerLocale)%></div>
  <div>Email: romangulyako@gmail.com</div>
  <div>2025</div>
</footer>
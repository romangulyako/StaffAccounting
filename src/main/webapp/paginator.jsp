<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page pageEncoding="UTF-8" %>
<div class="paginator-panel">
  <%
    Integer totalPages = (Integer) request.getAttribute(ConstantParamAndAttribute.TOTAL_PAGES);
    Integer pageSize = (Integer) request.getAttribute(ConstantParamAndAttribute.PAGE_SIZE);
    Integer pageNumber = (Integer) request.getAttribute(ConstantParamAndAttribute.PAGE_NUMBER);
    Locale localePaginator = (Locale) request.getAttribute(ConstantParamAndAttribute.LOCALE);
  %>
  <div class="paginator-button-block">
    <% if (pageNumber > 1) { %>
    <button class="paginator-button"
            name="<%=ConstantParamAndAttribute.PAGE_NUMBER%>"
            value="<%=pageNumber - 1%>"
            type="submit">
      <<
    </button>
    <% } %>
    <% if (pageNumber < totalPages) { %>
    <button class="paginator-button"
            name="<%=ConstantParamAndAttribute.PAGE_NUMBER%>"
            value="<%=pageNumber + 1%>"
            type="submit">
      >>
    </button>
    <% } %>
  </div>
  <div class="paginator-page-size-block">
    <label class="paginator-label"><%=LocalizationUtil.getMessage("page", localePaginator)%> <%=pageNumber%>/<%=totalPages%>.</label>
    <label for="page_size"><%=LocalizationUtil.getMessage("page_size", localePaginator)%></label>
    <input class="page-size-input"
           id="page_size"
           type="number"
           min="1"
           name="<%=ConstantParamAndAttribute.PAGE_SIZE%>"
           value="<%=pageSize%>">
    <button class="paginator-button"
            type="submit">
      <%=LocalizationUtil.getMessage("edit_button", localePaginator)%>
    </button>
  </div>
</div>
<%@ page import="by.itacademy.jd2.constant.ConstantParamAndAttribute" %>
<%@ page pageEncoding="UTF-8" %>
<div class="paginator-panel">
  <%
    Integer totalPages = (Integer) request.getAttribute(ConstantParamAndAttribute.TOTAL_PAGES);
    Integer pageSize = (Integer) request.getAttribute(ConstantParamAndAttribute.PAGE_SIZE);
    Integer pageNumber = (Integer) request.getAttribute(ConstantParamAndAttribute.PAGE_NUMBER);
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
    <label>Стр. <%=pageNumber%>/<%=totalPages%>.</label>
    <label for="page_size">Размер страницы:</label>
    <input class="paginator-input"
           id="page_size"
           type="text"
           name="<%=ConstantParamAndAttribute.PAGE_SIZE%>"
           value="<%=pageSize%>">
    <button class="paginator-button"
            type="submit">
      Изменить
    </button>
  </div>
</div>
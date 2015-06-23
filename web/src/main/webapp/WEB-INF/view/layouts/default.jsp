<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>

  <title>News-Portal</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta charset="utf-8"/>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <!-- Bootstrap -->
  <!-- Latest compiled and minified CSS -->
  <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
  <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/normalize.css">

  <script src="/assests/js/jquery-1.11.1.min.js" type="text/javascript"><jsp:text/></script>
  <script src="/assests/js/utils.js" type="text/javascript"><jsp:text/></script>

</head>

<body>

<div class="page-wrapper">

  <div id="content">

    <div class="logout">
     <c:if test="${usertype eq '[user]'|| usertype eq '[admin]'}">
        <form action="/portal/secure.form" method="post">
          <input class="btn btn-default" type="submit" name="logOut" value="Log Out">
        </form>
      </c:if>
    </div>

    <table class="table">
      <caption class="caption">
        <%-- <h1>${usertype}</h1>--%>
        <tiles:insertAttribute name="header"/>

      </caption>

      <tiles:insertAttribute name="body"/>

    </table>
  </div>
  <div class="page-buffer"></div>
</div>

<div class="page-footer">
  <tiles:insertAttribute name="footer"/>
</div>

</div>

</body>

</html>

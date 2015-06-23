<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Spring Security</title>

  <!-- Bootstrap -->
  <!-- Latest compiled and minified CSS -->
  <link type="text/css" rel="stylesheet" href="resources/css/style.css"/>
  <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="resources/css/normalize.css"/>



  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
  <![endif]-->
</head>

<body>

<div class="container">

  <div class="jumbotron" style="margin-top: 170px;">

    <p class="lead">
      NewsPortal - best news portal for all times.
    </p>
    <sec:authorize access="!isAuthenticated()">
      <p><a class="btn btn-default" href="<c:url value="/portal/login.form" />" role="button">Войти</a></p>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
      <p>Ваш логин: <sec:authentication property="principal.username" /></p>
      <p><a class="btn btn-default" href="<c:url value="/portal/login.form" />" role="button">Выйти</a></p>

    </sec:authorize>
  </div>

  <div class="footer">
    <p>© NewsPortal 2015</p>
  </div>

</div>
</body>
</html>
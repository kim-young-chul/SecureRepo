<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@property['redirect.url']" var="reurl"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>login success</title>
<script type="text/javascript" src="/resources/js/jquery-3.6.3.slim.min.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/js/login_success.js"></script>
</head>
<body>
<input type="hidden" id="reurl" name="reurl" value="${reurl}">
</body>
</html>

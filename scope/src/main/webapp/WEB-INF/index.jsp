<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" />
<html>
    <head>
        <link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css"/>
    </head>
    <body>
        <div>
            \${person.name} : ${person.name}
        </div>
        <div>
            \${applicationScope['scopedTarget.person'].name} : ${applicationScope['scopedTarget.person'].name}
        </div>
    </body>
</html>
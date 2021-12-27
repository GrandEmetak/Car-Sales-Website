<%-- библиотекой JSTL. Scriplet - это Java код написанный в JSP. Чтобы писать код в едином стиле используют библиотеку тегов JSTL. --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.cars.model.Post" %>
<%@ page import="ru.job4j.cars.model.User" %>
<%@ page import="ru.job4j.cars.model.Car" %>
<%@ page import="java.util.Collection" %>
<%@ page import="ru.job4j.cars.store.PostRepository" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <%--Иконки редактирования (квадрат с карандашом) --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Cars</title>
</head>

<body style="background: url('image/Screenshot_30.jpg') no-repeat fixed; -webkit-background-size: cover; background-size: cover;">

<%
    String id = request.getParameter("id");
    Post post = new Post(0, "");
    if (id != null) {
        post = PostRepository.instOf().findPostBiId(Integer.parseInt(id)).get(0);
    }
%>
<div class="container pt-3">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/candidate.do?userID=${user.id}">Объявления</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/candidate/postnew.jsp?userID=${user.id}">Добавить
                    объявление</a>
            </li>
            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login/login.jsp">Войти</a>
                </li>
            </c:if>
            <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">
                        <c:out value="${user.name}"/> | Выйти</a>
                </li>
            </c:if>
        </ul>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <h1 align="center">Cars</h1>
            </div>
            <div class="card-body">
                <table class="table">
                    <ul class="bottom-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/candidateT.do">
                                <h6>Список объявлений за последние 24 часа</h6></a>
                        </li>
                    </ul>
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Header</th>
                        <th scope="col">Car Info</th>
                        <th scope="col">Description</th>
                        <th scope="col">Price</th>
                        <th scope="col">Status</th>
                        <th scope="col">Autor</th>
                        <th scope="col">Data</th>

                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${posts}" var="post">
                        <tr>
                            <td>
                                <img src="<c:url value='/download?name=${post.id}'/>" width="100px" height="100px"/>
                            </td>
                            <td><c:out value="${post.header}"/></td>
                            <td>
                                <c:out value="${post.car.bodyType}"/>
                                <c:out value="${post.car.engine}"/>
                                <c:out value="${post.car.transmission}"/>
                            </td>
                            </td>
                            <td>
                                <c:out value="${post.description}"/>
                                <c:out value="${post.car.color}"/>
                                <c:out value="${post.car.drive}"/>
                                <c:out value="${post.car.mileage}"/>
                            </td>
                            <td><c:out value="${post.price}"/></td>
                            <td>
                                <c:if test="${post.status != true}">
                                    <c:out value="Продается/for sale"/>
                                </c:if>
                                <c:if test="${post.status != false}">
                                    <c:out value="Продано/sold"/>
                                </c:if>
                            </td>
                            <td><c:out value="${post.user.name}"/></td>
                            <td><c:out value="${post.created}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>

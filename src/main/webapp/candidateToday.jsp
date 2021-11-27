<%--
  Created by IntelliJ IDEA.
  User: SlartiBartFast-art
  Date: 27.10.2021
  Time: 16:22
  To change this template use File | Settings | File Templates.
   У нас есть отдельная кнопка Выйти - ее функционал реализуем отдельно. Для этого определим следующую ссылку в сервлете:
<c:if test="${user != null}">
    <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">Выйти</a>
    </li>
</c:if>
--%>
<%--
                   было <% for (Candidate candidate : (Collection<Candidate>) request.getAttribute("posts")) { %>
                    Когда в браузере открывается любая ссылка, он отправляет http запрос с типом GET.
                   public class CandidateServlet extends HttpServlet {
                   @Override
                   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                   req.setAttribute("posts", Store.instOf().findAllPosts()); - В методу doGet мы загружаем в request список вакансий.
                   req.getRequestDispatcher("candidate.jsp").forward(req, resp);    }

                  WARNING!!! было до переписывани под библиотеку JSPL
                   <% for (Candidate candidate : (Collection<Candidate>) request.getAttribute("candidates")) { --%>
<%--   <tr>
       <td>                <%--добавить иконку редактирования втаблицу и ссылку на страницу edit.
   <a href="<%=request.getContextPath()%>/candidate/edit.jsp?id=<%=candidate.getId()%>">
       <i class="fa fa-edit mr-3"></i>
   </a>
   <%= candidate.getName() %>
   </td>
   </tr>
   --%>

<%-- библиотекой JSTL. Напомню, что Scriplet - это Java код написанный в JSP. Чтобы писать код в едином стиле используют библиотеку тегов JSTL. --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.cars.model.Post" %>
<%@ page import="java.util.Collection" %>
<%@ page import="ru.job4j.cars.store.AdRepository" %>
<%@ page import="ru.job4j.cars.model.User" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
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

    <script src="js/validate.js"></script>

    <%--Иконки редактирования (квадрат с карандашом) --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Работа мечты</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    Post post = new Post(0, "");
    // Candidate candidate = new Candidate(0, "", "", "");
    if (id != null) {
        //   candidate = PsqlStore.instOf().findByIdCandidate(Integer.parseInt(id));
        post = AdRepository.instOf().findPostBiId(Integer.parseInt(id)).get(0);
    }
%>
<div class="container pt-3">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/candidates.do">Объявления</a>

            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/candidate/edit.jsp">Добавить
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
                        <th scope="col">Edit/Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%-- загрузка КандидатСервлеет, doGet - от него приходит перечень Кандидатов--%>
                    <c:forEach items="${posts}" var="post">
                        <tr>
                            <td> <%-- Передали в ДонлоадСервлет по ключу name - id Кандидата, doGet вернул фото из папки с таким номером--%>
                                <img src="<c:url value='/download?name=${post.id}'/>" width="50px" height="50px"/>
                            </td>
                            <td><c:out value="${post.header}"/></td>
                                <%-- c:out value="post.name"- Вывод значения post. --%>
                            <td><c:out value="${post.car.name}"/></td>
                            <td><c:out value="${post.description}"/></td>
                            <td><c:out value="${post.price}"/></td>
                            <td><c:out value="${post.status}"/></td>
                            <td><c:out value="${post.user.name}"/></td>
                            <td><c:out value="${post.created}"/></td>
                            <td>                <%--добавить иконку редактирования в таблицу и ссылку на страницу edit. --%>
                                <a href="<c:url value='/candidate/edit.jsp?id=${post.id}'/>">
                                    <em class="fa fa-edit mr-3"></em>
                                </a>
                                <br>
                                <a href="<c:url value='/upload.jsp?id=${post.id}'/>">
                                    Edit photo</a>
                                <br>
                                <form style="display: inline" action="<c:url value='/delete.do?id=${post.id}'/>"
                                      method="post">
                                    <button>Delete candidate</button>
                                </form>

                            </td>
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
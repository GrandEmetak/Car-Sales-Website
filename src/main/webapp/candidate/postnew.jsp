<%--
   Создадим страницу для создания нового объявления Post.
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.cars.model.Post" %>
<%@ page import="ru.job4j.cars.store.PostRepository" %>
<%@ page import="ru.job4j.cars.model.User" %>
<%@ page import="ru.job4j.cars.model.Car" %>
<%@ page import="ru.job4j.cars.model.Photo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <%--<Библиотека иконок для редактирования (Значек редактировать) --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script><!--Ajax Jquery -->

    <script src="${pageContext.request.contextPath}/js/validate.js"></script>
    <script src="${pageContext.request.contextPath}/js/postservl.js"></script>

<%--    <script>--%>
<%--        function validate() {--%>
<%--            let x = Boolean(true);--%>
<%--            if ($('#header').val() === '') {--%>
<%--                x = false;--%>
<%--            }--%>
<%--            if ($('#description').val() === '') {--%>
<%--                x = false;--%>
<%--            }--%>
<%--            if ($('#engine').val() === '') {--%>
<%--                x = false;--%>
<%--            }--%>
<%--            if ($('#price').val() === '') {--%>
<%--                x = false;--%>
<%--            }--%>
<%--            if ($('#status').val() === '') {--%>
<%--                x = false;--%>
<%--            }--%>
<%--            if ($('#mark').val() === '') {--%>
<%--                x = false;--%>
<%--            }--%>
<%--            if ($('#color').val() === '') {--%>
<%--                x = false;--%>
<%--            }--%>
<%--            if ($('#body').val() === '') {--%>
<%--                x = false;--%>
<%--            }--%>
<%--            if ($('#transm').val() === '') {--%>
<%--                x = false;--%>
<%--            }--%>
<%--            if ($('#drive').val() === '') {--%>
<%--                x = false;--%>
<%--            }--%>
<%--            if ($('#mileage').val() === '') {--%>
<%--                x = false;--%>
<%--            }--%>
<%--            let f = $('#body').val();--%>
<%--            alert('то что в форме ' + f);--%>
<%--            return x;--%>
<%--        }--%>
<%--    </script>--%>

    <title>Cars</title>
</head>

<body style="background: url('/image/Screenshot_30.jpg') no-repeat fixed; -webkit-background-size: cover; background-size: cover;">

<%
    String id = request.getParameter("id");
    Post post = Post.emptyP();
    if (id != null) {
        post = PostRepository.instOf().findPostBiId(Integer.parseInt(id)).get(0);
    }
%>
<div class="needs-validation" novalidate>
    <div class="form-auto">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <% if (id == null) { %>
                <h5>Enter New Post information / Введите информацию для подачи вашего объявления</h5>
                <% } else { %>
                <h5>you are in the Post edit field / Вы находитесь на странице редактирования объявления</h5>
                <% } %>
            </div>
            <div class="card-body">
<%--                <form action="<%=request.getContextPath()%>/postnew.do" method="post">--%>
                <form>
                    <div class="form-group">
                        <div class="col-md-6 mb-3">
                            <label>Header</label>
                            <input type="text" class="form-control" name="header" value="<%=post.getHeader()%>"
                                   id="header"
                                   placeholder="Pleas enter header you's post" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label>Description you auto</label>
                            <input type="text" class="form-control" name="description"
                                   value="<%=post.getDescription()%>"
                                   id="description"
                                   placeholder="Please enter description Post" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label>Engine info</label>
                            <input type="text" class="form-control" name="engine" value="<%=post.getCar().getEngine()%>"
                                   id="engine"
                                   placeholder="Please enter your engine info: fuel type, volume, hp" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label>Price</label>
                            <input type="text" class="form-control" name="price" value="<%=post.getPrice()%>"
                                   id="price"
                                   placeholder="Please enter your price" required>
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="status">Status</label> <select class="custom-select" id="status"
                                                                       required>
                            <option selected disabled value="<%=post.isStatus()%>">Choose...</option>
                            <option>true</option>
                            <option>false</option>
                        </select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label>Mark</label>
                            <input type="text" class="form-control" name="mark" value="<%=post.getCar().getMark()%>"
                                   id="mark"
                                   placeholder="Please enter your mark auto" required> <!-- доработать в сервлете-->
                        </div>
                        <div class="col-md-6 mb-3">
                            <label>Color auto</label>
                            <input type="text" class="form-control" name="color" value="<%=post.getCar().getColor()%>"
                                   id="color"
                                   placeholder="Please enter your color auto" required> <!-- доработать в сервлете-->
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="body">Body Type</label> <select class="custom-select" id="body"
                                                                        required>
                            <option selected disabled value="<%=post.getCar().getBodyType()%>">Choose...</option>
                            <option>true</option>
                            <option>sedan</option>
                            <option>wagon</option>
                            <option>hatchback</option>
                            <option>micro compact car</option>
                            <option>landau</option>
                            <option>pickup track</option>
                            <option>suv</option>
                        </select>
                        </div>

                        <div class=" col-md-3 mb-3">
                            <label for="transm">Transmission</label>
                            <select class="custom-select" id="transm" required>
                                <option selected disabled value="<%=post.getCar().getTransmission()%>">Choose...
                                </option>
                                <option>automate</option>
                                <option>cvc</option>
                                <option>manual</option>
                                <option>robot</option>
                            </select>
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="drive">Drive</label>
                            <select class="custom-select" id="drive" required>
                                <option selected disabled value="<%=post.getCar().getDrive()%>">Choose...</option>
                                <option>front-wheel drive</option>
                                <option>rear drive</option>
                                <option>all road 4x4</option>
                            </select>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label>Mileage</label>
                            <input type="text" class="form-control" name="mileage"
                                   value="<%=post.getCar().getMileage()%>"
                                   id="mileage"
                                   placeholder="Please enter your car mileage" required>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="postservl()">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

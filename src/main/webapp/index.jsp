<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.carsales.model.User" %>
<%@ page import="java.util.Collection" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Bootstrap Example</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script><!-- Подключаем сценарии -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <script src="scripts/sendGreeting.js"></script>
    <script src="scripts/validate.js"></script>
    <script src="scripts/add.js"></script>
    <script src="scripts/doneFalse.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>CARS</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<title>CARS</title>

<H1>Cars</H1>
<div class="fixed-top">
    <div class="collapse" id="navbarToggleExternalContent">
        <div class="bg-dark p-4">
            <h5 class="text-white h4"> Ознакомьтесь, пожалуйста с правилами! </h5>
            <span class="text-muted"> Для просмотра/внесения записей необходимо войти под своей авторизацией или зарегистирироваться </span>
        </div>
    </div>
    <nav class="navbar navbar-dark bg-dark" aria-labelledby="">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent"
                aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
</div>
<br>
<br>
<br>

<body style="background: url('image/main fone site.jpg') no-repeat fixed; -webkit-background-size: cover; background-size: cover;">

<div class="container">
    <div class="row">
        <ul class="nav">
            <br>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/reg.do">Регистрация</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> Авторизация </a>
            </li>
        </ul>
    </div>
    <br>
    <div class="custom-control custom-switch">
        <input type="checkbox" class="custom-control-input" id="checkbox">
        <label class="custom-control-label" for="checkbox">Show all Posts</label>
    </div>
    <p id="text" style="display: none;">На этом месте будет Ваш текст, как цель применения такого текста
        исключительно демонстрационная</p>
    <script> <!--показать контент по нажатию  $('#text').show(100); если не активна то он скрыт $('#text').hide(100);-->
    $('#checkbox').click(function () {
        if ($(this).is(':checked')) {
            $('#text').show(100);
            doneFalse();
        } else {
            location.reload();
        }
    });
    </script>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Сегодняшние объявления.
            </div>
            <div class="card-body">
                <table id="table" class="table">
                    <caption>After registration or authorization, the current posts will be available to you</caption>
                    <thead class="thead-dark">
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
                    <div>

                    </div>
                    </tbody>
                    <script><!--чекбокс/радиокнопка  состоянние true/false-->
                    $('#checkbox').click(function () {
                        let checkboxStatus = $(this).prop('checked')
                        console.log(checkboxStatus);
                    });
                    </script>


                </table>
            </div>
        </div>
    </div>

    <div class="row float-right">
        <script>
            $('input').on('click', function () {
                $('#outputField').text('Вы выбрали ' + $('input:checked').val());
            });
        </script>
    </div>
    <nav class="navbar navbar-light bg-light">
        <form class="form-inline">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </nav>
    </form>
</div>
</body>
</html>

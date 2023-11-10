<%@ page import="com.carsales.model.entity.Post" %>
<%@ page import="com.carsales.store.PostRepository" %>
<%@ page import="com.carsales.model.entity.User" %>
<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Upload</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <title>Cars</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    Post post = new Post(0, "");
    if (id != null) {
        post = PostRepository.instOf().findPostBiId(Integer.parseInt(id)).get(0);
    }
%>
<div class="container pt-3">
    <div class="card-body">
        <table class="table">
            <thead>
            <tr>
                <th>URL</th>
                <th>View</th>
            </tr>
            </thead>
            <tbody>
            <table class="container">
                <h2>Upload image</h2>
                <form action="<%=request.getContextPath()%>/upload?id=<%=post.getId()%>" method="post"
                      enctype="multipart/form-data">
                    <div class="checkbox">
                        <input type="file" name="file">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </table>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>


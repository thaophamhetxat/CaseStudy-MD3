<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #e9e9e9;
        }

        .topnav a {
            float: left;
            display: block;
            color: black;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #2196F3;
            color: white;
        }

        .topnav .search-container {
            float: right;
        }

        .topnav input[type=text] {
            padding: 6px;
            margin-top: 8px;
            font-size: 17px;
            border: none;
        }

        .topnav .search-container button {
            float: right;
            padding: 6px;
            margin-top: 8px;
            margin-right: 16px;
            background: #ddd;
            font-size: 17px;
            border: none;
            cursor: pointer;
        }

        .topnav .search-container button:hover {
            background: #ccc;
        }

        @media screen and (max-width: 600px) {
            .topnav .search-container {
                float: none;
            }

            .topnav a, .topnav input[type=text], .topnav .search-container button {
                float: none;
                display: block;
                text-align: left;
                width: 100%;
                margin: 0;
                padding: 14px;
            }

            .topnav input[type=text] {
                border: 1px solid #ccc;
            }
        }
    </style>
</head>
<body>
<div class="topnav">
    <a href="/hocvien?action=quit" class="btn btn-success">Home NV</a>
    <%--  <a href="/hocvien?action=homesp" class="btn btn-success">Home SP</a>--%>
    <a href="/hocvien?action=create" class="btn btn-success">Create nv</a>
    <div class="search-container">
        <form action="/hocvien?action=find" method="post">
            <input type="text" placeholder="Search.." name="findName">
            <button type="submit" class="btn btn-warning">Search</button>
        </form>
    </div>
</div>

<div class="container">
    <h2>THONG TIN HOC VIEN</h2>
    <form method="post">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Date</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Classroom</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><input placeholder="nhap name" name="name" value="${hocvien.name}"></td>
                <td><input placeholder="nhap date" name="date" value="${hocvien.date}"></td>
                <td><input placeholder="nhap address" name="address" value="${hocvien.address}"></td>
                <td><input placeholder="nhap phones" name="phone" value="${hocvien.phone}"></td>
                <td><input placeholder="nhap email" name="email" value="${hocvien.email}"></td>
                <td><input placeholder="nhap classroom" name="classroom" value="${hocvien.classroom}"></td>

            </tr>
            </tbody>
        </table>
        <button type="submit" class="btn btn-success">Edit</button>
        <a href="/hocvien?action=quit" class="btn btn-secondary">Quit</a>
    </form>
</div>

</body>
</html>
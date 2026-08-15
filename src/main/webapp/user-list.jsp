<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>User Management Application</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <style>

        body {
            background-color: #f5f5f5;
            color: #222;
            transition: 0.3s;
        }

        .navbar {
            background-color: #212529 !important;
        }

        .main-container {
            margin-top: 30px;
        }

        .search-box,
        .sort-box {
            background: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
        }

        .table {
            background: white;
        }

        .table tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .total-users {
            font-size: 18px;
            font-weight: bold;
        }

        /* SUCCESS MESSAGE */

        .success-message {
            position: fixed;
            right: 25px;
            bottom: 25px;
            min-width: 280px;
            z-index: 9999;
            animation: slideIn 0.5s ease;
        }

        @keyframes slideIn {

            from {
                right: -400px;
                opacity: 0;
            }

            to {
                right: 25px;
                opacity: 1;
            }
        }

        /* DARK MODE */

        body.dark-mode {
            background-color: #121212;
            color: white;
        }

        body.dark-mode .search-box,
        body.dark-mode .sort-box,
        body.dark-mode .table,
        body.dark-mode .card {
            background-color: #1e1e1e;
            color: white;
        }

        body.dark-mode .table {
            color: white;
        }

        body.dark-mode .table tbody tr:nth-child(even) {
            background-color: #292929;
        }

        body.dark-mode .form-control,
        body.dark-mode select {
            background-color: #333;
            color: white;
            border-color: #555;
        }

        body.dark-mode .table-bordered td,
        body.dark-mode .table-bordered th {
            border-color: #555;
        }

        .mode-button {
            border: 1px solid white;
            border-radius: 20px;
            padding: 5px 15px;
            background: transparent;
            color: white;
            cursor: pointer;
        }

    </style>

</head>

<body>

<!-- NAVBAR -->

<nav class="navbar navbar-expand-md navbar-dark">

    <a class="navbar-brand"
       href="<%=request.getContextPath()%>/list">

        🏠 User Management App

    </a>

    <div class="ml-auto">

        <a class="nav-link d-inline text-white"
           href="<%=request.getContextPath()%>/list">

            Users

        </a>

        <button class="mode-button"
                onclick="toggleMode()">

            🌓 Mode

        </button>

    </div>

</nav>


<div class="container main-container">

    <h2 class="text-center mb-4">

        👥 User Management

    </h2>


    <!-- TOTAL USERS -->

    <div class="total-users mb-3">

        📊 Total Users:
        <span class="badge badge-primary">

            ${listUser.size()}

        </span>

    </div>


    <!-- SEARCH -->

    <div class="search-box">

        <form action="<%=request.getContextPath()%>/list"
              method="get"
              class="form-inline">

            <label class="mr-2 font-weight-bold">

                🔍 Search by name or email

            </label>

            <input type="text"
                   name="search"
                   value="${search}"
                   class="form-control mr-2"
                   placeholder="Enter name or email">

            <button type="submit"
                    class="btn btn-primary">

                🔍 Search

            </button>

        </form>

    </div>


    <!-- SORT -->

    <div class="sort-box">

        <form action="<%=request.getContextPath()%>/list"
              method="get"
              class="form-inline">

            <!-- Keep search while sorting -->

            <input type="hidden"
                   name="search"
                   value="${search}">

            <label class="mr-2 font-weight-bold">

                Sort by:

            </label>

            <select name="sort"
                    class="form-control mr-2">

                <option value="id"
                    ${sort == 'id' ? 'selected' : ''}>

                    ID

                </option>

                <option value="name"
                    ${sort == 'name' ? 'selected' : ''}>

                    Name

                </option>

            </select>

            <button type="submit"
                    class="btn btn-dark">

                Apply

            </button>

        </form>

    </div>


    <!-- ADD USER -->

    <div class="mb-3">

        <a href="<%=request.getContextPath()%>/new"
           class="btn btn-success">

            ➕ Add New User

        </a>

    </div>


    <!-- USERS TABLE -->

    <div class="table-responsive">

        <table class="table table-bordered table-hover">

            <thead class="thead-dark">

                <tr>

                    <th>ID</th>

                    <th>Name</th>

                    <th>Email</th>

                    <th>Country</th>

                    <th>Actions</th>

                </tr>

            </thead>


            <tbody>

                <c:forEach var="user"
                           items="${listUser}">

                    <tr>

                        <td>
                            <c:out value="${user.id}"/>
                        </td>

                        <td>
                            <c:out value="${user.name}"/>
                        </td>

                        <td>
                            <c:out value="${user.email}"/>
                        </td>

                        <td>
                            <c:out value="${user.country}"/>
                        </td>

                        <td>

                            <a href="<%=request.getContextPath()%>/edit?id=${user.id}"
                               class="btn btn-warning btn-sm">

                                ✏️ Edit

                            </a>

                            <a href="<%=request.getContextPath()%>/delete?id=${user.id}"
                               class="btn btn-danger btn-sm"
                               onclick="return confirm('Are you sure you want to delete this user?');">

                                🗑️ Delete

                            </a>

                        </td>

                    </tr>

                </c:forEach>


                <!-- NO USERS -->

                <c:if test="${empty listUser}">

                    <tr>

                        <td colspan="5"
                            class="text-center">

                            No users found.

                        </td>

                    </tr>

                </c:if>

            </tbody>

        </table>

    </div>

</div>


<!-- SUCCESS MESSAGE -->

<c:if test="${not empty sessionScope.successMessage}">

    <div id="successMessage"
         class="alert alert-success success-message">

        ✅
        <c:out value="${sessionScope.successMessage}"/>

    </div>

    <%
        session.removeAttribute("successMessage");
    %>

</c:if>


<script>

    // DARK / LIGHT MODE

    function toggleMode() {

        document.body.classList.toggle("dark-mode");

        if (document.body.classList.contains("dark-mode")) {

            localStorage.setItem("darkMode", "true");

        } else {

            localStorage.setItem("darkMode", "false");

        }

    }


    // REMEMBER MODE

    window.onload = function() {

        if (localStorage.getItem("darkMode") === "true") {

            document.body.classList.add("dark-mode");

        }

        // SUCCESS MESSAGE DISAPPEARS AFTER 5 SECONDS

        var message =
            document.getElementById("successMessage");

        if (message) {

            setTimeout(function() {

                message.style.opacity = "0";

                message.style.transition = "opacity 0.5s";

                setTimeout(function() {

                    message.remove();

                }, 500);

            }, 5000);

        }

    };

</script>


</body>

</html>
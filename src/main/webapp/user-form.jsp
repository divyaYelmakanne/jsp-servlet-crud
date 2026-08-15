<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>${formTitle}</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <style>

        body {
            background-color: #f5f5f5;
            transition: 0.3s;
        }

        .card {
            border-radius: 12px;
        }

        body.dark-mode {
            background-color: #121212;
            color: white;
        }

        body.dark-mode .card {
            background-color: #1e1e1e;
            color: white;
        }

        body.dark-mode .form-control {
            background-color: #333;
            color: white;
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

<nav class="navbar navbar-dark bg-dark">

    <a class="navbar-brand"
       href="<%=request.getContextPath()%>/list">

        🏠 User Management App

    </a>

    <button class="mode-button ml-auto"
            onclick="toggleMode()">

        🌓 Mode

    </button>

</nav>


<br>


<div class="container col-md-5">

    <div class="card">

        <div class="card-body">

            <h2 class="text-center mb-4">

                ${formTitle}

            </h2>


            <!-- FORM -->

            <form action="<%=request.getContextPath()%>/${user != null ? 'update' : 'insert'}"
                  method="post">


                <!-- ID FOR EDIT -->

                <input type="hidden"
                       name="id"
                       value="${user.id}">


                <!-- NAME -->

                <div class="form-group">

                    <label>

                        👤 User Name

                    </label>

                    <input type="text"
                           name="name"
                           class="form-control"
                           value="${user.name}"
                           required>

                </div>


                <!-- EMAIL -->

                <div class="form-group">

                    <label>

                        📧 User Email

                    </label>

                    <input type="email"
                           name="email"
                           class="form-control"
                           value="${user.email}"
                           required>

                </div>


                <!-- COUNTRY -->

                <div class="form-group">

                    <label>

                        🌍 User Country

                    </label>

                    <input type="text"
                           name="country"
                           class="form-control"
                           value="${user.country}"
                           required>

                </div>


                <!-- BUTTONS -->

                <button type="submit"
                        class="btn btn-success">

                    ✅ ${buttonText}

                </button>


                <a href="<%=request.getContextPath()%>/list"
                   class="btn btn-secondary">

                    Cancel

                </a>

            </form>

        </div>

    </div>

</div>


<script>

    function toggleMode() {

        document.body.classList.toggle("dark-mode");

        if (document.body.classList.contains("dark-mode")) {

            localStorage.setItem("darkMode", "true");

        } else {

            localStorage.setItem("darkMode", "false");

        }

    }


    window.onload = function() {

        if (localStorage.getItem("darkMode") === "true") {

            document.body.classList.add("dark-mode");

        }

    };

</script>


</body>

</html>
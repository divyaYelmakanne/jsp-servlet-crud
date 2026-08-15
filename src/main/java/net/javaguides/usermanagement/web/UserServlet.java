package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import net.javaguides.usermanagement.dao.UserDAO;
import net.javaguides.usermanagement.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {

            switch (action) {

            case "/new":
                showNewForm(request, response);
                break;

            case "/insert":
                insertUser(request, response);
                break;

            case "/edit":
                showEditForm(request, response);
                break;

            case "/update":
                updateUser(request, response);
                break;

            case "/delete":
                deleteUser(request, response);
                break;

            case "/list":
            case "/":
            default:
                listUser(request, response);
                break;
            }

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

    // =========================
    // LIST USERS
    // =========================
    private void listUser(HttpServletRequest request,
                          HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<User> listUser = userDAO.selectAllUsers();

        // SEARCH
        String search = request.getParameter("search");

        if (search != null && !search.trim().isEmpty()) {

            String searchText = search.trim().toLowerCase();

            listUser = listUser.stream()
                    .filter(user ->
                            user.getName().toLowerCase().contains(searchText)
                            ||
                            user.getEmail().toLowerCase().contains(searchText)
                    )
                    .collect(Collectors.toList());
        }

        // SORT
        String sort = request.getParameter("sort");

        if ("name".equals(sort)) {

            listUser.sort(
                    Comparator.comparing(
                            User::getName,
                            String.CASE_INSENSITIVE_ORDER
                    )
            );

        } else if ("id".equals(sort)) {

            listUser.sort(
                    Comparator.comparingInt(User::getId)
            );
        }

        request.setAttribute("listUser", listUser);

        // Keep search text
        request.setAttribute("search", search);

        // Keep selected sort
        request.setAttribute("sort", sort);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/user-list.jsp");

        dispatcher.forward(request, response);
    }

    // =========================
    // NEW USER FORM
    // =========================
    private void showNewForm(HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("formTitle", "Add New User");
        request.setAttribute("buttonText", "Save");

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/user-form.jsp");

        dispatcher.forward(request, response);
    }

    // =========================
    // INSERT USER
    // =========================
    private void insertUser(HttpServletRequest request,
                            HttpServletResponse response)
            throws SQLException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User newUser = new User(name, email, country);

        userDAO.insertUser(newUser);

        // SUCCESS MESSAGE
        HttpSession session = request.getSession();
        session.setAttribute(
                "successMessage",
                "User successfully added!"
        );

        response.sendRedirect(
                request.getContextPath() + "/list"
        );
    }

    // =========================
    // EDIT FORM
    // =========================
    private void showEditForm(HttpServletRequest request,
                              HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        User existingUser = userDAO.selectUser(id);

        request.setAttribute("user", existingUser);
        request.setAttribute("formTitle", "Edit User");
        request.setAttribute("buttonText", "Update");

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/user-form.jsp");

        dispatcher.forward(request, response);
    }

    // =========================
    // UPDATE USER
    // =========================
    private void updateUser(HttpServletRequest request,
                            HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User user = new User(id, name, email, country);

        userDAO.updateUser(user);

        // SUCCESS MESSAGE
        HttpSession session = request.getSession();
        session.setAttribute(
                "successMessage",
                "User successfully updated!"
        );

        response.sendRedirect(
                request.getContextPath() + "/list"
        );
    }

    // =========================
    // DELETE USER
    // =========================
    private void deleteUser(HttpServletRequest request,
                            HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        userDAO.deleteUser(id);

        // SUCCESS MESSAGE
        HttpSession session = request.getSession();
        session.setAttribute(
                "successMessage",
                "User successfully deleted!"
        );

        response.sendRedirect(
                request.getContextPath() + "/list"
        );
    }
}
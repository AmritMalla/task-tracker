package com.amt.time_tracker.controller;

import com.amt.time_tracker.dao.TaskDao;
import com.amt.time_tracker.dao.UserDao;
import com.amt.time_tracker.model.Task;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/")
public class TaskController extends HttpServlet {

    private static final long serialVersionUID = -5250937727571737007L;

    private TaskDao taskDao;

    private UserDao userDao;

    public void init() {
        taskDao = new TaskDao();
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertTask(request, response);
                    break;
                case "/delete":
                    deleteTask(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/list":
                    listTask(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Task> taskList = new ArrayList<>();

        request.setAttribute("taskList", taskList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task/task-list.jsp");
        dispatcher.forward(request, response);
    }

    private void insertTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Task.TaskBuilder builder = Task.builder();
        builder.title(request.getParameter("title"))
                .description(request.getParameter("description"))
                .taskHour(Float.parseFloat(request.getParameter("taskHour")))
                .productivity(Float.parseFloat(request.getParameter("productivity")))
                .userId(userDao.getUserIdByUsername(request.getParameter("username")));
        taskDao.insertTask(builder.build());
        response.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("task/task-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Task task = taskDao.selectTask(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("task/task-form.jsp");
        request.setAttribute("task", task);
        dispatcher.forward(request, response);
    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");


        Task.TaskBuilder builder = Task.builder();
        builder.id(Long.parseLong(request.getParameter("id")))
                .title(request.getParameter("title"))
                .taskHour(Float.parseFloat(request.getParameter("taskHour")))
                .productivity(Float.parseFloat(request.getParameter("productivity")))
                .userId(userDao.getUserIdByUsername(username));
        taskDao.updateTask(builder.build());
        response.sendRedirect("list");
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        taskDao.deleteTask(id);
        response.sendRedirect("list");
    }


}

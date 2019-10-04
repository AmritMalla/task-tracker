package com.amt.time_tracker.dao;

import com.amt.time_tracker.model.Task;
import com.amt.time_tracker.utils.JdbcUtils;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class TaskDao {

    private static final String TASK_INSERT_SQL =
            " INSERT INTO tasks (title, description,task_date,task_hour,productivity, user_id)" +
                    " VALUES (?,?,?,?,?,?);";

    private static final String TASK_SELECT_BY_ID_SQL = "SELECT id, title, description, task_date, task_hour, productivity FROM tasks WHERE id = ?";

    private static final String RECENT_TASK_BY_USER_ID = "SELECT * FROM tasks where user_id = ?";

    private static final String TASK_DELETE_BY_ID_SQL = "DELETE FROM tasks WHERE id = ?;";

    private static final String UPDATE_TASK = "UPDATE tasks SET title = ?, description = ?, task_hour = ?, productivity = ? WHERE id = ?;";

    public void insertTask(Task task) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(TASK_INSERT_SQL)) {
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
            preparedStatement.setFloat(4, task.getTaskHour());
            preparedStatement.setFloat(5, task.getProductivity());
            preparedStatement.setLong(6, task.getUserId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JdbcUtils.printSQLException(e);
        }
    }

    public Task selectTask(Long taskId) {
        Task task = null;
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(TASK_SELECT_BY_ID_SQL)) {
            preparedStatement.setLong(1, taskId);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDate taskDate = resultSet.getDate("task_date").toLocalDate();
                Float taskHour = resultSet.getFloat("task_hour");
                Float productivity = resultSet.getFloat("productivity");
                Long userId = resultSet.getLong("user_id");

                task = new Task(id, title, description, taskDate, taskHour, productivity, userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public List<Task> selectRecentTasksForUser(Long userId) {
        List<Task> taskList = new ArrayList<>();

        try (Connection connection = JdbcUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(RECENT_TASK_BY_USER_ID)) {

            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDate taskDate = resultSet.getDate("task_date").toLocalDate();
                Float taskHour = resultSet.getFloat("task_hour");
                Float productivity = resultSet.getFloat("productivity");
                Long userId1 = resultSet.getLong("user_id");

                taskList.add(new Task(id, title, description, taskDate, taskHour, productivity, userId1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public boolean deleteTask(Long id) {
        boolean deleted = false;
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(TASK_DELETE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            deleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    public boolean updateTask(Task task) {
        boolean update = false;
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASK)) {
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setFloat(3, task.getTaskHour());
            preparedStatement.setFloat(4, task.getProductivity());
            preparedStatement.setLong(5, task.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            update = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            JdbcUtils.printSQLException(e);
        }
        return update;
    }
}

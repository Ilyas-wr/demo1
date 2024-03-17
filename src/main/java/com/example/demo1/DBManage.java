package com.example.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManage {
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/SpringTask",
                    "postgres",
                    "postgres"
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }

    public static User getUser(String email) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM \"users\" WHERE email = ?")) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String password = resultSet.getString("password");
                    String fullName = resultSet.getString("full_name");
                    user = new User();
                    user.setFullName(fullName);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setId(id);
                }
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public static void registUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users(email, password, full_name) VALUES (?, ?, ?)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());

            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addBlog(Blog blog) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO blogs(title,content,userid,postdate) VALUES (?, ?, ?,now())");
            preparedStatement.setString(1, blog.getTitle());
            preparedStatement.setString(2, blog.getContent());
            preparedStatement.setLong(3, blog.getUser().getId());

            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Blog> getAllBlog(){
        List<Blog> blogs = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT blogs.id, blogs.title,blogs.content,users.full_name,blogs.postdate FROM blogs INNER JOIN users ON blogs.userid = users.id"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String id = String.valueOf(resultSet.getLong("id"));
                String title = resultSet.getString("title");
                String text = resultSet.getString("content");
                String username = resultSet.getString("full_name");
                Timestamp time = resultSet.getTimestamp("postdate");

                User user = new User();
                user.setFullName(username);

                Blog blog = new Blog();
                blog.setId(Long.valueOf(id));
                blog.setTitle(title);
                blog.setContent(text);
                blog.setUser(user);
                blog.setPostDate(time);

                blogs.add(blog);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return blogs;
    }

    public static Blog getBlogById(Long id){
        Blog blog = null;

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT blogs.title,blogs.content,blogs.postdate,users.full_name FROM blogs INNER JOIN users ON blogs.userid=users.id WHERE blogs.id=?");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                String title = resultSet.getString("title");
                String text = resultSet.getString("content");
                String username = resultSet.getString("full_name");
                Timestamp time = resultSet.getTimestamp("postdate");

                User user = new User();
                user.setFullName(username);

                blog = new Blog();
                blog.setTitle(title);
                blog.setContent(text);
                blog.setUser(user);
                blog.setPostDate(time);

            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

return blog;
    }

    public static void addComm(Comments comments) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO comment(commenttext,userid,blogid,postdate) VALUES (?, ?, ?,now())");
            preparedStatement.setString(1, comments.getComment());
            preparedStatement.setLong(2, comments.getUser().getId());
            preparedStatement.setLong(3, comments.getBlog().getId());

            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

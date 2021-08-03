package dao;

import model.ClassHV;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectClass {
    static Connection connection;
    static ArrayList<ClassHV> listClass = new ArrayList<>();

    static {
        try {
            connection = ConnectionMySQL.getConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ClassHV> select() throws SQLException, ClassNotFoundException {
        String select = "select * from class";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(select);

        while (resultSet.next()) {
            int id = Integer.parseInt(resultSet.getString("id"));
            String className = resultSet.getString("className");
            listClass.add(new ClassHV(id, className));
        }
        return listClass;
    }
}


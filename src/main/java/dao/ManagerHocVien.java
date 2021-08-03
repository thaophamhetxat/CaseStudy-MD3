package dao;

import model.HocVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerHocVien {
    static Connection connection;
    static ArrayList<HocVien> listHocVien = new ArrayList<>();
    static {
        try {
            connection = ConnectionMySQL.getConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<HocVien> select() throws SQLException, ClassNotFoundException {
        String select = "select * from hocvien";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(select);

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String date = resultSet.getString("date");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            int classroom = resultSet.getInt("classroom");

            listHocVien.add(new HocVien(name, date, address, phone,email,classroom));
        }
        return listHocVien;
    }

    public static void create(HocVien hocVien) throws SQLException {
        String create = "insert into hocvien value(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(create);
        preparedStatement.setString(1, hocVien.getName());
        preparedStatement.setString(2, hocVien.getDate());
        preparedStatement.setString(3, hocVien.getAddress());
        preparedStatement.setString(4, hocVien.getPhone());
        preparedStatement.setString(5, hocVien.getEmail());
        preparedStatement.setInt(6, hocVien.getClassroom());
        preparedStatement.execute();

    }

    public static void edit(HocVien hocVien) throws SQLException {
        String edit = "update hocvien set date=?,address=?,phone=?,email=?,classroom=? where name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(edit);
        preparedStatement.setString(1, hocVien.getDate());
        preparedStatement.setString(2, hocVien.getAddress());
        preparedStatement.setString(3, hocVien.getPhone());
        preparedStatement.setString(4, hocVien.getEmail());
        preparedStatement.setInt(5, hocVien.getClassroom());
        preparedStatement.setString(6, hocVien.getName());
        preparedStatement.execute();
    }

    public static void delete(String name) throws SQLException {
        String delete = "delete from hocvien where name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setString(1, name);
        preparedStatement.execute();
    }

    public static ArrayList<HocVien> findByName(String findName) throws SQLException {
        ArrayList<HocVien> findList = new ArrayList<>();
        String findByName = "select * from hocvien where name like '%" + findName + "%'";
        PreparedStatement preparedStatement = connection.prepareStatement(findByName);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String date = resultSet.getString("date");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            int classroom = resultSet.getInt("classroom");

            findList.add(new HocVien(name, date, address, phone,email,classroom));
        }
        return findList;
    }
    public int getTotalHocVien() throws SQLException {
        String coun = "select count(*) from hocvien";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(coun);
        while (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }
    public List<HocVien>pagingHocVien(int index) throws SQLException {
        List<HocVien> list  = new ArrayList<>();
        String query = "select * from hocvien LIMIT ?,3";


        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return list;
    }
}

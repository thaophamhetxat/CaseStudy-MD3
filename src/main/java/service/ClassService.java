package service;

import dao.SelectClass;
import model.ClassHV;

import java.sql.SQLException;
import java.util.ArrayList;


public class ClassService {
    public static ArrayList<ClassHV> listClass = new ArrayList<>();

    public ClassService() throws SQLException, ClassNotFoundException {
        listClass = SelectClass.select();


    }
}

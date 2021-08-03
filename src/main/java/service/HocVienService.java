package service;

import dao.ManagerHocVien;
import model.HocVien;

import java.sql.SQLException;
import java.util.ArrayList;

public class HocVienService {

    public ArrayList<HocVien> listHocVien= new ArrayList<>();

    public HocVienService() {
        try {
            listHocVien = (ArrayList<HocVien>) ManagerHocVien.select();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void save(HocVien hocVien) throws SQLException {
        ManagerHocVien.create(hocVien);
        listHocVien.add(hocVien);
    }

    public void edit(HocVien hocVien, int index) throws SQLException {
        ManagerHocVien.edit(hocVien);
        listHocVien.set(index, hocVien);
    }

    public void delete(int index) throws SQLException {
        ManagerHocVien.delete(listHocVien.get(index).getName());
        listHocVien.remove(index);
    }


    public ArrayList<HocVien> findByName(String name) throws SQLException {
        return ManagerHocVien.findByName(name);

    }


}

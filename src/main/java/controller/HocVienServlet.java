package controller;

import dao.ManagerHocVien;
import model.HocVien;
import service.ClassService;
import service.HocVienService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "HocVienServlet", value = "/hocvien")
public class HocVienServlet extends HttpServlet {
    HocVienService hocVienService = new HocVienService();
    ManagerHocVien managerHocVien = new ManagerHocVien();
    ClassService classService;

    {
        try {
            classService = new ClassService();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher requestDispatcher;
        if (action == null) {
            action = "";
        }
        switch (action) {
//            case "homesp":
//                response.sendRedirect("SanPham/showSanPham.jsp");
//                break;
            case "pag":
                processRequest(request,response);
                break;
            case "create":
                request.setAttribute("listClass", classService.listClass);
                requestDispatcher = request.getRequestDispatcher("/createHocVien.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "edit":
                showEdit(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            default:
                request.setAttribute("listHocVien", hocVienService.listHocVien);
                requestDispatcher = request.getRequestDispatcher("showHocVien.jsp");
                requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher requestDispatcher;
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                create(request, response);
                break;
            case "edit":
                edit(request, response);
                break;
            case "find":
                find(request, response);
                break;
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String date = request.getParameter("date");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        int classroom = Integer.parseInt(request.getParameter("classroom"));


        HocVien hocVien = new HocVien(name, date, address, phone, email, classroom);
        try {
            hocVienService.save(hocVien);
            request.setAttribute("listHocVien", hocVienService.listHocVien);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("showHocVien.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameEdit = request.getParameter("name");
        String dateEdit = request.getParameter("date");
        String addressEdit = request.getParameter("address");
        String phoneEdit = request.getParameter("phone");
        String emailEdit = request.getParameter("email");
        int classroomEdit = Integer.parseInt(request.getParameter("classroom"));

        HocVien hocVienEdit = new HocVien(nameEdit,dateEdit,addressEdit,phoneEdit,emailEdit,classroomEdit);

        int index = Integer.parseInt(request.getParameter("index"));
        try {
            hocVienService.edit(hocVienEdit, index);
            request.setAttribute("listHocVien", hocVienService.listHocVien);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("showHocVien.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int index = Integer.parseInt(request.getParameter("index"));
        try {
            hocVienService.delete(index);
            response.sendRedirect("/hocvien");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int indexEdit = Integer.parseInt(request.getParameter("index"));
        request.setAttribute("hocvien", hocVienService.listHocVien.get(indexEdit));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("editHocVien.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String findName = request.getParameter("findName");
        try {
            request.setAttribute("listHocVien", hocVienService.findByName(findName));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("showHocVien.jsp");
            requestDispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        try {
            int count = managerHocVien.getTotalHocVien();//1
            int endPage = count/3;
            if(count%3 !=0){
                endPage++;
            }
            request.setAttribute("endPage",endPage);
            requestDispatcher = request.getRequestDispatcher("/showHocVien.jsp");
            requestDispatcher.forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

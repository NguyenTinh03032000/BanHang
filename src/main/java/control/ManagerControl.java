/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Account;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ManagerControl", urlPatterns = {"/manager"})
public class ManagerControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String indexPage = request.getParameter("index");//lay tu phan trang       
        if(indexPage == null) {
        	indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);

        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        int id = a.getId();
        DAO dao = new DAO();
        List<Product> list = dao.getProductBySellID(id);// list all
        List<Product> listPage = dao.pagingProduct(index);//list phan trang
        List<Category> listC = dao.getAllCategory();
        int count = dao.getTotalProduct();//lay so luong product hien co
        int endPage = count/10;//tinh so trang
        if(count % 10 != 0) {
        	endPage++;
        } 

        request.setAttribute("listCC", listC);
        request.setAttribute("listP", list);//tat ca product
        request.setAttribute("listPPage", listPage);//Lay product phan trang
        request.setAttribute("tag", index);//danh dau trang dang chon
        request.setAttribute("endP", endPage);
        request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

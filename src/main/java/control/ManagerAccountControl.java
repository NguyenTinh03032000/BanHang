package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.Account;
import entity.Category;
import entity.Product;

@WebServlet("/manageaccount")
public class ManagerAccountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        HttpSession session = request.getSession();
//        Account a = (Account) session.getAttribute("acc");
//        int id = a.getId();
    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        List<Account> list = dao.getAllAccount();
//        List<Category> listC = dao.getAllCategory();

        request.setAttribute("listAC", list);
//        request.setAttribute("listP", list);
        request.getRequestDispatcher("ManagerAccount.jsp").forward(request, response);
//        processRequest(request, response);
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

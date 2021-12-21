package control.ManagerAccount;

import dao.DAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EditAcountControl", urlPatterns = {"/editAccount"})
public class EditAcountControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 String puID = request.getParameter("uID");
         String puser = request.getParameter("user");
         String ppass = request.getParameter("pass");
         String pisSell = request.getParameter("isSell");
         String pisAdmin = request.getParameter("isAdmin");
         
         int IpuID =Integer.parseInt(puID);
         int IpisSell =Integer.parseInt(pisSell);
         int IpisAdmin =Integer.parseInt(pisAdmin);

         DAO dao = new DAO();
         dao.editAccount(IpuID,puser, ppass, IpisSell, IpisAdmin );
         response.sendRedirect("manageaccount");
//        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

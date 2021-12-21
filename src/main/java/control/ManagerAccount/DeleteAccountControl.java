package control.ManagerAccount;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
@WebServlet("/deleteAccount")
public class DeleteAccountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteAccountControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String aid = request.getParameter("aid");
		DAO dao = new DAO();
		dao.deleteAccount(aid);
		response.sendRedirect("manageaccount");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

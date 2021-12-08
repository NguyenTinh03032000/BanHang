package control;

import dao.DAO;
import entity.Account;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "OrderControl", urlPatterns = {"/order"})
public class OrderControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if(acc == null) { 
        	
        	//response.sendRedirect("Login.jsp");
        	PrintWriter out = response.getWriter();
        	out.println("<script>\r\n"
        			+ "        	var option = confirm(\"Bạn phải đăng nhập trước khi mua hàng\");\r\n"
        			+ "	       	 if(option === true){\r\n"
        			+ "	       		 window.location.href=\"Login.jsp\";\r\n"
        			+ "	       	 }else if(option === false){\r\n"
        			+ "	       		 window.location.href=\"/BanHangJSP/print\";\r\n"
        			+ "	       	 }\r\n"
        			+ "	       	</script>");
        	
	       	
        }else {
	        Cookie arr[] = request.getCookies();
	        List<Product> list = new ArrayList<>();
	        DAO dao = new DAO();
	        for (Cookie o : arr) {
	            if (o.getName().equals("id")) {
	                String txt[] = o.getValue().split("_");
	                for (String s : txt) {
	                    list.add(dao.getProductByID(s));
	                }
	            }
	        }
	        for (int i = 0; i < list.size(); i++) {
	            int count = 1;
	            for (int j = i+1; j < list.size(); j++) {
	                if(list.get(i).getId() == list.get(j).getId()){
	                    count++;
	                    list.remove(j);
	                    j--;
	                    list.get(i).setAmount(count);
	                }
	            }
	        }
	        for (Cookie o : arr) {
	            o.setMaxAge(0);
	            response.addCookie(o);
	        }        
	        response.sendRedirect("sendEmail");   
        }    
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
    }

}

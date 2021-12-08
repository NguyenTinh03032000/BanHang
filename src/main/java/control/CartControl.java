package control;

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

import dao.DAO;
import entity.Product;

@WebServlet(name = "CartControl", urlPatterns = {"/cart"})
public class CartControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id = request.getParameter("id");
        String is = request.getParameter("is");
        Cookie arr[] = request.getCookies();
        String txt = "";
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                txt = txt + o.getValue();
                o.setMaxAge(0);
                response.addCookie(o);
            }
        }
        if (txt.isEmpty()) {
            txt = id;
        } else {
            txt = txt + "_" + id;
        }
        Cookie c = new Cookie("id", txt);
        c.setMaxAge(60 * 60 * 24);
        response.addCookie(c);
        
        //get list product in cookies
        List<Product> list1 = new ArrayList<>();
        DAO dao = new DAO();
        if(arr != null) {
        	for (Cookie o : arr) {
                if (o.getName().equals("id")) {
                	String sum[] = o.getValue().split("_"); 
                	for (String s : sum) {
                        list1.add(dao.getProductByID(s));
                    }
                }
            }   
        }
    	PrintWriter out = response.getWriter();
        out.println(list1.size()+1);
        //response.sendRedirect("print");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Category;
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

@WebServlet(name = "HomeControl", urlPatterns = {"/home"})
public class HomeControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
  
        Cookie arr[] = request.getCookies();
        List<Product> list1 = new ArrayList<>();
        DAO dao = new DAO();
        if(arr != null) {
        	for (Cookie o : arr) {
                if (o.getName().equals("id")) {
                    String txt[] = o.getValue().split("_");
                    for (String s : txt) {
                        list1.add(dao.getProductByID(s));
                    }
                }
            }
	        if(!list1.isEmpty()) {
	        	for (int i = 0; i < list1.size(); i++) {
		            int count = 0;
		            for (int j = i+1; j < list1.size(); j++) {
		                if(list1.get(i).getId() == list1.get(j).getId()){
		                    count++;
		                    list1.remove(j);
		                    j--;
		                    list1.get(i).setAmount(count);
		                }
		            }
		        }
	        }
        }
        int sumProduct = list1.size();
        //DAO dao = new DAO();
        List<Product> list = dao.getTop3();
        List<Category> listC = dao.getAllCategory();
        Product last = dao.getLast();
        
        request.setAttribute("sumP", sumProduct);
        request.setAttribute("listP", list);
        request.setAttribute("listCC", listC);
        request.setAttribute("p", last);
        request.getRequestDispatcher("Home.jsp").forward(request, response);

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

package control;

import dao.DAO;
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

import org.apache.catalina.connector.Response;

@WebServlet(name = "ShowCartControl", urlPatterns = {"/print"})
public class ShowCartControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
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
            int count = 0;
            for (int j = i+1; j < list.size(); j++) {
                if(list.get(i).getId() == list.get(j).getId()){
                    count++;
                    list.remove(j);
                    j--;
                    list.get(i).setAmount(count);
                }
            }
        }
        double total = 0;
        for (Product o : list) {
            total = total + o.getAmount() * o.getPrice();
        }
        int sumProduct = list.size();       
        double sum = Math.round(1.1*total);
        request.setAttribute("sumP", sumProduct);
        request.setAttribute("list", list);
        request.setAttribute("total", total);
        request.setAttribute("vat", 0.1 * total);
        request.setAttribute("sum",  sum);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
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

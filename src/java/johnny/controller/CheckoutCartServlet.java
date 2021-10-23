/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package johnny.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import johnny.cart.CartObject;
import johnny.shoppa.ShoppaDAO;
import johnny.shoppa.ShoppaDTO;

/**
 *
 * @author JohnnyMC
 */
@WebServlet(name = "CheckoutCartServlet", urlPatterns = {"/CheckoutCartServlet"})
public class CheckoutCartServlet extends HttpServlet {

    private final String VIEW_ITEM_CONTROLLER = "ViewCartServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //1. Khách hàng tới chổ để giỏ
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. Khách hàng lấy giỏ hàng
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Khách hàng kiểm tra trong giỏ có item nào không
                    Map<String, ShoppaDTO> items = cart.getItems();
                    ShoppaDAO dao = new ShoppaDAO();
                    if (items != null) {
                        //4. Khách hàng thanh toán hàng
                        for (Map.Entry<String, ShoppaDTO> item : items.entrySet()) {
                            dao.removeNCopies(item.getKey(), item.getValue().getQuantity());
                            dao.checkoutGame(item.getValue());
                        };
                        //5. Dọn giỏ hàng
                        session.removeAttribute("CART");
                    }
                }
            }
        } catch (SQLException ex) {
            log("CheckoutCartServlet _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckoutCartServlet _ Naming " + ex.getMessage());

        } finally {
            response.sendRedirect(VIEW_ITEM_CONTROLLER);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

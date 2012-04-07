/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.CustomerEntity;
import entities.RewardTypeEntity;
import facades.RewardTypeEntityFacadeRemote;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managers.CustomerManagerRemote;
import testdata.TestData;

/**
 *
 * @author zen9
 */
public class LoginServlet extends HttpServlet {

    @EJB
    private RewardTypeEntityFacadeRemote rewardTypeEntityFacade;
    @EJB
    private CustomerManagerRemote customerManagerBean;
    private CustomerEntity customer;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher dispatcher;
        ServletContext servletContext = this.getServletContext();

        String path = "login";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null || username.equals("") || password.equals("")) {
            this.addDummyData();
            request.setAttribute("failed", new String("missing username or password"));
        } else {
            customer = this.customerManagerBean.loginCustomer(username, password);
            if (customer == null) {
                request.setAttribute("failed", new String("missing username or password"));
            } else {
                path = "redeem";
                request = this.setupAttributes(request);
            }
        }


        dispatcher = servletContext.getNamedDispatcher(path);
        if (dispatcher == null) {
            dispatcher = servletContext.getNamedDispatcher("error");
        }
        dispatcher.forward(request, response);

    }

    protected HttpServletRequest setupAttributes(HttpServletRequest request) {
        request.setAttribute("customer", customer);
        List<RewardTypeEntity> rewards = this.rewardTypeEntityFacade.findAll();
        request.setAttribute("rewards", rewards);
        return request;
    }

    protected void addDummyData() {
        try {
            TestData test = new TestData();
            test.addCustomer();
            test.addMerchants();
            test.addRewardType();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

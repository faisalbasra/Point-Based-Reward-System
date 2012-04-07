/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.CustomerEntity;
import entities.RedemptionEntity;
import entities.RedemptionLineEntity;
import entities.RewardTypeEntity;
import facades.CustomerEntityFacadeRemote;
import facades.RewardTypeEntityFacadeRemote;
import java.io.IOException;
import java.lang.String;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managers.RedemptionManagerRemote;

/**
 *
 * @author zen9
 */
public class RedeemServlet extends HttpServlet {

    @EJB
    private CustomerEntityFacadeRemote customerEntityFacade;
    @EJB
    private RewardTypeEntityFacadeRemote rewardTypeEntityFacade;
    @EJB
    private RedemptionManagerRemote redemptionManagerBean;
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

        Enumeration params = request.getParameterNames();
        String element;
        int requiredPoints = 0;
        Boolean isRewardQuantityEnough = true;
        String customerId = request.getParameter("customerId");
        customer = this.customerEntityFacade.find(customerId);

        while (params.hasMoreElements()) {
            element = (String) params.nextElement();
            try {
                requiredPoints += this.decodeParam(element, request);
            } catch (Exception ex) {
                isRewardQuantityEnough = false;
                request.setAttribute("message", ex.getMessage());
            }
        }
        if (isRewardQuantityEnough) {
            if (requiredPoints > customer.getPoints()) {
                request.setAttribute("message", "You do not have sufficient point, you require " + requiredPoints);
            } else {
                RedemptionEntity redemption = this.redemptionManagerBean.createRedemption(this.customer.getId(), new Date());
                params = request.getParameterNames();
                while (params.hasMoreElements()) {
                    element = (String) params.nextElement();
                    redemption = this.addRedemptionForCustomer(element, request, redemption);
                }
                this.customer.setPoints(customer.getPoints() - requiredPoints);
                this.customerEntityFacade.edit(customer);
                request.setAttribute("message", "You have redeemed successfully");
            }
        }
        RequestDispatcher dispatcher;
        ServletContext servletContext = this.getServletContext();

        dispatcher = servletContext.getNamedDispatcher("redeem");
        request = this.setupAttributes(request);
        dispatcher.forward(request, response);

    }

    protected HttpServletRequest setupAttributes(HttpServletRequest request) {
        request.setAttribute("customer", customer);
        List<RewardTypeEntity> rewards = this.rewardTypeEntityFacade.findAll();
        request.setAttribute("rewards", rewards);
        return request;
    }

    protected int decodeParam(String param, HttpServletRequest request) throws Exception {
        String[] kv = param.split("_");
        int quantity;
        int merchantId;
        String quantityStr;
        if (kv[0].equals("rewardquantity")) {

            quantityStr = request.getParameter("rewardquantity_" + kv[1]);
            if (quantityStr != null && !quantityStr.equals("0")) {

                RewardTypeEntity reward = this.rewardTypeEntityFacade.find(Long.parseLong(kv[1]));
                quantity = Integer.parseInt(quantityStr);
                merchantId = Integer.parseInt(request.getParameter("merchants_" + kv[1]));
                if (reward.getQuantities() < quantity) {
                    throw new Exception("Not enough reward to be redeemed");
                }
                System.out.println("The rewardType id is " + kv[1]);
                System.out.println("The requested quantity is " + quantity);
                System.out.println("The selected merchant id is " + merchantId);
                return reward.getPoints() * quantity;

            }

        }

        return 0;
    }

    protected RedemptionEntity addRedemptionForCustomer(String param, HttpServletRequest request, RedemptionEntity redemption) {
        String[] kv = param.split("_");
        int quantity;
        int merchantId;
        String quantityStr;
        if (kv[0].equals("rewardquantity")) {

            quantityStr = request.getParameter("rewardquantity_" + kv[1]);
            if (quantityStr != null && !quantityStr.equals("0")) {
                quantity = Integer.parseInt(quantityStr);
                merchantId = Integer.parseInt(request.getParameter("merchants_" + kv[1]));
                RedemptionLineEntity line = this.redemptionManagerBean.createRedemptionLine(quantity, Long.parseLong(kv[1]));
                redemption = this.redemptionManagerBean.addRedemptionLine(redemption.getId(), line.getId());
            }

        }
        return redemption;

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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import entities.ClaimEntity;
import entities.RedemptionEntity;
import entities.RedemptionLineEntity;
import facades.RedemptionEntityFacadeRemote;
import facades.RedemptionLineEntityFacadeRemote;
import facades.RewardTypeEntityFacadeRemote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zen9
 */
public class ReportServlet extends HttpServlet {
    @EJB
    private RedemptionEntityFacadeRemote redemptionEntityFacade;
    @EJB
    private RedemptionLineEntityFacadeRemote redemptionLineEntityFacade;
    @EJB
    private RewardTypeEntityFacadeRemote rewardTypeEntityFacade;
   
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
        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReportServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReportServlet at " + request.getContextPath () + "</h1>");
            ClaimEntity claim;
            List<RedemptionEntity> redemptions = this.redemptionEntityFacade.findAll();
            for(RedemptionEntity r:redemptions){
                out.println("<div>");
                out.println("<strong>Redemption " + r.getId() + "</strong>");
                out.println("<br>");
                out.println("redeemed at: " + r.getDateOfRedemption());
                out.println("<br>");
                out.println("customer is: " + r.getCustomer().getName());
                out.println("<br>");
                out.println("customer stays at: " + r.getCustomer().getContact().getAddress());
                out.println("<br>");
                Set<RedemptionLineEntity> lines = r.getRedemptionlines();
                for(RedemptionLineEntity l:lines){
                    out.println("&nbsp; RewardType:" + l.getRewardType().getName());
                    out.println("<br>");
                    out.println("&nbsp; Quantity redeemed: " + l.getQuantity());
                    out.println("<br>");
                }
                claim = r.getClaim();
                if(claim != null){
                    out.print("The redemption is claimed at ");
                    out.print(claim.getDateOfClaim());
                }
                out.println("<br><br>");
                out.println("<div></div></div>");
            }

            out.println("</body>");
            out.println("</html>");
            
        } finally { 
            out.close();
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

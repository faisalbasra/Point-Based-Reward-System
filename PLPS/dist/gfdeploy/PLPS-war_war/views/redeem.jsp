<%-- 
    Document   : redeem
    Created on : Mar 27, 2012, 8:10:10 PM
    Author     : zen9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, entities.RewardTypeEntity, entities.MerchantEntity, entities.CustomerEntity" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
            String message;
            if (request.getAttribute("message") != null) {
                message = (String) request.getAttribute("message");
            } else {
                message = "";
            }
            CustomerEntity customer = (CustomerEntity) request.getAttribute("customer");
            List<RewardTypeEntity> rewards = (List) request.getAttribute("rewards");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Redeem loyalty points</title>
        <style type="text/css">
            .linediv span
            {
                display:block;
                float:left;
                width:130px;
            }
        </style>
    </head>
    <body>
        <h1>Hi, Your have <%=customer.getPoints()%> points left</h1>

        <h2><%=message%></h2>

        <form action="/PLPS-war/RedeemServlet">
            <div class="linediv">
                <span>Name</span>
                <span>Points</span>
                <span>Quantities</span>
                <span>Choose quantity</span>
                <span>Choose from Merchants</span>
            </div>
            <div style="clear:both;"></div>
            <%
            if (rewards != null) {
                for (RewardTypeEntity r : rewards) {

            %>
            <div class="linediv">
                <span><%=r.getName()%></span>
                <span><%=r.getPoints()%></span>
                <span><%=r.getQuantities()%></span>
                <span>
                    <input type="text" name="rewardquantity_<%=r.getId()%>" value="0"/>
                </span>
                <%
                    for (MerchantEntity m : r.getMerchants()) {
                %>

                <span>
                    <input type="radio" name="merchants_<%=r.getId()%>" checked="checked" value="<%=m.getId()%>" /> <%=m.getName()%> <br>
                </span>

                <%
                    }
                %>
            </div>
            <div style="clear:both;"></div>
            <%
                }
            }
            %>
            <input type="hidden" name="customerId" value="<%=customer.getId()%>"/>
            <input type="submit" value="Redeem" name="redeem" />
        </form>




    </body>
</html>

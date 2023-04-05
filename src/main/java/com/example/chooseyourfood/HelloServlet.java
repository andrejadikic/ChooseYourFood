package com.example.chooseyourfood;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "orders", value = "/orders")
public class HelloServlet extends HttpServlet {
    private static Map<String, List<String>> food;
    private Map<String, Integer> order;
    private String password;

    public void init() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/andrejadikic/projekti/ChooseYourFood/ChooseYourFood/src/main/resources/password.txt"));
            password = reader.readLine();
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("password") == null || !request.getParameter("password").equals(password)) {
            response.getOutputStream().println("Password je obavezan");
            response.setStatus(403);
            return;
        }
        PrintWriter out = response.getWriter();
        out.println("<html><body><form method=\"POST\" action = \"orders\">");
        out.println("<h1>" + "Meals" + "</h1>");
        out.println("<html><body>");

        List<Order> listOfMeals = (List<Order>) getServletContext().getAttribute("allOrders");
        Map<String, List<String>> food = (Map<String, List<String>>) getServletContext().getAttribute("food");

        for(String day: food.keySet()) {
            out.println("<h1>" + day + "</h1><br>");
            out.println("<table style=\"width:100 % \">");
            out.println("<tr> <th>#</th> <th>Meal</th> <th>Number of orders</th> </tr>");
            int j = 0;
            for (Order meal : listOfMeals) {
                if (meal.getDay().equals(day)) {
                    j++;
                    out.println("<tr> <th>" + j + "</th> <th>" + meal.getFood() + "</th> <th>" + meal.getNumberOfOrders() + "</th> </tr>");
                }
            }
            out.println("<style>\n" +
                    "table, th, td {\n" +
                    "  border: 1px solid pink;\n" +
                    "}\n" +
                    "</style></table>\n");
        }

        out.println("--------------------------<br>");
        out.println("<br><input type=\"submit\" name\"submit\" value\"Clear\"/></form>");
        out.println("</body></html>");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        List<Order> listOfMeals = (List<Order>) getServletContext().getAttribute("allOrders");
        for (Meal o: listOfMeals) {
            o.setOrderNumber(0);
        }
        Map<String, List<Meal>> map = (Map<String, List<Meal>>) getServletContext().getAttribute("map");
        for (Map.Entry<String, List<Meal>> m: map.entrySet()){
            getServletContext().setAttribute(m.getKey(), null);
        }
        getServletContext().setAttribute("map", map);
    }



    public void destroy() {
    }
}
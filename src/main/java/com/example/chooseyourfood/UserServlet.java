package com.example.chooseyourfood;


import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "user", value = "/user")
public class UserServlet extends HttpServlet {
    private boolean flag;
    private Map<String, List<String>> food;
    private Map<String, List<Order>> orderForUser;
    private List<String> users;
    private Map<String, List<Order>> foodForDay;
    private List<Order> orders;

    public void init() {
        food = new LinkedHashMap<>();
        //meals = new LinkedHashMap<>();
        orders = new LinkedList<>();
        flag = true;
        food.put("ponedeljak",foodForDay("ponedeljak.txt"));
        food.put("utorak",foodForDay("utorak.txt"));
        food.put("sreda",foodForDay("sreda.txt"));
        food.put("cetvrtak",foodForDay("cetvrtak.txt"));
        food.put("petak",foodForDay("petak.txt"));

        getServletContext().setAttribute("allOrders", orders);
        getServletContext().setAttribute("food", food);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(flag) {
            response.setContentType("text/html");
            request.setAttribute("food", food);
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        }else{
            response.sendRedirect("/greska.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        flag=false;
        getServletContext().setAttribute(request.getSession().getId(),false);
        List<Order> list = new ArrayList<>();
        for(String day: food.keySet()){
            String meal = request.getParameter(day);
            Order order = new Order(day,meal);
            if(orders.contains(order)){
                orders.get(orders.indexOf(order)).addOrder();
            }
            synchronized (this){
                list.add(order);
                users.add(request.getSession().getId());
            }
        }


//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/andrejadikic/projekti/ChooseYourFood/ChooseYourFood/nesto.txt"));
//        bufferedWriter.write("njnj");
//        // post hello servletu da je obavljena kupovina
//        System.out.println(request.getParameter("ponedeljak"));
//        request.setAttribute("food", food);
        //request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

        response.setStatus(200);
        response.sendRedirect("/potvrda.html");
    }

    public void destroy() {
    }

    private List<String> foodForDay(String fileName){
        List<String> list = new ArrayList<>();
        String path = "/Users/andrejadikic/projekti/ChooseYourFood/ChooseYourFood/src/main/resources/" + fileName;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                orders.add(new Order(fileName,line));
                list.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public boolean isFlag() {
        return flag;
    }

    public Map<String, List<String>> getFood() {
        return food;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setFood(Map<String, List<String>> food) {
        this.food = food;
    }
}

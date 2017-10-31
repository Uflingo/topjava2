package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<MealWithExceed> mealWithExceeds = MealsUtil.getAllWithExceeded(MealsUtil.meals,2000);
//        int k = 101;
//        request.setAttribute("MealList", k);
//        request.getRequestDispatcher("/meals.jsp").forward(request, response);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int k = 101;
        req.setAttribute("k",k);
        req.getRequestDispatcher("/meals.jsp").forward(req,resp);
    }
}

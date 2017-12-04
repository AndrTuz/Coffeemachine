package com.tuz.coffemachine.commands;

import com.tuz.coffemachine.models.Drink;
import com.tuz.coffemachine.models.Order;
import com.tuz.coffemachine.models.User;
import com.tuz.coffemachine.resources.ConfigurationManager;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class GoToUsermain implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        ArrayList<Drink> drinks = (ArrayList<Drink>) session.getAttribute("drinks_list");
        String langAtr = (String) session.getAttribute("lang");
        request.getSession().invalidate();
        HttpSession session1 = request.getSession(true);
        session1.setAttribute("lang", langAtr);
        session1.setAttribute("user", user);
        session1.setAttribute("drinks_list", drinks);
        String page = ConfigurationManager.getProperty("path.page.usermain");
        session1.setAttribute("current_page", page);
        return page;
    }
}

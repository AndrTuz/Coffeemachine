package com.tuz.coffemachine.commands;

import com.tuz.coffemachine.resources.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GoToRefillcard implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String prev_page = (String)session.getAttribute("current_page");
        session.setAttribute("prev_page", prev_page);
        String page = ConfigurationManager.getProperty("path.page.refillcard");
        session.setAttribute("current_page", page);
        return page;
    }
}

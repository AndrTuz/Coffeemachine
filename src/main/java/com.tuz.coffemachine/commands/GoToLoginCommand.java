package com.tuz.coffemachine.commands;

import com.tuz.coffemachine.resources.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GoToLoginCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String page = ConfigurationManager.getProperty("path.page.login");
        session.setAttribute("current_page", page);
        return page;
    }
}

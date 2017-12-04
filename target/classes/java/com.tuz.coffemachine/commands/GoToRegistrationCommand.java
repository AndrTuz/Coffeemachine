package com.tuz.coffemachine.commands;

import com.tuz.coffemachine.resources.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GoToRegistrationCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String page = ConfigurationManager.getProperty("path.page.registration");
        session.setAttribute("current_page", page);
        return page;
    }
}

package com.tuz.coffemachine.commands;

import com.tuz.coffemachine.resources.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {
    private static final String ATTR_NAME_LANG = "lang";
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String page = ConfigurationManager.getProperty("path.page.startpage");
        if(session.getAttribute(ATTR_NAME_LANG)==null) {
            session.setAttribute(ATTR_NAME_LANG, "ru_RU");
        }
        String lang = (String) session.getAttribute(ATTR_NAME_LANG);
        request.getSession().invalidate();
        HttpSession session1 = request.getSession(true);
        session1.setAttribute(ATTR_NAME_LANG, lang);

        return page;
    }
}

package com.tuz.coffemachine.commands;

import com.tuz.coffemachine.resources.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetRussianCommand implements ActionCommand {
    private static final String ATTR_NAME_LANG = "lang" ;
    private static final String ATTR_NAME_PAGE = "current_page" ;

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute(ATTR_NAME_LANG, "ru_RU");
        String page = (String) session.getAttribute(ATTR_NAME_PAGE);
        if(page == null){
            page = ConfigurationManager.getProperty("path.page.startpage");
            session.setAttribute(ATTR_NAME_PAGE, page);
        }
        return page;
    }
}

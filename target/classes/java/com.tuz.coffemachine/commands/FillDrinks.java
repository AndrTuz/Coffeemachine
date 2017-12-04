package com.tuz.coffemachine.commands;

import com.tuz.coffemachine.logic.DrinkLogic;
import com.tuz.coffemachine.logic.exception.LogicException;
import com.tuz.coffemachine.resources.ConfigurationManager;
import com.tuz.coffemachine.resources.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FillDrinks implements ActionCommand {
    private static final Logger LOG= Logger.getLogger(FillDrinks.class);
    private static final String ATTR_NAME_LANG = "lang";
    private static final String ATTR_NAME_PAGE = "current_page";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String locale =null;
        String page =null;
        if(session.getAttribute(ATTR_NAME_LANG)==null){
            locale="ru";
            session.setAttribute(ATTR_NAME_LANG,"ru_RU");
        }else{
            locale = (String) session.getAttribute(ATTR_NAME_LANG);
            locale = locale.split("_")[0];
        }
        try {
            session.setAttribute("drinks_list", new DrinkLogic().fillingDrinks());
            session.setAttribute("filleddrinksmessage", new MessageManager().getProperty("message.filleddrinksmessage", locale));
            page = ConfigurationManager.getProperty("path.page.adminpage");
            session.setAttribute(ATTR_NAME_PAGE, page);
        }catch (LogicException ex){
            LOG.error(ex);
            page=ConfigurationManager.getProperty("path.page.error");
            return page;
        }
        return page;
    }
}

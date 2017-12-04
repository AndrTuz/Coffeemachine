package com.tuz.coffemachine.commands;

import com.tuz.coffemachine.models.Drink;
import com.tuz.coffemachine.models.Order;
import com.tuz.coffemachine.models.User;
import com.tuz.coffemachine.models.exception.IncorrectArgumentException;
import com.tuz.coffemachine.resources.ConfigurationManager;
import com.tuz.coffemachine.resources.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class CreateOrderCommand implements ActionCommand {

    private static final Logger LOG= Logger.getLogger(CreateOrderCommand.class);
    private static final String DRINK_1_COUNT = "drink_1_count";
    private static final String DRINK_2_COUNT = "drink_2_count";
    private static final String DRINK_3_COUNT = "drink_3_count";
    private static final String DRINK_4_COUNT = "drink_4_count";
    private static final String DRINK_5_COUNT = "drink_5_count";
    private static final String DRINK_6_COUNT = "drink_6_count";
    private static final String DRINKS_LIST = "drinks_list";
    private static final String USER = "user";
    private static final String ATTR_NAME_LANG = "lang";
    private static final String ATTR_NAME_PAGE = "current_page";

    private int checkDrink(int drink_count, ArrayList<Drink> user_drinks, Drink drink) throws IncorrectArgumentException {
        int sum=0;
        if (drink_count > 0){
            for (int i = 0; i < drink_count; i++) {
                sum += drink.getCost();
        }
        drink.setCountInOrder(drink_count);
        user_drinks.add(drink);
        }
        return sum;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String page = null;
        String locale=null;
        if(session.getAttribute(ATTR_NAME_LANG)==null){
            locale="ru";
            session.setAttribute(ATTR_NAME_LANG,"ru_RU");
        }else{
            locale = (String) session.getAttribute(ATTR_NAME_LANG);
            locale = locale.split("_")[0];
        }

        ArrayList<Drink> drinks = (ArrayList<Drink>)session.getAttribute(DRINKS_LIST);
        ArrayList<Drink> user_drinks = new ArrayList<>();
        User currentUser = (User) session.getAttribute(USER);
        int total_sum_order = 0;

        try {
            int drink_1_count = Integer.parseInt(request.getParameter(DRINK_1_COUNT));
            int drink_2_count = Integer.parseInt(request.getParameter(DRINK_2_COUNT));
            int drink_3_count = Integer.parseInt(request.getParameter(DRINK_3_COUNT));
            int drink_4_count = Integer.parseInt(request.getParameter(DRINK_4_COUNT));
            int drink_5_count = Integer.parseInt(request.getParameter(DRINK_5_COUNT));
            int drink_6_count = Integer.parseInt(request.getParameter(DRINK_6_COUNT));

            total_sum_order += checkDrink(drink_1_count, user_drinks, drinks.get(0));
            total_sum_order += checkDrink(drink_2_count, user_drinks, drinks.get(1));
            total_sum_order += checkDrink(drink_3_count, user_drinks, drinks.get(2));
            total_sum_order += checkDrink(drink_4_count, user_drinks, drinks.get(3));
            total_sum_order += checkDrink(drink_5_count, user_drinks, drinks.get(4));
            total_sum_order += checkDrink(drink_6_count, user_drinks, drinks.get(5));

            if (total_sum_order == 0){
                session.setAttribute("message", new MessageManager().getProperty("message.wrongchoose", locale));
                page = ConfigurationManager.getProperty("path.page.usermain");
                session.setAttribute(ATTR_NAME_PAGE, page);
                return page;
            }
            Order userOrder = new Order();
            userOrder.setUser(currentUser);
            userOrder.setDrinks(user_drinks);
            userOrder.setTotal_sum(total_sum_order);
            session.setAttribute("user_order", userOrder);
        }catch (IllegalArgumentException ex){
            System.out.println("Wrong input");
            session.setAttribute("message", new MessageManager().getProperty("message.errorOrder", locale));
            page = ConfigurationManager.getProperty("path.page.usermain");
            session.setAttribute(ATTR_NAME_PAGE, page);
            return page;
        }catch (IncorrectArgumentException ex){
            LOG.error(ex);
            page=ConfigurationManager.getProperty("path.page.error");
            return page;
        }


        if (total_sum_order > currentUser.getTotal_sum()){
            session.setAttribute("errorConfirmOrder", new MessageManager().getProperty("message.errorSumOrder", locale));
        }
        page = ConfigurationManager.getProperty("path.page.order");
        session.setAttribute(ATTR_NAME_PAGE, page);

        return page;
    }
}

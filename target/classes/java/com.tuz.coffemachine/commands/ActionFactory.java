package com.tuz.coffemachine.commands;

import com.tuz.coffemachine.commands.client.CommandEnum;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request){
        ActionCommand current = null;
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()){
            return current;
        }
        CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
        current = currentEnum.getCurrentCommand();
        return current;
    }
}

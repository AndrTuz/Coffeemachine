package com.tuz.coffemachine.commands.client;

import com.tuz.coffemachine.commands.*;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationNewUserCommand();
        }
    },
    GOTOREGISTRATION {
        {
            this.command = new GoToRegistrationCommand();
        }
    },
    GOTOREFILLCARD {
        {
            this.command = new GoToRefillcard();
        }
    },
    GOTOUSERMAIN {
        {
            this.command = new GoToUsermain();
        }
    },
    CREATEORDER {
        {
            this.command = new CreateOrderCommand();
        }
    },
    CONFIRMORDER {
        {
            this.command = new ConfirmOrderCommand();
        }
    },
    REFILLCARD {
        {
            this.command = new RefillCardCommand();
        }
    },
    FILLDRINKS {
        {
            this.command = new FillDrinks();
        }
    },
    GOTOLOGIN {
        {
            this.command = new GoToLoginCommand();
        }
    },
    SETRUSSIAN {
        {
            this.command = new SetRussianCommand();
        }
    },
    SETENGLISH {
        {
            this.command = new SetEnglishCommand();
        }
    },
    DELETEDRINK {
        {
            this.command = new DeleteDrinkCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}

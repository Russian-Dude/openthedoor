package com.rdude.openthedoor.command.text;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Collection;

@Component
public abstract class TextCommand {

    public abstract String getCommandText();

    public abstract String getDescription();

    public abstract boolean isShowInMainMenu();

    public abstract Collection<BotApiMethod<?>> execute(Message message);

    public boolean checkMessage(Message message) {
        return getCommandText().equals(message.getText());
    }

}

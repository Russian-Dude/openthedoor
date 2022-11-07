package com.rdude.openthedoor.command.text;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collection;
import java.util.List;

@Component
public class StartTextCommand extends TextCommand {

    @Override
    public String getCommandText() {
        return "/start";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean isShowInMainMenu() {
        return false;
    }

    @Override
    public Collection<BotApiMethod<?>> execute(Message message) {
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("...");
        ReplyKeyboardMarkup replyKeyboardMarkup = ReplyKeyboardMarkup.builder()
                .keyboardRow(new KeyboardRow(List.of(new KeyboardButton("откройте дверь"))))
                .build();
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return List.of(sendMessage);
    }
}

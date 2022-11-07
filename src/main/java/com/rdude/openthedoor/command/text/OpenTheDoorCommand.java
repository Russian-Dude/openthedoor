package com.rdude.openthedoor.command.text;

import com.rdude.openthedoor.command.text.TextCommand;
import com.rdude.openthedoor.replies.InlineCommand;
import com.rdude.openthedoor.replies.ReplyMessages;
import com.rdude.openthedoor.utils.InlineMarkupGenerator;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Collection;
import java.util.List;

@Component
public class OpenTheDoorCommand extends TextCommand {

    @Override
    public String getCommandText() {
        return "откройте дверь";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean isShowInMainMenu() {
        return true;
    }

    @Override
    public boolean checkMessage(Message message) {
        return message.getChat().isGroupChat() && super.checkMessage(message);
    }

    @Override
    public Collection<BotApiMethod<?>> execute(Message message) {
        String userName = message.getFrom().getFirstName();
        userName = userName.isBlank() ? message.getFrom().getUserName() : userName;

        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(ReplyMessages.openTheDoor(userName));
        sendMessage.setReplyMarkup(InlineMarkupGenerator.generate(InlineCommand.COMING));

        var deleteCommandMessage = new DeleteMessage(message.getChatId().toString(), message.getMessageId());

        return List.of(deleteCommandMessage, sendMessage);
    }
}

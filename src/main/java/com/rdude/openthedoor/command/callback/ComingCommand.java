package com.rdude.openthedoor.command.callback;

import com.rdude.openthedoor.replies.InlineCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Collection;
import java.util.List;

@Component
public class ComingCommand extends CallbackCommand {

    @Override
    public InlineCommand getInlineCommand() {
        return InlineCommand.COMING;
    }

    @Override
    public Collection<BotApiMethod<?>> execute(CallbackQuery query) {

        String comingUsername = query.getFrom().getFirstName();
        comingUsername = comingUsername.isBlank() ? query.getFrom().getUserName() : comingUsername;
        String messageText = query.getMessage().getText();

        if (messageText.startsWith(comingUsername)) {
            return List.of();
        }

        var editMessage = new EditMessageText();
        editMessage.setChatId(query.getMessage().getChatId());
        editMessage.setMessageId(query.getMessage().getMessageId());
        editMessage.setReplyMarkup(null);
        editMessage.setText(messageText + "\r\n\r\n" + comingUsername + " в пути!");

        return List.of(editMessage);
    }
}

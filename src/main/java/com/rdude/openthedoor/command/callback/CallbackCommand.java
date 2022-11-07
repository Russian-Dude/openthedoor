package com.rdude.openthedoor.command.callback;

import com.rdude.openthedoor.replies.InlineCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Collection;

@Component
public abstract class CallbackCommand {

    public abstract InlineCommand getInlineCommand();

    public abstract Collection<BotApiMethod<?>> execute(CallbackQuery query);

    public boolean checkCallback(CallbackQuery query) {
        return getInlineCommand().getCallback().equals(query.getData());
    }

}

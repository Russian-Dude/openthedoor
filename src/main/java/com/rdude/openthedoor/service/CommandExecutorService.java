package com.rdude.openthedoor.service;

import com.rdude.openthedoor.command.callback.CallbackCommand;
import com.rdude.openthedoor.command.text.TextCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandExecutorService {

    private final List<TextCommand> textCommands;
    private final List<CallbackCommand> callbackCommands;

    public Collection<BotApiMethod<?>> execute(Message message) {
        return textCommands.stream()
                .filter(command -> command.checkMessage(message))
                .findFirst()
                .map(command -> command.execute(message))
                .orElseGet(List::of);
    }

    public Collection<BotApiMethod<?>> execute(CallbackQuery callbackQuery) {
        return callbackCommands.stream()
                .filter(command -> command.checkCallback(callbackQuery))
                .findFirst()
                .map(command -> command.execute(callbackQuery))
                .orElseGet(List::of);
    }
}

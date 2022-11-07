package com.rdude.openthedoor.bot;

import com.rdude.openthedoor.config.BotConfig;
import com.rdude.openthedoor.service.CommandExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import java.util.List;

@Component
@Slf4j
public class OpenTheDoorBot extends SpringWebhookBot {

    private final BotConfig botConfig;

    private final CommandExecutorService commandExecutorService;

    public OpenTheDoorBot(SetWebhook setWebhook,
                          BotConfig botConfig,
                          CommandExecutorService commandExecutorService,
                          @Qualifier("main_menu_commands") List<BotCommand> mainMenuCommands) {
        super(setWebhook);
        this.botConfig = botConfig;
        this.commandExecutorService = commandExecutorService;
        tryToExecute(new SetMyCommands(mainMenuCommands, new BotCommandScopeDefault(), null));
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public String getBotPath() {
        return botConfig.getWebhookPath();
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            commandExecutorService.execute(message)
                    .forEach(this::tryToExecute);
        }

        CallbackQuery callbackQuery = update.getCallbackQuery();
        if (callbackQuery != null) {
            commandExecutorService.execute(callbackQuery)
                    .forEach(this::tryToExecute);
        }
        return null;
    }

    private void tryToExecute(BotApiMethod<?> botApiMethod) {
        try {
            execute(botApiMethod);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}

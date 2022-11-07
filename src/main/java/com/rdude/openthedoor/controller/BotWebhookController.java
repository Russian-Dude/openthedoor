package com.rdude.openthedoor.controller;

import com.rdude.openthedoor.bot.OpenTheDoorBot;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
public class BotWebhookController {

    private final OpenTheDoorBot openTheDoorBot;

    @PostMapping(value = "/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return openTheDoorBot.onWebhookUpdateReceived(update);
    }

}

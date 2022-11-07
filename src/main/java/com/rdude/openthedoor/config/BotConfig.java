package com.rdude.openthedoor.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BotConfig {

    @Value("${bot.webhook-path}")
    private String webhookPath;

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

}
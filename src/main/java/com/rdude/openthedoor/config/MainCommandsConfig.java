package com.rdude.openthedoor.config;

import com.rdude.openthedoor.command.text.TextCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class MainCommandsConfig {

    private final List<TextCommand> commands;

    @Bean(name = "main_menu_commands")
    public List<BotCommand> mainMenuCommands() {
        return commands.stream()
                .filter(TextCommand::isShowInMainMenu)
                .map(command -> new BotCommand(command.getCommandText(), command.getDescription()))
                .collect(Collectors.toList());
    }

}

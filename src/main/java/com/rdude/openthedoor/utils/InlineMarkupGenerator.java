package com.rdude.openthedoor.utils;

import com.rdude.openthedoor.replies.InlineCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InlineMarkupGenerator {

    public static InlineKeyboardMarkup generate(InlineCommand... commands) {
        var buttons = Arrays.stream(commands)
                .map(command -> {
                    var button = new InlineKeyboardButton();
                    button.setText(command.getText());
                    button.setCallbackData(command.getCallback());
                    return button;
                })
                .toList();
        return new InlineKeyboardMarkup(List.of(buttons));
    }

}

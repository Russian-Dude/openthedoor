package com.rdude.openthedoor.replies;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReplyMessages {

    public static String openTheDoor(String whoAsk) {
        return whoAsk + " пришел! Откройте дверь :)";
    }

}

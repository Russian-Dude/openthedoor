package com.rdude.openthedoor.replies;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum InlineCommand {

    COMING("иду", "coming")
    ;

    private final String text;
    private final String callback;

}

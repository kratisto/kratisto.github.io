package com.thefrenchtouch.iptvreborned.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidM3U extends RuntimeException {
    public InvalidM3U(){
        super("Content or an URL must be provide");
    }
}

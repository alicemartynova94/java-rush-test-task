package com.game.exceptions;

public class PlayerNotFoundOrIDInvalidException extends RuntimeException{

    public PlayerNotFoundOrIDInvalidException(String message){
        super(message);
    }
}

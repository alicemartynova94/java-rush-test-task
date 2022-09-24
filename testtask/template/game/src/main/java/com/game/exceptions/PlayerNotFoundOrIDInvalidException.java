package com.game.exceptions;

public class PlayerNotFoundExceptionOrIDInvalid extends RuntimeException{

    public PlayerNotFoundExceptionOrIDInvalid(String message){
        super(message);
    }
}

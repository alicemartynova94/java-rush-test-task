package com.game.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PlayerGlobalExceptionsHandler {

    @ExceptionHandler
    public ResponseEntity<PlayerNotFoundOrIDInvalid> handlePlayerNotFoundOrIDInvalidException(PlayerNotFoundOrIDInvalidException exception){
        PlayerNotFoundOrIDInvalid playerNotFoundOrIDInvalid = new PlayerNotFoundOrIDInvalid();
        playerNotFoundOrIDInvalid.setInfo(exception.getMessage());
        return new ResponseEntity<>(playerNotFoundOrIDInvalid, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PlayerNotFoundOrIDInvalid> handlePlayerNotFoundOrIDInvalidException(Exception exception){
        PlayerNotFoundOrIDInvalid playerNotFoundOrIDInvalid = new PlayerNotFoundOrIDInvalid();
        playerNotFoundOrIDInvalid.setInfo(exception.getMessage());
        return new ResponseEntity<>(playerNotFoundOrIDInvalid, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<UnableToCreateNewPlayer> handleUnableToCreateNewPlayerException(UnableToCreateNewPlayerException exception){
        UnableToCreateNewPlayer unableToCreateNewPlayer  = new UnableToCreateNewPlayer();
        unableToCreateNewPlayer.setInfo(exception.getMessage());
        return new ResponseEntity<>(unableToCreateNewPlayer, HttpStatus.BAD_REQUEST);
    }

}

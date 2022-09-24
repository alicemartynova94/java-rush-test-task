package com.game.controller;

import com.game.entity.Player;
import com.game.exceptions.PlayerNotFoundOrIDInvalidException;
import com.game.exceptions.UnableToCreateNewPlayerException;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class MyController {

    @Autowired
    private PlayerService playerService;


    @GetMapping("/players")
    public Page<Player> getPlayersList(Specification<Player> specification, Pageable pageable){
        return playerService.getAllPlayers(specification, pageable);
    }

    @GetMapping("/players/count")
    public Long getPlayersCount(){
        return playerService.getPlayersCount();
    }

    @GetMapping("/players/{id}")
    public void deletePlayer(@PathVariable int id){
        Player player = playerService.getPlayer(id);
        if(player==null){
            throw new PlayerNotFoundOrIDInvalidException("There is no such player");
        }
        playerService.deletePlayer(id);
    }

    @GetMapping("/players/{id}")
    public Player getPlayer (@PathVariable int id){
        Player player = playerService.getPlayer(id);
        if(player==null){
            throw new PlayerNotFoundOrIDInvalidException("There is no such player");
        }
        return player;
    }

    @PostMapping("/players")
    public void createPlayer(@RequestBody Player player){
        if(player.getName().length() > 12 || player.getName() == null || player.getName().isEmpty()){
            throw new UnableToCreateNewPlayerException("The name of this player is invalid");
        }
        if(player.getTitle().length() > 30){
            throw new UnableToCreateNewPlayerException("The title of this player is invalid");
        }
        if(player.getExperience() < 0 || player.getExperience() > 10000000 || player.getExperience() == null){
            throw new UnableToCreateNewPlayerException("Invalid experience");
        }
        if(player.getProfession() == null){
            throw new UnableToCreateNewPlayerException("Please, chose your profession");
        }
        if(player.getRace() == null){
            throw new UnableToCreateNewPlayerException("Race is invalid");
        }
        if(player.getBanned() == null) {
            player.setBanned(false);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(player.getBirthday().getTime());
         if(player.getBirthday() == null || calendar.get(Calendar.YEAR) < 2000L || calendar.get(Calendar.YEAR) > 3000L){
             throw new UnableToCreateNewPlayerException("Birthday is invalid");
         }
        playerService.createPlayer(player);
    }

    @PostMapping("/players/{id}")
    public void updatePlayer(@PathVariable int id){
        Player player = playerService.getPlayer(id);
        //
        if(player==null){
            throw new PlayerNotFoundOrIDInvalidException("There is no such player");
        }

    }

    @GetMapping("/players/name/{name}")
    public List<Player> showAllPlayersByName(@PathVariable String name){
        List<Player> players = playerService.findByNameContainingIgnoreCase(name);
        return players;
    }

    @DeleteMapping("/players/title/{title}")
    public List<Player> showAllPlayersByTitle(@PathVariable String title){
        List<Player> players = playerService.findByTitleContainingIgnoreCase(title);
        return players;
    }
}

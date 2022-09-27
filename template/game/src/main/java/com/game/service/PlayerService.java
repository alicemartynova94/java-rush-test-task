package com.game.service;

import com.game.entity.Player;
import com.game.entity.PlayerPage;
import com.game.entity.PlayerSearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PlayerService {
    public Page<Player> getAllPlayers(PlayerPage playerPage, PlayerSearchCriteria playerSearchCriteria);

    public Player getPlayer(long id);

    public Player createPlayer(Player player);

    public void updatePlayer (Player player);

    public void deletePlayer(long id);

    public Long getPlayersCount();


}

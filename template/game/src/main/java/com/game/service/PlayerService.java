package com.game.service;

import com.game.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PlayerService {
    public List<Player> getAllPlayers();

    public Player getPlayer(long id);

    public void createPlayer(Player player);

    public void updatePlayer (Player player);

    public void deletePlayer(long id);

    public Long getPlayersCount();

    public List<Player> findByNameContainingIgnoreCase(String name);

    public List<Player> findByTitleContainingIgnoreCase(String title);

}

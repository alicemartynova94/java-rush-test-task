package com.game.service;

import com.game.repository.PlayerRepository;
import com.game.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Long getPlayersCount() {
        return playerRepository.count();
    }

    @Override
    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Player getPlayer(long id) {
        Player player = null;
        Optional<Player> optional = playerRepository.findById(id);
        if(optional.isPresent()){
            player = optional.get();
        }
        return player;
    }

    @Override
    public void createPlayer(Player player) {
        playerRepository.save(player);
    }

    @Override

    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }

    @Override
    public List<Player> findByNameContainingIgnoreCase(String name) {
        List<Player> players = playerRepository.findByNameContainingIgnoreCase(name);
        return players;
    }

    @Override
    public List<Player> findByTitleContainingIgnoreCase(String title) {
        List<Player> players = playerRepository.findByTitleContainingIgnoreCase(title);
        return players;
    }

}

package com.game.service;

import com.game.entity.PlayerPage;
import com.game.entity.PlayerSearchCriteria;
import com.game.repository.PlayerCriteriaRepository;
import com.game.repository.PlayerRepository;
import com.game.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;
    private final PlayerCriteriaRepository playerCriteriaRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository,
                             PlayerCriteriaRepository playerCriteriaRepository) {
        this.playerCriteriaRepository = playerCriteriaRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public Page<Player> getAllPlayers(PlayerPage playerPage,
                                      PlayerSearchCriteria playerSearchCriteria) {
        return playerRepository.findAll(playerPage, playerSearchCriteria);
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
    public Player createPlayer(Player player) {

        return playerRepository.save(player);
    }

    @Override

    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }


}

package com.game.repository;

import com.game.entity.Player;
import com.game.entity.PlayerPage;
import com.game.entity.PlayerSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {


    Page<Player> findAll(PlayerPage playerPage, PlayerSearchCriteria playerSearchCriteria);
}

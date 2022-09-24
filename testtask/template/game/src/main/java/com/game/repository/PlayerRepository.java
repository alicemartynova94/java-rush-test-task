package com.game.dao;

import com.game.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByNameStartingWith(String name);
    List<Player> findByNameEndingWithIgnoreCase(String name);
    List<Player> findByNameContainingIgnoreCase(String name);

    List<Player> findByTitleContainingIgnoreCase(String title);

    List<Player> findByTitleStartingWith(String title);

    List<Player> findByTitleEndingWithIgnoreCase(String title);
}

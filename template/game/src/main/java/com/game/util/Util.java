package com.game.util;

import com.game.entity.Player;

public class Util {

    public static Integer calculateCurrentLevel(Integer experence){
        Integer result = (((int)Math.sqrt(2500 + 200 * experence))-50)/100;
        return result;
    }

    public static Integer calculateExperience(Integer experience, Integer level){
        Integer result = 50 * (level + 1) * (level + 2) - experience;
        return result;
    }

    }

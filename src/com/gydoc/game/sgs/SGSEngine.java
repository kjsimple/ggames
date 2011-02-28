package com.gydoc.game.sgs;

import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import com.gydoc.game.RandomUtil;
import com.gydoc.game.Player;

public class SGSEngine {
    
    private static final int ROLE_VIP = 1;
    private static final int ROLE_FAN = 2;
    private static final int ROLE_ZHONG = 3;
    private static final int ROLE_NEI = 4;
    
    private int status = 0;
    private static int CARD_TOTAL = 108;
    private List<Card> cards = null;
    private static final List<Card> ALL_CARDS = new ArrayList<Card>();
    private List<Player> players = new ArrayList<Player>();
    private Map<Player, Integer> playerToRole = new HashMap<Player, Integer>();
    
    static {
        for (int i = 0; i < CARD_TOTAL; i++) {
            ALL_CARDS.add(new Card(i));
        }
    }
    
    public SGSEngine(List<Player> players) {
        if (players.size() < 2 || players.size() > 8) {
            throw new IllegalArgumentException("The number of players could not be less than 2 or greater than 8.");
        }
        this.players.addAll(players);
    }
    
    private List<Integer> determineRoles() {
        switch (players.size()) {
            case 2 :
                return Arrays.asList(ROLE_VIP, ROLE_FAN);
            case 3 :                      
                return Arrays.asList(ROLE_VIP, ROLE_FAN, ROLE_NEI);
            case ROLE_NEI :               
                return Arrays.asList(ROLE_VIP, ROLE_FAN, ROLE_ZHONG, ROLE_NEI);
            case 5 :                      
                return Arrays.asList(ROLE_VIP, ROLE_FAN, ROLE_FAN, ROLE_ZHONG, ROLE_NEI);
            case 6 :                      
                return Arrays.asList(ROLE_VIP, ROLE_FAN, ROLE_FAN, ROLE_FAN, ROLE_ZHONG, ROLE_NEI);
            case 7 :                      
                return Arrays.asList(ROLE_VIP, ROLE_FAN, ROLE_FAN, ROLE_FAN, ROLE_ZHONG, ROLE_ZHONG, ROLE_NEI);
            case 8 :                      
                return Arrays.asList(ROLE_VIP, ROLE_FAN, ROLE_FAN, ROLE_FAN, ROLE_FAN, ROLE_ZHONG, ROLE_ZHONG, ROLE_NEI);
            default :
                throw new IllegalStateException("The number of players is invalid.");
        }
    }
    
    private void initCards() {
        cards = RandomUtil.getRandomSequence(ALL_CARDS);
    }
    
    private void initRoles() {
        List<Integer> roles = RandomUtil.getRandomSequence(determineRoles());
        for (int i = 0; i < players.size(); i++) {
            playerToRole.put(players.get(i), roles.get(i));
        }
    }
    
    public void start() {
        status = 1;
        initCards();
        initRoles();
    }
    
    public boolean isRunning() {
        return status == 1;
    }
    
}

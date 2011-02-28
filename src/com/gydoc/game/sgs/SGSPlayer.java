package com.gydoc.game.sgs;

import java.util.Collection;
import java.util.LinkedList;

public class SGSPlayer {
    
    private Collection<Card> cards = new LinkedList<Card>();
    
    private String name;
    
    public SGSPlayer() {
        this("unknow");
    }
    
    public SGSPlayer(String name) {
        this.name = name;
    }
    
    public Reply answer(Action action) {
        return null;
    }
    
    public boolean addCard(Card card) {
        return cards.add(card);
    }
    
}

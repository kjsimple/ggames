package com.gydoc.game.cchess;

import java.io.Serializable;

/**
 *
 */
public class CChessPlayer implements Serializable {

    public enum Side {Red, Black}
    
    private String name;
    private String side;

    public CChessPlayer(String name, String side) {
        this.name = name;
        this.side = side;
    }

    public String getName() {
        return name;
    }

    public String getSide() {
        return side;
    }
    
}

package com.gydoc.game.cchess;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

/**
 *
 */
public abstract class CChessElement implements Serializable {

    protected int x;
    protected int y;
    protected CChessPlayer.Side side;
    private ChineseChess game;

    public CChessElement(int x, int y, CChessPlayer.Side side) {
        this(x, y, side, null);
    }

    public CChessElement(int x, int y, CChessPlayer.Side side, ChineseChess game) {
        this.x = x;
        this.y = y;
        this.side = side;
        this.game = game;
    }

    public abstract boolean canMoveTo(int x, int y);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CChessPlayer.Side getSide() {
        return side;
    }

    @JsonIgnore
    public ChineseChess getGame() {
        return game;
    }

    public void setGame(ChineseChess game) {
        this.game = game;
    }
    
}

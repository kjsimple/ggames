package com.gydoc.game.cchess;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ChineseChess implements Serializable {

    /**
     * 0 full game
     * 1 partial game
     */
    private int mode;
    private CChessPlayer player1;
    private CChessPlayer player2;
    private boolean started = false;
    private CChessElement[][] data;

    public ChineseChess() {
        this(null, null);
    }

    public ChineseChess(CChessPlayer player1, CChessPlayer player2) {
        this(null, 0, player1, player2);
    }

    public ChineseChess(CChessElement[][] data, int mode, CChessPlayer player1, CChessPlayer player2) {
        this.mode = mode;
        this.player1 = player1;
        this.player2 = player2;
        if (data == null) {
            data = new CChessElement[10][9];
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    data[i][j] = CChessUtil.createElement(i, j, i > 4 ? CChessPlayer.Side.Black : CChessPlayer.Side.Red);
                    if (data[i][j] != null) {
                        data[i][j].setGame(this);
                    }
                }
            }
        }

        this.data = data;
        for (CChessElement[] temp : data) {
            for (CChessElement temp2 : temp) {
                if (temp2 != null) {
                    temp2.setGame(this);
                }
            }
        }
    }

    public void start() {
        started = true;
    }

    public boolean isFullGame() {
        return mode == 0;
    }

    public boolean isPartialGame() {
        return mode == 1;
    }

    public int getMode() {
        return mode;
    }

    public List<CChessElement> elemsInLine(CChessElement e, int x, int y) {
        return elemsInLine(e.getX(), e.getY(), x, y);
    }

    public List<CChessElement> elemsInLine(int startX, int startY, int endX, int endY) {
        if (startX != endX && startY != endY) {
            throw new IllegalArgumentException("That could not be a horizontal or vertical line.");
        }
        boolean isHorizontal = (startX == endX);
        List<CChessElement> ret = new LinkedList<CChessElement>();
        for (CChessElement[] temp : data) {
            for (CChessElement temp2 : temp) {
                if (isHorizontal) {
                    if (between(temp2.getY(), endY, startY)) {
                        ret.add(temp2);
                    }
                } else {
                    if (between(temp2.getX(), endX, startX)) {
                        ret.add(temp2);
                    }
                }
            }
        }
        return ret;
    }

    public CChessElement getElement(int x, int y) {
        return data[x][y];
    }

    public CChessElement[][] getAllElems() {
        return data;
    }

    private boolean between(int i, int t1, int t2) {
        if (t1 > t2) {
            return i > t2 && i < t1;
        } else {
            return i > t1 && i < t2;
        }
    }
    
}

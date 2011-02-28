package com.gydoc.game.cchess;

import java.util.List;

/**
 *
 */
public class PaoElement extends CChessElement {

    public PaoElement(int x, int y, CChessPlayer.Side side) {
        super(x, y, side);
    }

    public PaoElement(int x, int y, CChessPlayer.Side side, ChineseChess game) {
        super(x, y, side, game);
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        if (this.getX() != x && this.getY() != y) {
            return false;
        }
            
        List<CChessElement> elems = getGame().elemsInLine(x, y, getX(), getY());
        CChessElement elemInTar = getGame().getElement(x, y);
        return (elems.size() == 1 && (elemInTar != null && !elemInTar.getSide().equals(getSide()))) || (elems.size() == 0 && elemInTar == null);
    }

}

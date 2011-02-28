package com.gydoc.game.cchess;

import java.util.List;

/**
 *
 */
public class JuElement extends CChessElement {

    public JuElement(int x, int y, CChessPlayer.Side side) {
        super(x, y, side);
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        if (this.x != x && this.y != y) {
            return false;
        }

        List elems = getGame().elemsInLine(this, x, y);
        return elems.size() == 0;
    }

}

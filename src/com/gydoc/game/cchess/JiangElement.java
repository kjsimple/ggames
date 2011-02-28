package com.gydoc.game.cchess;

/**
 *
 */
public class JiangElement extends CChessElement {

    public JiangElement(int x, int y, CChessPlayer.Side side) {
        super(x, y, side);
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        if (x > 5 || y < 3 || (x > 2 && x < 7)) {
            return false;
        }

        if ((Math.abs(x - this.x) == 1 && (y == this.y))
            || ((x == this.x) && (Math.abs(y - this.y) == 1))) {
            return true;
        }
        return false;
    }
    
}

package com.gydoc.game.cchess;

/**
 *
 */
public class MaElement extends CChessElement {

    public MaElement(int x, int y, CChessPlayer.Side side) {
        super(x, y, side);
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        if (Math.abs(this.getX() - x) == 1 && (Math.abs(this.y - y)) == 2) {
            if (getGame().elemsInLine(getX(), getY(), getX(), y).size() == 0) {
                return true;
            }
        }
        if (Math.abs(this.getX() - x) == 2 && (Math.abs(this.y - y)) == 1) {
            if (getGame().elemsInLine(x, getY(), getX(), getY()).size() == 0) {
                return true;
            }
        }
        return false;
    }

}

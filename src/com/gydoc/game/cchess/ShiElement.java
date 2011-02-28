package com.gydoc.game.cchess;

/**
 *
 */
public class ShiElement extends CChessElement {

    public ShiElement(int x, int y, CChessPlayer.Side side) {
        super(x, y, side);
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        CChessElement elemInTar = getGame().getElement(x, y);
        if ((this.getX() == 0 || this.getX() == 2) && (this.getY() == 3 || this.getY() == 5)) {
            return (x == 1 && y == 4);
        }
        if (this.getX() == 1 && this.getY() == 4) {
            return (x == 0 && y == 3) || (x == 0 && y == 5) || (x == 2 && y == 3) || (x == 2 && y == 5);
        }
        if ((this.getX() == 7 || this.getX() == 9) && (this.getY() == 3 || this.getY() == 5)) {
            return (x == 8 && y == 4);
        }
        if (this.getX() == 8 && this.getY() == 4) {
            return (x == 7 && y == 3) || (x == 7 && y == 5) || (x == 9 && y == 3) || (x == 9 && y == 5);
        }
        return false;
    }

}

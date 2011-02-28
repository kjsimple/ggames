package com.gydoc.game.cchess;

/**
 *
 */
public class ZuElement extends CChessElement {

    public ZuElement(int x, int y, CChessPlayer.Side side) {
        super(x, y, side);
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        switch (side) {
            case Black:
                if (this.x <= 4) {
                    return (x - this.x) == 1 && y == this.y;
                } else {
                    return ((x - this.x == 1) && (y == this.y)) || ((x == this.x) && Math.abs(y - this.y) == 1);
                }
            case Red:
                if (this.x >= 5) {
                    return (x - this.x == -1) && (y == this.y);
                } else {
                    return ((x - this.x == -1) && (y == this.y)) || ((x == this.x) && Math.abs(y - this.y) == 1);
                }
            default :
                throw new IllegalStateException("Could not move while side is null");
        }
    }
    
}

package com.gydoc.game.cchess;

/**
 *
 */
public class XiangElement extends CChessElement {

    public XiangElement(int x, int y, CChessPlayer.Side side) {
        super(x, y, side);
    }

    public XiangElement(int x, int y, CChessPlayer.Side side, ChineseChess game) {
        super(x, y, side, game);
    }

    @Override
    public boolean canMoveTo(int x, int y) {
        if (2 != Math.abs(getX()-x) || 2 != Math.abs(getY()-y)) {
            return false;
        }

        if ((2 == (getX()-x) && 2 == (getY()-y)) && getX() != 5) {
            return null == getGame().getElement(getX() - 1, getY() - 1);
        }
        if ((2 == (getX()-x) && -2 == (getY()-y)) && getX() != 5) {
            return null == getGame().getElement(getX() - 1, getY() + 1);
        }
        if ((-2 == (getX()-x) && 2 == (getY()-y)) && getX() != 4) {
            return null == getGame().getElement(getX() + 1, getY() - 1);
        }
        if ((-2 == (getX()-x) && -2 == (getY()-y)) && getX() != 4) {
            return null == getGame().getElement(getX() + 1, getY() + 1);
        }
        return false;
    }

}

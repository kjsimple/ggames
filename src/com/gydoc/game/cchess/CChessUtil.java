package com.gydoc.game.cchess;

import java.util.List;

/**
 *
 */
public class CChessUtil {

    private static int[][] IDS = new int[][]{
                                             {3, 4, 7, 6, 1, 6, 7, 4, 3},
                                             {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                                             {-1, 5, -1, -1, -1, -1, -1, 5, -1},
                                             {2, -1, 2, -1, 2, -1, 2, -1, 2},
                                             {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                                             {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                                             {2, -1, 2, -1, 2, -1, 2, -1, 2},
                                             {-1, 5, -1, -1, -1, -1, -1, 5, -1},
                                             {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                                             {3, 4, 7, 6, 1, 6, 7, 4, 3},
                                            };

    private CChessUtil() {

    }

    public static CChessElement createElement(int x, int y, CChessPlayer.Side side) {
        return createElement(x, y, side, IDS[x][y]);
    }

    public static CChessElement createElement(int x, int y, CChessPlayer.Side side, int id) {
        switch (id) {
            case 1 : return new JiangElement(x, y, side);
            case 2 : return new ZuElement(x, y, side);
            case 3 : return new JuElement(x, y, side);
            case 4 : return new MaElement(x, y, side);
            case 5 : return new PaoElement(x, y, side);
            case 6 : return new ShiElement(x, y, side);
            case 7 : return new XiangElement(x, y, side);
            default: return null;
        }
    }

}

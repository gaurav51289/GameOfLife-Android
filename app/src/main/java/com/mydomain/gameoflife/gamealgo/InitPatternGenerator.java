package com.mydomain.gameoflife.gamealgo;



public class InitPatternGenerator {


    public static void getGliderPattern(boolean[][] gridArray, int H, int W){

        int hh = H/2; //half of the height
        int hw = W/2; //half of the width

        gridArray[hh][(hw) - 1] = true;
        gridArray[hh + 1][(hw)] = true;
        gridArray[hh - 1][hw+1] = true;
        gridArray[hh][hw+1] = true;
        gridArray[hh+1][hw+1] = true;

        return;
    }


    public static void getSmallExploderPattern(boolean[][] gridArray, int H, int W) {

        int hh = H/2;
        int hw = W/2;

        gridArray[hh][hw] = true;
        gridArray[hh-1][hw] = true;
        gridArray[hh][hw - 1] = true;
        gridArray[hh][hw + 1] = true;
        gridArray[hh+1][hw - 1] = true;
        gridArray[hh+1][hw + 1] = true;
        gridArray[hh+2][hw] = true;

        return;
    }
}

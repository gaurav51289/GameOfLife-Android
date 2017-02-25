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

    public static void getExploderPattern(boolean[][] gridArray, int H, int W) {

        int hh = H/2;
        int hw = W/2;

        gridArray[hh-2][hw-2] = true;
        gridArray[hh-1][hw-2] = true;
        gridArray[hh][hw-2] = true;
        gridArray[hh+1][hw-2] = true;
        gridArray[hh+2][hw-2] = true;

        gridArray[hh-2][hw+2] = true;
        gridArray[hh-1][hw+2] = true;
        gridArray[hh][hw+2] = true;
        gridArray[hh+1][hw+2] = true;
        gridArray[hh+2][hw+2] = true;

        gridArray[hh-2][hw] = true;
        gridArray[hh+2][hw] = true;

        return;
    }

    public static void get10CellRowPattern(boolean[][] gridArray, int H, int W) {

        int hh = H/2;
        int hw = W/2;

        gridArray[hh][hw-5] = true;
        gridArray[hh][hw-4] = true;
        gridArray[hh][hw-3] = true;
        gridArray[hh][hw-2] = true;
        gridArray[hh][hw-1] = true;
        gridArray[hh][hw] = true;
        gridArray[hh][hw+1] = true;
        gridArray[hh][hw+2] = true;
        gridArray[hh][hw+3] = true;
        gridArray[hh][hw+4] = true;

        return;
    }

    public static void getLightWeightSpaceshipPattern(boolean[][] gridArray, int H, int W) {

        int hh = H/2;
        int hw = W/2;

        gridArray[hh-1][hw-2] = true;
        gridArray[hh+1][hw-2] = true;
        gridArray[hh-2][hw-1] = true;
        gridArray[hh-2][hw] = true;
        gridArray[hh-2][hw+1] = true;
        gridArray[hh-2][hw+2] = true;
        gridArray[hh-1][hw+2] = true;
        gridArray[hh][hw+2] = true;
        gridArray[hh+1][hw+1] = true;

        return;
    }

    public static void getGosperGliderGunPattern(boolean[][] gridArray, int H, int W) {

        int hh = H/2;
        int hw = W/2;

        gridArray[hh][hw] = true;
        gridArray[hh][hw-1] = true;
        gridArray[hh-1][hw-1] = true;
        gridArray[hh+1][hw-1] = true;
        gridArray[hh-2][hw-2] = true;
        gridArray[hh+2][hw-2] = true;
        gridArray[hh][hw-3] = true;
        gridArray[hh-3][hw-4] = true;
        gridArray[hh+3][hw-4] = true;
        gridArray[hh-3][hw-5] = true;
        gridArray[hh+3][hw-5] = true;
        gridArray[hh-2][hw-6] = true;
        gridArray[hh+2][hw-6] = true;
        gridArray[hh][hw-7] = true;
        gridArray[hh-1][hw-7] = true;
        gridArray[hh+1][hw-7] = true;
        gridArray[hh][hw-16] = true;
        gridArray[hh-1][hw-16] = true;
        gridArray[hh][hw-17] = true;
        gridArray[hh-1][hw-17] = true;
        gridArray[hh-1][hw+3] = true;
        gridArray[hh-2][hw+3] = true;
        gridArray[hh-3][hw+3] = true;
        gridArray[hh-1][hw+4] = true;
        gridArray[hh-2][hw+4] = true;
        gridArray[hh-3][hw+4] = true;
        gridArray[hh][hw+5] = true;
        gridArray[hh-4][hw+5] = true;
        gridArray[hh][hw+7] = true;
        gridArray[hh+1][hw+7] = true;
        gridArray[hh-4][hw+7] = true;
        gridArray[hh-5][hw+7] = true;
        gridArray[hh-2][hw+17] = true;
        gridArray[hh-3][hw+17] = true;
        gridArray[hh-2][hw+18] = true;
        gridArray[hh-3][hw+18] = true;


        return;
    }
}

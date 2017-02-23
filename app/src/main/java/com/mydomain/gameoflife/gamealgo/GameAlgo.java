package com.mydomain.gameoflife.gamealgo;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;


public class GameAlgo {

    public static final int C = 64;

    public static DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
    public static final int W = metrics.widthPixels / C;
    public static final int H = metrics.heightPixels / C;

    private static final boolean[][] gridArray = new boolean[H][W];

    private Context mContext;

    public GameAlgo(Context context) {
        this.mContext = context;
        initGrid();
    }

    public static boolean[][] getGridArray() {
        return gridArray;
    }

    public static void setGridCell(int h, int w){
        gridArray[h][w] = true;
    }

    public static void unsetGridCell(int h, int w){
        gridArray[h][w] = false;
    }

    public void initGrid() {

        clearGrid();

        gridArray[9][(W / 2) - 3] = true;
        gridArray[10][(W / 2) - 2] = true;
        gridArray[8][(W / 2) - 1] = true;
        gridArray[9][(W / 2) - 1] = true;
        gridArray[10][(W / 2) - 1] = true;
    }

    public void clearGrid() {
        for(int h = 0; h < H; h++){
            for(int w = 0; w < W; w++){
                gridArray[h][w] = false;
            }
        }
    }

    public void createNextGrid() {
        int neighbours;
        int min = 2;
        int max = 3;
        int born = 3;

        boolean[][] nextGridArray = new boolean[H][W];

        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                neighbours = getNeighbourCount(h, w);

                if (gridArray[h][w]) {
                    if ((neighbours >= min) && (neighbours <= max)) {
                        nextGridArray[h][w] = true;
                    }
                } else {
                    if (neighbours == born) {
                        nextGridArray[h][w] = true;
                    }
                }
            }
        }

        //Update the Grid Array with new Grid Array
        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                gridArray[h][w] = nextGridArray[h][w];
            }
        }
    }


    private int getNeighbourCount(int y, int x) {
        int neighbourCount = 0;
        if(gridArray[y][x]){
            neighbourCount = -1;
        }else{
            neighbourCount = 0;
        }
        for (int h = -1; h <= +1; h++) {
            for (int w = -1; w <= +1; w++) {
                if (gridArray[(H + (y + h)) % H][(W + (x + w)) % W]) {
                    neighbourCount++;
                }
            }
        }
        return neighbourCount;
    }
}

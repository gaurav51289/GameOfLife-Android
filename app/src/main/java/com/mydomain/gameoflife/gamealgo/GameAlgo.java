package com.mydomain.gameoflife.gamealgo;

import android.content.Context;
import android.content.res.Resources;
import android.preference.PreferenceActivity;
import android.util.DisplayMetrics;
import android.util.Log;

import com.mydomain.gameoflife.activities.SettingsActivity;


public class GameAlgo {

    public static final int C = 32;

    public static DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
    public static final float DEVICE_WIDTH_PX = metrics.widthPixels;
    public static final float DEVICE_HEIGHT_PX = metrics.heightPixels;
    public static final int W = (int) (DEVICE_WIDTH_PX / C);
    public static final int H = (int) (DEVICE_HEIGHT_PX / C);

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

        int init_pattern = Integer.parseInt(SettingsActivity.getInitPattern(mContext));

        switch (init_pattern){
            case 1:
                break;
            case 2:
                InitPatternGenerator.getGliderPattern(gridArray, H, W);
                break;
            case 3:
                InitPatternGenerator.getSmallExploderPattern(gridArray, H, W);
                break;
            case 4:
                InitPatternGenerator.getExploderPattern(gridArray, H, W);
                break;
            case 5:
                InitPatternGenerator.get10CellRowPattern(gridArray, H, W);
                break;
            case 6:
                InitPatternGenerator.getLightWeightSpaceshipPattern(gridArray, H, W);
                break;
            case 7:
                InitPatternGenerator.getGosperGliderGunPattern(gridArray, H, W);
                break;
        }
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
        int min = Integer.parseInt(SettingsActivity.getUnderPopNum(mContext));
        int max = Integer.parseInt(SettingsActivity.getOverPopNum(mContext));
        int born = Integer.parseInt(SettingsActivity.getSpawnNum(mContext));


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

    public float[] getHorizontalLinesArr() {

        int nol = H;
        int nop = nol*4;
        float[] returnArr = new float[nop] ;
        int count = 1;
        for(int i = 0; i < nop; i=i+4){

            returnArr[i] = 0;
            returnArr[i+1] = (count * C);
            returnArr[i+2] = DEVICE_WIDTH_PX;
            returnArr[i+3] = (count * C);
            count++;
        }


        return returnArr;
    }

    public float[] getVerticalLinesArr() {

        int nol = W;
        int nop = nol*4;
        float[] returnArr = new float[nop] ;
        int count = 1;
        for(int i = 0; i < nop; i=i+4){

            returnArr[i] = (count * C);
            returnArr[i+1] = 0;
            returnArr[i+2] = (count * C);
            returnArr[i+3] = DEVICE_HEIGHT_PX;
            count++;
        }


        return returnArr;
    }

}

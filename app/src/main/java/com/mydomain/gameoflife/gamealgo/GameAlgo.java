package com.mydomain.gameoflife.gamealgo;

import android.content.Context;
import android.content.res.Resources;
import android.preference.PreferenceActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.mydomain.gameoflife.R;
import com.mydomain.gameoflife.activities.SettingsActivity;


public class GameAlgo {

    public int C;

    public DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
    public float DEVICE_WIDTH_PX = metrics.widthPixels;
    public float DEVICE_HEIGHT_PX = metrics.heightPixels;
    public int W;
    public int H;
    private int init_pattern;
    private boolean[][] gridArray;

    private Context mContext;

    public GameAlgo(Context context) {
        this.mContext = context;
        initGrid();
    }

    public boolean[][] getGridArray() {
        return gridArray;
    }

    public void setGridCell(int h, int w){
        gridArray[h][w] = true;
    }

    public void unsetGridCell(int h, int w){
        gridArray[h][w] = false;
    }



    public void initGrid() {

        clearGrid();

        C = Integer.parseInt(SettingsActivity.getGridSize(mContext));
        W = (int) (DEVICE_WIDTH_PX / C);
        H = (int) (DEVICE_HEIGHT_PX / C);
        gridArray = new boolean[H][W];

        init_pattern = Integer.parseInt(SettingsActivity.getInitPattern(mContext));

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
                if( W < 40 ){
                    clearGrid();
                    Toast.makeText(mContext, R.string.grid_size_not_valid, Toast.LENGTH_LONG).show();
                }else{
                    InitPatternGenerator.getGosperGliderGunPattern(gridArray, H, W);
                }
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

        if(init_pattern != 7){
            for (int h = -1; h <= +1; h++) {
                for (int w = -1; w <= +1; w++) {
                    if (gridArray[(H + (y + h)) % H][(W + (x + w)) % W]) {
                        neighbourCount++;
                    }
                }
            }
        }else{
            for (int h = -1; h <= +1; h++) {
                for (int w = -1; w <= +1; w++) {
                    try{
                        if (gridArray[((y + h))][((x + w))]) {
                            neighbourCount++;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        continue;
                    }
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

package com.mydomain.gameoflife.gamealgo;

import android.content.Context;


public class GameAlgo {

    public static final int CELL_SIZE = 16;
    public static final int WIDTH = 1080 / CELL_SIZE;
    public static final int HEIGHT = 1920 / CELL_SIZE;

    private static final int[][] gridArray = new int[HEIGHT][WIDTH];

    private Context mContext;

    public GameAlgo(Context context) {
        this.mContext = context;
        initGrid();
    }

    public static int[][] getGridArray() {
        return gridArray;
    }

    private void initGrid() {

        clearGrid();

        gridArray[9][(WIDTH / 2) - 3] = 1;
        gridArray[10][(WIDTH / 2) - 2] = 1;
        gridArray[8][(WIDTH / 2) - 1] = 1;
        gridArray[9][(WIDTH / 2) - 1] = 1;
        gridArray[10][(WIDTH / 2) - 1] = 1;
    }

    private void clearGrid() {
        for(int h = 0; h < HEIGHT; h++){
            for(int w = 0; w < WIDTH; w++){
                gridArray[h][w] = 0;
            }
        }
    }

    public void createNextGrid() {
        int neighbours;
        int minimum = 2;
        int maximum = 3;
        int spawn = 3;

        int[][] nextGridArray = new int[HEIGHT][WIDTH];

        for (int h = 0; h < HEIGHT; h++) {
            for (int w = 0; w < WIDTH; w++) {
                neighbours = getNeighbourCount(h, w);

                if (gridArray[h][w] != 0) {
                    if ((neighbours >= minimum) && (neighbours <= maximum)) {
                        nextGridArray[h][w] = neighbours;
                    }
                } else {
                    if (neighbours == spawn) {
                        nextGridArray[h][w] = spawn;
                    }
                }
            }
        }

        //Update the Grid Array with new Grid Array
        for (int h = 0; h < HEIGHT; h++) {
            for (int w = 0; w < WIDTH; w++) {
                gridArray[h][w] = nextGridArray[h][w];
            }
        }
    }


    private int getNeighbourCount(int y, int x) {
        int total = 0;
        if(gridArray[y][x] != 0){
            total = -1;
        }else{
            total = 0;
        }
        for (int h = -1; h <= +1; h++) {
            for (int w = -1; w <= +1; w++) {
                if (gridArray[(HEIGHT + (y + h)) % HEIGHT][(WIDTH + (x + w))
                        % WIDTH] != 0) {
                    total++;
                }
            }
        }
        return total;
    }
}

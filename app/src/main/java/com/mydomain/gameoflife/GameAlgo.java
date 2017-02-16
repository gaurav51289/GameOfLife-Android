package com.mydomain.gameoflife;

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

        resetGrid();

        gridArray[9][(WIDTH / 2) - 3] = 1;
        gridArray[10][(WIDTH / 2) - 2] = 1;
        gridArray[8][(WIDTH / 2) - 1] = 1;
        gridArray[9][(WIDTH / 2) - 1] = 1;
        gridArray[10][(WIDTH / 2) - 1] = 1;
    }

    private void resetGrid() {
        for(int h = 0; h < HEIGHT; h++){
            for(int w = 0; w < WIDTH; w++){
                gridArray[h][w] = 0;
            }
        }
    }

    public void createNextGeneration() {
        int neighbours;
        int minimum = 2; //Integer.parseInt(PreferencesActivity.getMinimumVariable(this._context));
        int maximum = 3; //Integer.parseInt(PreferencesActivity.getMaximumVariable(this._context));
        int spawn = 3; //Integer.parseInt(PreferencesActivity.getSpawnVariable(this._context));

        int[][] nextGridArray = new int[HEIGHT][WIDTH];

        for (int h = 0; h < HEIGHT; h++) {
            for (int w = 0; w < WIDTH; w++) {
                neighbours = calculateNeighbours(h, w);

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
        for (int h = 0; h < HEIGHT; h++) {
            for (int w = 0; w < WIDTH; w++) {
                gridArray[h][w] = nextGridArray[h][w];
            }
        }
    }


    private int calculateNeighbours(int y, int x) {
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

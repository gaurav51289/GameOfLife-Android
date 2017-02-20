package com.mydomain.gameoflife.gamealgo;


public class GameState {

    private static GameState gameState = null;

    public static final int PAUSE = 0;
    public static final int PLAY = 1;

    private int state;

    private GameState(){

    }

    public static GameState getInstance(){
        if(gameState == null){
            gameState = new GameState();
        }
        return gameState;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}

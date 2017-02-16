package com.mydomain.gameoflife;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;


public class GridView extends View {

    public static final int PAUSE = 0;
    public static final int RUNNING = 1;

    private long movementDelay = 250;
    private RefreshHandler mRefreshHandler = new RefreshHandler();
    private GameAlgo mGameAlgo;

    public GridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mGameAlgo = new GameAlgo(context);
        initGridView();
    }

    @Override
    protected void onDraw(Canvas canvas){
        Context context = getContext();
        Paint paintBackground = new Paint();
        paintBackground.setColor(ContextCompat.getColor(context, R.color.background));

        Paint paintCell = new Paint();
        paintCell.setColor(ContextCompat.getColor(context, R.color.cell));

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBackground);

        for (int h = 0; h < GameAlgo.HEIGHT; h++) {
            for (int w = 0; w < GameAlgo.WIDTH; w++) {
                if (GameAlgo.getGridArray()[h][w] != 0) {
                    canvas.drawRect(
                            w * GameAlgo.CELL_SIZE,
                            h * GameAlgo.CELL_SIZE,
                            (w * GameAlgo.CELL_SIZE) + (GameAlgo.CELL_SIZE -1),
                            (h * GameAlgo.CELL_SIZE) + (GameAlgo.CELL_SIZE -1),
                            paintCell);
                }
            }
        }
    }

    public void setViewState(int viewState) {
        if(viewState == RUNNING){
            updateView();
            return;
        }

        if(viewState == PAUSE){
            //TODO : to implement
        }
    }

    private void initGridView(){
        setFocusable(true);
    }

    private void updateView() {
        mGameAlgo.createNextGeneration();
        mRefreshHandler.nextRefresh(movementDelay);
    }

    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message message){
            GridView.this.updateView();
            GridView.this.invalidate();
        }

        public void nextRefresh(long delayMills){
            removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMills);
        }

    }


}

package com.mydomain.gameoflife.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.mydomain.gameoflife.gamealgo.GameAlgo;
import com.mydomain.gameoflife.R;


public class CustomGridView extends View {

    public static final int PAUSE = 0;
    public static final int RUNNING = 1;

    private long movementDelay = 200;
    private RefreshHandler mRefreshHandler = new RefreshHandler();
    private GameAlgo mGameAlgo;

    public CustomGridView(Context context, AttributeSet attributeSet) {
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
//                    canvas.drawRect(
//                            w * GameAlgo.CELL,
//                            h * GameAlgo.CELL,
//                            (w * GameAlgo.CELL) + (GameAlgo.CELL -1),
//                            (h * GameAlgo.CELL) + (GameAlgo.CELL -1),
//                            paintCell);

                    int cellSize = GameAlgo.CELL;
                    float horCenter = (w * cellSize) + (cellSize/2) ;
                    float verCenter = (h * cellSize) + (cellSize/2);
                    int cellRadius = (cellSize/2) - 2;
                    canvas.drawCircle(horCenter, verCenter, cellRadius, paintCell);

                }
            }
        }
    }

    public void setViewState(int viewState) {
        if(viewState == RUNNING){
            updateGridView();
            return;
        }

        if(viewState == PAUSE){
            //TODO : to implement
        }
    }

    private void initGridView(){
        setFocusable(true);
    }

    private void updateGridView() {
        mGameAlgo.createNextGrid();
        mRefreshHandler.nextRefresh(movementDelay);
    }

    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message message){
            CustomGridView.this.updateGridView();
            CustomGridView.this.invalidate();
        }

        public void nextRefresh(long delayMills){
            removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMills);
        }

    }


}

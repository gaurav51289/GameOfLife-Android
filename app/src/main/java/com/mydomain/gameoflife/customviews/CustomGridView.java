package com.mydomain.gameoflife.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mydomain.gameoflife.activities.SettingsActivity;
import com.mydomain.gameoflife.gamealgo.GameAlgo;
import com.mydomain.gameoflife.R;
import com.mydomain.gameoflife.gamealgo.GameState;


public class CustomGridView extends View implements View.OnTouchListener {


    private long movementDelay = 255;
    private RefreshHandler mRefreshHandler = new RefreshHandler();
    private GameAlgo mGameAlgo;

    private float touchX, touchY;
    private int cellSize;
    private int cellRadius;

    private int currentInitPattern, currentGridSize;

    public CustomGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

            mGameAlgo = new GameAlgo(context);
            cellSize = mGameAlgo.C;
            cellRadius = (cellSize/2) - 4;
            currentInitPattern = Integer.parseInt(SettingsActivity.getInitPattern(context));
            currentGridSize = Integer.parseInt(SettingsActivity.getGridSize(context));
            initGridView();

    }


    private void initGridView(){
        setFocusable(true);
    }

    public void updateGridView() {
        mGameAlgo.createNextGrid();
        mRefreshHandler.nextRefresh(movementDelay);
    }

    public void resetGridView(){

        mGameAlgo.initGrid();

    }

    public void setMovementDelay(long movementDelay) {
        this.movementDelay = movementDelay;
    }

    @Override
    protected void onDraw(Canvas canvas){
            Context context = getContext();
            if(currentInitPattern != Integer.parseInt(SettingsActivity.getInitPattern(context))){
                currentInitPattern = Integer.parseInt(SettingsActivity.getInitPattern(context));
                resetGridView();
            }

            if(currentGridSize != Integer.parseInt(SettingsActivity.getGridSize(context))){
                currentGridSize = Integer.parseInt(SettingsActivity.getGridSize(context));
                resetGridView();
                cellSize = mGameAlgo.C;
                cellRadius = (cellSize/2) - 4;
            }

            Paint paintBackground = new Paint();
            paintBackground.setColor(ContextCompat.getColor(context, R.color.background));

            Paint paintLines = new Paint();
            paintLines.setColor(ContextCompat.getColor(context, R.color.gridlines));

            Paint paintCell = new Paint();
            paintCell.setColor(ContextCompat.getColor(context, R.color.cell));

            canvas.drawRect(0, 0, getWidth(), getHeight(), paintBackground);

            float[] fArr = mGameAlgo.getVerticalLinesArr();
            canvas.drawLines(fArr, 0, fArr.length, paintLines);
            fArr = mGameAlgo.getHorizontalLinesArr();
            canvas.drawLines(fArr, 0, fArr.length, paintLines);


            for (int h = 0; h < mGameAlgo.H; h++) {
                for (int w = 0; w < mGameAlgo.W; w++) {
                    if (mGameAlgo.getGridArray()[h][w]) {

                        float horCenter = (w * cellSize) + (cellSize/2) ;
                        float verCenter = (h * cellSize) + (cellSize/2);

                        canvas.drawCircle(horCenter, verCenter, cellRadius, paintCell);

                    }
                }
            }

            invalidate();

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){

            //getting touch co-ordinates
            touchX = event.getX();
            touchY = event.getY();

            //finding the center of the cell touched
            touchX = (int) (((int)(touchX/cellSize) + 0.5) * cellSize);
            touchY = (int) (((int)(touchY/cellSize) + 0.5) * cellSize);

            //calculating the grid array index
            int gridArrX = (int) (touchX/cellSize);
            int gridArrY = (int) (touchY/cellSize);

            //setting newly touched cell in the grid array
            boolean currentCellState = mGameAlgo.getGridArray()[gridArrY][gridArrX];
            if(!currentCellState){
                mGameAlgo.setGridCell(gridArrY, gridArrX);
            }else{
                mGameAlgo.unsetGridCell(gridArrY, gridArrX);
            }
        }

        return true;
    }



    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message message){

            GameState gs = GameState.getInstance();

            if(gs.getState() == GameState.PLAY) {
                CustomGridView.this.updateGridView();
            }
            CustomGridView.this.invalidate();
        }

        public void nextRefresh(long delayMills){
            removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMills);
        }

    }


}

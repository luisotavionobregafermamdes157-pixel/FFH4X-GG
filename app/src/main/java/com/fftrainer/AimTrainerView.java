package com.fftrainer;

import android.content.Context;
import android.graphics.*;
import android.view.*;
import java.util.Random;

public class AimTrainerView extends View {
    private final Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG); private final Random random=new Random();
    private boolean running=false; private int hits=0; private float x=200,y=200,r=45,fov=90;
    public AimTrainerView(Context c){super(c); paint.setTypeface(Typeface.DEFAULT); setBackgroundColor(Color.rgb(18,18,30));}
    public void setFov(float value){fov=value; invalidate();}
    public void startTraining(){running=true;hits=0;newTarget();invalidate();}
    public void stopTraining(){running=false;invalidate();}
    public int getHits(){return hits;}
    private void newTarget(){x=r+random.nextFloat()*Math.max(1,getWidth()-2*r);y=r+random.nextFloat()*Math.max(1,getHeight()-2*r);}
    @Override protected void onDraw(Canvas c){super.onDraw(c); paint.setStyle(Paint.Style.STROKE);paint.setStrokeWidth(3);paint.setColor(Color.GRAY);c.drawCircle(getWidth()/2f,getHeight()/2f,Math.min(getWidth(),getHeight())*fov/360f,paint); if(running){paint.setStyle(Paint.Style.FILL);paint.setColor(Color.rgb(233,69,96));c.drawCircle(x,y,r,paint);paint.setColor(Color.WHITE);c.drawCircle(x,y,8,paint);}}
    @Override public boolean onTouchEvent(android.view.MotionEvent e){if(e.getAction()==MotionEvent.ACTION_DOWN&&running){float dx=e.getX()-x,dy=e.getY()-y;if(dx*dx+dy*dy<=r*r){hits++;newTarget();invalidate();}return true;}return true;}
}

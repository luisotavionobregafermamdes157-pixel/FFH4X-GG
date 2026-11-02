package com.fftrainer;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;

public class MainActivity extends Activity {
    private AimTrainerView trainer;
    private TextView status;
    private SeekBar fov;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(24,24,24,24);
        root.setBackgroundColor(Color.rgb(26,26,46));

        TextView title = new TextView(this);
        title.setText("FFH4X GG • AIM TRAINER");
        title.setTextColor(Color.WHITE); title.setTextSize(23); title.setGravity(Gravity.CENTER);
        root.addView(title, new LinearLayout.LayoutParams(-1,70));

        status = new TextView(this);
        status.setText("Status: Pronto"); status.setTextColor(Color.LTGRAY); status.setGravity(Gravity.CENTER);
        root.addView(status, new LinearLayout.LayoutParams(-1,55));

        TextView fovLabel = new TextView(this);
        fovLabel.setText("FOV: 90"); fovLabel.setTextColor(Color.WHITE);
        root.addView(fovLabel);
        fov = new SeekBar(this); fov.setMax(150); fov.setProgress(90);
        fov.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            public void onProgressChanged(SeekBar s,int p,boolean u){ fovLabel.setText("FOV: "+Math.max(30,p)); if(trainer!=null) trainer.setFov(Math.max(30,p)); }
            public void onStartTrackingTouch(SeekBar s){} public void onStopTrackingTouch(SeekBar s){}
        });
        root.addView(fov);

        trainer = new AimTrainerView(this); trainer.setFov(90);
        root.addView(trainer, new LinearLayout.LayoutParams(-1,0,1));

        LinearLayout buttons = new LinearLayout(this); buttons.setOrientation(LinearLayout.HORIZONTAL);
        Button start = new Button(this); start.setText("INICIAR TREINO");
        Button stop = new Button(this); stop.setText("PARAR");
        buttons.addView(start,new LinearLayout.LayoutParams(0,60,1)); buttons.addView(stop,new LinearLayout.LayoutParams(0,60,1));
        root.addView(buttons);
        start.setOnClickListener(v->{trainer.startTraining(); status.setText("Status: Treinando • Acertos: 0");});
        stop.setOnClickListener(v->{trainer.stopTraining(); status.setText("Status: Parado • Acertos: "+trainer.getHits());});
        setContentView(root);
    }
}

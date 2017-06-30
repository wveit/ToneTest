package com.example.androidu.tonetest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar mFrequencySeekBar;
    SeekBar mVolumeSeekBar;
    TextView mFrequencyTextView;
    TextView mVolumeTextView;
    Button mStartAndStopButton;

    int mFrequencySetting = 0;
    int mVolumeSetting = 0;

    TonePlayer mTonePlayer;

    public final int MIN_FREQUENCY = 0;
    public final int MAX_FREQUENCY = 10000;

    public final int MIN_VOLUME = 0;
    public final int MAX_VOLUME = 20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTonePlayer = new TonePlayer();

        mFrequencySeekBar = (SeekBar) findViewById(R.id.sb_frequency_slider);
        mVolumeSeekBar = (SeekBar) findViewById(R.id.sb_volume_slider);
        mFrequencyTextView = (TextView) findViewById(R.id.tv_frequency_setting);
        mVolumeTextView = (TextView) findViewById(R.id.tv_volume_setting);
        mStartAndStopButton = (Button) findViewById(R.id.btn_start_and_stop);

        mFrequencyTextView.setText("Frequency: 0");
        mVolumeTextView.setText("Volume: 0");
        mStartAndStopButton.setText("Start Tone");

        mFrequencySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mFrequencySetting = (int) (MIN_FREQUENCY + (double) progress / seekBar.getMax() * (MAX_FREQUENCY - MIN_FREQUENCY));
                    mFrequencyTextView.setText("Frequency: " + mFrequencySetting);
                    mTonePlayer.setFrequency(mFrequencySetting);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        mVolumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    mVolumeSetting = (int) (MIN_VOLUME + (double) progress / seekBar.getMax() * (MAX_VOLUME - MIN_VOLUME));
                    mVolumeTextView.setText("Volume: " + mVolumeSetting);
                    mTonePlayer.setVolume(mVolumeSetting);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        mStartAndStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTonePlayer.isPlaying()){
                    mTonePlayer.stop();
                    mStartAndStopButton.setText("Play Tone");
                }
                else{
                    mTonePlayer.start();
                    mStartAndStopButton.setText("Stop Tone");
                }
            }
        });
    }
}

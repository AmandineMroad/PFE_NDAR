package polytech.pfe_ndar;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class AudioActivity extends Activity {
    private String rawName;
    private MediaPlayer player;

    private double startTime=0;
    private SeekBar seekBar;
    private Handler handler = new Handler();
    private TextView currentTimeText;

    private static final @IdRes int ID_BUTTON_PREV = R.id.audio_button_prev;
    private static final @IdRes int ID_BUTTON_PLAY = R.id.audio_button_play;
    private static final @IdRes int ID_BUTTON_PAUSE = R.id.audio_button_pause;
    private static final @IdRes int ID_BUTTON_STOP = R.id.audio_button_stop;

    private static final int BUTTON_PREV = 10;
    private static final int BUTTON_PLAY = 11;
    private static final int BUTTON_PAUSE = 12;
    private static final int BUTTON_STOP = 13;

    private static boolean first = true;

    protected void onCreate(Bundle savedInstanceState){
        //TODO set image
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_layout);

        Bundle extras = getIntent().getExtras();
        rawName = extras.getString("raw_name");

        //media player creation
        int rawResId = getResources().getIdentifier(rawName, "raw", getPackageName());
        Uri path = Uri.parse("android.resource://polytech.pfe_ndar/" + rawResId);
        player = MediaPlayer.create(this, path);

        //media player listeners attachment
        ImageButton button = (ImageButton)findViewById(ID_BUTTON_PREV);
        button.setOnClickListener(new AudioPlayerListener(BUTTON_PREV));
        button = (ImageButton)findViewById(ID_BUTTON_PLAY);
        button.setOnClickListener(new AudioPlayerListener(BUTTON_PLAY));
        button = (ImageButton) findViewById(ID_BUTTON_PAUSE);
        button.setOnClickListener(new AudioPlayerListener(BUTTON_PAUSE));
        button = (ImageButton) findViewById(ID_BUTTON_STOP);
        button.setOnClickListener(new AudioPlayerListener(BUTTON_STOP));

        //seekBar initialization
        seekBar = (SeekBar) findViewById(R.id.audio_seekbar);
        seekBar.setClickable(false);
        currentTimeText = (TextView) findViewById(R.id.audio_current_text);

    }

    private Runnable updateTime = new Runnable() {
        @Override
        public void run() {
            startTime = player.getCurrentPosition();
            currentTimeText.setText(String.format("%d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime)%60));
            seekBar.setProgress((int) startTime);
            handler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        player.stop();
    }

    private class AudioPlayerListener implements View.OnClickListener {
        int buttonNumber;

        public AudioPlayerListener(int buttonNumber){
            this.buttonNumber = buttonNumber;
        }

        @Override
        public void onClick(View v) {
            ImageButton button;
            boolean pauseIsEnabled, playIsEnabled;
            pauseIsEnabled = playIsEnabled = false;
            switch (buttonNumber){
                case BUTTON_PREV:
                    player.reset();
                    pauseIsEnabled = true;
                    playIsEnabled = false;
                    break;
                case BUTTON_PLAY:
                    player.start();
                    pauseIsEnabled= true;
                    playIsEnabled = false;
                //displayed time and seekbar update
                    double finalTime;
                    finalTime = player.getDuration();
                    startTime = player.getCurrentPosition();
                    //final time initialization
                    if (first) {
                        first = false;
                        seekBar.setMax((int) finalTime);
                        TextView textView = (TextView) findViewById(R.id.audio_final_text);
                        textView.setText(String.format("/ %d:%d",
                                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                                TimeUnit.MILLISECONDS.toSeconds((long) finalTime)%60));
                    }
                    updateTime.run();
                    break;
                case BUTTON_PAUSE:
                    player.pause();
                    playIsEnabled = true;
                    pauseIsEnabled = false;
                    break;
                case BUTTON_STOP:
                    player.stop();
                    button = (ImageButton) findViewById(ID_BUTTON_PREV);
                    button.setEnabled(false);
                    playIsEnabled = true;
                    pauseIsEnabled = false;
                    //QUESTION: revenir au menu ?
                    break;
            }
            button = (ImageButton) findViewById(ID_BUTTON_PLAY);
            button.setEnabled(playIsEnabled);
            button = (ImageButton) findViewById(ID_BUTTON_PAUSE);
            button.setEnabled(pauseIsEnabled);
        }
    }


}

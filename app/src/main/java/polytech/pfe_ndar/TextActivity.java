package polytech.pfe_ndar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {
    String TextDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        Bundle extras = getIntent().getExtras();
        TextDesc = extras.getString("raw_path");
        TextView tv= (TextView) findViewById(R.id.text_description_scroll);
        tv.setText(TextDesc);
    }
}

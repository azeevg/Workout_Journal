package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class HelpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        TextView helpText = (TextView) findViewById(R.id.helpText);
        DBHelper db = new DBHelper(this);
        helpText.setText(Html.fromHtml(db.GetHelp()));
        helpText.setMovementMethod(new ScrollingMovementMethod());
    }
}

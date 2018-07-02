package com.example.administrator.wordsearch.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.wordsearch.view.R;

/**
 * Created by Administrator on 4/20/2018.
 */

public class TutorialActivity extends Activity {
//	private static String LOG_TAG = "TutorialActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.tutorial_main);
        ViewGroup mainView = (ViewGroup)this.findViewById(R.id.tutorial_main);
        TextView text = (TextView)this.getLayoutInflater().inflate(R.layout.tutorial_text_view_message, mainView, false);
        text.setText(R.string.tutorial_message);
        mainView.addView(text);
        this.addSection(mainView, R.string.tutorial_heading_trackball, R.array.tutorial_messages_trackball);
        TextView text2 = (TextView)this.getLayoutInflater().inflate(R.layout.tutorial_text_view_heading, mainView, false);
        text2.setText(R.string.tutorial_heading_touch);
        mainView.addView(text2);
        TextView text3 = (TextView)this.getLayoutInflater().inflate(R.layout.tutorial_text_view_message, mainView, false);
        text3.setText(R.string.tutorial_message2);
        mainView.addView(text3);
        this.addSection(mainView, R.string.tutorial_heading_drag, R.array.tutorial_messages_drag);
        this.addSection(mainView, R.string.tutorial_heading_tap, R.array.tutorial_messages_tap);
        this.addSection(mainView, R.string.tutorial_heading_tips, R.array.tutorial_messages_tips);
    }

    /**
     *
     * @param mainView the viewgroup to add a tutorial section into
     * @param headingId the resourceid of a string to use for the section header
     * @param messagesId the resourceid of an array of strings to use for the section tips
     */
    private void addSection(ViewGroup mainView, int headingId, int messagesId) {
        TextView text = (TextView)this.getLayoutInflater().inflate(R.layout.tutorial_text_view_heading, mainView, false);
        text.setText(headingId);
        mainView.addView(text);
        int count = 1;
        for (String message : this.getResources().getStringArray(messagesId)) {
            text = (TextView)this.getLayoutInflater().inflate(R.layout.tutorial_text_view_message, mainView, false);
            text.setText(count+") "+message);
            mainView.addView(text);
            count++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.tutorial_options, menu);
        menu.findItem(R.id.menu_quit).setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_quit:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
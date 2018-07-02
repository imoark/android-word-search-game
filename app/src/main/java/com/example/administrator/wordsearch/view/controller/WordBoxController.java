package com.example.administrator.wordsearch.view.controller;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.wordsearch.view.R;
import com.example.administrator.wordsearch.model.Grid;
import com.example.administrator.wordsearch.model.Theme;

import java.util.List;

/**
 * Created by Administrator on 4/20/2018.
 */

public class WordBoxController implements View.OnClickListener, IWordBoxController, Handler.Callback {
    final private Button next;
    final private Button prev;
    final private TextView wordBox;
    final private TextView letterBox;
    final private Handler handler;
    private List<String> words;
    private int wordsIndex = 0;

    protected WordBoxController(Button prev, Button next, TextView wordBox, TextView letterBox) {
        this.letterBox = letterBox;
        this.prev = prev;
        this.prev.setOnClickListener(this);
        this.next = next;
        this.next.setOnClickListener(this);
        this.wordBox = wordBox;
        this.handler = new Handler(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.prev:
                if (wordsIndex > 0) {
                    wordsIndex--;
                }
                break;
            case R.id.next:
                if (wordsIndex < words.size() - 1) {
                    wordsIndex++;
                }
                break;
            default:
                return;
        }
        Message.obtain(handler, MSG_UPDATE_WORD_BOX).sendToTarget();
    }

    public void resetWords(Grid grid) {
        this.words = grid.getWordList();
        this.wordsIndex = 0;
        Message.obtain(handler, MSG_UPDATE_WORD_BOX).sendToTarget();
    }

    public void setLetter(CharSequence charSequence) {
        if (charSequence != null && charSequence.length() > 1) {
            charSequence = String.valueOf(charSequence.charAt(charSequence.length()-1));
        }
        Message.obtain(handler, MSG_SET_LETTER_BOX, charSequence).sendToTarget();
    }

    public void wordFound(String str) {
        words.remove(str);
        wordsIndex = 0;
        Message.obtain(handler, MSG_UPDATE_WORD_BOX).sendToTarget();
    }

    public void updateTheme(Theme theme) {
        Message.obtain(handler, MSG_UPDATE_THEME, theme).sendToTarget();
    }

    final static private int MSG_SET_LETTER_BOX = 0;
    final static private int MSG_UPDATE_WORD_BOX = 1;
    final static private int MSG_UPDATE_THEME = 2;
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_SET_LETTER_BOX: {
                CharSequence letterBoxText = (CharSequence)msg.obj;
                if (letterBoxText == null) {
                    prev.setVisibility(Button.VISIBLE);
                    letterBox.setVisibility(TextView.INVISIBLE);
                } else {
                    prev.setVisibility(Button.INVISIBLE);
                    letterBox.setText(letterBoxText);
                    letterBox.setVisibility(TextView.VISIBLE);
                }
                break;
            }
            case MSG_UPDATE_WORD_BOX: {
                if (wordsIndex < 0 || wordsIndex > words.size()) {
                    wordsIndex = 0;
                }
                CharSequence text = "";
                if (words.size() != 0) {
                    text = words.get(wordsIndex);
                }
                next.setEnabled(wordsIndex < words.size()-1);
                prev.setEnabled(wordsIndex > 0);
                wordBox.setText(text);
                break;
            }
            case MSG_UPDATE_THEME: {
                Theme theme = (Theme)msg.obj;
                this.letterBox.setTextColor(theme.picked);
                this.wordBox.setTextColor(theme.normal);
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }
}

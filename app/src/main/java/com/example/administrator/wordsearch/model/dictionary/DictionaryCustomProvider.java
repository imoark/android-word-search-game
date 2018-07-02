package com.example.administrator.wordsearch.model.dictionary;

import java.util.Random;

import android.content.Context;
import android.database.Cursor;

import com.example.administrator.wordsearch.view.WordDictionaryProvider.Word;



/**
 * Created by Administrator on 4/20/2018.
 */

public class DictionaryCustomProvider implements IDictionary {

    final private Random random = new Random();
    final private Context ctx;

    protected DictionaryCustomProvider(Context ctx) {
        this.ctx = ctx;
    }

    public String getNextWord(int minLength, int maxLength) {
        Cursor cursor = ctx.getContentResolver().query(Word.CONTENT_URI, new String[] { Word.WORD }, null, null, null);
        String str = null;
        if (cursor!= null) {
            if (cursor.getCount() != 0) {
                cursor.moveToPosition(random.nextInt(cursor.getCount()));
                str = cursor.getString(0);
            }
            cursor.close();
        }
        return str;
    }

}
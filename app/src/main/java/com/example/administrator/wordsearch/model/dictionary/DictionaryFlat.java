package com.example.administrator.wordsearch.model.dictionary;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by Administrator on 4/20/2018.
 */

public class DictionaryFlat implements IDictionary {
    private final static String LOG_TAG = "DictionaryFlat";
    private final static Random random = new Random();
    private final String fileName;
    private final int fileSize;
    private final Context ctx;

    public DictionaryFlat(Context ctx, String fileName2, int fileSize2) {
        this.fileName = fileName2;
        this.fileSize = fileSize2;
        this.ctx = ctx;
    }

    public String getNextWord(int minLength, int maxLength) {
        String str = null;
        int tries = 0;
        do {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(ctx.getAssets().open(fileName)), 10000);
                br.skip(random.nextInt(fileSize));
                br.readLine();
                str = br.readLine();
                br.close();
            } catch (IOException e) {
                str = null;
                Log.e(LOG_TAG,"reading file failed", e);
            }
            tries++;
        } while (str != null && (str.length() < minLength || str.length() > maxLength) && tries < DictionaryFactory.MAX_TRIES);
        return str;
    }
}


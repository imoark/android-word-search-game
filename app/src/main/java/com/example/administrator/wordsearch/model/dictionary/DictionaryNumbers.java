package com.example.administrator.wordsearch.model.dictionary;

import java.util.Random;

/**
 * Created by Administrator on 4/20/2018.
 */

public class DictionaryNumbers implements IDictionary {
    private final Random random = new Random();

    public String getNextWord(int minLength, int maxLength) {
        int length = minLength;
        int diff = maxLength-minLength;
        if (diff > 0) {
            length += random.nextInt(diff);
        }
        String str = "";
        for (int index = 0; index < length; index++) {
            str += random.nextInt(10);
        }
        return str;
    }

}


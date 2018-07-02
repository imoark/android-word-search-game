package com.example.administrator.wordsearch.model.dictionary;

import java.util.Random;

import android.content.Context;
import android.util.Log;

import com.example.administrator.wordsearch.view.R;


/**
 * Created by Administrator on 4/20/2018.
 */

public class DictionaryFactory {
    public enum DictionaryType {
        FOODS,
        SEASONAL,
        CHRISTMAS,
        PLACES,
        ANIMALS,
        PEOPLE,
        ADJECTIVES,
        KIDS,
        SAT,
        MISC,
        GERMAN,
        INSANE,
        NUMBERS,
        CUSTOM,
        RANDOM;
    }
    private static final String LOG_TAG = "DICTIONARYFACTORY";
    public static final int MAX_TRIES = 5;
    private Random random = new Random();
    private final Context ctx;
    private DictionaryType currentDictionaryType = DictionaryType.RANDOM;
    private IDictionary currentDic = null;

    /**
     * @param ctx application context used to access resources
     */
    public DictionaryFactory(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * @return if we are currently using a custom dictionary provider
     */
    public Boolean isCustomDictionary() {
        return DictionaryType.CUSTOM.equals(currentDictionaryType);
    }

    /**
     * @param themeIndex constant representing a dictionary type
     * @return new dictionary of the requested type
     */
    private IDictionary getDictionary(DictionaryType themeIndex) {
        IDictionary dic = null;
        switch (themeIndex) {
            case FOODS:
                dic = new DictionaryStringArray(ctx.getResources().getStringArray(R.array.WORDS_FOODS));
                break;
            case SEASONAL:
                dic = new DictionaryStringArray(ctx.getResources().getStringArray(R.array.WORDS_SEASONAL));
                break;
            case CHRISTMAS:
                dic = new DictionaryStringArray(ctx.getResources().getStringArray(R.array.WORDS_CHRISTMAS));
                break;
            case PLACES:
                dic = new DictionaryStringArray(ctx.getResources().getStringArray(R.array.WORDS_PLACES));
                break;
            case ANIMALS:
                dic = new DictionaryStringArray(ctx.getResources().getStringArray(R.array.WORDS_ANIMALS));
                break;
            case PEOPLE:
                dic = new DictionaryStringArray(ctx.getResources().getStringArray(R.array.WORDS_PEOPLE));
                break;
            case ADJECTIVES:
                dic = new DictionaryStringArray(ctx.getResources().getStringArray(R.array.WORDS_ADJECTIVES));
                break;
            case SAT:
                dic = new DictionaryFlat(ctx, "sat", 5850);
                break;
            case KIDS:
                dic = new DictionaryStringArray(ctx.getResources().getStringArray(R.array.WORDS_KIDS));
                break;
            case GERMAN:
                dic = new DictionaryStringArray(ctx.getResources().getStringArray(R.array.WORDS_GERMAN));
                break;
            case MISC:
                dic = new DictionaryFlat(ctx, "dictionary", 537360);
                break;
            case INSANE:
                dic = new DictionaryLetters();
                break;
            case NUMBERS:
                dic = new DictionaryNumbers();
                break;
            case CUSTOM:
                dic = new DictionaryCustomProvider(ctx);
                break;
            default:
                Log.e(LOG_TAG, "invalid index received");
            case RANDOM:
                dic = getDictionary(DictionaryType.values()[random.nextInt(DictionaryType.values().length-5)]);
                break;
        }
        return dic;
    }

    public IDictionary getDictionary(String type) {
        DictionaryType newType = DictionaryType.RANDOM;
        try {
            newType = DictionaryType.valueOf(type);
        } catch (Exception e) {
//			Log.v(LOG_TAG, "DictionaryType unknown: ",e);
            newType = DictionaryType.RANDOM;
        }
        // if random or custom dictionary types creates a new dictionary based on the type
        switch (newType) {
            case RANDOM:
                this.currentDic = getDictionary(DictionaryType.RANDOM);
                break;
            case CUSTOM:
                this.currentDic = getDictionary(DictionaryType.CUSTOM);
                break;
            default:
                if (!newType.equals(currentDictionaryType)) {
                    this.currentDic = getDictionary(newType);
                }
                break;
        }
        currentDictionaryType = newType;
        return this.currentDic;
    }

    public String getCurrentTheme() {
        return this.currentDictionaryType.toString();
    }
}


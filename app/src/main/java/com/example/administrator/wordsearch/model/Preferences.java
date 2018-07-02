package com.example.administrator.wordsearch.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.administrator.wordsearch.Constants;
import com.example.administrator.wordsearch.view.R;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by Administrator on 4/20/2018.
 */

public class Preferences {
    private final String PREFS_SIZE;
    private final String PREFS_TOUCHMODE;
    private final String PREFS_TOUCHMODE_DEFAULT;
    private final String PREFS_TOUCHMODE_DRAG;
    private final String PREFS_CATEGORY;

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREFS_SCORE_DEFAULT_NAME = "score_default_name";
    private static final String PREFS_SCORE_TIME = "score_time";
    private static final String PREFS_SCORE_NAME = "score_name";
    private static final String PREFS_SCORE = "score";
    private static final String PREFS_SEPARATOR = ":";
    private final SharedPreferences settings_scores;
    private final SharedPreferences settings;

    public Preferences(Context ctx) {
        settings_scores = ctx.getSharedPreferences(PREFS_NAME, 0);
        settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        PREFS_CATEGORY = ctx.getString(R.string.prefs_category);
        PREFS_SIZE = ctx.getString(R.string.prefs_size);
        PREFS_TOUCHMODE = ctx.getString(R.string.prefs_touch_mode);
        PREFS_TOUCHMODE_DEFAULT = ctx.getString(R.string.DRAG);
        PREFS_TOUCHMODE_DRAG = ctx.getString(R.string.DRAG);
    }

    public String getCategory() {
        return settings.getString(PREFS_CATEGORY, "RANDOM");
    }

    public int getSize() {
        int size_int = Constants.GRID_SIZE_DEFAULT;
        try {
            String size = settings.getString(PREFS_SIZE, null);
            size_int = Integer.valueOf(size);
        } catch (Exception e) {
            size_int = Constants.GRID_SIZE_DEFAULT;
        }
        return size_int;
    }

    public LinkedList<HighScore> getTopScores() {
        LinkedList<HighScore> scores = new LinkedList<HighScore>();
        for (int levelNum = 0; levelNum < Constants.MAX_TOP_SCORES; levelNum++) {
            String level = Integer.toString(levelNum);
            String name = settings_scores.getString(getTopScoreNameKey(level), "");
            long score = settings_scores.getLong(getTopScoreKey(level), -1);
            long time = settings_scores.getLong(getTopScoreTimeKey(level), -1);
            if (score != -1) {
                HighScore highScore = new HighScore(name, score, time);
                scores.add(highScore);
            }
        }
        return scores;
    }

    private String getTopScoreKey(String level) {
        return PREFS_SCORE + PREFS_SEPARATOR + level;
    }
    private String getTopScoreNameKey(String level) {
        return PREFS_SCORE_NAME + PREFS_SEPARATOR + level;
    }
    private String getTopScoreTimeKey(String level) {
        return PREFS_SCORE_TIME + PREFS_SEPARATOR + level;
    }

    public boolean getTouchMode() {
        return PREFS_TOUCHMODE_DRAG.equals(settings.getString(PREFS_TOUCHMODE, PREFS_TOUCHMODE_DEFAULT));
    }

    public void resetTopScores() {
        SharedPreferences.Editor editor = settings_scores.edit();
        for (int levelNum = 0; levelNum < Constants.MAX_TOP_SCORES; levelNum++) {
            String level = Integer.toString(levelNum);
            editor.remove(getTopScoreKey(level));
            editor.remove(getTopScoreNameKey(level));
            editor.remove(getTopScoreTimeKey(level));
        }
        editor.commit();
    }

    public void setTopScores(LinkedList<HighScore> highScores) {
        SharedPreferences.Editor editor = settings_scores.edit();
        Collections.sort(highScores);
        for (int levelNum = 0; levelNum < Constants.MAX_TOP_SCORES; levelNum++) {
            String level = Integer.toString(levelNum);
            if (levelNum < highScores.size()) {
                HighScore highScore = highScores.get(levelNum);
                editor.putString(getTopScoreNameKey(level), highScore.getName());
                editor.putLong(getTopScoreKey(level), highScore.getScore());
                editor.putLong(getTopScoreTimeKey(level), highScore.getTime());
            } else {
                editor.remove(getTopScoreKey(level));
                editor.remove(getTopScoreNameKey(level));
                editor.remove(getTopScoreTimeKey(level));
            }
        }
        editor.commit();
    }

    public void setDetaultName(String name) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREFS_SCORE_DEFAULT_NAME, name);
        editor.commit();
    }

    public String getDefaultName() {
        return settings.getString(PREFS_SCORE_DEFAULT_NAME, "");
    }

    public void increaseGamePlayCount() {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(Constants.KEY_GAME_PLAY_COUNT, getGamePlayCount()+1);
        editor.commit();
    }

    public Integer getGamePlayCount() {
        return settings.getInt(Constants.KEY_GAME_PLAY_COUNT, 0);
    }


    public Boolean isDonateIngored() {
        return settings.getBoolean(Constants.KEY_DONATE_IGNORE, false);
    }

    public void setDonateIgnore() {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Constants.KEY_DONATE_IGNORE, true);
        editor.commit();
    }
}
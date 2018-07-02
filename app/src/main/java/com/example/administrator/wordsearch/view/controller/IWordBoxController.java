package com.example.administrator.wordsearch.view.controller;

import com.example.administrator.wordsearch.model.Grid;
import com.example.administrator.wordsearch.model.Theme;

/**
 * Created by Administrator on 4/20/2018.
 */

public interface IWordBoxController {
    /**
     *
     * @param charSequence sets the letter to show the user which letter is being touched
     * 						null to hide the preview letter
     */
    public void setLetter(CharSequence charSequence);

    /**
     * removes a word from the list of words to find
     *
     * @param str word to remove the list of words
     * @return number of words left to find
     */
    public void wordFound(String str);

    /**
     * resets the list of words available to the user
     *
     * @param wordList new list of available words
     */
    public void resetWords(Grid grid);

    /**
     * applies a theme to the implementation's views
     *
     * @param theme new theme to apply
     */
    public void updateTheme(Theme theme);
}

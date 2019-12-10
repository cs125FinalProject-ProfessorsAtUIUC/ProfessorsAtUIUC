package com.professorsatuiuc;

import android.service.textservice.SpellCheckerService;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;

public class MySpellingSession extends SpellCheckerService.Session {
    @Override
    public void onCreate() {
    }

    @Override
    public SuggestionsInfo onGetSuggestions(TextInfo textInfo, int suggestionsLimit) {
        String word = textInfo.getText();
        String suggestions[] = null;
        if(word.equals("Dance 100")){
            suggestions = new String[]{"Danc 100", "Danc 101", "Danc 107"};
        }else{
            suggestions = new String[]{};
        }
        SuggestionsInfo suggestionsInfo = new
                SuggestionsInfo(SuggestionsInfo.RESULT_ATTR_LOOKS_LIKE_TYPO, suggestions);
        return suggestionsInfo;
    }
}

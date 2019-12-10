package com.professorsatuiuc;

import android.service.textservice.SpellCheckerService;

public class SpellingService extends SpellCheckerService {
    @Override
    public Session createSession() {
        return new MySpellingSession();
    }
}














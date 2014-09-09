package org.vince.pegdown.furigana;

import org.parboiled.matchers.CharRangeMatcher;

/**
 * Created by vincent on 09/09/14.
 */
public class KanjiMatcher extends CharRangeMatcher {

    public KanjiMatcher() {
        super((char)0x4e00,(char)0x9faf);
    }

}
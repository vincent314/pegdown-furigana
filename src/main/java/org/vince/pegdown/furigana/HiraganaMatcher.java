package org.vince.pegdown.furigana;

import org.parboiled.matchers.CharRangeMatcher;

/**
 * Created by vincent on 09/09/14.
 */
public class HiraganaMatcher extends CharRangeMatcher {

    public HiraganaMatcher() {
        super((char) 0x3040, (char) 0x3096);
    }
}
/*
 * Copyright (C) 2014 Vincent Marmin
 *
 *
 *     Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *     The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.vince.pegdown.furigana;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.Cached;
import org.parboiled.annotations.DontLabel;
import org.pegdown.ast.TextNode;
import org.pegdown.plugins.InlinePluginParser;

/**
 * Rules to parse markdown furigana syntax.
 * Syntax exemple : 水（みず）
 * <p/>
 * Created by vincent on 05/09/14.
 */
public class FuriganaParser extends BaseParser<Object> implements InlinePluginParser {

    /**
     * Build the main furigana parser rule.
     *
     * @return
     */
    public Rule inputLine() {
        return Sequence(beforeAndKanji(), open(), furigana(), close(),
                push(new FuriganaNode((String) pop(1), (String) pop(0))));
    }

    public Rule beforeAndKanji() {
        return Sequence(OneOrMore(any()),push(match()));
    }

    public Rule any(){
        return NoneOf("（*~_\n\r\t\f");
    }

    /**
     * Matches a Kanji character
     *
     * @return The resulting pegdown rule
     */
    public Rule kanji() {
        return Sequence(kanjiRange(),
                push(match()));
    }

    /**
     * Matches the opening paranthese (a long one)
     *
     * @return The resulting pegdown rule
     */
    public Rule open() {
        return Ch('（');
    }

    /**
     * Matches the closing paranthese (a long one)
     *
     * @return The resulting pegdown rule
     */
    public Rule close() {
        return Ch('）');
    }

    /**
     * Matches one or more hiragana used as furigana
     *
     * @return The resulting pegdown rule
     */
    public Rule furigana() {
        return Sequence(OneOrMore(hiraganaRange()),
                push(match()));
    }

    /**
     * Matches
     *
     * @return The resulting pegdown rule
     */
    @Override
    public Rule[] inlinePluginRules() {
        return new Rule[] { inputLine() };
    }

    /**
     * Matches a Kanji
     *
     * @return The resulting pegdown rule
     */
    @Cached
    @DontLabel
    public Rule kanjiRange() {
        return new KanjiMatcher();
    }

    /**
     * Matches a Hiragana
     *
     * @return The resulting pegdown rule
     */
    @Cached
    @DontLabel
    public Rule hiraganaRange() {
        return new HiraganaMatcher();
    }
}

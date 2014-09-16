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

import org.pegdown.ast.InlineHtmlNode;
import org.pegdown.ast.SuperNode;
import org.pegdown.ast.Visitor;
/**
 * Furigana Pegdown Node. Host kanji and furigana data, also render HTML.
 *
 * Created by vincent on 07/09/14.
 */
public class FuriganaNode extends SuperNode {
    private final String kanji;
    private final String furigana;
    private final String before;

    /**
     * Constructor
     *
     * @param kanji    Kanji to annotate with furigana
     * @param furigana Hiragana characters to render as furigana
     */
    public FuriganaNode(String before, String kanji, String furigana) {
        this.before = before;
        this.kanji = kanji;
        this.furigana = furigana;
    }

    public FuriganaNode(String before, String furigana) {
        if (before == null || before.length() < 2) {
            this.before = "";
        } else {
            this.before = before.substring(0, before.length() - 1);
        }
        this.kanji = before.substring(before.length() - 1);
        this.furigana = furigana;
    }

    /**
     * Build HTML string with ruby tag.
     *
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        StringBuilder builder = new StringBuilder();
        builder.append(before);
        builder.append("<ruby>");
        builder.append(kanji);
        builder.append("<rp>(</rp>");
        builder.append("<rt>").append(furigana).append("</rt>");
        builder.append("<rp>)</rp>");
        builder.append("</ruby>");
        InlineHtmlNode node = new InlineHtmlNode(builder.toString());
        visitor.visit(node);
    }
}

package org.vince.pegdown.furigana;

import org.pegdown.ast.InlineHtmlNode;
import org.pegdown.ast.SuperNode;
import org.pegdown.ast.Visitor;

/**
 * Created by vincent on 07/09/14.
 */
public class FuriganaNode extends SuperNode {
    public String kanji;
    public String furigana;

    public FuriganaNode(String kanji, String furigana) {
        this.kanji = kanji;
        this.furigana = furigana;
    }

    @Override
    public void accept(Visitor visitor) {
        StringBuilder builder = new StringBuilder();
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

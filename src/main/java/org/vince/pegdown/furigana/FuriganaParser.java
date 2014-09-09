package org.vince.pegdown.furigana;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.Cached;
import org.parboiled.annotations.DontLabel;
import org.pegdown.plugins.InlinePluginParser;

/**
 * Created by vincent on 05/09/14.
 */
public class FuriganaParser extends BaseParser<Object> implements InlinePluginParser {


    public Rule inputLine() {
        return Sequence(
                kanji(),
                open(),
                furigana(),
                push(new FuriganaNode((String)pop(1),(String)pop(0))),
                close());
    }

    public Rule kanji() {
        return Sequence(kanjiRange(),
                push(match()));
    }

    public Rule open() {
        return Ch('（');
    }

    public Rule close() {
        return Ch('）');
    }

    public Rule furigana() {
        return Sequence(OneOrMore(hiraganaRange()),
                push(match()));
    }

    @Override
    public Rule[] inlinePluginRules() {
        return new Rule[]{inputLine()};
    }

    @Cached
    @DontLabel
    public Rule kanjiRange() {
        return new KanjiMatcher();
    }

    @Cached
    @DontLabel
    public Rule hiraganaRange() {
        return new HiraganaMatcher();
    }
}

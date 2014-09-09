package org.vince.pegdown.furigana;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.pegdown.plugins.PegDownPlugins;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by vincent on 09/09/14.
 */
public class TestFuriganaParser {

    private PegDownProcessor processor;

    @BeforeClass
    public void before(){
        PegDownPlugins plugins = PegDownPlugins.builder().withPlugin(FuriganaParser.class).build();
        processor = new PegDownProcessor(Extensions.NONE, plugins);
    }

    @Test(dataProvider = "furiganaOK")
    public void testMarkdownToHtml(String input, String expected) {
        String html = processor.markdownToHtml(input);
        Assert.assertEquals(html,expected);
    }

    @DataProvider(name="furiganaOK")
    public static Object[][] testData() {
        return new Object[][]{
                {"日（にち）","<p><ruby>日<rt>(</rt><rp>にち</rp><rt>)</rt></ruby></p>"},
                {"Le kanji 人（ひと） signifie **personne**","<p>Le kanji <ruby>人<rt>(</rt><rp>ひと</rp><rt>)</rt></ruby> signifie <strong>personne</strong></p>"},
                {"番（ばん）","<p><ruby>番<rt>(</rt><rp>ばん</rp><rt>)</rt></ruby></p>"},
                {"一（いち）番（ばん）","<p><ruby>一<rt>(</rt><rp>いち</rp><rt>)</rt></ruby><ruby>番<rt>(</rt><rp>ばん</rp><rt>)</rt></ruby></p>"},
                {"いち番（ばん）の人は","<p>いち番（ばん）の人は</p>"},
                {"一（いち）番（ばん）安（やす）い","<p><ruby>一<rt>(</rt><rp>いち</rp><rt>)</rt></ruby><ruby>番<rt>(</rt><rp>ばん</rp><rt>)</rt></ruby><ruby>安<rt>(</rt><rp>やす</rp><rt>)</rt></ruby>い</p>"},
        };
    }
}

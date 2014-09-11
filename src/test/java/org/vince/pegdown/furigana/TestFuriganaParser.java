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
                {"日（にち）","<p><ruby>日<rp>(</rp><rt>にち</rt><rp>)</rp></ruby></p>"},
                {"Le kanji 人（ひと） signifie **personne**","<p>Le kanji <ruby>人<rp>(</rp><rt>ひと</rt><rp>)</rp></ruby> signifie <strong>personne</strong></p>"},
                {"番（ばん）","<p><ruby>番<rp>(</rp><rt>ばん</rt><rp>)</rp></ruby></p>"},
                {"一（いち）番（ばん）","<p><ruby>一<rp>(</rp><rt>いち</rt><rp>)</rp></ruby><ruby>番<rp>(</rp><rt>ばん</rt><rp>)</rp></ruby></p>"},
                {"いち番（ばん）の人は","<p>いち番（ばん）の人は</p>"},
                {"一（いち）番（ばん）安（やす）い","<p><ruby>一<rp>(</rp><rt>いち</rt><rp>)</rp></ruby><ruby>番<rp>(</rp><rt>ばん</rt><rp>)</rp></ruby><ruby>安<rp>(</rp><rt>やす</rt><rp>)</rp></ruby>い</p>"},
        };
    }
}

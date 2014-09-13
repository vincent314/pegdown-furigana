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

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.pegdown.plugins.PegDownPlugins;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Unit test for the Furigana Parser
 *
 * Created by vincent on 09/09/14.
 */
public class TestFuriganaParser {

    private PegDownProcessor processor;

    @BeforeClass
    public void before(){
        PegDownPlugins plugins = PegDownPlugins.builder().withPlugin(FuriganaParser.class).build();
        processor = new PegDownProcessor(Extensions.NONE, plugins);
    }

    /**
     * Render markdown input and compare over expected rendered string.
     * @param input Markdown string
     * @param expected HTML string
     */
    @Test(dataProvider = "furiganaOK")
    public void testMarkdownToHtml(String input, String expected) {
        String html = processor.markdownToHtml(input);
        assertEquals(html, expected);
    }

    /**
     * Provides test cases
     * @return test data
     */
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

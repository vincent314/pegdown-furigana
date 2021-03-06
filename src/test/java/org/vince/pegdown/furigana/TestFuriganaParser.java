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

import org.parboiled.Parboiled;
import org.parboiled.parserunners.TracingParseRunner;
import org.parboiled.support.ParsingResult;
import org.pegdown.Extensions;
import org.pegdown.Parser;
import org.pegdown.PegDownProcessor;
import org.pegdown.plugins.PegDownPlugins;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

/**
 * Unit test for the Furigana Parser
 * <p/>
 * Created by vincent on 09/09/14.
 */
public class TestFuriganaParser {

    private PegDownProcessor processor;

    @BeforeClass
    public void before() {
        PegDownPlugins plugins = PegDownPlugins.builder().withPlugin(FuriganaParser.class).build();
        processor = new PegDownProcessor(Extensions.TABLES, plugins);
    }

    /**
     * Render markdown input and compare over expected rendered string.
     *
     * @param input    Markdown string
     * @param expected HTML string
     */
    @Test(dataProvider = "furiganaOK")
    public void testMarkdownToHtml(String input, String expected) {
        String html = processor.markdownToHtml(input);
        assertEquals(html, expected);
    }

    /**
     * Provides test cases
     *
     * @return test data
     */
    @DataProvider(name = "furiganaOK")
    public static Object[][] testData() {
        return new Object[][]{
                {
                        "日（にち）",
                        "<p><ruby>日<rp>(</rp><rt>にち</rt><rp>)</rp></ruby></p>"
                },
                {
                        "Le kanji 人（ひと） signifie **personne**",
                        "<p>Le kanji <ruby>人<rp>(</rp><rt>ひと</rt><rp>)</rp></ruby> signifie <strong>personne</strong></p>"
                },
                {
                        "番（ばん）",
                        "<p><ruby>番<rp>(</rp><rt>ばん</rt><rp>)</rp></ruby></p>"
                },
                {
                        "一（いち）番（ばん）",
                        "<p><ruby>一<rp>(</rp><rt>いち</rt><rp>)</rp></ruby><ruby>番<rp>(</rp><rt>ばん</rt><rp>)</rp></ruby></p>"
                },
                {
                        "いち番（ばん）の人は",
                        "<p>いち<ruby>番<rp>(</rp><rt>ばん</rt><rp>)</rp></ruby>の人は</p>"
                },
                {
                        "一（いち）番（ばん）は安（やす）い",
                        "<p><ruby>一<rp>(</rp><rt>いち</rt><rp>)</rp></ruby><ruby>番<rp>(</rp><rt>ばん</rt><rp>)</rp></ruby>は<ruby>安<rp>(</rp><rt>やす</rt><rp>)</rp></ruby>い</p>"
                },
                {
                        "始（はじ）まる",
                        "<p><ruby>始<rp>(</rp><rt>はじ</rt><rp>)</rp></ruby>まる</p>"
                },
                {
                        "**始（はじ）まる**",
                        "<p><strong><ruby>始<rp>(</rp><rt>はじ</rt><rp>)</rp></ruby>まる</strong></p>"
                },
                {
                        "__始（はじ）まる__",
                        "<p><strong><ruby>始<rp>(</rp><rt>はじ</rt><rp>)</rp></ruby>まる</strong></p>"
                },
                {
                        "この人（ひと）の名（な）前（まえ）はヴァンサンです。",
                        "<p>この<ruby>人<rp>(</rp><rt>ひと</rt><rp>)</rp></ruby>の<ruby>名<rp>(</rp><rt>な</rt><rp>)</rp></ruby>" +
                                "<ruby>前<rp>(</rp><rt>まえ</rt><rp>)</rp></ruby>はヴァンサンです。</p>"
                },
                {
                        "この人（ひと）の名（な）前（まえ）はながいです",
                        "<p>この<ruby>人<rp>(</rp><rt>ひと</rt><rp>)</rp></ruby>の<ruby>名<rp>(</rp><rt>な</rt><rp>)</rp></ruby>" +
                                "<ruby>前<rp>(</rp><rt>まえ</rt><rp>)</rp></ruby>はながいです</p>"
                },
                {
                        "TestOf**InsideStrong**Value",
                        "<p>TestOf**InsideStrong**Value</p>"
                },
                {
                        "あの人（ひと）",
                        "<p>あの<ruby>人<rp>(</rp><rt>ひと</rt><rp>)</rp></ruby></p>"
                },
                {
                        "外人（じん）",
                        "<p>外<ruby>人<rp>(</rp><rt>じん</rt><rp>)</rp></ruby></p>"
                },
                {
                        "Title\n=======\nBody",
                        "<h1>Title</h1><p>Body</p>"
                },
                {
                        "Title\n\n人（じん）",
                        "<p>Title</p><p><ruby>人<rp>(</rp><rt>じん</rt><rp>)</rp></ruby></p>"
                },
                {
                        "フランス語 | Actif | Passif\n" +
                                "---------|-------|---------\n" +
                                "tirer | 抜（め）く | 抜かれる\n"+
                                "tirer|抜（め）く|抜かれる\n",
                        "<table>\n" +
                                "  <thead>\n" +
                                "    <tr>\n" +
                                "      <th>フランス語 </th>\n" +
                                "      <th>Actif </th>\n" +
                                "      <th>Passif</th>\n" +
                                "    </tr>\n" +
                                "  </thead>\n" +
                                "  <tbody>\n" +
                                "    <tr>\n" +
                                "      <td>tirer </td>\n" +
                                "      <td><ruby>抜<rp>(</rp><rt>め</rt><rp>)</rp></ruby>く </td>\n" +
                                "      <td>抜かれる</td>\n" +
                                "    </tr>\n" +
                                "    <tr>\n" +
                                "      <td>tirer</td>\n" +
                                "      <td><ruby>抜<rp>(</rp><rt>め</rt><rp>)</rp></ruby>く</td>\n" +
                                "      <td>抜かれる</td>\n" +
                                "    </tr>\n" +
                                "  </tbody>\n" +
                                "</table>"
                },
                {
                        "|col1 | col2 | col3|\n" +
                                "|---------|-------|---------|\n" +
                                "|value1 | value2 | value3|\n" +
                                "|value1 | value2 | value3|\n",
                        "<table>\n" +
                                "  <thead>\n" +
                                "    <tr>\n" +
                                "      <th>col1 </th>\n" +
                                "      <th>col2 </th>\n" +
                                "      <th>col3</th>\n" +
                                "    </tr>\n" +
                                "  </thead>\n" +
                                "  <tbody>\n" +
                                "    <tr>\n" +
                                "      <td>value1 </td>\n" +
                                "      <td>value2 </td>\n" +
                                "      <td>value3</td>\n" +
                                "    </tr>\n" +
                                "    <tr>\n" +
                                "      <td>value1 </td>\n" +
                                "      <td>value2 </td>\n" +
                                "      <td>value3</td>\n" +
                                "    </tr>\n" +
                                "  </tbody>\n" +
                                "</table>"
                }
        };
    }

    @Test
    public void testTracingOK() {
        FuriganaParser parser = Parboiled.createParser(FuriganaParser.class);
        TracingParseRunner<FuriganaNode> runner = new TracingParseRunner<FuriganaNode>(parser.inputLine());
        ParsingResult<FuriganaNode> result = runner.run("水（みず）");
        System.out.println(runner.getLog());
        assertTrue(result.matched);
    }

    @Test
    public void testTracingKO() {
        FuriganaParser parser = Parboiled.createParser(FuriganaParser.class);
        TracingParseRunner<FuriganaNode> runner = new TracingParseRunner<FuriganaNode>(parser.inputLine());
        ParsingResult<FuriganaNode> result = runner.run("の人（ひと）");
        System.out.println(runner.getLog());
        assertTrue(result.matched);
    }

}

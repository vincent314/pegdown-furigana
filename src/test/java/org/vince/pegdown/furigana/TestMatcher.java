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

import org.mockito.Mockito;
import org.parboiled.MatcherContext;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit tests for the kanji and hiragan matchers.
 * <p/>
 * Check that they correctly match a set of characters.
 * <p/>
 * Created by vincent on 09/09/14.
 */
public class TestMatcher {


    /**
     * Test Kanji Matcher
     */
    @Test
    public void testKanjiMatcher() {
        KanjiMatcher matcher = new KanjiMatcher();
        MatcherContext context = Mockito.mock(MatcherContext.class);

//        True asserts
        Mockito.when(context.getCurrentChar()).thenReturn('人');
        Assert.assertTrue(matcher.match(context));
        Mockito.when(context.getCurrentChar()).thenReturn('電');
        Assert.assertTrue(matcher.match(context));

//        False asserts
        Mockito.when(context.getCurrentChar()).thenReturn('A');
        Assert.assertFalse(matcher.match(context));
        Mockito.when(context.getCurrentChar()).thenReturn('9');
        Assert.assertFalse(matcher.match(context));
        Mockito.when(context.getCurrentChar()).thenReturn('あ');
        Assert.assertFalse(matcher.match(context));
        Mockito.when(context.getCurrentChar()).thenReturn('（');
        Assert.assertFalse(matcher.match(context));
    }

    /**
     * Test the Hiragana matcher
     */
    @Test
    public void testHiraganaMatcher() {
        HiraganaMatcher matcher = new HiraganaMatcher();
        MatcherContext context = Mockito.mock(MatcherContext.class);

//        True asserts
        Mockito.when(context.getCurrentChar()).thenReturn('あ');
        Assert.assertTrue(matcher.match(context));
        Mockito.when(context.getCurrentChar()).thenReturn('お');
        Assert.assertTrue(matcher.match(context));
        Mockito.when(context.getCurrentChar()).thenReturn('ん');
        Assert.assertTrue(matcher.match(context));
        Mockito.when(context.getCurrentChar()).thenReturn('を');
        Assert.assertTrue(matcher.match(context));

//        False asserts
        Mockito.when(context.getCurrentChar()).thenReturn('日');
        Assert.assertFalse(matcher.match(context));
        Mockito.when(context.getCurrentChar()).thenReturn('a');
        Assert.assertFalse(matcher.match(context));
        Mockito.when(context.getCurrentChar()).thenReturn('0');
        Assert.assertFalse(matcher.match(context));
        Mockito.when(context.getCurrentChar()).thenReturn('）');
        Assert.assertFalse(matcher.match(context));
    }
}

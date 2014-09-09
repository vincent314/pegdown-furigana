package org.vince.pegdown.furigana;

import org.mockito.Mockito;
import org.parboiled.MatcherContext;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by vincent on 09/09/14.
 */
public class TestMatcher {


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

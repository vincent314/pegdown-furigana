pegdown-furigana
================

**Pegdown-furigana** is an extension of Markdown processor [Pegdown](https://github.com/sirthias/pegdown) and is mainly
useful to Japanese language learners.

It extends the markdown syntax and adds [furigana](http://en.wikipedia.org/wiki/Furigana) support by using the 
&lt;ruby> HTML5 tag.
 
This extension is mostly based on the excellent 
[djfun's markdown extension](https://github.com/djfun/furigana_markdown/blob/master/furigana.py) for Python.

Dependencies
------------------

This extension is based on **Pegdown v1.4.2**. However, it may work with older Pegdown versions, just check that the PegDownProcessor accepts plugins. 

Using the Pegdown-furigana extension library
-------------------------

Add **pegdown-furigana.jar** to your classpath and instanciate the PegDownProcessor.

    PegDownPlugins plugins = PegDownPlugins.builder().withPlugin(FuriganaParser.class).build();
    PegDownProcessor processor = new PegDownProcessor(Extensions.NONE, plugins);
    processor.markdownToHtml("漢（かん）字（じ）");
    
How to write furigana with pegdown-furigana
-------------

Writing furigana above any kanji in HTML5 requires 4 steps :
 
1. write the Kanji
2. open a parenthesis, the long one you can find in your Japanese IME (Input Method Editor).
3. type the hiragana — one or more — you want to turn into furigana. **Hiragana only!**
4. close your parenthesis – once more, a long one.

For example, typing `福（ふく）` in your markdown document will produce this in HTML5 :

    <ruby><rb>福</rb><rp>（</rp><rt>ふく</rt><rp>）</rp></ruby>
    
And should look like this in your final document : <ruby><rb>福</rb><rp>（</rp><rt>ふく</rt><rp>）</rp></ruby>.
 
If, instead of this, you see hiragana between parentheses, it means your browser (ie. Firefox) does not support the *ruby* tag. To solve this problem, you can install an extension such as **HTML Ruby**.

If you get small hiragana above your kanji, \o/ you are done.

Using Pegdown-Furigana with Gaiden
-----------------

[Gaiden](https://github.com/kobo/gaiden) is a tool that generates static HTML websites from Markdown files. It uses Pegdown as a processor, as a result you can use Pegdown-Furigana with Gaiden.

I [forked this project](https://github.com/vincent314/gaiden) and added Furigana support, so if you want to test Pegdown-Furigana with Gaiden right away, feel free to [download the zip file](https://github.com/vincent314/gaiden/releases/tag/0.4-SNAPSHOT).


pegdown-furigana
================

**Pegdown-furigana** is an extension to [Pegdown](https://github.com/sirthias/pegdown) Markdown processor and is mainly
useful for learners of japanese language.
It extends the markdown syntaxe and adds [furigana](http://en.wikipedia.org/wiki/Furigana) support by using the 
&lt;ruby> HTML5 tag.
 
This extension is mainly based on the excellent 
[djfun's markdown extension](https://github.com/djfun/furigana_markdown/blob/master/furigana.py) for python.

Dependencies
------------------

This extension is based on **Pegdown v1.4.2**, however it may work with older Pegdown version, just check that PegDownProcessor
class accepts plugins. 

Using Pegdown-furigana extension library
-------------------------

Add **pegdown-furigana.jar** to your classpath and instanciate the PegDownProcessor.

    PegDownPlugins plugins = PegDownPlugins.builder().withPlugin(FuriganaParser.class).build();
    PegDownProcessor processor = new PegDownProcessor(Extensions.NONE, plugins);
    processor.markdownToHtml("漢（かん）字（じ）");
    
Furigana usage
-------------

In order to write furigana over any kanji in HTML5, you'll have to write in a markdown input composed in 4 parts :
 
1. the Kanji
2. opening paranthese, a long paranthese provided by your IME (Input Method Editor) in japanese mode, not the usual one.
3. hiragana **only** — one or many — rendered as furigana
4. closing paranthese, a long one.

For instance, typing `福（ふく）` in your markdown document, should render the HTML5 ruby tag like this :

    <ruby><rb>福</rb><rp>（</rp><rt>ふく</rt><rp>）</rp></ruby>
    
As a result, you should get <ruby><rb>福</rb><rp>（</rp><rt>ふく</rt><rp>）</rp></ruby>.
 
If you get hiragana between parantheses, your browser does not support ruby tag (ie. Firefox) and you should install the **HTML Ruby** extension.

If you get small hiragana over the kanji, your are done.

Using Pegdown-Furigana with Gaiden
-----------------

[Gaiden](https://github.com/kobo/gaiden) tool generates static HTML websites from Markdown files. It uses Pegdown as
processor, that's why you can use Pegdown-Furigana with Gaiden.

I [forked this project](https://github.com/vincent314/gaiden) and added Furigana support, so you can test Pegdown-Furigana
immediately with Gaiden : [download the zip](https://github.com/vincent314/gaiden/releases/tag/0.4-SNAPSHOT).


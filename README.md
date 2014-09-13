pegdown-furigana
================

Pegdown extension to render furigana (tips over japanese Kanji) using &lt;ruby> HTML5 tag based on the excellent 
[djfun's markdown extension](https://github.com/djfun/furigana_markdown/blob/master/furigana.py).

Using Pegdown-furigana extension
-------------------------

Add **pegdown-furigana.jar** to your classpath and instanciate the PegDownProcessor like this :

    PegDownPlugins plugins = PegDownPlugins.builder().withPlugin(FuriganaParser.class).build();
    PegDownProcessor processor = new PegDownProcessor(Extensions.NONE, plugins);
    
Furigana usage
-------------

Writing furigana over any kanji, you'll have to write a string in 4 parts :
 
1. the Kanji
2. opening paranthese, a long paranthese provided by your IME (Input Method Editor) in japanese mode, not the usual one.
3. hiragana — one or many — rendered as furigana
4. closing paranthese, a long one.

For instance, typing `福（ふく）` in your markdown document, should render the HTML5 ruby tag like this :

    <ruby><rb>福</rb><rp>（</rp><rt>ふく</rt><rp>）</rp></ruby>
    
As a result, you should get <ruby><rb>福</rb><rp>（</rp><rt>ふく</rt><rp>）</rp></ruby>.
 
If you get hiragana between parantheses, your browser does not support ruby tag (ie. Firefox) and you should install the **HTML Ruby** extension.

If you get small hiragana over the kanji, your are done.


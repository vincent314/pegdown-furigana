pegdown-furigana
================

Pegdown extension to render furigana (tips over japanese Kanji) using &lt;ruby> HTML5 tag

Using Pegdown-furigana extension
-------------------------

Add **pegdown-furigana.jar** to your classpath and instanciate the PegDownProcessor like this :

    PegDownPlugins plugins = PegDownPlugins.builder().withPlugin(FuriganaParser.class).build();
    PegDownProcessor processor = new PegDownProcessor(Extensions.NONE, plugins);
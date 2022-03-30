Sample antlr4 maven project
===================================================

March 30, 2022

Parses a filter grammar.

For example:

    "A"         Predicate<Set<String> p = x -> return x.contains("A")
    
    "A and B"   Predicate<Set<String> p = x -> return x
                    .contains("A")
                    .and(x.contains("B");
    
    "A or B"    Predicate<Set<String> p = x -> return x
                    .contains("A")
                    .or(x.contains("B");

(The predicate generation is not done.)

Setup
---------------------------------------------------
1. If grammar (g4 file) is in right spot in directory tree, the 
   antlr4-maven-plugin will find it and compile as part of mvn
   compile.  The right spot is exactly the same package as your
   listener, but under src/main/antlr4 instead of src/main/java.
2. To get IntelliJ to see the generated sources, run mvn compile
   to create the generated Antlr4 sources in target/antlr4.  Then,
   right click that generated directory, pick "Mark Directory as",
   and set it as a "Generated Sources Root".  After a maven clean, 
   all generated classes will show as red.  Just run maven compile 
   again.

References
---------------------------------------------------
  * [Antlr4 Maven Plugin](https://www.antlr.org/api/maven-plugin/latest/usage.html). 
    Default directory for Antlr sources is src/main/antlr4.
  * [Build Helper Maven Plugin](https://www.mojohaus.org/build-helper-maven-plugin/).
    Used to add generated sources to the classpath.  In this case,
    target/generated-sources/antlr4.
  * [Baeldung Java with ANTLR](https://www.baeldung.com/java-antlr). As 
    always, a short and sweet summary.
  * [List of sample antlr4 grammars](https://github.com/antlr/grammars-v4).
    Long list with some pretty obscure stuff.  Fun to browse.
  * [The Definitive ANTLR 4 Reference](https://pragprog.com/search/?q=the-definitive-antlr-4-reference)

   
package com.markbucciarelli.antlr4;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;

public class TestParser {

  @Test
  void oneFilterID() {
    // setup
    String filter = "A";
    List<String> expected = Arrays.asList(
      "enterExpression: A",
      "  enterFilterID: A",
      "  exitFilterID: A",
      "exitExpression: A"
    );

    // execute
    MyTestListener listener = parse(filter);

    // verify
    assertLinesMatch(expected, listener.events);
  }

  @Test
  void testOr() {
    // setup
    String filter = "A or B";
    List<String> expected = Arrays.asList(
      "enterExpression: AorB",
      "  enterExpression: A",
      "    enterFilterID: A",
      "    exitFilterID: A",
      "  exitExpression: A",
      "  enterOperator: or",
      "  exitOperator: or",
      "  enterExpression: B",
      "    enterFilterID: B",
      "    exitFilterID: B",
      "  exitExpression: B",
      "exitExpression: AorB"
    );

    // execute
    MyTestListener listener = parse(filter);

    // verify
    assertLinesMatch(expected, listener.events);
  }

  @Test
  void testAnd() {
    // setup
    String filter = "A and B";
    List<String> expected = Arrays.asList(
      "enterExpression: AandB",
      "  enterExpression: A",
      "    enterFilterID: A",
      "    exitFilterID: A",
      "  exitExpression: A",
      "  enterOperator: and",
      "  exitOperator: and",
      "  enterExpression: B",
      "    enterFilterID: B",
      "    exitFilterID: B",
      "  exitExpression: B",
      "exitExpression: AandB"
    );

    // execute
    MyTestListener listener = parse(filter);

    // verify
    assertLinesMatch(expected, listener.events);
  }

  @Test
  void testNested() {
    // setup
    String filter = "(A and B) or ((D and E) or D)";
    List<String> expected = Arrays.asList(
      "enterExpression: (AandB)or((DandE)orD)",
      "  enterExpression: (AandB)",
      "    enterExpression: AandB",
      "      enterExpression: A",
      "        enterFilterID: A",
      "        exitFilterID: A",
      "      exitExpression: A",
      "      enterOperator: and",
      "      exitOperator: and",
      "      enterExpression: B",
      "        enterFilterID: B",
      "        exitFilterID: B",
      "      exitExpression: B",
      "    exitExpression: AandB",
      "  exitExpression: (AandB)",
      "  enterOperator: or",
      "  exitOperator: or",
      "  enterExpression: ((DandE)orD)",
      "    enterExpression: (DandE)orD",
      "      enterExpression: (DandE)",
      "        enterExpression: DandE",
      "          enterExpression: D",
      "            enterFilterID: D",
      "            exitFilterID: D",
      "          exitExpression: D",
      "          enterOperator: and",
      "          exitOperator: and",
      "          enterExpression: E",
      "            enterFilterID: E",
      "            exitFilterID: E",
      "          exitExpression: E",
      "        exitExpression: DandE",
      "      exitExpression: (DandE)",
      "      enterOperator: or",
      "      exitOperator: or",
      "      enterExpression: D",
      "        enterFilterID: D",
      "        exitFilterID: D",
      "      exitExpression: D",
      "    exitExpression: (DandE)orD",
      "  exitExpression: ((DandE)orD)",
      "exitExpression: (AandB)or((DandE)orD)"
    );

    // execute
    MyTestListener listener = parse(filter);

    // verify
    assertLinesMatch(expected, listener.events);
  }

  private MyTestListener parse(String filter) {
    FilterLexer serverFilterLexer = new FilterLexer(CharStreams.fromString(filter));
    CommonTokenStream tokens = new CommonTokenStream(serverFilterLexer);
    FilterParser parser = new FilterParser(tokens);
    ParseTreeWalker walker = new ParseTreeWalker();
    MyTestListener listener = new MyTestListener();
    walker.walk(listener, parser.expression());
    return listener;
  }
}

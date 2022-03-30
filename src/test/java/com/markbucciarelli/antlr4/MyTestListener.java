package com.markbucciarelli.antlr4;

import java.util.ArrayList;
import java.util.List;

public class MyTestListener extends FilterBaseListener {

  public List<String> events = new ArrayList<>();
  private int depth = 0;

  private void addEvent(String method, String context) {
    if (depth < 0) {
      throw new IllegalStateException("negative depth");
    }
    StringBuilder y = new StringBuilder();
    for (int i = 0; i < depth; i++) {
      y.append("  ");
    }
    y.append(method);
    y.append(": ");
    y.append(context);
    events.add(y.toString());
  }

  @Override
  public void enterExpression(FilterParser.ExpressionContext ctx) {
    addEvent("enterExpression", ctx.getText());
    depth++;
  }

  @Override
  public void exitExpression(FilterParser.ExpressionContext ctx) {
    depth--;
    addEvent("exitExpression", ctx.getText());
  }

  @Override
  public void enterOperator(FilterParser.OperatorContext ctx) {
    addEvent("enterOperator", ctx.getText());
    depth++;
  }

  @Override
  public void exitOperator(FilterParser.OperatorContext ctx) {
    depth--;
    addEvent("exitOperator", ctx.getText());
  }

  @Override
  public void enterFilterID(FilterParser.FilterIDContext ctx) {
    addEvent("enterFilterID", ctx.getText());
    depth++;
  }

  @Override
  public void exitFilterID(FilterParser.FilterIDContext ctx) {
    depth--;
    addEvent("exitFilterID", ctx.getText());
  }
}

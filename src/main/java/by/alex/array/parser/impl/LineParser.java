package by.alex.array.parser.impl;

import by.alex.array.parser.DataParser;

public class LineParser implements DataParser {

  @Override
  public int[] parse(String rawLine) {
    String[] tokens = rawLine.strip().split(DELIMITER_REGEX);
    int[] values = new int[tokens.length];
    for (int i = 0; i < tokens.length; i++) {
      values[i] = Integer.parseInt(tokens[i]);
    }
    return values;
  }
}

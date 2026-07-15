package by.alex.array.parser.impl;

import by.alex.array.exception.ArrayProcessingException;
import by.alex.array.parser.DataParser;
import by.alex.array.validator.ArrayValidator;
import by.alex.array.validator.impl.ArrayValidatorImpl;

public class LineParser implements DataParser {

  @Override
  public int[] parse(String rawLine) throws ArrayProcessingException {
    if (rawLine == null || rawLine.isBlank()) {
      return new int[0];
    }
    ArrayValidator validator = new ArrayValidatorImpl();
    boolean isValid = validator.isValidNumberLine(rawLine);
    if (!isValid) {
      throw new ArrayProcessingException("Invalid raw line: " + rawLine);
    }
    String stripped = rawLine.strip();
    String[] tokens = stripped.split(DELIMITER_REGEX);
    int[] values = new int[tokens.length];
    for (int i = 0; i < tokens.length; i++) {
      values[i] = Integer.parseInt(tokens[i]);
    }
    return values;
  }
}
package by.alex.array.validator.impl;

import by.alex.array.validator.ArrayValidator;

public class ArrayValidatorImpl implements ArrayValidator {

  @Override
  public boolean isValidNumberLine(String rawLine) {
    return (rawLine != null && rawLine.matches(VALID_NUMBER_LINE_REGEX));
  }
}
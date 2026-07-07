package by.alex.array.validator;

public interface ArrayValidator {

  String VALID_NUMBER_LINE_REGEX = "^\\s*-?\\d+(?:\\s*,\\s*-?\\d+)*\\s*$";

  boolean isValidNumberLine(String rawLine);
}

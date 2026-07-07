package by.alex.array.parser;

import by.alex.array.exception.ArrayProcessingException;

public interface DataParser {

  String DELIMITER_REGEX = "\\s*,\\s*";

  int[] parse(String rawLine) throws ArrayProcessingException;
}

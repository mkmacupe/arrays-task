package by.alex.array.parser;

public interface DataParser {

  String DELIMITER_REGEX = "\\s*,\\s*";

  int[] parse(String rawLine);
}

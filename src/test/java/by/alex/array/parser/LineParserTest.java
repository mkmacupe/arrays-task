package by.alex.array.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import by.alex.array.exception.ArrayProcessingException;
import by.alex.array.parser.impl.LineParser;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LineParserTest {

  private final DataParser parser = new LineParser();

  @ParameterizedTest
  @MethodSource("provideValidLines")
  void shouldParseValidLine(String line, int[] expected) throws ArrayProcessingException {
    int[] result = parser.parse(line);
    assertArrayEquals(expected, result);
  }

  @ParameterizedTest
  @MethodSource("provideBlankLines")
  void shouldReturnEmptyArrayForBlankLines(String line) throws ArrayProcessingException {
    int[] expected = new int[0];
    int[] result = parser.parse(line);
    assertArrayEquals(expected, result);
  }

  @ParameterizedTest
  @MethodSource("provideInvalidLines")
  void shouldThrowExceptionForInvalidLines(String line) {
    assertThrows(
        ArrayProcessingException.class,
        () -> {
          parser.parse(line);
        });
  }

  static Stream<Arguments> provideValidLines() {
    return Stream.of(
        Arguments.of("1, 2, 3", new int[] {1, 2, 3}),
        Arguments.of("10, 20, 30, 40, 50", new int[] {10, 20, 30, 40, 50}),
        Arguments.of("-5, -10, -15, -20", new int[] {-5, -10, -15, -20}),
        Arguments.of("100, -50, -25", new int[] {100, -50, -25}),
        Arguments.of("5, -2, 18, 0, 4", new int[] {5, -2, 18, 0, 4}),
        Arguments.of("42, 17, 99, 3, 56, 8", new int[] {42, 17, 99, 3, 56, 8}));
  }

  static Stream<String> provideBlankLines() {
    return Stream.of(null, "", "   ");
  }

  static Stream<String> provideInvalidLines() {
    return Stream.of(
        "1y1 21 32",
        "1, 2, x3, 6..5, 77",
        "12 34 5a 7 90",
        "hello 3 8 9",
        "1; 2; 3",
        "11- 2 – 42-");
  }
}

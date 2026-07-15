package by.alex.array.reader.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import by.alex.array.exception.ArrayProcessingException;
import by.alex.array.reader.DataReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class FileDataReaderTest {

  @Test
  public void testReadLinesReturnsAllLinesPositive(@TempDir Path tempDir) throws Exception {
    Path file = tempDir.resolve("data.txt");
    Files.write(file, List.of("1, 2, 3", "4, 5, 6"));
    DataReader reader = new FileDataReader();

    List<String> actual = reader.readLines(file.toString());

    assertEquals(List.of("1, 2, 3", "4, 5, 6"), actual);
  }

  @Test
  public void testReadLinesNullPathThrowsExceptionNegative() {
    // when
    DataReader reader = new FileDataReader();

    // then
    assertThrows(ArrayProcessingException.class, () -> reader.readLines(null));
  }

  @Test
  public void testReadLinesMissingFileThrowsExceptionNegative() {
    // when
    DataReader reader = new FileDataReader();

    // then
    assertThrows(ArrayProcessingException.class, () -> reader.readLines("no_such_file_12345.txt"));
  }
}
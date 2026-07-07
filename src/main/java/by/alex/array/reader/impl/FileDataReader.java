package by.alex.array.reader.impl;

import by.alex.array.exception.ArrayProcessingException;
import by.alex.array.reader.DataReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileDataReader implements DataReader {

  @Override
  public List<String> readLines(String filePath) throws ArrayProcessingException {
    if (filePath == null) {
      throw new ArrayProcessingException("File path cannot be null");
    }
    try {
      Path path = Path.of(filePath);
      return Files.readAllLines(path);
    } catch (IOException | IllegalArgumentException e) {
      throw new ArrayProcessingException("Cannot read file: " + filePath, e);
    }
  }
}

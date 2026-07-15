package by.alex.array.reader.impl;

import by.alex.array.exception.ArrayProcessingException;
import by.alex.array.reader.DataReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileDataReader implements DataReader {
  private static final Logger logger = LogManager.getLogger(FileDataReader.class);

  @Override
  public List<String> readLines(String filePath) throws ArrayProcessingException {
    if (filePath == null) {
      throw new ArrayProcessingException("File path cannot be null");
    }
    try {
      Path path = Path.of(filePath);
      List<String> lines = Files.readAllLines(path);
      logger.info("Successfully read {} lines from file: {}", lines.size(), filePath);
      return lines;
    } catch (IOException | IllegalArgumentException e) {
      throw new ArrayProcessingException("Cannot read file: " + filePath, e);
    }
  }
}
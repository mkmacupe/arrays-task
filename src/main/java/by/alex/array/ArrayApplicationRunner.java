package by.alex.array;

import by.alex.array.factory.ArrayFactory;
import by.alex.array.factory.impl.IntArrayFactory;
import by.alex.array.entity.IntArrayEntity;
import by.alex.array.exception.ArrayProcessingException;
import by.alex.array.parser.DataParser;
import by.alex.array.parser.impl.LineParser;
import by.alex.array.reader.DataReader;
import by.alex.array.reader.impl.FileDataReader;
import by.alex.array.service.ArrayCalculationService;
import by.alex.array.service.ArraySortService;
import by.alex.array.service.impl.ArrayCalculationServiceImpl;
import by.alex.array.service.impl.ArraySortServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayApplicationRunner {

  private static final Logger logger = LogManager.getLogger();

  private final DataReader reader = new FileDataReader();
  private final DataParser parser = new LineParser();
  private final ArrayFactory factory = new IntArrayFactory();
  private final ArrayCalculationService calculationService = new ArrayCalculationServiceImpl();
  private final ArraySortService sortService = new ArraySortServiceImpl();

  public void run(String filePath) {
    try {
      List<String> lines = reader.readLines(filePath);
      for (String line : lines) {
        processRawLine(line);
      }
    } catch (ArrayProcessingException e) {
      logger.error(e.getMessage(), e);
    }
  }

  private void processRawLine(String rawLine) {
    try {
      int[] numbers = parser.parse(rawLine);
      if (numbers.length > 0) {
        IntArrayEntity array = factory.create(numbers);
        logStatistics(array);
      } else {
        logger.debug("Empty line skipped: {}", rawLine);
      }
    } catch (ArrayProcessingException e) {
      logger.error("Error parsing line: {}", rawLine, e);
    }
  }

  private void logStatistics(IntArrayEntity array) {
    int[] values = array.getValues();
    logger.info("Array: {}", Arrays.toString(values));

    OptionalInt min = calculationService.findMin(array);
    min.ifPresent(value -> logger.info("Min: {}", value));

    OptionalInt max = calculationService.findMax(array);
    max.ifPresent(value -> logger.info("Max: {}", value));

    OptionalLong sum = calculationService.calculateSum(array);
    sum.ifPresent(value -> logger.info("Sum: {}", value));

    OptionalDouble average = calculationService.calculateAverage(array);
    average.ifPresent(value -> logger.info("Average: {}", value));

    int[] bubbleSorted = sortService.bubbleSort(array);
    logger.info("Bubble sorted: {}", Arrays.toString(bubbleSorted));

    int[] insertionSorted = sortService.insertionSort(array);
    logger.info("Insertion sorted: {}", Arrays.toString(insertionSorted));
  }
}

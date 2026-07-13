package by.alex.array;

import by.alex.array.comparator.ArrayComparators;
import by.alex.array.entity.ArrayParameters;
import by.alex.array.entity.IntArrayEntity;
import by.alex.array.exception.ArrayProcessingException;
import by.alex.array.factory.ArrayFactory;
import by.alex.array.factory.impl.IntArrayFactory;
import by.alex.array.parser.DataParser;
import by.alex.array.parser.impl.LineParser;
import by.alex.array.reader.DataReader;
import by.alex.array.reader.impl.FileDataReader;
import by.alex.array.repository.ArrayRepository;
import by.alex.array.repository.impl.InMemoryArrayRepository;
import by.alex.array.specification.SumSpecification;
import by.alex.array.warehouse.Warehouse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayApplicationRunner {

  private static final Logger logger = LogManager.getLogger();

  private final DataReader reader = new FileDataReader();
  private final DataParser parser = new LineParser();
  private final ArrayFactory factory = new IntArrayFactory();
  private final ArrayRepository repository = InMemoryArrayRepository.getInstance();

  public void run(String filePath) {
    try {
      List<String> lines = reader.readLines(filePath);
      for (String line : lines) {
        processRawLine(line);
      }

      List<IntArrayEntity> allArrays = repository.sort(ArrayComparators.BY_ID);
      logger.info("All arrays count: {}", allArrays.size());

      for (IntArrayEntity entity : allArrays) {
        Optional<ArrayParameters> paramsOpt = Warehouse.getInstance().get(entity.getId());
        paramsOpt.ifPresent(
            params ->
                logger.info(
                    "Entity ID {}: sum={}, avg={}, min={}, max={}",
                    entity.getId(),
                    params.sum(),
                    params.average(),
                    params.min(),
                    params.max()));
      }

      List<IntArrayEntity> filtered = repository.findBy(new SumSpecification(10L, 50L));
      logger.info("Arrays with sum between 10 and 50 count: {}", filtered.size());

      filtered = repository.sort(ArrayComparators.BY_SUM);
      for (IntArrayEntity entity : filtered) {
        logger.info(
            "Sorted Array ID {}: values={}", entity.getId(), Arrays.toString(entity.getValues()));
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
        repository.add(array);
      } else {
        logger.debug("Empty line skipped: {}", rawLine);
      }
    } catch (ArrayProcessingException e) {
      logger.error("Error parsing line: {}", rawLine, e);
    }
  }
}

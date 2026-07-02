package by.alex.array.reader;

import by.alex.array.exception.ArrayProcessingException;
import java.util.List;

public interface DataReader {

  List<String> readLines(String filePath) throws ArrayProcessingException;
}

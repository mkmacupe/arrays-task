package by.alex.array;

public class ArraysApplication {

  private static final String DATA_FILE_PATH = "data/arrays.txt";

  public static void main(String[] args) {
    ArrayApplicationRunner runner = new ArrayApplicationRunner();
    runner.run(DATA_FILE_PATH);
  }
}
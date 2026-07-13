package by.alex.array.comparator;

import by.alex.array.entity.IntArrayEntity;
import java.util.Arrays;
import java.util.Comparator;

public enum ArrayComparators implements Comparator<IntArrayEntity> {
  BY_ID(Comparator.comparingLong(IntArrayEntity::getId)),
  BY_FIRST_ELEMENT(
      Comparator.comparingInt(array -> array.getValues().length > 0 ? array.getValues()[0] : 0)),
  BY_SUM(
      Comparator.comparingLong(
          array ->
              Arrays.stream(array.getValues())
                  .mapToLong(element -> element)
                  .sum())),
  BY_LENGTH(Comparator.comparingInt(array -> array.getValues().length));

  private final Comparator<IntArrayEntity> delegate;

  ArrayComparators(Comparator<IntArrayEntity> delegate) {
    this.delegate = delegate;
  }

  @Override
  public int compare(IntArrayEntity firstArray, IntArrayEntity secondArray) {
    return delegate.compare(firstArray, secondArray);
  }
}

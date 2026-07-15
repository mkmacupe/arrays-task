package by.alex.array.specification;

import by.alex.array.entity.IntArrayEntity;
import java.util.function.Predicate;

public class LengthSpecification implements Predicate<IntArrayEntity> {
  private final int threshold;

  public LengthSpecification(int threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean test(IntArrayEntity entity) {
    return entity.getValues().length > threshold;
  }
}
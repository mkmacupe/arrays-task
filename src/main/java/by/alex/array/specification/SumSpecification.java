package by.alex.array.specification;

import by.alex.array.entity.IntArrayEntity;
import java.util.Arrays;
import java.util.function.Predicate;

public class SumSpecification implements Predicate<IntArrayEntity> {
  private final long sumMin;
  private final long sumMax;

  public SumSpecification(long sumMin, long sumMax) {
    this.sumMin = sumMin;
    this.sumMax = sumMax;
  }

  @Override
  public boolean test(IntArrayEntity entity) {
    long sum =
        Arrays.stream(entity.getValues())
            .mapToLong(value -> value)
            .sum();
    return sum >= sumMin && sum <= sumMax;
  }
}
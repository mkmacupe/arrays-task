package by.alex.array.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.alex.array.entity.IntArrayEntity;
import org.junit.jupiter.api.Test;

public class SpecificationTest {

  @Test
  public void testSumSpecificationInRangePositive() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});

    boolean result = new SumSpecification(5L, 10L).test(entity);

    assertTrue(result);
  }

  @Test
  public void testSumSpecificationOutOfRangeNegative() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});

    boolean result = new SumSpecification(7L, 10L).test(entity);

    assertFalse(result);
  }

  @Test
  public void testAverageSpecificationBelowThresholdPositive() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {2, 4, 6});

    boolean result = new AverageSpecification(5.0).test(entity);

    assertTrue(result);
  }

  @Test
  public void testAverageSpecificationNotBelowThresholdNegative() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {2, 4, 6});

    boolean result = new AverageSpecification(4.0).test(entity);

    assertFalse(result);
  }

  @Test
  public void testMaxSpecificationEqualsThresholdPositive() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 5, 3});

    boolean result = new MaxSpecification(5).test(entity);

    assertTrue(result);
  }

  @Test
  public void testMaxSpecificationNotEqualsThresholdNegative() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 5, 3});

    boolean result = new MaxSpecification(4).test(entity);

    assertFalse(result);
  }

  @Test
  public void testMinSpecificationNotBelowThresholdPositive() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {3, 5, 7});

    boolean result = new MinSpecification(3).test(entity);

    assertTrue(result);
  }

  @Test
  public void testMinSpecificationBelowThresholdNegative() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {3, 5, 7});

    boolean result = new MinSpecification(4).test(entity);

    assertFalse(result);
  }

  @Test
  public void testLengthSpecificationAboveThresholdPositive() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});

    boolean result = new LengthSpecification(2).test(entity);

    assertTrue(result);
  }

  @Test
  public void testLengthSpecificationNotAboveThresholdNegative() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});

    boolean result = new LengthSpecification(3).test(entity);

    assertFalse(result);
  }
}

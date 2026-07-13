package by.alex.array.entity;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import by.alex.array.exception.ArrayProcessingException;
import org.junit.jupiter.api.Test;

public class IntArrayEntityTest {

  @Test
  public void testSetElementChangesValuePositive() throws ArrayProcessingException {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});

    entity.setElement(1, 99);

    assertArrayEquals(new int[] {1, 99, 3}, entity.getValues());
  }

  @Test
  public void testSetElementOutOfBoundsThrowsExceptionNegative() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});

    assertThrows(ArrayProcessingException.class, () -> entity.setElement(5, 99));
  }

  @Test
  public void testGetValuesReturnsDefensiveCopyPositive() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});

    int[] snapshot = entity.getValues();
    snapshot[0] = 999;

    assertArrayEquals(new int[] {1, 2, 3}, entity.getValues());
  }
}

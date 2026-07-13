package by.alex.array.observer.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.alex.array.entity.ArrayParameters;
import by.alex.array.entity.IntArrayEntity;
import by.alex.array.warehouse.Warehouse;
import java.lang.reflect.Field;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayObserverImplTest {

  @BeforeEach
  public void setUp() throws Exception {
    Field instanceField = Warehouse.class.getDeclaredField("instance");
    instanceField.setAccessible(true);
    instanceField.set(null, null);
  }

  @Test
  public void testOnValuesChangedStoresStatisticsPositive() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {10, -2, 5, 3});
    ArrayObserverImpl observer = new ArrayObserverImpl();

    observer.onValuesChanged(entity);

    Optional<ArrayParameters> statsOpt = Warehouse.getInstance().get(1L);
    assertTrue(statsOpt.isPresent());
    ArrayParameters stats = statsOpt.get();
    assertAll(
        () -> assertEquals(-2, stats.min()),
        () -> assertEquals(10, stats.max()),
        () -> assertEquals(16L, stats.sum()),
        () -> assertEquals(4.0, stats.average(), 0.001));
  }

  @Test
  public void testOnValuesChangedEmptyArrayRemovesStatisticsNegative() {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[0]);
    ArrayObserverImpl observer = new ArrayObserverImpl();
    Warehouse.getInstance().put(1L, new ArrayParameters(1, 1, 1L, 1.0));

    observer.onValuesChanged(entity);

    assertFalse(Warehouse.getInstance().get(1L).isPresent());
  }
}

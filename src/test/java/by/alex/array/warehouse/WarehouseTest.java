package by.alex.array.warehouse;

import static org.junit.jupiter.api.Assertions.*;

import by.alex.array.entity.ArrayParameters;
import by.alex.array.entity.IntArrayEntity;
import by.alex.array.exception.ArrayProcessingException;
import by.alex.array.observer.impl.ArrayObserverImpl;
import by.alex.array.repository.impl.InMemoryArrayRepository;
import java.lang.reflect.Field;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WarehouseTest {

  @BeforeEach
  public void setUp() throws Exception {
    resetSingleton(Warehouse.class);
    resetSingleton(InMemoryArrayRepository.class);
  }

  private void resetSingleton(Class<?> clazz) throws Exception {
    Field instanceField = clazz.getDeclaredField("instance");
    instanceField.setAccessible(true);
    instanceField.set(null, null);
  }

  @Test
  public void testPutAndGetPositive() {
    ArrayParameters expected = new ArrayParameters(1, 10, 15L, 5.0);
    Warehouse.getInstance().put(1L, expected);

    Optional<ArrayParameters> actualOpt = Warehouse.getInstance().get(1L);

    assertTrue(actualOpt.isPresent());
    assertEquals(expected, actualOpt.get());
  }

  @Test
  public void testGetNegative() {
    Optional<ArrayParameters> actualOpt = Warehouse.getInstance().get(999L);
    assertFalse(actualOpt.isPresent());
  }

  @Test
  public void testRemovePositive() {
    ArrayParameters params = new ArrayParameters(1, 10, 15L, 5.0);
    Warehouse.getInstance().put(1L, params);

    Warehouse.getInstance().remove(1L);

    assertFalse(Warehouse.getInstance().get(1L).isPresent());
  }

  @Test
  public void testGetReturnsValueAfterPutPositive() {
    ArrayParameters params = new ArrayParameters(1, 10, 15L, 5.0);
    Warehouse.getInstance().put(1L, params);

    assertTrue(Warehouse.getInstance().get(1L).isPresent());
  }

  @Test
  public void testObserverAutoUpdatePositive() throws ArrayProcessingException {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});
    entity.attach(new ArrayObserverImpl());

    entity.setElement(0, 10);

    ArrayParameters expected = new ArrayParameters(2, 10, 15L, 5.0);
    Optional<ArrayParameters> actualOpt = Warehouse.getInstance().get(1L);

    assertTrue(actualOpt.isPresent());
    assertEquals(expected, actualOpt.get());
  }

  @Test
  public void testObserverEmptyArrayNegative() throws ArrayProcessingException {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});
    entity.attach(new ArrayObserverImpl());

    entity.setValues(new int[0]);

    assertFalse(Warehouse.getInstance().get(1L).isPresent());
  }

  @Test
  public void testDetachObserverPositive() throws ArrayProcessingException {
    IntArrayEntity entity = new IntArrayEntity(1L, new int[] {1, 2, 3});
    entity.attach(new ArrayObserverImpl());
    InMemoryArrayRepository.getInstance().add(entity);

    InMemoryArrayRepository.getInstance().remove(entity);

    entity.setElement(0, 99);

    assertFalse(Warehouse.getInstance().get(1L).isPresent());
  }

  @Test
  public void testGetInstanceReturnsSameInstancePositive() {
    Warehouse first = Warehouse.getInstance();
    Warehouse second = Warehouse.getInstance();

    assertSame(first, second);
  }
}

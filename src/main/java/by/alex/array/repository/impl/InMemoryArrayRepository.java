package by.alex.array.repository.impl;

import by.alex.array.entity.IntArrayEntity;
import by.alex.array.repository.ArrayRepository;
import by.alex.array.warehouse.Warehouse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InMemoryArrayRepository implements ArrayRepository {
  private static final Logger logger = LogManager.getLogger(InMemoryArrayRepository.class);
  private static InMemoryArrayRepository instance;
  private final List<IntArrayEntity> storage = new ArrayList<>();

  private InMemoryArrayRepository() {}

  public static InMemoryArrayRepository getInstance() {
    if (instance == null) {
      instance = new InMemoryArrayRepository();
    }
    return instance;
  }

  @Override
  public boolean add(IntArrayEntity array) {
    if (array != null) {
      boolean result = storage.add(array);
      logger.info("Entity with ID {} added to repository: {}", array.getId(), result);
      return result;
    }
    return false;
  }

  @Override
  public boolean remove(IntArrayEntity array) {
    if (array != null) {
      array.detach();
      Warehouse.getInstance().remove(array.getId());
      boolean result = storage.remove(array);
      logger.info("Entity with ID {} removed from repository: {}", array.getId(), result);
      return result;
    }
    return false;
  }

  @Override
  public List<IntArrayEntity> findBy(Predicate<IntArrayEntity> specification) {
    List<IntArrayEntity> result = new ArrayList<>();
    for (IntArrayEntity entity : storage) {
      if (specification.test(entity)) {
        result.add(entity);
      }
    }
    return result;
  }

  @Override
  public List<IntArrayEntity> findByStream(Predicate<IntArrayEntity> specification) {
    return storage.stream()
        .filter(specification)
        .collect(Collectors.toList());
  }

  @Override
  public List<IntArrayEntity> sort(Comparator<IntArrayEntity> comparator) {
    List<IntArrayEntity> result = new ArrayList<>(storage);
    result.sort(comparator);
    return result;
  }
}
package by.alex.array.repository.impl;

import by.alex.array.entity.IntArrayEntity;
import by.alex.array.repository.ArrayRepository;
import by.alex.array.warehouse.Warehouse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryArrayRepository implements ArrayRepository {
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
      return storage.add(array);
    }
    return false;
  }

  @Override
  public boolean remove(IntArrayEntity array) {
    if (array != null) {
      array.detach();
      Warehouse.getInstance().remove(array.getId());
      return storage.remove(array);
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

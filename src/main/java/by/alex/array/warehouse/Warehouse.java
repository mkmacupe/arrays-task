package by.alex.array.warehouse;

import by.alex.array.entity.ArrayParameters;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Warehouse {
  private static Warehouse instance;
  private final Map<Long, ArrayParameters> storage = new HashMap<>();

  private Warehouse() {}

  public static Warehouse getInstance() {
    if (instance == null) {
      instance = new Warehouse();
    }
    return instance;
  }

  public ArrayParameters put(long id, ArrayParameters parameters) {
    return storage.put(id, parameters);
  }

  public Optional<ArrayParameters> get(long id) {
    return Optional.ofNullable(storage.get(id));
  }

  public ArrayParameters remove(long id) {
    return storage.remove(id);
  }
}

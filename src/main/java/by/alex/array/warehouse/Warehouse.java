package by.alex.array.warehouse;

import by.alex.array.entity.ArrayParameters;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Warehouse {
  private static final Logger logger = LogManager.getLogger(Warehouse.class);
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
    ArrayParameters previous = storage.put(id, parameters);
    logger.info("Warehouse parameters updated for Entity ID {}: {}", id, parameters);
    return previous;
  }

  public Optional<ArrayParameters> get(long id) {
    return Optional.ofNullable(storage.get(id));
  }

  public ArrayParameters remove(long id) {
    ArrayParameters previous = storage.remove(id);
    logger.info("Warehouse parameters removed for Entity ID {}: {}", id, previous);
    return previous;
  }
}
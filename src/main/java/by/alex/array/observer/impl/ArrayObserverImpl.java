package by.alex.array.observer.impl;

import by.alex.array.entity.ArrayParameters;
import by.alex.array.entity.IntArrayEntity;
import by.alex.array.observer.ArrayObserver;
import by.alex.array.service.ArrayCalculationService;
import by.alex.array.service.impl.ArrayCalculationServiceImpl;
import by.alex.array.warehouse.Warehouse;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayObserverImpl implements ArrayObserver {

  private static final Logger logger = LogManager.getLogger(ArrayObserverImpl.class);

  @Override
  public void onValuesChanged(IntArrayEntity array) {
    logger.info("Observer detected values changed for Entity ID: {}. Recalculating statistics...",
        array.getId());
    ArrayCalculationService calculationService = new ArrayCalculationServiceImpl();
    OptionalInt minOpt = calculationService.findMin(array);
    OptionalInt maxOpt = calculationService.findMax(array);
    OptionalLong sumOpt = calculationService.calculateSum(array);
    OptionalDouble avgOpt = calculationService.calculateAverage(array);

    if (minOpt.isPresent() && maxOpt.isPresent() && sumOpt.isPresent() && avgOpt.isPresent()) {
      ArrayParameters params =
          new ArrayParameters(
              minOpt.getAsInt(), maxOpt.getAsInt(), sumOpt.getAsLong(), avgOpt.getAsDouble());
      Warehouse.getInstance()
               .put(array.getId(), params);
    } else {
      Warehouse.getInstance()
               .remove(array.getId());
    }
  }
}
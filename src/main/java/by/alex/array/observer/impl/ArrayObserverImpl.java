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

public class ArrayObserverImpl implements ArrayObserver {

  @Override
  public void onValuesChanged(IntArrayEntity array) {
    ArrayCalculationService calculationService = new ArrayCalculationServiceImpl();
    OptionalInt minOpt = calculationService.findMin(array);
    OptionalInt maxOpt = calculationService.findMax(array);
    OptionalLong sumOpt = calculationService.calculateSum(array);
    OptionalDouble avgOpt = calculationService.calculateAverage(array);

    if (minOpt.isPresent() && maxOpt.isPresent() && sumOpt.isPresent() && avgOpt.isPresent()) {
      ArrayParameters params =
          new ArrayParameters(
              minOpt.getAsInt(), maxOpt.getAsInt(), sumOpt.getAsLong(), avgOpt.getAsDouble());
      Warehouse.getInstance().put(array.getId(), params);
    } else {
      Warehouse.getInstance().remove(array.getId());
    }
  }
}

package by.alex.array.entity;

import by.alex.array.exception.ArrayProcessingException;
import by.alex.array.observer.ArrayObserver;
import java.util.Arrays;
import java.util.StringJoiner;

public class IntArrayEntity {
  private final long id;
  private int[] values;

  private ArrayObserver observer;

  public IntArrayEntity(long id, int[] values) {
    this.id = id;
    this.values = values == null ? new int[0] : Arrays.copyOf(values, values.length);
  }

  public long getId() {
    return id;
  }

  public int[] getValues() {
    return Arrays.copyOf(values, values.length);
  }

  public void setValues(int[] values) {
    this.values = values == null ? new int[0] : Arrays.copyOf(values, values.length);
    notifyObserver();
  }

  public void setElement(int index, int value) throws ArrayProcessingException {
    if (index >= 0 && index < values.length) {
      this.values[index] = value;
      notifyObserver();
    } else {
      throw new ArrayProcessingException("Index out of bounds: " + index);
    }
  }

  public void attach(ArrayObserver observer) {
    this.observer = observer;
  }

  public void detach() {
    this.observer = null;
  }

  private void notifyObserver() {
    if (observer != null) {
      observer.onValuesChanged(this);
    }
  }

  @Override
  public boolean equals(Object otherObject) {
    if (this == otherObject) {
      return true;
    }
    if (otherObject == null || getClass() != otherObject.getClass()) {
      return false;
    }
    IntArrayEntity that = (IntArrayEntity) otherObject;
    return id == that.id && Arrays.equals(values, that.values);
  }

  @Override
  public int hashCode() {
    int result = Long.hashCode(id);
    result = 31 * result + Arrays.hashCode(values);
    return result;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", IntArrayEntity.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("values=" + Arrays.toString(values))
        .toString();
  }
}

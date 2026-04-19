/*
 * DOUBLEGSOFT.COM CONFIDENTIAL
 *
 * [2016] - [?] doublegsoft.com
 *
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of doublegsoft.com and its suppliers, if any.
 * The intellectual and technical concepts contained herein
 * are proprietary to doublegsoft.com and its suppliers  and
 * may be covered by China and Foreign Patents, patents in
 * process, and are protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from doublegsoft.com.
 */
package com.doublegsoft.jcommons.metaui.layout;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author <a href="mailto:guo.guo.gan@gmail.com">Christian Gann</a>
 *
 * @since 1.0
 */
public class Row<T> {

  private final List<Cell<T>> cells = new ArrayList<>();

  public void addCell(Cell<T> cell) {
    cells.add(cell);
  }

  public void addCell(Cell<T> cell, int index) {
    if (cells.size() > index) {
      return;
    }
    if (cells.size() <= index) {
      for (int i = cells.size(); i < index; i++) {
        Cell<T> newCell = new Cell<>();
        cells.add(newCell);
      }
    }
    cells.add(cell);
  }

  public void addValue(T value) {
    Cell<T> cell = new Cell<>();
    cell.setValue(value);
    cells.add(cell);
  }

  public List<Cell<T>> getCells() {
    return cells;
  }

  public Cell<T> getCell(int index) {
    if (cells.size() == 0) {
      return null;
    }
    if (cells.size() - 1 >= index) {
      return cells.get(index);
    }
    return null;
  }

  public boolean isEmpty() {
    return cells.isEmpty();
  }
  
  void addValue(T value, int cellIndex) {
    if (cells.size() <= cellIndex) {
      addValue(value);
    } else {
      Cell<T> cell = cells.get(cellIndex);
      cell.addValues(value);
    }
  }
}

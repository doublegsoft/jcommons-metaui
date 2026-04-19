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

import com.doublegsoft.jcommons.metaui.WidgetDefinition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The object is to describe position system.
 *
 * @author <a href="mailto:guo.guo.gan@gmail.com">Christian Gann</a>
 *
 * @since 1.0
 */
public class Grid<T> {

  protected final List<Row<T>> rows = new ArrayList<>();
  
  protected final List<Cell<T>> cols = new ArrayList<>();
  
  /**
   * Simply layouts the given objs as grid layout. 
   * <p>
   * Not scientific but engineering.
   * 
   * @param <T>
   *      the generic type
   * 
   * @param objs
   *      the layouting objects
   * 
   * @return the {@link Grid} instance 
   */
  public static <T> Grid<T> layout(List<T> objs) {
    Grid<T> retVal = new Grid<>();
    
    List<T> sorted = sort(objs);
    
    // the existing rows in the grid
    Map<Integer, Row<T>> existingRows = new HashMap<>();
    // the added objects
    Set<T> added = new HashSet<>();
    for (T obj : sorted) {
      Position pos = getPosition(obj);
      int rowIndex = pos.getRowIndex();
      int cellIndex = pos.getCellIndex();
      int rowSpan = pos.getRowSpan();
      int cellSpan = pos.getCellSpan();
      if (rowIndex == Position.INVALID_INDEX) {
        Row<T> row = new Row<>();
        row.addValue(obj);
        retVal.addRow(row);
        continue;
      }
      Row<T> row = null;
      if (existingRows.containsKey(rowIndex)) {
        row = existingRows.get(rowIndex);
      } else {
        row = new Row<>();
        existingRows.put(rowIndex, row);
        // 占据多行的处理方式
        if (rowSpan > 1) {
          for (int i = 1; i < rowSpan; i++) {
            existingRows.put(rowIndex + i, row);
          }
        }
        retVal.addRow(row);
      }
      for (T innerObj : sorted) {
        Position innerPos = getPosition(innerObj);
        int innerRowIndex = innerPos.rowIndex;
        int innerCellIndex = innerPos.cellIndex;
        if (innerRowIndex >= rowIndex && (innerRowIndex <= rowIndex + rowSpan - 1) && !added.contains(innerObj)) {
          Cell<T> cell = row.getCell(innerCellIndex - 1);
          if (cell == null) {
            cell = new Cell<>();
            cell.setValue(innerObj);
            row.addCell(cell, innerCellIndex - 1);
          } else {
            if (cell.getValue() != null) {
              cell.addValue(cell.value);
              cell.setValue(null);
            }
            cell.addValue(innerObj);
          }
          added.add(innerObj);
          break;
        }
      }
    }
    
    return retVal;
  }
  
  public void addRow(Row<T> row) {
    rows.add(row);
  }

  public void addValue(T value) {
    Row<T> row = new Row<>();
    row.addValue(value);
    rows.add(row);
  }

  public Row<T> getLastRow() {
    if (rows.isEmpty()) {
      return null;
    }
    return rows.get(rows.size() - 1);
  }

  public Row<T> getRow(int index) {
    return rows.get(index);
  }

  public List<Row<T>> getRows() {
    return rows;
  }
  
  private static <T> List<T> sort(List<T> objs) {
    List<T> retVal = new ArrayList<>();
    
    for (T obj : objs) {
      Position pos = getPosition(obj);
      boolean inserted = false;
      for (int i = 0; i < retVal.size(); i++) {
        Position innerPos = getPosition(retVal.get(i));
        int cmp = pos.compareTo(innerPos);
        if (cmp == -1) {
          retVal.add(i, obj);
          inserted = true;
          break;
        }
      }
      if (!inserted) {
        retVal.add(obj);
      }
    }
    
    return retVal;
  }
  
  private static <T> Position getPosition(T obj) {
    if (obj.getClass() == WidgetDefinition.class) {
      return ((WidgetDefinition) obj).getPosition();
    } else if (Map.class.isAssignableFrom(obj.getClass())) {
      Map<String, Object> map = (Map<String, Object>) obj;
      Position pos = (Position) map.get("pos");
      if (pos == null) {
        pos = (Position) map.get("position");
      }
      return pos;
    }
    throw new IllegalArgumentException("unsupport the given obj type: " + obj.getClass());
  }
 
}

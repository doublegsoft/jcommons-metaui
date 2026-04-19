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

import java.util.Objects;

/**
 * The object is to describe position system.
 *
 * @author <a href="mailto:guo.guo.gan@gmail.com">Christian Gann</a>
 *
 * @since 1.0
 */
public class Position implements Comparable<Position> {

  public static final int INVALID_INDEX = -1;

  public static final Position DEFAULT = new Position() {

    @Override
    public int getRowIndex() {
      return INVALID_INDEX;
    }

    @Override
    public int getCellIndex() {
      return INVALID_INDEX;
    }

    @Override
    public Size getSize() {
      return Size.DEFAULT;
    }

  };

  protected int rowIndex = INVALID_INDEX;

  protected int cellIndex = INVALID_INDEX;

  protected int rowSpan = 1;

  protected int cellSpan = 1;

  protected Size size = Size.DEFAULT;

  public static Position at(String pos) {
    if (pos == null) {
      return Position.DEFAULT;
    }
    Position retVal = new Position();
    String[] parts = pos.substring(1, pos.length() - 1).split(",");
    switch (parts.length) {
      case 2:
        retVal.size = Size.of("(" + parts[0] + "," + parts[1] + ")");
        break;
      case 4:
        if (!parts[0].contains("*")) {
          retVal.rowIndex = Integer.valueOf(parts[0].trim());
        } else {
          retVal.rowIndex = Integer.valueOf(parts[0].split("\\*")[0].trim());
          retVal.rowSpan = Integer.valueOf(parts[0].split("\\*")[1].trim());
        }
        if (!parts[1].contains("*")) {
          retVal.cellIndex = Integer.valueOf(parts[1].trim());
        } else {
          retVal.cellIndex = Integer.valueOf(parts[1].split("\\*")[0].trim());
          retVal.cellSpan = Integer.valueOf(parts[1].split("\\*")[1].trim());
        }

        retVal.size = Size.of("(" + parts[2] + "," + parts[3] + ")");
        break;
      default:
        break;
    }
    return retVal;
  }

  public int getRowIndex() {
    return rowIndex;
  }

  public int getCellIndex() {
    return cellIndex;
  }

  public int getRowSpan() {
    return rowSpan;
  }

  public int getCellSpan() {
    return cellSpan;
  }

  public Size getSize() {
    return size;
  }

  @Override
  public int compareTo(Position o) {
    if (rowIndex < o.rowIndex) {
      return -1;
    } else if (rowIndex > o.rowIndex) {
      return 1;
    }
    if (rowSpan != 1 && o.rowSpan == 1) {
      return -1;
    } else if (rowSpan == 1 && o.rowSpan != 1) {
      return 1;
    }
    if (cellIndex < o.cellIndex) {
      return -1;
    } else {
      return 1;
    }
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 41 * hash + this.rowIndex;
    hash = 41 * hash + this.cellIndex;
    hash = 41 * hash + this.rowSpan;
    hash = 41 * hash + this.cellSpan;
    hash = 41 * hash + Objects.hashCode(this.size);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Position other = (Position) obj;
    if (this.rowIndex != other.rowIndex) {
      return false;
    }
    if (this.cellIndex != other.cellIndex) {
      return false;
    }
    if (this.rowSpan != other.rowSpan) {
      return false;
    }
    if (this.cellSpan != other.cellSpan) {
      return false;
    }
    if (!Objects.equals(this.size, other.size)) {
      return false;
    }
    return true;
  }

  protected Position() {

  }
}

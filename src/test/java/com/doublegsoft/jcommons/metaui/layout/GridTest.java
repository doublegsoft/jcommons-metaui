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
import java.util.List;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit tests for {@link Grid}.
 * 
 * @author <a href="mailto:guo.guo.gan@gmail.com">Christian Gann</a>
 * 
 * @since 1.0
 */
public class GridTest {
  
  @Ignore
  public void test_01() {
    Grid<WidgetDefinition> grid = Grid.layout(createWidgets(new String[]{"(1, 1, 60%, 0)", "(2, 1, 60%, 0)", "(1, 2, 40%, 0)", "(2, 2, 40%, 0)"}));

    Assert.assertEquals(2, grid.getRows().size());
    Assert.assertEquals(2, grid.getRows().get(0).getCells().size());
    Assert.assertEquals(2, grid.getRows().get(1).getCells().size());

    for (Row<WidgetDefinition> row : grid.getRows()) {
      for (Cell<WidgetDefinition> cell : row.getCells()) {
        System.out.print(cell.getValue().getId());
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  @Ignore
  public void test_02() {
    Grid<WidgetDefinition> grid = Grid.layout(createWidgets(new String[]{"(1, 1, 60%, 0)", "(2, 1, 60%, 0)", "(1, 2, 20%, 0)", "(2, 2, 40%, 0)", "(1, 3, 20%, 0)"}));

    Assert.assertEquals(2, grid.getRows().size());
    Assert.assertEquals(3, grid.getRows().get(0).getCells().size());
    Assert.assertEquals(2, grid.getRows().get(1).getCells().size());

    for (Row<WidgetDefinition> row : grid.getRows()) {
      for (Cell<WidgetDefinition> cell : row.getCells()) {
        System.out.print(cell.getValue().getId());
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  @Ignore
  public void test_03() {
    Grid<WidgetDefinition> grid = Grid.layout(createWidgets(new String[]{"(1, 1, 60%, 0)", "(1, 2, 20%, 0)", "(1, 3, 20%, 0)"}));

    Assert.assertEquals(1, grid.getRows().size());
    Assert.assertEquals(3, grid.getRows().get(0).getCells().size());

    for (Row<WidgetDefinition> row : grid.getRows()) {
      for (Cell<WidgetDefinition> cell : row.getCells()) {
        System.out.print(cell.getValue().getId());
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  @Ignore
  public void test_04() {
    Grid<WidgetDefinition> grid = Grid.layout(createWidgets(new String[]{"(1, 1, 50%, 0)", "(1, 2, 50%, 0)", "(2, 1, 100%, 0)"}));

    Assert.assertEquals(2, grid.getRows().size());
    Assert.assertEquals(2, grid.getRows().get(0).getCells().size());

    for (Row<WidgetDefinition> row : grid.getRows()) {
      for (Cell<WidgetDefinition> cell : row.getCells()) {
        System.out.print(cell.getValue().getId());
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  @Ignore
  public void test_05() {
    System.out.println("|--------|------------|");
    System.out.println("|        |            |");
    System.out.println("|        |------------|");
    System.out.println("|        |            |");
    System.out.println("|--------|------------|");
    Grid<WidgetDefinition> grid = Grid.layout(createWidgets(new String[]{"(1*2, 1, 50%, 0)", "(1, 2, 50%, 0)", "(2, 2, 50%, 0)"}));

    Assert.assertEquals(1, grid.getRows().size());
    Assert.assertEquals("w1", grid.getRows().get(0).getCells().get(0).value.getId());
    Assert.assertEquals(2, grid.getRows().get(0).getCells().get(1).getRows().size());

    for (Row<WidgetDefinition> row : grid.getRows()) {
      for (Cell<WidgetDefinition> cell : row.getCells()) {
        if (cell.getValue() != null) {
          System.out.print(cell.getValue().getId());
        } else {
          for (Object rowObj : cell.getRows()) {
            Row<WidgetDefinition> innerRow = (Row<WidgetDefinition>)rowObj;
            for (Cell<WidgetDefinition> innerCell : innerRow.getCells()) {
              System.out.print(innerCell.getValue().getId());
            }
          }
        }
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  @Ignore
  public void test_06() {
    System.out.println("|------------|-------|");
    System.out.println("|            |       |");
    System.out.println("|------------|       |");
    System.out.println("|            |       |");
    System.out.println("|------------|-------|");
    Grid<WidgetDefinition> grid = Grid.layout(createWidgets(new String[]{"(1*2, 2, 50%, 0)", "(1, 1, 50%, 0)", "(2, 1, 50%, 0)"}));

    Assert.assertEquals(1, grid.getRows().size());
    Assert.assertEquals("w1", grid.getRows().get(0).getCells().get(1).value.getId());
    Assert.assertEquals(2, grid.getRows().get(0).getCells().get(0).getRows().size());
//    Assert.assertEquals(1, grid.getRows().get(0).getCells().get(1).getRows().size());

    for (Row<WidgetDefinition> row : grid.getRows()) {
      for (Cell<WidgetDefinition> cell : row.getCells()) {
        if (cell.getValue() != null) {
          System.out.print(cell.getValue().getId());
        } else {
          for (Object rowObj : cell.getRows()) {
            Row<WidgetDefinition> innerRow = (Row<WidgetDefinition>)rowObj;
            for (Cell<WidgetDefinition> innerCell : innerRow.getCells()) {
              System.out.print(innerCell.getValue().getId());
            }
          }
        }
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  @Ignore
  public void test_07() {
    System.out.println("|--------|------------|-------|");
    System.out.println("|        |            |       |");
    System.out.println("|        |------------|       |");
    System.out.println("|        |            |       |");
    System.out.println("|--------|------------|-------|");
    Grid<WidgetDefinition> grid = Grid.layout(createWidgets(new String[]{"(1*2, 1, 50%, 0)", "(1, 2, 50%, 0)", "(2, 2, 50%, 0)", "(1*2, 3, 50%, 0)"}));

    Assert.assertEquals(1, grid.getRows().size());
    Assert.assertEquals("w1", grid.getRows().get(0).getCells().get(0).value.getId());
    Assert.assertEquals(2, grid.getRows().get(0).getCells().get(1).getRows().size());
    Assert.assertEquals("w4", grid.getRows().get(0).getCells().get(2).value.getId());

    for (Row<WidgetDefinition> row : grid.getRows()) {
      for (Cell<WidgetDefinition> cell : row.getCells()) {
        if (cell.getValue() != null) {
          System.out.print(cell.getValue().getId());
        } else {
          for (Object rowObj : cell.getRows()) {
            Row<WidgetDefinition> innerRow = (Row<WidgetDefinition>)rowObj;
            for (Cell<WidgetDefinition> innerCell : innerRow.getCells()) {
              System.out.print(innerCell.getValue().getId());
            }
          }
        }
        System.out.print(" ");
      }
      System.out.println();
    }
  }
  
  private List<WidgetDefinition> createWidgets(String[] positions) {
    List<WidgetDefinition> retVal = new ArrayList<>();
    for (int i = 1; i <= positions.length; i++) {
      WidgetDefinition w = new WidgetDefinition();
      w.setId("w" + i);
      w.setPosition(Position.at(positions[i - 1]));
      retVal.add(w);
    }
    return retVal;
  }
}

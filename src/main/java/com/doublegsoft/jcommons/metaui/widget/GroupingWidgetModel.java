/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doublegsoft.jcommons.metaui.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author gg
 */
public class GroupingWidgetModel extends PrimitiveWidgetModel {
  
  protected final List<PrimitiveWidgetModel> primitiveWidgetModels = new ArrayList<>();
  
  public void addPimitiveWidgetModel(PrimitiveWidgetModel primitiveWidgetModel) {
    primitiveWidgetModels.add(primitiveWidgetModel);
  }

  public List<PrimitiveWidgetModel> getPrimitiveWidgetModels() {
    return Collections.unmodifiableList(primitiveWidgetModels);
  }
  
}

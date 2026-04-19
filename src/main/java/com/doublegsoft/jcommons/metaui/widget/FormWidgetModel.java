/*
 * DOUBLEGSOFT.COM CONFIDENTIAL
 *
 * 2019 doublegsoft.com
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
package com.doublegsoft.jcommons.metaui.widget;

import com.doublegsoft.jcommons.metaui.WidgetModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author <a href="mailto:guo.guo.gan@gmail.com">Christian Gann</a>
 * 
 * @since 5.0
 * 
 * @version 5.0 - initial creation on Oct 18, 2019
 */
public class FormWidgetModel extends WidgetModel {
  
  /**
   * the operations in form.
   * <p>
   * example:
   * <pre>
   * {@code
   *   #(&employee)![save|��ְ(&employee), update|��ְ(&employee#status=F)]
   * }
   * </pre>
   */
//  protected final static Action[] SUPPORTING_ACTIONS = {Action.CREATE, Action.UPDATE, Action.LOAD, Action.DELETE};
  
  protected final List<WidgetModel> widgetModels = new ArrayList<>();
  
  public void addWidgetModel(WidgetModel widgetModel) {
    widgetModels.add(widgetModel);
  }
  
  public List<WidgetModel> getWidgetModels() {
    return Collections.unmodifiableList(widgetModels);
  }
}

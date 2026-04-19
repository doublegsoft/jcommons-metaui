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
package com.doublegsoft.jcommons.metaui;

/**
 * {@link WidgetModel} is a model to express {@link WidgetDefinition} instance. 
 * <p>
 * And generally a widget model is instantiated by parsing guidbase widget 
 * model language by guidbase framework.
 * 
 * @author <a href="mailto:guo.guo.gan@gmail.com">Christian Gann</a>
 * 
 * @since 5.0
 * 
 * @version 5.0 - initial creation on Oct 18, 2019
 */
public class WidgetModel {
  
  /**
   * the model expression.
   */
  protected String model;

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }
  
}

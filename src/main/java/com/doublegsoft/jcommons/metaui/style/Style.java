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
package com.doublegsoft.jcommons.metaui.style;

import com.doublegsoft.jcommons.lang.HashObject;

/**
 * The {@link Style} object is to describe style system.
 *
 * @author <a href="mailto:guo.guo.gan@gmail.com">Christian Gann</a>
 *
 * @since 3.0
 */
public class Style {
  
  public static final String FLOAT = "float";
  
  public static final String ALIGN = "align";
  
  public static final String FONT_SIZE = "font-size";
  
  public static final String FONT_STYLE = "font-style";
  
  private final HashObject styles = new HashObject();
  
  /**
   * Parses the style expression and gets a {@link Style} instance.
   * 
   * @param styleExpression 
   *      the style expression, like html css expression
   * 
   * @return a {@link Style} instance
   */
  public static Style parse(String styleExpression) {
    Style retVal = new Style();
    if (styleExpression == null) {
      return retVal;
    }
    String[] strs = styleExpression.split(";");
    for (String str : strs) {
      String[] kv = str.trim().split(":");
      if (kv.length != 2) {
        continue;
      } 
      retVal.addStyle(kv[0], kv[1]);
    }
    return retVal;
  }
  
  public void addStyle(String name, String value) {
    styles.add(name, value);
  }
  
  public String getStyle(String name) {
    return styles.get(name);
  }
}

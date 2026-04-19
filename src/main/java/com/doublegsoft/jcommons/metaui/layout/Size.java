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

import com.doublegsoft.jcommons.lang.StringPair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The object is to describe element size.
 *
 * @author <a href="mailto:guo.guo.gan@gmail.com">Christian Gann</a>
 *
 * @since 1.0
 */
public class Size {

  public static final int MATCH_PARENT = -1;

  public static final int WRAP_CONTENT = 0;

  public static final Size DEFAULT = new Size() {

    @Override
    public int getHeight() {
      return WRAP_CONTENT;
    }

    @Override
    public int getWidth() {
      return WRAP_CONTENT;
    }

  };

  public static final Size PARENT = new Size() {

    @Override
    public int getHeight() {
      return MATCH_PARENT;
    }

    @Override
    public int getWidth() {
      return MATCH_PARENT;
    }

  };

  /**
   * x/100, the percentage value in a row.
   */
  private int width = WRAP_CONTENT;

  private String widthUnit;
  /**
   * the real height in gui, the unit is px.
   */
  private int height = WRAP_CONTENT;

  private String heightUnit;

  public static Size of(String size) {
    if (size == null) {
      return Size.DEFAULT;
    }
    Size retVal = new Size();
    List<String> parts = new ArrayList<>();// new Typebase().tupletype(size);
    StringPair widthPair = pair(parts.get(0));
    StringPair heightPair = pair(parts.get(1));

    retVal.width = Integer.valueOf(widthPair.getKey());
    retVal.widthUnit = widthPair.getValue();
    retVal.height = Integer.valueOf(heightPair.getKey());
    retVal.heightUnit = heightPair.getValue();

    return retVal;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public String getWidthUnit() {
    return widthUnit;
  }

  public String getHeightUnit() {
    return heightUnit;
  }

  public String getWidthWithUnit() {
    if (widthUnit != null) {
      return width + widthUnit;
    }
    return String.valueOf(width);
  }

  public String getHeightWithUnit() {
    if (heightUnit != null) {
      return height + heightUnit;
    }
    return String.valueOf(height);
  }

  private static int safeInt(String str) {
    try {
      return Integer.valueOf(str.trim());
    } catch (Exception ex) {
      return WRAP_CONTENT;
    }
  }

  private static StringPair pair(String str) {
    StringPair retVal = new StringPair();
    Pattern pat = Pattern.compile("\\d+");
    Matcher matcher = pat.matcher(str);
    matcher.find();
    retVal.setKey(matcher.group());
    retVal.setValue(str.replaceAll("\\d+", ""));
    return retVal;
  }

  @Override
  public int hashCode() {
    int hash = 5;
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
    final Size other = (Size) obj;
    if (this.width != other.width) {
      return false;
    }
    if (this.height != other.height) {
      return false;
    }
    if (!Objects.equals(this.widthUnit, other.widthUnit)) {
      return false;
    }
    if (!Objects.equals(this.heightUnit, other.heightUnit)) {
      return false;
    }
    return true;
  }

  Size() {

  }
}

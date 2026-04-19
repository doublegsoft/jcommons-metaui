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
package com.doublegsoft.jcommons.metaui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * {@link Options} type encapsulates widget options.
 *
 * @author <a href="mailto:guo.guo.gan@gmail.com">Christian Gann</a>
 *
 * @since 1.0
 */
public class Options extends TreeMap<String, Object> {

  public static final String TITLE = "title";

  public Options() {
    super(String.CASE_INSENSITIVE_ORDER);
  }

  public Options(Map<String, Object> other) {
    super(String.CASE_INSENSITIVE_ORDER);
    super.putAll(other);
  }

  public <T> T get(String key) {
    return (T) super.get(key);
  }

  public Map<String, Options> getWidgetOptions() {
    Map<String, Options> retVal = new HashMap<>();
    for (Entry<String, Object> e : entrySet()) {
      if (e.getKey().indexOf("$") == 0) {
        retVal.put(e.getKey().substring(1), new Options((Map<String, Object>) e.getValue()));
      }
    }
    return retVal;
  }
}

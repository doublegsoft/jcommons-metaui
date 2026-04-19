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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link PagesDefinition} type encapsulates all page data.
 *
 * @author <a href="mailto:guo.guo.gan@gmail.com">Christian Gann</a>
 *
 * @since 1.0
 */
public class PagesDefinition {

  private final Map<PageDefinition, PageDefinition> pages = new HashMap<>();

  public List<PageDefinition> list() {
    List<PageDefinition> retVal = new ArrayList<>();
    retVal.addAll(pages.values());
    return retVal;
  }

  public void add(PageDefinition page) {
    pages.put(page, page);
  }

  public PageDefinition find(String module, String name) {
    return find(module + "/" + name);
  }

  public PageDefinition find(String moduleAndName) {
    PageDefinition key = new PageDefinition(moduleAndName);
    return pages.get(key);
  }
}

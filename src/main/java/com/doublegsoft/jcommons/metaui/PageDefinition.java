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

import com.doublegsoft.jcommons.metaui.layout.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * {@link PageDefinition} type encapsulates page data.
 *
 * @author <a href="mailto:guo.guo.gan@gmail.com">Christian Gann</a>
 *
 * @since 1.0
 */
public class PageDefinition extends WidgetDefinition {

  private String module;

  private String name;

  private String title;

  public PageDefinition(String moduleAndName) {
    String[] strs = moduleAndName.split("/");
    if (!moduleAndName.contains("/")) {
      setName(moduleAndName);
    } else {
      int idx = moduleAndName.lastIndexOf("/");
      setName(moduleAndName.substring(idx + 1));
      setModule(moduleAndName.substring(0, idx));
    }
    page = this;
    size = Size.PARENT;
  }

  public List<WidgetDefinition> getPageWidgets() {
    return getWidgets(this);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getModule() {
    return module;
  }

  public final void setModule(String module) {
    this.module = module;
  }

  public String getName() {
    return name;
  }

  public final void setName(String name) {
    this.name = name;
  }

  @Override
  public List<WidgetDefinition> getWidgets() {
    return getWidgets(this);
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + Objects.hashCode(this.module);
    hash = 97 * hash + Objects.hashCode(this.name);
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
    final PageDefinition other = (PageDefinition) obj;
    if (!Objects.equals(this.module, other.module)) {
      return false;
    }
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    return true;
  }

  private List<WidgetDefinition> getWidgets(WidgetDefinition widget) {
    List<WidgetDefinition> retVal = new ArrayList<>();
    widget.widgets.forEach(child -> {
      retVal.add(child);
      retVal.addAll(getWidgets(child));
    });
    return retVal;
  }
}

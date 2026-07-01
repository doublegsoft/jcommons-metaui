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
import com.doublegsoft.jcommons.utils.Strings;

import java.util.*;

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
    return getWidgetsInternally(this);
  }

  @Override
  public List<WidgetDefinition> getWidgets() {
    return getWidgetsInternally(this);
  }

  public List<WidgetDefinition> getContainers() {
    return getWidgetsInternally(this, 1);
  }

  public Set<String> getParams() {
    Set<String> retVal = new HashSet<>();
    for (WidgetDefinition widget : getWidgets()) {
      if (!Strings.isEmpty(widget.value("param"))) {
        retVal.add(widget.value("param"));
      }
    }
    return retVal;
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

  public WidgetDefinition byId(String id) {
    for (WidgetDefinition child : getWidgets()) {
      if (id.equals(child.getId())) {
        return child;
      }
    }
    return null;
  }

  public WidgetDefinition byVar(String variable) {
    for (WidgetDefinition child : getWidgets()) {
      String var = child.value("variable");
      if (variable.equals(var)) {
        return child;
      }
    }
    return null;
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

}

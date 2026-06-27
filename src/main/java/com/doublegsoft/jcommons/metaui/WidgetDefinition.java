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

import com.doublegsoft.jcommons.metaui.layout.Position;
import com.doublegsoft.jcommons.metaui.layout.Size;
import com.doublegsoft.jcommons.utils.Safe;
import com.doublegsoft.jcommons.utils.Strings;

import java.util.*;

/**
 * It is to describe widget data.
 *
 * @author <a href="mailto:guo.guo.gan@gmail.com">Christian Gann</a>
 *
 * @since 1.0
 */
public class WidgetDefinition implements Comparable<WidgetDefinition> {

  protected String id;

  protected String type;
  
  /**
   * the process logic for this widget.
   * 
   * @since 3.1
   */
  protected String process;

  protected Position position;

  protected PageDefinition page;

  /**
   * Checks this widget is still as a tiled widget.
   *
   * And added on Feb 12, 2024.
   *
   * @since 5.0
   */
  protected boolean tiled;

  /**
   * the parent container.
   */
  protected WidgetDefinition container;

  protected Size size = Size.DEFAULT;

  protected final Options options = new Options();

  protected final List<WidgetDefinition> widgets = new ArrayList<>();

  public static WidgetDefinition from(String id, Options options) {
    WidgetDefinition retVal = new WidgetDefinition();
    retVal.id = id;
    retVal.options.putAll(options);
    retVal.position = Position.at(options.get("position"));
    if (retVal.size != null) {
      retVal.size = Size.of(options.get("size"));
    }
    Map<String, Options> childOptions = options.getWidgetOptions();
    childOptions.entrySet().stream().forEach((e) -> {
      retVal.addWidget(from(e.getKey(), e.getValue()));
    });
    return retVal;
  }

  public void addWidget(WidgetDefinition widget) {
    if (page != null) {
      widget.page = page;
    }
    widget.container = this;
    widgets.add(widget);
  }

  public void addOption(String name, Object value) {
    options.put(name, value);
  }

  public <T> T getOption(String name) {
    return options.get(name);
  }

  public <T> T getOption(String name, T dflt) {
    T retVal = options.get(name);
    if (retVal == null) {
      return dflt;
    }
    return retVal;
  }

  public Options getOptions() {
    return new Options(options);
  }

  public boolean isTiled() {
    return tiled;
  }

  public void setTiled(boolean tiled) {
    this.tiled = tiled;
  }

  /**
   * Gets the direct widgets of the page, and sorted by position.
   *
   * @return the widgets
   */
  public List<WidgetDefinition> getWidgets() {
    return getWidgetsInternally(this);
  }

  public List<WidgetDefinition> getChildren() {
    List<WidgetDefinition> retVal = new ArrayList<>();
    retVal.addAll(widgets);
    return retVal;
  }

  public boolean contains(String type) {
    for (WidgetDefinition child : getWidgets()) {
      if (type.equals(child.getType())) {
        return true;
      }
    }
    return false;
  }

  public List<WidgetDefinition> byType(String type) {
    List<WidgetDefinition> retVal = new ArrayList<>();
    for (WidgetDefinition child : getWidgets()) {
      if (type.equals(child.getType())) {
        retVal.add(child);
      }
    }
    return retVal;
  }

  public List<String> groups() {
    List<String> retVal = new ArrayList<>(); // 因为是有顺序的
    Set<String> existing = new HashSet<>();
    for (WidgetDefinition child : widgets) {
      String groupName = child.value("group");
      if (groupName == null) {
        groupName = "";
      }
      if (!existing.contains(groupName)) {
        retVal.add(groupName);
        existing.add(groupName);
      }
    }
    return retVal;
  }

  public List<WidgetDefinition> group(String name) {
    name = name == null ? "" : name;
    List<WidgetDefinition> retVal = new ArrayList<>();
    for (WidgetDefinition child : widgets) {
      String groupName = child.value("group");
      groupName = groupName == null ? "" : groupName;
      if (groupName.equals(name)) {
        retVal.add(child);
      }
    }
    return retVal;
  }

  public List<List<WidgetDefinition>> rows(String name, int cols) {
    name = name == null ? "" : name;
    List<WidgetDefinition> widgets = group(name);
    List<List<WidgetDefinition>> retVal = new ArrayList<>();
    List<WidgetDefinition> row = new ArrayList<>();
    int spanInRow = 0;
    for (WidgetDefinition widget : widgets) {
      if ("button".equals(widget.getType())) {
        continue;
      }
      Integer span = Safe.safeInteger((String) widget.value("span"));
      span = span == null ? 1 : span;
      spanInRow += span;
      if (spanInRow <= cols) {
        row.add(widget);
      } else {
        retVal.add(row);
        row = new ArrayList<>();
        row.add(widget);
        spanInRow = span;
      }
    }
    if (!row.isEmpty()) {
      retVal.add(row);
    }
    return retVal;
  }

  public WidgetDefinition ancestor(String type) {
    return ancestor(this, type);
  }

  public boolean has(String type) {
    for (WidgetDefinition child : getChildren()) {
      if (child.type.equals(type)) {
        return true;
      }
    }
    return false;
  }

  public String getId() {
    return id;
  }

  public Position getPosition() {
    return position;
  }

  public Size getSize() {
    return size;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public void setSize(Size size) {
    this.size = size;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public PageDefinition getPage() {
    return page;
  }

  public void setPage(PageDefinition page) {
    this.page = page;
  }

  public WidgetDefinition getContainer() {
    return container;
  }

  public String getProcess() {
    return process;
  }

  public void setProcess(String process) {
    this.process = process;
  }

  @Deprecated
  public <T> T getValue(String attr) {
    return options.get(attr);
  }

  @Deprecated
  public void setValue(String attr, Object value) {
    options.put(attr, value);
  }

  public String value(String attr) {
    return value(attr, null);
  }

  public String value(String attr, String defaultValue) {
    String retVal = options.get(attr);
    if (retVal == null) {
      if (defaultValue != null) {
        return defaultValue;
      }
      return "";
    }
    return retVal;
  }

  public WidgetDefinition byRef() {
    String ref = value("ref");
    if (ref == null) {
      return null;
    }
    for (WidgetDefinition child : page.getWidgets()) {
      if (ref.equals(child.getId())) {
        return child;
      }
    }
    return null;
  }

  public boolean isReadonly() {
    return "true".equals(options.get("readonly"));
  }

  public String getTitle() {
    String title = options.get("title");
    if (title == null) {
      return "";
    }
    return title;
  }

  public List<WidgetDefinition> getInputs() {
    return getInputsInternally(this);
  }

  public List<WidgetDefinition> getButtons() {
    return getButtonsInternally(this);
  }

  @Override
  public int compareTo(WidgetDefinition o) {
    if (position == null) {
      return -1;
    }
    if (o.position == null) {
      return 1;
    }
    if (position.getClass() != o.position.getClass()) {
      return 0;
    }
    return position.compareTo(o.position);
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 17 * hash + Objects.hashCode(this.id);
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
    final WidgetDefinition other = (WidgetDefinition) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    return true;
  }

  protected List<WidgetDefinition> getWidgetsInternally(WidgetDefinition widget) {
    List<WidgetDefinition> retVal = new ArrayList<>();
    widget.widgets.forEach(child -> {
      retVal.add(child);
      retVal.addAll(getWidgetsInternally(child));
    });
    return retVal;
  }

  private List<WidgetDefinition> getInputsInternally(WidgetDefinition parent) {
    List<WidgetDefinition> retVal = new ArrayList<>();
    for (WidgetDefinition child : parent.widgets) {
      if (Strings.in(child.type,
          "date", "time", "text", "longtext", "number",
          "check", "radio", "conditionaltext", "tags",
          "select", "cascade", "district", "time", "multiselect",
          "files", "images", "videos", "avatar", "hidden")) {
        if (child.ancestor("criteria_form") != null ||
            child.ancestor("entry_form") != null ||
            child.ancestor("display_form") != null ||
            child.ancestor("excel_form") != null) {
          retVal.add(child);
        }
      } else {
        retVal.addAll(getInputsInternally(child));
      }
    }
    return retVal;
  }

  private List<WidgetDefinition> getButtonsInternally(WidgetDefinition parent) {
    List<WidgetDefinition> retVal = new ArrayList<>();
    for (WidgetDefinition child : parent.widgets) {
      if (Strings.in(child.type, "button")) {
        retVal.add(child);
      } else {
        retVal.addAll(getInputsInternally(child));
      }
    }
    return retVal;
  }

  private WidgetDefinition ancestor(WidgetDefinition present, String type) {
    if (present == null || present.container == null) {
      return null;
    }
    if (present.container.type.equals(type)) {
      return present.container;
    }
    return ancestor(present.container, type);
  }
}

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

import static com.doublegsoft.jcommons.metaui.WidgetDefinition.from;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

/**
 * {@link YamlToPages} provides functions to parse json string to {@link PageDefinition} object.
 *
 * @author <a href="mailto:guo.guo.gan@gmail.com">Christian Gann</a>
 *
 * @since 1.0
 */
public class YamlToPages {

  public static PagesDefinition parse(String yaml) {
    PagesDefinition retVal = new PagesDefinition();
    Yaml y = new Yaml();
    List<Map<String, Object>> pagesData = (List<Map<String, Object>>) y.load(yaml);
    pagesData.stream().map((pageData) -> {
      PageDefinition page = new PageDefinition(pageData.keySet().iterator().next());
      Map<String, Object> pageOptions = (Map<String, Object>) pageData.values().iterator().next();
      Options opts = new Options(pageOptions);
      page.setTitle(opts.get(Options.TITLE));
      Map<String, Options> childOptions = opts.getWidgetOptions();
      childOptions.entrySet().stream().forEach((e) -> {
        page.addWidget(from(e.getKey(), e.getValue()));
      });
      return page;
    }).forEach((page) -> {
      retVal.add(page);
    });

    return retVal;
  }

  public static PagesDefinition parse(InputStream in) throws IOException {
    StringBuilder json = new StringBuilder();
    String line;
    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    while ((line = br.readLine()) != null) {
      json.append(line).append("\n");
    }
    return parse(json.toString());
  }

  private YamlToPages() {

  }

}

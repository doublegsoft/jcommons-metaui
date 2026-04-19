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

import com.doublegsoft.jcommons.metaui.WidgetModelExpressionParser;
import com.doublegsoft.jcommons.metaui.widget.FormWidgetModel;
import com.doublegsoft.jcommons.metaui.widget.GroupingWidgetModel;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:guo.guo.gan@gmail.com">Christian Gann</a>
 * 
 * @since 5.0
 * 
 * @version 5.0 - initial creation on Oct 18, 2019
 */
public class WidgetModelExpressionTest {
  
  @Test
  public void test_form() throws Exception {
    String expr = "name+mobile+email+(@save+@delete)|操作";
    FormWidgetModel form = WidgetModelExpressionParser.parseWidgetModelExpression(expr);
    
    Assert.assertEquals(4, form.getWidgetModels().size());
    
    GroupingWidgetModel grouping = (GroupingWidgetModel) form.getWidgetModels().get(3);
    Assert.assertEquals(2, grouping.getPrimitiveWidgetModels().size());
    Assert.assertEquals("操作", grouping.getText());
  }
  
  @Test
  public void test_table() throws Exception {
    String expr = " (name+primary_project)|项目+(contract+customer)|客户合同+principal+(start_date+progress)|工程进度+(@edit+@view+@remove)|操作";
    FormWidgetModel form = WidgetModelExpressionParser.parseWidgetModelExpression(expr);
    
    Assert.assertEquals(5, form.getWidgetModels().size());
    
    GroupingWidgetModel grouping = (GroupingWidgetModel) form.getWidgetModels().get(0);
    Assert.assertEquals(2, grouping.getPrimitiveWidgetModels().size());
    Assert.assertEquals("项目", grouping.getText());
  }
}

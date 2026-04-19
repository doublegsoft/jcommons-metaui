/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.doublegsoft.jcommons.metaui;

import com.doublegsoft.jcommons.metaui.*;
import com.doublegsoft.jcommons.metaui.MetauiParser.Metaui_actionContext;
import com.doublegsoft.jcommons.metaui.MetauiParser.Metaui_elementContext;
import com.doublegsoft.jcommons.metaui.MetauiParser.Metaui_element_groupContext;
import com.doublegsoft.jcommons.metaui.MetauiParser.Metaui_elementsContext;
import com.doublegsoft.jcommons.metaui.MetauiParser.Metaui_formContext;
import com.doublegsoft.jcommons.metaui.widget.FormWidgetModel;
import com.doublegsoft.jcommons.metaui.widget.GroupingWidgetModel;
import com.doublegsoft.jcommons.metaui.widget.PrimitiveWidgetModel;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 *
 * @since 5.0
 */
public class WidgetModelExpressionParser {
  
  public static FormWidgetModel parseWidgetModelExpression(String expr) {
    FormWidgetModel retVal = new FormWidgetModel();
    com.doublegsoft.jcommons.metaui.MetauiParser parser = createParser(expr);
    Metaui_formContext ctx = parser.metaui_form();
    Metaui_elementsContext ctxElements = ctx.metaui_elements();
    for (Metaui_element_groupContext ctxGroup : ctxElements.metaui_element_group()) {
      if (ctxGroup.action != null) {
        PrimitiveWidgetModel primitive = new PrimitiveWidgetModel();
        primitive.setAction(ctxGroup.action.getText());
        retVal.addWidgetModel(primitive);
      } else if (ctxGroup.element != null) {
        PrimitiveWidgetModel primitive = new PrimitiveWidgetModel();
        primitive.setSource(ctxGroup.element.getText());
        retVal.addWidgetModel(primitive);
      } else if (ctxGroup.metaui_element().size() > 0) {
        GroupingWidgetModel grouping = new GroupingWidgetModel();
        for (Metaui_elementContext ctxElement : ctxGroup.metaui_element()) {
          String elementName = ctxElement.METAUI_ID().getText();
          PrimitiveWidgetModel primitive = new PrimitiveWidgetModel();
          primitive.setSource(elementName);
          grouping.addPimitiveWidgetModel(primitive);
        }
        if (ctxGroup.label != null) {
          grouping.setText(ctxGroup.label.getText());
        }
        retVal.addWidgetModel(grouping);
      } else if (ctxGroup.metaui_action().size() > 0) {
        GroupingWidgetModel grouping = new GroupingWidgetModel();
        for (Metaui_actionContext ctxAction : ctxGroup.metaui_action()) {
          String actionName = ctxAction.name.getText();
          PrimitiveWidgetModel primitive = new PrimitiveWidgetModel();
          primitive.setAction(actionName);
          if (ctxAction.label != null) {
            primitive.setText(ctxAction.label.getText());
          }
          grouping.addPimitiveWidgetModel(primitive);
        }
        if (ctxGroup.label != null) {
          grouping.setText(ctxGroup.label.getText());
        }
        retVal.addWidgetModel(grouping);
      }
    }
    return retVal;
  }
  
  private static com.doublegsoft.jcommons.metaui.MetauiParser createParser(String expr) {
    com.doublegsoft.jcommons.metaui.MetauiLexer lexer = new com.doublegsoft.jcommons.metaui.MetauiLexer(CharStreams.fromString(expr));
    CommonTokenStream token = new CommonTokenStream(lexer);
    com.doublegsoft.jcommons.metaui.MetauiParser parser = new com.doublegsoft.jcommons.metaui.MetauiParser(token);
    parser.setErrorHandler(new BailErrorStrategy());
    return parser;
  }
  
}

grammar Metaui;

METAUI_WS:                       [ \n\t\r]+ -> channel(HIDDEN);

fragment
METAUI_NAMECHAR
  :   METAUI_NAMEFIRSTCHAR
  |   '0'..'9'
  |   '_'
  |   '\u00B7'
  |   '\u0300'..'\u036F'
  |   '\u203F'..'\u2040'
  ;

fragment
METAUI_NAMEFIRSTCHAR
  :   'A'..'Z'
  |   'a'..'z'
  |   '\u00C0'..'\u00D6'
  |   '\u00D8'..'\u00F6'
  |   '\u00F8'..'\u02FF'
  |   '\u0370'..'\u037D'
  |   '\u037F'..'\u1FFF'
  |   '\u200C'..'\u200D'
  |   '\u2070'..'\u218F'
  |   '\u2C00'..'\u2FEF'
  |   '\u3001'..'\uD7FF'
  |   '\uF900'..'\uFDCF'
  |   '\uFDF0'..'\uFFFD'
  ; // ignores | ['\u10000-'\uEFFFF];

METAUI_ID:                       METAUI_NAMEFIRSTCHAR METAUI_NAMECHAR*;

METAUI_SYMBOL_ACTIONS:          '!';
METAUI_SYMBOL_ELEMENTS:         '#';
METAUI_SYMBOL_MODEL:            '&';
METAUI_SYMBOL_MIXIN:            '+';
METAUI_SYMBOL_LABEL:            '|';
METAUI_SYMBOL_SET:              '=';
METAUI_SYMBOL_RESOURCE:         '@';
METAUI_SYMBOL_SYSTEM:           '$';

metaui_id
  :   METAUI_ID ('.' METAUI_ID)*
  ;

metaui_element
  :   METAUI_ID
  ;

metaui_element_group
  :   '(' metaui_element ('+' metaui_element)+ ')' ('|' label=metaui_id)?
  |   '(' metaui_action ('+' metaui_action)+ ')' ('|' label=metaui_id)?
  |   element=metaui_element
  |   action=metaui_action
  ;

metaui_elements
  :   metaui_element_group ('+' metaui_element_group)*
  ;

metaui_action
  :   '@' name=metaui_id ('|' label=metaui_id)? ('{' METAUI_SYMBOL_MODEL model=metaui_id (metaui_valuing)? '}')?
  ;

metaui_valuing
  :   key=metaui_id '=' value=metaui_id
  ;

metaui_form
  :   metaui_elements
  ;

metaui_table
  :   metaui_elements
  ;
                       


grammar Filter;


//   A and B
//   (A and B) or C
//   A and (B or C)

expression
    : expression operator expression
    | '(' expression ')'
    | filterID
    ;

operator
    : AND
    | OR
    ;

filterID
    : TEXT
    ;

AND
    : 'AND'
    | 'and'
    ;

OR
    : 'OR'
    | 'or'
    ;

TEXT
    : LETTER+
    ;

fragment LETTER
    : [A-Za-z]
    ;

WS
   : [ \r\n\t] + -> skip
   ;

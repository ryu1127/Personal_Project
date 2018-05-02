import java.util.*;
public class Parser {
    
    Token token;         
    Lexer lexer;
    String Id = "main";

    public Parser(Lexer lex) { 
        lexer = lex;           
        token = lexer.next(); 
    }
  
    private String match (TokenType t) {
        String value = token.value();
        if (token.type().equals(t))
            token = lexer.next();
        else
            error(t);
        return value;
    }
 
    private void error(TokenType tok) {
        System.err.println("Syntax error: expecting: " + tok 
                           + "; saw: " + token);
        System.exit(1);
    }
  
    private void error(String tok) {
        System.err.println("Syntax error: expecting: " + tok 
                           + "; saw: " + token);
        System.exit(1);
    }
  
    public Program program() {
        TokenType[ ] header = {TokenType.Int, TokenType.Main,
                          TokenType.LeftParen, TokenType.RightParen};
        for (int i=0; i<header.length; i++) 
            match(header[i]);
        match(TokenType.LeftBrace);
        Declarations decpart = declarations();
        Block body = statements();
        match(TokenType.RightBrace);
        return new Program(decpart, body);
    }

    private Declarations declarations () {
        Declarations ds = new Declarations ();  
        while (isType( )) {
            declaration(ds);
        }  
        return ds;                
    }
  
    private void declaration (Declarations ds) {
        Type type = type();
        while (! token.type().equals(TokenType.Semicolon)){
            String id = match(TokenType.Identifier);
            ds.add(new Declaration(id, type));
            if (token.type().equals(TokenType.Comma))
                match(TokenType.Comma);
        }
        match(TokenType.Semicolon);
    }
  
    private Type type () {
        Type type = null;
        if (token.type().equals(TokenType.Int))
            type = Type.INT;
        else if (token.type().equals(TokenType.Bool))
            type = Type.BOOL;
        else if (token.type().equals(TokenType.Float))
            type = Type.FLOAT;
        else if (token.type().equals(TokenType.Char))
            type = Type.CHAR;
        else error("int | bool | float | char");
        token = lexer.next(); 
        return type;          
    }
  
    private Statement statement() {
        Statement s = new Skip();
        if (token.type().equals(TokenType.Semicolon))
            token = lexer.next();
        else if (token.type().equals(TokenType.LeftBrace)) {
            token = lexer.next();
            s = statements();
            match(TokenType.RightBrace);
        }
        else if (token.type().equals(TokenType.If)) 
            s = ifStatement();
        else if (token.type().equals(TokenType.While))  
            s = whileStatement();
        else if (token.type().equals(TokenType.Identifier))  
            s = assignment();
        else error("Illegal statement");
        return s;
    }
  
    private Block statements () {
        Block b = new Block();
        while (! token.type().equals(TokenType.RightBrace)) {
            b.members.add(statement()); 
        }
        return b;
    }

    private Assignment assignment () {
        Variable target = new Variable(match(TokenType.Identifier));
        match(TokenType.Assign);
        Expression source = expression();
        match(TokenType.Semicolon);
        return new Assignment(target, source);
    }

    private Conditional ifStatement () {
        match(TokenType.If);
        Expression test = expression();
        Statement thenbranch = statement();
        Statement elsebranch = new Skip();
        if (token.type().equals(TokenType.Else)){
            token = lexer.next();
            elsebranch = statement();
        }
        return new Conditional(test, thenbranch, elsebranch);
    }
  
    private Loop whileStatement () {
        match(TokenType.While);
        match(TokenType.LeftParen);
        Expression test = expression();
        match(TokenType.RightParen);
        Statement body = statement();
        return new Loop(test, body);
    }

    private Expression expression () {
        Expression e = conjunction();
        while (token.type().equals(TokenType.Or)) {
            Operator op = new Operator(token.value());
            token = lexer.next();
            Expression term2 = conjunction();
            e = new Binary(op, e, term2);
        }
        return e;     
    }
  
    private Expression conjunction () {
        Expression e = equality();
        while (token.type().equals(TokenType.And)) {
            Operator op = new Operator(token.value());
            token = lexer.next();
            Expression term2 = equality();
            e = new Binary(op, e, term2);
        }
        return e;     
    }
  
    private Expression equality () {
        Expression e = relation();
        while (isEqualityOp()) {
            Operator op = new Operator(token.value());
            token = lexer.next();
            Expression term2 = relation();
            e = new Binary(op, e, term2);
        }
        return e;
    }

    private Expression relation (){
        Expression e = addition();
        while (isRelationalOp()){
            Operator op = new Operator(token.value());
            token = lexer.next();
            Expression term2 = addition();
            e = new Binary(op, e, term2);
        }
        return e;
    }
  
    private Expression addition () {
        Expression e = term();
        while (isAddOp()) {
            Operator op = new Operator(match(token.type()));
            Expression term2 = term();
            e = new Binary(op, e, term2);
        }
        return e;
    }
  
    private Expression term () {
        Expression e = factor();
        while (isMultiplyOp()) {
            Operator op = new Operator(match(token.type()));
            Expression term2 = factor();
            e = new Binary(op, e, term2);
        }
        return e;
    }
  
    private Expression factor() {
        if (isUnaryOp()) {
            Operator op = new Operator(match(token.type()));
            Expression term = primary();
            return new Unary(op, term);
        }
        else return primary();
    }
  
    private Expression primary () {
        Expression e = null;
        if (token.type().equals(TokenType.Identifier)) {
            Variable v = new Variable(match(TokenType.Identifier));
            e = v;
        } else if (isLiteral()) {
            e = literal();
        } else if (token.type().equals(TokenType.LeftParen)) {
            token = lexer.next();
            e = expression();       
            match(TokenType.RightParen);
        } else if (isType( )) {
            Operator op = new Operator(match(token.type()));
            match(TokenType.LeftParen);
            Expression term = expression();
            match(TokenType.RightParen);
            e = new Unary(op, term);
        } else error("Identifier | Literal | ( | Type");
        return e;
    }

    private Value literal( ) {
        String s = null;
        switch (token.type()) {
        case IntLiteral:
            s = match(TokenType.IntLiteral);
            return new IntValue(Integer.parseInt(s));
        case CharLiteral:
            s = match(TokenType.CharLiteral);
            return new CharValue(s.charAt(0));
        case True:
            s = match(TokenType.True);
            return new BoolValue(true);
        case False:
            s = match(TokenType.False);
            return new BoolValue(false);
        case FloatLiteral:
            s = match(TokenType.FloatLiteral);
            return new FloatValue(Float.parseFloat(s));
        }
        throw new IllegalArgumentException( "should not reach here");
    }
  

    private boolean isAddOp( ) {
        return token.type().equals(TokenType.Plus) ||
               token.type().equals(TokenType.Minus);
    }
    
    private boolean isMultiplyOp( ) {
        return token.type().equals(TokenType.Multiply) ||
               token.type().equals(TokenType.Divide);
    }
    
    private boolean isUnaryOp( ) {
        return token.type().equals(TokenType.Not) ||
               token.type().equals(TokenType.Minus);
    }
    
    private boolean isEqualityOp( ) {
        return token.type().equals(TokenType.Equals) ||
            token.type().equals(TokenType.NotEqual);
    }
    
    private boolean isRelationalOp( ) {
        return token.type().equals(TokenType.Less) ||
               token.type().equals(TokenType.LessEqual) || 
               token.type().equals(TokenType.Greater) ||
               token.type().equals(TokenType.GreaterEqual);
    }
    
    private boolean isType( ) {
        return token.type().equals(TokenType.Int)
            || token.type().equals(TokenType.Bool) 
            || token.type().equals(TokenType.Float)
            || token.type().equals(TokenType.Char);
    }
    
    private boolean isLiteral( ) {
        return token.type().equals(TokenType.IntLiteral) ||
            isBooleanLiteral() ||
            token.type().equals(TokenType.FloatLiteral) ||
            token.type().equals(TokenType.CharLiteral);
    }
    
    private boolean isBooleanLiteral( ) {
        return token.type().equals(TokenType.True) ||
            token.type().equals(TokenType.False);
    }
    
    public static void main(String args[]) {
    Parser parser  = new Parser(new Lexer("/Users/ryu/Desktop/workspace/my_lexer/src/command2.txt"));
        Program prog = parser.program();
        prog.display(); 
    } 
}
/*import java.io.*;

public class Lexer {

    private boolean isEof = false;
    private char ch = ' '; 
    private BufferedReader input;
    private String line = "";
    private int lineno = 0;
    private int col = 1;
    private final String letters = "abcdefghijklmnopqrstuvwxyz"
        + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String digits = "0123456789";
    private final char eolnCh = '\n';
    private final char eofCh = '\004';
    

    public Lexer (String fileName) { // source filename
        try {
            input = new BufferedReader (new FileReader(fileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            System.exit(1);
        }
    }

    private char nextChar() { // Return next char
        if (ch == eofCh)
            error("Attempt to read past end of file");
        col++;
        if (col >= line.length()) {
            try {
                line = input.readLine( );
            } catch (IOException e) {
                System.err.println(e);
                System.exit(1);
            } // try
            if (line == null) // at end of file
                line = "" + eofCh;
            else {
                // System.out.println(lineno + ":\t" + line);
                lineno++;
                line += eolnCh;
            } // if line
            col = 0;
        } // if col
        return line.charAt(col);
    }
            

    public Token next( ) { // Return next token
        do {
            if (isLetter(ch)) { // ident or keyword
                String spelling = concat(letters + digits);
                return Token.keyword(spelling);
            } else if (isDigit(ch)) { // int or float literal
                String number = concat(digits);
                if (ch != '.')  // int Literal
                    return Token.mkIntLiteral(number);
                number += concat(digits);
                return Token.mkFloatLiteral(number);
            } else switch (ch) {
            case ' ': case '\t': case '\r': case eolnCh:
                ch = nextChar();
                break;
            
            case '/':  // divide or comment
                ch = nextChar();
                if (ch != '/')  return Token.divideTok;
                // comment
                do {
                    ch = nextChar();
                } while (ch != eolnCh);
                ch = nextChar();
                break;
            
            case '\'':  // char literal
                char ch1 = nextChar();
                nextChar(); // get '
                ch = nextChar();
                return Token.mkCharLiteral("" + ch1);
                
            case eofCh: return Token.eofTok;
            
            case '+': ch = nextChar();
                return Token.plusTok;

                // - * ( ) { } ; ,  student exercise
                
            case '-': ch = nextChar(); return Token.minusTok;
            case '*': ch = nextChar(); return Token.multiplyTok;
            case '(': ch = nextChar(); return Token.leftParenTok;
            case ')': ch = nextChar(); return Token.rightParenTok;
            case '{': ch = nextChar(); return Token.leftBraceTok;
            case '}': ch = nextChar(); return Token.rightBraceTok;
            case ';': ch = nextChar(); return Token.semicolonTok;
            case ',': ch = nextChar(); return Token.commaTok;
            
                
            case '&': check('&'); return Token.andTok;
            case '|': check('|'); return Token.orTok;

            case '=':
                return chkOpt('=', Token.assignTok,
                                   Token.eqeqTok);
                // < > !  student exercise 
            case '<': return chkOpt('=',Token.ltTok,Token.lteqTok);
            case '>': return chkOpt('=',Token.gtTok,Token.gteqTok);
            case '!': return chkOpt('=',Token.notTok,Token.noteqTok);

            default:  error("Illegal character " + ch); 
            } // switch
        } while (true);
    } // next


    private boolean isLetter(char c) {
        return (c>='a' && c<='z' || c>='A' && c<='Z');
    }
  
    private boolean isDigit(char c) {
        return (c>='0' && c<='9');  // student exercise
    }

    private void check(char c) {
        ch = nextChar();
        if (ch != c) 
            error("Illegal character, expecting " + c);
        ch = nextChar();
    }

    private Token chkOpt(char c, Token one, Token two) {
        ch = nextChar();
        if(ch != c) {
        	return one;
        }
        else {
        	ch = nextChar();
        	return two;
        }
    	
    	  // student exercise
    }

    private String concat(String set) {
        String r = "";
        do {
            r += ch;
            ch = nextChar();
        } while (set.indexOf(ch) >= 0);
        return r;
    }

    public void error (String msg) {
        System.err.print(line);
        System.err.println("Error: column " + col + " " + msg);
        System.exit(1);
    }

    static public void main ( String[] argv ) {
        Lexer lexer = new Lexer("C:/Users/RYU/Google 드라이브/my_lexer/src/command.txt");
        Token tok = lexer.next( );
        while (tok != Token.eofTok) {
            System.out.println(tok.toString());
            tok = lexer.next( );
        } 
    } // main

}*/













import java.io.*;

public class Lexer {
    private boolean isEof = false;
    private char ch = ' ';
    private BufferedReader input;
    private String line = "";
    private int lineno = 0;
    private int col = 1;
    private final String letters = "abcdefghijklmnopqrstuvwxyz"
        + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String digits = "0123456789";
    private final char eolnCh = '\n';
    private final char eofCh = '\004';

    public Lexer (String fileName) { // source filename
        try {
            input = new BufferedReader (new FileReader(fileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            System.exit(1);
        }
    }

    // 파일 한 줄을 읽어오기 위한 함수
    private char nextChar() { // Return next char
        if (ch == eofCh)
            error("Attempt to read past end of file");
        col++;
        if (col >= line.length()) {
            try {
                line = input.readLine( );
            } catch (IOException e) {
                System.err.println(e);
                System.exit(1);
            } // try
            if (line == null) // at end of file
                line = "" + eofCh;
            else if (line.startsWith("//")) { // line이 //으로 시작하면 그 line 전체가 주석이므로 몇 번째 라인인지 출력하지 않기 위해 처리를 해줌
               ++lineno;
               line += eolnCh;
            }
            else {
               ++lineno;
                // System.out.println("line " + (lineno));
                line += eolnCh;
            } // if line
            col = 0;
        } // if col
        return line.charAt(col);
    }

    // 문장 한 줄에서 토큰 하나를 읽어와서 토큰으로 식별하기 위한 함수
    public Token next( ) { // Return next token
        do {
            if (isLetter(ch)) { // ident or keyword
                String spelling = concat(letters + digits);
                return Token.keyword(spelling);
            } else if (isDigit(ch)) { // int or float literal
                String number = concat(digits);
                if (ch != '.')  // int Literal
                    return Token.mkIntLiteral(number);
                number += concat(digits);
                return Token.mkFloatLiteral(number);
            } else switch (ch) {
            case ' ': case '\t': case '\r': case eolnCh:
                ch = nextChar();
                break;
                
            case '/':  // divide or comment
                ch = nextChar();
                if (ch != '/')  return Token.divideTok;
                // comment
                do {
                    ch = nextChar();
                } while (ch != eolnCh);
                ch = nextChar();
                break;
                
            case '\'':  // char literal
                char ch1 = nextChar();
                nextChar(); // get '
                ch = nextChar();
                return Token.mkCharLiteral("" + ch1);

            case eofCh: return Token.eofTok;
            
            case '+': ch = nextChar();
               return Token.plusTok;
            // - * ( ) { } ; ,  student exercise
            case '-': ch = nextChar();
               return Token.minusTok;
            case '*': ch = nextChar();
               return Token.multiplyTok;
            case '(': ch = nextChar();
               return Token.leftParenTok;
            case ')': ch = nextChar();
               return Token.rightParenTok;
            case '{': ch = nextChar();
               return Token.leftBraceTok;
            case '}': ch = nextChar();
               return Token.rightBraceTok;
            case ';': ch = nextChar();
               return Token.semicolonTok;
            case ',': ch = nextChar();
               return Token.commaTok;

            case '&': check('&'); return Token.andTok;
            case '|': check('|'); return Token.orTok;

            case '=':
                return chkOpt('=', Token.assignTok,
                                   Token.eqeqTok);
            // < > !  student exercise
            case '<': 
               return chkOpt('=', Token.ltTok,
                                 Token.lteqTok);
            case '>': 
               return chkOpt('=', Token.gtTok,
                                 Token.gteqTok);   
            case '!': 
               return chkOpt('=', Token.notTok,
                                 Token.noteqTok);
               
            default:  error("Illegal character " + ch);
            } // switch
        } while (true);
    } // next

    // 입력받은 문자가 알파벳인지 확인하기 위한 함수
    private boolean isLetter(char c) {
        return (c>='a' && c<='z' || c>='A' && c<='Z');
    }

    // 입력받은 문자가 숫자인지 확인하기 위한 함수
    private boolean isDigit(char c) {
        return (c>='0' && c<='9'); // 직접 작성한 코드부
    }
    
    // 다음에 올 문자가 변수 c가 맞는지 확인 해 보는 것 (&&, ||, !=, ==, >=, <= 등에 쓰임)
    private void check(char c) {
        ch = nextChar();
        if (ch != c)
            error("Illegal character, expecting " + c);
        ch = nextChar();
    }

    // student exercise
    private Token chkOpt(char c, Token one, Token two) {
       ch = nextChar();
       if (ch != c)
          return one;
       ch = nextChar();
        return two;
    }

    // 문자열을 연결하기 위한 함수
    private String concat(String set) {
        String r = "";
        do {
            r += ch;
            ch = nextChar();
        } while (set.indexOf(ch) >= 0);
        return r;
    }

    // 어느 곳에서 에러가 발생했는지 알려주기 위한 함수
    public void error (String msg) {
        System.err.print(line);
        System.err.println("Error: column " + col + " " + msg);
        System.exit(1);
    }

    // 테스트 하기 위한 메인함수
    static public void main ( String[] argv ) {
        Lexer lexer = new Lexer("C:/Users/RYU/Google 드라이브/my_lexer/src/command2.txt");
        Token tok = lexer.next( );
        while (tok != Token.eofTok) {
            System.out.println("\t" + tok.toString());
            tok = lexer.next( );
        }
    } // main
   public int getLineno() {
      return lineno;
   }
}






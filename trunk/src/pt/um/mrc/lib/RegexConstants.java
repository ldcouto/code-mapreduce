package pt.um.mrc.lib;

import java.util.regex.Pattern;

public class RegexConstants
{
    protected RegexConstants()
    {}

    public static Pattern CLASS_HEADER_PATTERN = Pattern
            .compile("(public|protected|private|abstract|static|final|native|synchronized|transient|volatile|strictfp|\\s)+class\\s+[a-zA-Z0-9.,<> \\_]*\\s*\\{");

    public static Pattern IMPORT_PATTERN = Pattern.compile("import(static|\\s)+[\\w.]*(\\*)?(\\s)*;");

    public static Pattern PACKAGE_PATTERN = Pattern.compile("package(\\s)+[a-zA-Z.]*(\\s)*;");

    public static Pattern NON_EMPTY_LINE = Pattern.compile("[^\\s]+\\n+|}");
    
    private static String ifClause = "(^|\\s)+if(\\s)*\\(";
    private static String forClause = "(^|\\s)+for(\\s)*\\(";
    private static String whileClause = "(^|\\s|\\})+while(\\s)*\\(";
    private static String caseClause = "(^|\\s)+case(\\s)+(.)\\;";
    private static String catchClause = "(^|\\s)+catch(\\s)*\\(";
    private static String andClause = "(^|\\s)+\\&\\&(\\s)+";
    private static String orClause = "(^|\\s)+\\|\\|(\\s)+";
    private static String ternaryOpClause = "(\\s)+\\?(\\s)+";
    private static String controlStatementsClause = "(" + ifClause + "|" + forClause + "|" + whileClause + "|" + caseClause + "|" + catchClause + "|"
            + andClause + "|" + orClause + "|" + ternaryOpClause + ")";

    public static Pattern CONTROL_STATEMENT_PATTERN = Pattern.compile(controlStatementsClause);
    
    public static String WHITESPACES_REGEX ="\\s";
    
    public static String DOTSTAR_REGEX = "(\\.\\*)$";
    
    public static String CLASS_LITERAL = "class";
    
    public static String EXTENDS_LITERAL = "extends";
    
    public static String IMPLEMENTS_LITERAL = "implements";
    
    public static String CLASS_HEADER_TERMINATOR = "\\{";
    
    public static String EMPTY_ARGUMENTS = "[ ]";
}

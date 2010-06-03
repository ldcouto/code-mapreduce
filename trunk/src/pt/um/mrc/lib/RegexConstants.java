package pt.um.mrc.lib;

import java.util.regex.Pattern;

/**
 * Regular expressions constants used all around the project.
 */
public class RegexConstants
{

    /**
     * Protected constructor since it is a static only class
     */
    protected RegexConstants()
    {}

    /** The class header pattern. */
    public static Pattern CLASS_HEADER_PATTERN = Pattern
            .compile("(public|protected|private|abstract|static|final|native|synchronized|transient|volatile|strictfp|\\s)+class\\s+[a-zA-Z0-9.,<> \\_]*\\s*\\{");

    /** The import declaration pattern. */
    public static Pattern IMPORT_PATTERN = Pattern.compile("import(static|\\s)+[\\w.]*(\\*)?(\\s)*;");

    /** The package declaration pattern. */
    public static Pattern PACKAGE_PATTERN = Pattern.compile("package(\\s)+[a-zA-Z.]*(\\s)*;");

    /** The non empty line regex. */
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

    /** The control statements pattern. */
    public static Pattern CONTROL_STATEMENT_PATTERN = Pattern.compile(controlStatementsClause);

    /** The white space regex. */
    public static String WHITESPACES_REGEX = "\\s";

    /** The dot star regex. */
    public static String DOTSTAR_REGEX = "(\\.\\*)$";

    /** The class literal. */
    public static String CLASS_LITERAL = "class";

    /** The extends literal. */
    public static String EXTENDS_LITERAL = "extends";

    /** The implements literal. */
    public static String IMPLEMENTS_LITERAL = "implements";

    /** The class header terminator. */
    public static String CLASS_HEADER_TERMINATOR = "\\{";

    /** The empty arguments string. */
    public static String EMPTY_ARGUMENTS = "[ ]";

    /** Comment regex */
    public static String COMMENT_REGEX = "(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/)|(//.*\n)";
    
    /** String literal regex */
    public static Pattern STRING_LITERAL = Pattern.compile("\\\"([^\"\\\\]|\\\\.)*\\\"");
}

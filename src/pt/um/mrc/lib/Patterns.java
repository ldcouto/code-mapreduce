package pt.um.mrc.lib;

import java.util.regex.Pattern;

public class Patterns
{
    protected Patterns()
    {}

    public static Pattern CLASS_NAME_PATTERN = Pattern
    .compile("public|protected|private|abstract|static|final|native|synchronized|transient|volatile|strictfp|\\s+class\\s+([a-zA-Z0-9.,<> \\_]*)\\s*\\{");

    
    public static Pattern CLASS_HEADER_PATTERN = Pattern
            .compile("(public|protected|private|abstract|static|final|native|synchronized|transient|volatile|strictfp|\\s)+class\\s+[a-zA-Z0-9.,<> \\_]*\\s*\\{");

    public static Pattern IMPORT_PATTERN = Pattern
            .compile("import(static|\\s)+[\\w.]*(\\*)?(\\s)*;");

    public static Pattern PACKAGE_PATTERN = Pattern.compile("package(\\s)+[a-zA-Z.]*(\\s)*;");

    private static String ifClause = "(^|\\s)+if(\\s)*\\(";
    private static String forClause = "(^|\\s)+for(\\s)*\\(";
    private static String whileClause = "(^|\\s|\\})+while(\\s)*\\(";
    private static String caseClause = "(^|\\s)+case(\\s)+(.)\\;";
    private static String catchClause = "(^|\\s)+catch(\\s)*\\(";
    private static String andClause = "(^|\\s)+\\&\\&(\\s)+";
    private static String orClause = "(^|\\s)+\\|\\|(\\s)+";
    private static String controlStatementsClause = "("+ifClause+"|"+forClause+"|"+whileClause+"|"+caseClause+"|"+catchClause+"|"+andClause+"|"+orClause+")";
    
    public static Pattern CONTROL_STATEMENT_PATTERN = Pattern
            .compile(controlStatementsClause);
}

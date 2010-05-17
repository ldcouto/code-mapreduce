package pt.um.mrc.lib;

import java.util.regex.Pattern;

public class Patterns
{
    protected Patterns()
    {}

    public static Pattern CLASS_HEADER_PATTERN = Pattern
            .compile("(public|protected|private|abstract|static|final|native|synchronized|transient|volatile|strictfp|\\s)+class\\s+[a-zA-Z.,<> ]*\\s*\\{");

    public static Pattern IMPORT_PATTERN = Pattern
            .compile("import(static|\\s)+[\\w.]*(\\*)?(\\s)*;");

    public static Pattern PACKAGE_PATTERN = Pattern.compile("package(\\s)+[a-zA-Z.]*(\\s)*;");

    public static Pattern CONTROL_STATEMENT_PATTERN = Pattern
            .compile("(if|for|while|case|catch|\\&\\&|\\|\\|)");
}

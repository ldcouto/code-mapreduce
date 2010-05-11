package pt.um.mrc.lib;

import java.util.regex.Pattern;

public abstract class Patterns
{
    public static Pattern CLASS_HEADER_PATTERN = Pattern
            .compile("(public|protected|private|abstract|static|final|native|synchronized|transient|volatile|strictfp|\\s)+class\\s+[a-zA-Z.,<> ]*\\s*\\{");

    public static Pattern IMPORT_PATTERN = Pattern.compile("import(static|\\s)+[\\w.]*(\\*)?;");

    public static Pattern PACKAGE_PATTERN = Pattern.compile("package(\\s)+[a-zA-Z.]*;");

}

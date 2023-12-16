package frontend;

public enum TriStateBoolean {
    TRUE, FALSE, UNDEFINED;

    public static TriStateBoolean fromBoolean(boolean value){
        return value ? TRUE:FALSE;
    }
}

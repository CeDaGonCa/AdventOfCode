package AdventofCode2025.Q7;

public enum Tachyon {
    START, BEAM, EMPTY, SPLITTER;
    public String toString(){
        return switch (this) {
            case START -> "S";
            case BEAM -> "|";
            case EMPTY -> " ";
            case SPLITTER -> "^";
        };
    }
}



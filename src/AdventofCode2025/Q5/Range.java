package AdventofCode2025.Q5;

public record Range(long lower, long upper) {
    public boolean isInBound(long x) {
        return x >= lower && x <= upper;
    }
}

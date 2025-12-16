package AdventofCode2025.Q3;

import AdventofCode2025.AOCReader;

import java.util.Arrays;

public class Q3 extends AOCReader {
    public Q3() {
        super("Q3");
    }

    public static void main(String[] args) {
        Q3 obj = new Q3();
        obj.solve();
    }

    public void solve() {
        int accumulator = 0;
        System.out.println("Part 1:");
        for (String s : this.inputStrings) {
            int maxInLine = 0;
            for (int i = 0; i < s.length() - 1; i++) {
                for (int j = i + 1; j < s.length(); j++) {
                    String val = s.charAt(i) + "" + s.charAt(j);
                    maxInLine = Math.max(maxInLine, Integer.parseInt(val));
                }
            }
            accumulator += maxInLine;
        }
        System.out.println(accumulator);
        System.out.println("Part 2:");
        solvePart2();
    }
    public void solvePart2(){
        long joltage = 0;
        for(String bank :this.inputStrings){
            int[] batteries = Arrays.stream(bank.split("")).mapToInt(Integer::parseInt).toArray();
            int start = 0;
            int usable = 11;
            int end = batteries.length - usable;
            StringBuilder joltageBank = new StringBuilder();
            while(usable >=0){
                int[] candidates = Arrays.copyOfRange(batteries, start, end);
                Arrays.sort(candidates);
                int max = candidates[candidates.length - 1];
                joltageBank.append(max);
                for(int i = start; i <= end; i++){
                    if(max == batteries[i]){
                        start = i+1;
                        break;
                    }
                }
                usable--;
                end = batteries.length - usable;
            }
            joltage += Long.parseLong(joltageBank.toString());
        }
        System.out.println(joltage);
    }
}

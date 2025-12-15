package AdventofCode2025.Q2;

import AdventofCode2025.AOCReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

public class Q2 extends AOCReader{
    public Q2() {
        super("Q2");
    }
    public static void main(String[] args) {
        Q2 obj = new Q2();
        obj.solve();
    }
    public void solve(){
        List<String> input = new ArrayList<String>();
        for(String s: this.inputStrings){
            input.addAll(List.of(s.split(",")));
        }
        // Divide it into 2 parts for readibility
        // It can be put into one function but it would be messier
        this.solvePart1(input);
        this.solvePart2(input);

    }
    public void solvePart1(List<String> input){
        System.out.println("Part 1:");
        long acc = 0;
        for (String s: input){
            String[] range = s.split("-");
            long start = Long.parseLong(range[0]);
            long end = Long.parseLong(range[1]);
            for(long i = start; i<=end; i++){
                String tmp = Long.toString(i);
                // If # of digits is odd, then it cannot be invalid
                if(tmp.length()%2 != 0){
                    continue;
                }
                // divide string in 2 if LHS = RHS
                if(tmp.substring(tmp.length()/2).equals(tmp.substring(0,tmp.length()/2))){
                    //System.out.println("Invalid a " + tmp + " by repeating:" +tmp.substring(tmp.length()/2));
                    acc += i;
                }
            }
        }
        System.out.println("Sum of invalid ID's = "+ acc);
    }
    public void solvePart2(List<String> input){
        System.out.println("Part 2:");
        long acc2 = 0;
        for (String s: input){
            String[] range = s.split("-");
            long start = Long.parseLong(range[0]);
            long end = Long.parseLong(range[1]);
            for(long i = start; i<=end; i++){
                String tmp = Long.toString(i);
                // if j > length/2 and hasnt find an invalid subset the it cant be invalid
                // Go through all posible sizes of subdivisions of the String
                for(int j = 1;j<tmp.length()/2+1;j++){
                    // if j is not a divisor of the length then the string cannot be divided
                    // evenly
                    if(tmp.length()%j != 0){
                        continue;
                    }
                    // Regex, split it into string of atmost size j
                    String[] substrings = tmp.split("(?<=\\G.{" + j + "})");
                    boolean invalid = true;

                    for(String subS: substrings){
                        // make sure there the substring matches all other substrings
                        // else break
                        if(!subS.equals(substrings[0])){
                            invalid = false;
                            break;
                        }
                    }
                    if(invalid){
                        acc2 += i;
                        break;
                    }
                }
            }

        }
        System.out.println("Sum of invalid ID's = "+ acc2);
    }
    // Code is really slow, wonder if there is a faster way to do it
}

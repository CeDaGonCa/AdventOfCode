package AdventofCode2025.Q6;

import AdventofCode2025.AOCReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q6 extends AOCReader {
    public Q6() {
        super("Q6");
    }

    public static void main(String[] args) {
        Q6 obj = new Q6();
        obj.solve();
    }
    public void solve(){
        solveP1();
        solveP2();
    }
    public void solveP1(){
        List<List<String>> input = new ArrayList<>();
        for (String s : this.inputStrings) {
            List<String> tmp = Arrays.stream(s.split("\\s+")).filter(a -> !a.isEmpty()).toList();
            input.add(tmp);
        }
        long ret = 0;
        for(int i = 0; i < input.get(0).size(); i++){
            List<String> column = new ArrayList<>();
            for(int j = 0; j < input.size()-1; j++){
                column.add(input.get(j).get(i));
            }
            if(input.get(input.size()-1).get(i).equals("+")){
                ret += add(column);
            }else {
                ret += multiply(column);
            }
        }
        System.out.println(ret);
    }
    public void solveP2(){
        long ret = 0;
        boolean lastOppAdd = inputStrings.get(inputStrings.size()-1).charAt(0) == '+';
        List<String> currentOpp = new ArrayList<>();
        int maxSize = 0;
        for( String s : this.inputStrings){
            maxSize = Math.max(maxSize, s.length());
        }
        for(int i = 0; i < maxSize; i++){
            StringBuilder column = new StringBuilder();
            for(int j = 0; j < inputStrings.size()-1; j++){
                if(i < inputStrings.get(j).length() && inputStrings.get(j).charAt(i) != ' '){
                    column.append(inputStrings.get(j).charAt(i));
                }
            }
            if(column.isEmpty()){
                long tmp;
                if(lastOppAdd){
                    tmp = add(currentOpp);
                }else{
                    tmp = multiply(currentOpp);
                }
                ret += tmp;
                System.out.println(tmp);
                lastOppAdd = inputStrings.get(inputStrings.size()-1).charAt(i+1) == '+';
                //System.out.println(currentOpp);
                currentOpp.clear();
            }else{
                currentOpp.add(column.toString());
            }
            if(i == maxSize-1){
                long tmp;
                if(lastOppAdd){
                    tmp = add(currentOpp);
                }else{
                    tmp = multiply(currentOpp);
                }
                ret += tmp;
            }
        }
        System.out.println(ret);
    }
    public long add(List<String> column){
        long count = 0;
        for (String s : column) {
            count += Long.parseLong(s);
        }
        return count;
    }
    public long multiply(List<String> column){
        long count = 1;
        for (String s : column) {
            count *= Long.parseLong(s);
        }
        return count;
    }

}

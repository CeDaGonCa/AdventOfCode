package AdventofCode2025.Q1;

import AdventofCode2025.AOCReader;



public class Q1 extends AOCReader {
    public Q1() {
        super("Q1");
    }
    public static void main(String[] args) {
        Q1 obj = new Q1();
        //P1
        obj.solve();
    }
    public void solve(){
        int pos = 50;
        int prevPos = pos;
        // # of times we where at position 0
        int counterZeroEnd = 0;
        int counterZeroClick = 0;
        int counter = 0;
        int posFunny = pos;
        for(String s: this.inputStrings){
            // Skip first character to get the movement of the dials
            int move = Integer.parseInt(s.substring(1));
            for(int i = 0; i < move; i++){
                if(s.charAt(0) == 'L'){
                    posFunny--;
                }else{
                    posFunny++;
                }
                if(posFunny < 0){
                    posFunny += 100;
                }
                posFunny %= 100;
                if(posFunny == 0){
                    counter++;
                }
            }

            counterZeroClick += move/100; // Counting # of full turns, each full turn we pass through one zero
            move = move%100;
            // If going to the left substract from position
            if(s.charAt(0) == 'L') move *=-1;

            pos += move;
            if(prevPos != 0 && pos <= 0) counterZeroClick++;
            // add 100 to make the lap from the left
            if(pos < 0) pos += 100;
            // modulo 100 to make lap from the right
            if(pos > 99) counterZeroClick++;
            pos %= 100;
            prevPos = pos;
            if(pos == 0) counterZeroEnd++;
            if(counter != counterZeroClick){
                System.out.println("real " + posFunny + "\n mine " + pos);
                System.out.println(counter + " " + counterZeroClick);
            }
        }
        System.out.println("Part 1:");
        System.out.println(counterZeroEnd);
        System.out.println("Part 2:");
        System.out.println(counter);
    }
}

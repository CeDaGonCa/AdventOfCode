package AdventofCode2025.Q5;

import AdventofCode2025.AOCReader;

import java.util.*;

public class Q5 extends AOCReader {
    public Q5() {
        super("Q5");
    }
    public static void main(String[] args) {
        Q5 q5 = new Q5();
        q5.solve();
    }
    public void solve(){
        boolean isRanges = true;
        List<Range> ranges = new ArrayList<>();
        int count = 0;
        for(String s: this.inputStrings){
            if(s.isBlank()){
                isRanges = false;
                continue;
            }
            if(isRanges){
                String[] tmp = s.split("-");
                if(Long.MAX_VALUE == Long.parseLong(tmp[0])){
                    System.out.println("Invalid range");
                }
                ranges.add(new Range(Long.parseLong(tmp[0]), Long.parseLong(tmp[1])));
            } else {
                count += isFresh(Long.parseLong(s),ranges)? 1 :0 ;
            }
        }
        System.out.println("Number of ranges: " + count);
        System.out.println("Part 2");
        // Make 2 list of the lower and upper bounds respectively
        List<Long> init = new ArrayList<>();
        List<Long> end = new ArrayList<>();
        for(Range r: ranges){
            init.add(r.lower());
            end.add(r.upper());
        }
        // Sort both lists in ascending order
        init.sort(Long::compareTo);
        init.add(Long.MAX_VALUE);
        end.sort(Long::compareTo);
        Queue<Long> lastLowBound = new LinkedList<>();
        int boundCounter = 0;
        List<Range> disjointRanges = new ArrayList<>();
        int i = 0, j = 0;
        long rangesSize = 0;
        /*
        Think of it as the problem of showing if a parenthesis assortment is correct
        have 2 pointers L & U -> lower bd list & upper bd list respectively
        if *L < *U then we have the start of a range, since either *U is the
        original upper bound of *L or *U is inside the original range of *U
        then we move L to the next val, and we have a counter that will increase by one
        denoting that there is a lower bound for which we havent found the upper found yet
        if *L > *U then we end one range and move U to the right, so we decrease the counter that we have, showing
        that there is a lower bound for which we found their original upper bound. If counter = 0
        then all lower bounds have found their respective upper bound. Therefore we create a range
        with lower bound = first value parsed with *L, and ends with the last value we parsed on
        *U. If *L = *U is tricky, it could be either an isolated point, or mean that there are atleast
        2 ranges with same upper and lower bound. then if counter = 0, and next lower bound is not the same
        as *L then we know its an isolated point. Lastly we go through all Ranges created, calculate their
        sizes and add them.
         */
        while (i<init.size() && j<end.size()) {
            long lowVal = init.get(i);
            long upperVal = end.get(j);
            if(lowVal < upperVal){
                lastLowBound.add(lowVal);
                boundCounter++;
                i++;
            } else if(lowVal > upperVal){
                boundCounter--;
                if(boundCounter == 0){
                    disjointRanges.add(new Range(lastLowBound.poll(), upperVal));
                    lastLowBound.clear();
                }
                j++;
            } else {
                if(boundCounter == 0 && (i == init.size()-1 || lowVal != init.get(i+1))) rangesSize++;
                i++;
                j++;
            }
        }


        for(Range r: disjointRanges){
            rangesSize += r.upper() - r.lower() + 1;
        }
        System.out.println("Size of Rangers: " + rangesSize);
    }
    public boolean isFresh(long x, List<Range> ranges){
        for(Range r: ranges){
            if(r.isInBound(x)){
                return true;
            }
        }
        return false;
    }
}

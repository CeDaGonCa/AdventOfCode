package AdventofCode2025.Q8;

import AdventofCode2025.AOCReader;

import java.util.*;

public class Q8 extends AOCReader {
    public Q8() {
        super("Q8");
    }
    public static void main(String[] args) {
        Q8 obj = new Q8();
        obj.solve();
    }
    public void solve(){
        List<XYZcoord> coords = makeCoordList();
        PriorityQueue<Pairs> pairs = makePairs(coords);
        UnionFind unionFind = new UnionFind(coords.size());
        HashMap<XYZcoord,Integer> coordToIdx = new HashMap<>();
        for(int i = 0; i < coords.size(); i++){
            coordToIdx.put(coords.get(i),i);
        }
        solveP1(1000,coords,pairs,unionFind,coordToIdx);
        solveP2(pairs,unionFind,coordToIdx);

    }
    public void solveP1(int numberOfShortestConnections,List<XYZcoord> coords,PriorityQueue<Pairs> pairs,UnionFind unionFind,HashMap<XYZcoord,Integer> coordToIdx){
        for(int i = 0 ; i < numberOfShortestConnections;i++){
            Pairs pair = pairs.poll();
            assert pair != null;
            unionFind.union(coordToIdx.get(pair.coord1()),coordToIdx.get(pair.coord2()));
        }
        HashMap<Integer,Integer> sizes = new HashMap<>();
        for(int i = 0; i < coords.size(); i++){
            int tmp = unionFind.find(i);
            if(sizes.containsKey(tmp)){
                sizes.put(tmp,sizes.get(tmp)+1);
            } else {
                sizes.put(tmp,1);
            }
        }
        List<Integer> sizesList = new ArrayList<>(sizes.values().stream().toList());
        sizesList.sort(Collections.reverseOrder());
        long ret = 1;
        for(int i = 0; i < 3; i++){
            ret *= sizesList.get(i);
        }
        System.out.println("Multiplication of size of 3 largest circuits: " + ret);
    }
    public long getPairDistance(Pairs pair){
        long xdiff = pair.coord1().x() - pair.coord2().x();
        long ydiff = pair.coord1().y() - pair.coord2().y();
        long zdiff = pair.coord1().z() - pair.coord2().z();
        return (xdiff*xdiff) + (ydiff*ydiff) + (zdiff*zdiff);
    }
    public void solveP2(PriorityQueue<Pairs> pairs,UnionFind unionFind,HashMap<XYZcoord,Integer> coordToIdx){
        XYZcoord lastCoord1 = null;
        XYZcoord lastCoord2 = null;
        while(!pairs.isEmpty()){
            Pairs pair = pairs.poll();
            int idx1 = coordToIdx.get(pair.coord1());
            int idx2 = coordToIdx.get(pair.coord2());
            if(unionFind.find(idx1) != unionFind.find(idx2)){
                lastCoord1 = pair.coord1();
                lastCoord2 = pair.coord2();
                unionFind.union(coordToIdx.get(pair.coord1()),coordToIdx.get(pair.coord2()));
            }
        }
        assert lastCoord1 != null;
        assert lastCoord2 != null;
        long ret = lastCoord1.x()* lastCoord2.x();
        System.out.println("Multiplication of X factor of last connections: " + ret);
    }
    private List<XYZcoord> makeCoordList(){
        List<XYZcoord> coords = new ArrayList<>();
        for(String s: this.inputStrings){
            String[] tmp = s.split(",");
            coords.add(new XYZcoord(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2])));
        }
        return coords;
    }
    private PriorityQueue<Pairs> makePairs(List<XYZcoord> coords){
        PriorityQueue<Pairs> pairs = new PriorityQueue<>((Pair1,Pair2) -> Long.compare(getPairDistance(Pair1),getPairDistance(Pair2)));
        for(int i = 0; i < coords.size(); i++){
            for(int j = i+1; j < coords.size(); j++){
                pairs.add(new Pairs(coords.get(i), coords.get(j)));
            }
        }
        return pairs;
    }

}

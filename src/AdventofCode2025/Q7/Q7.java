package AdventofCode2025.Q7;

import AdventofCode2025.AOCReader;

import java.util.HashMap;

public class Q7 extends AOCReader {
    public Q7() {
        super("Q7");
    }
    public static void main(String[] args) {
        Q7 obj = new Q7();
        obj.solveP1();
        obj.solveP2();
    }
    public void solveP1(){
        Tachyon[][] manifold = makeManifold();
        int counter = 0;
        for(int i = 0; i < manifold.length; i++){
            for(int j = 0; j < manifold[i].length; j++){
                if(manifold[i][j] == Tachyon.START && i + 1 < manifold.length){
                    manifold[i+1][j] = Tachyon.BEAM;
                }else if(manifold[i][j] == Tachyon.EMPTY && i > 0 &&
                        (manifold[i-1][j] == Tachyon.START || manifold[i-1][j] == Tachyon.BEAM)){
                    manifold[i][j] = Tachyon.BEAM;
                } else if(manifold[i][j] == Tachyon.SPLITTER && manifold[i-1][j] == Tachyon.BEAM){
                    if(j > 0 && manifold[i][j-1] == Tachyon.EMPTY){
                        manifold[i][j-1] = Tachyon.BEAM;
                    }
                    if(j + 1 < manifold[i].length && manifold[i][j+1] == Tachyon.EMPTY){
                        manifold[i][j+1] = Tachyon.BEAM;
                    }
                    counter++;
                }
            }
        }
        System.out.println("Number of Tachyons: " + counter);
    }
    public Tachyon[][] makeManifold(){
        Tachyon[][] manifold = new Tachyon[this.inputStrings.size()][this.inputStrings.get(0).length()];
        for (int i = 0; i < this.inputStrings.size(); i++) {
            for (int j = 0; j < this.inputStrings.get(i).length(); j++) {
                char currentChar = this.inputStrings.get(i).charAt(j);
                if(currentChar == 'S'){
                    manifold[i][j]=Tachyon.START;
                }else if(currentChar == '.'){
                    manifold[i][j]=Tachyon.EMPTY;
                }else if(currentChar == '^'){
                    manifold[i][j]=Tachyon.SPLITTER;
                }
            }
        }
        return manifold;
    }
    public void solveP2(){
        Tachyon[][] manifold = makeManifold();
        HashMap<coordinates,Long> memory = new HashMap<>();
        for(int i = 0; i < manifold[0].length; i++){
            if(manifold[0][i] == Tachyon.START){
                System.out.println("Number of paths: "+ DFS(1,i,manifold,memory));
                return;
            }
        }
    }
    public long DFS(int posY, int posX, Tachyon[][] manifold, HashMap<coordinates,Long> memory){
        coordinates current = new coordinates(posX, posY);
        if(memory.containsKey(current)){
            return memory.get(current);
        }
        if(posX < 0 || posX >= manifold[0].length){
            return 0;
        }
        if(posY >= manifold.length){
            return 1;
        }
        long ret = 0;
        if(manifold[posY][posX] == Tachyon.SPLITTER){
            ret += DFS(posY,posX-1,manifold,memory);
            ret += DFS(posY,posX+1,manifold,memory);
        }else {
            manifold[posY][posX]=Tachyon.BEAM;
            ret += DFS(posY+1,posX,manifold,memory);
        }
        memory.put(current,ret);
        return ret;
    }
}

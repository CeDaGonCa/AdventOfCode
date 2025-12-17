package AdventofCode2025.Q4;

import AdventofCode2025.AOCReader;

import java.util.HashSet;

public class Q4 extends AOCReader {
    public Q4() {
        super("Q4");
    }
    public static void main(String[] args) {
        Q4 obj = new Q4();
        obj.solve();
    }
    public void solve(){
        // Change the input to a boolean matrix for simplicity
        boolean[][] grid = makeGrid();

        int counter = 0;
        // Part one
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] && countAround(i, j, grid) < 4) {
                    // if has less than 4 rolls around then remove it
                    counter++;
                }
            }
        }
        // For Part 1 we only need to remove them once, i.e. we don't need to change the matrix, just count
        // the number of points that can be removed.
        // could be made overall code a bit more efficient by combining it with part 2 so that the code doesn't
        // need to re-count all the rolls that can be removed on the first step
        // but left separated for readability/simplicity

        System.out.println("Part 1: ");
        System.out.println(counter);

        // Part Two
        counter = 0;
        HashSet<GridPosition> removed = new HashSet<>();
        do {
            removed.clear();
            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[i].length; j++){
                    if(grid[i][j] && countAround(i,j,grid) < 4){
                        removed.add(new GridPosition(i,j));
                        counter++;
                    }
                }
            }
            // remove all counter rolls from the grid
            removed.forEach(a->grid[a.x()][a.y()] = false);
        }while(!removed.isEmpty());
        // if removed is empty then there is no roll removed in a step
        // then we cannot remove any other roll
        System.out.println("Part 2: ");
        System.out.println(counter);
    }
    private int countAround(int x, int y,boolean[][] grid){
        int count = 0;
        // all positions around [x,y]
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(j == 0 && i == 0) continue;
                count += hasRoll(x+i,y+j,grid) ? 1 : 0;
            }
        }
        return count;
    }
    private boolean hasRoll(int x, int y, boolean[][] grid){
        // Checking if is a valid coordinate + if it has a roll
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length  && grid[x][y];
    }
    private boolean[][] makeGrid(){
        boolean[][] grid = new boolean[this.inputStrings.size()][this.inputStrings.get(0).length()];
        for(int i = 0; i < this.inputStrings.size(); i++){
            for(int j = 0; j < this.inputStrings.get(i).length(); j++){
                if(this.inputStrings.get(i).charAt(j) == '@'){
                    grid[i][j] = true;
                }
            }
        }
        return grid;
    }
}

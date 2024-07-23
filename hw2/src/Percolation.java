import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    public int N;
    public boolean[][] grid;
    public int numberOfOpenSites;
    private final WeightedQuickUnionUF uf;
    //private final WeightedQuickUnionUF backwash;
    private int xyTo1D(int row, int col) {
        return row *N + col;
    }
    private void Legel(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {throw new IllegalArgumentException();}
    }

    public Percolation(int N) {
        if (N < 1 ){throw new IllegalArgumentException();}
        this.N = N;
        this.grid = new boolean[N][N];
        this.numberOfOpenSites = 0;
        this.uf = new WeightedQuickUnionUF(N * N + 2);
        //this.backwash = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {uf.union(N*N, i);}//backwash.union(N*N, i);
        for(int i = N*(N-1);i < N*N;i++) {uf.union(N*N+1, i);}
    }
    public void open(int row, int col) {
        Legel(row, col);
        if(!grid[row][col]) {
            grid[row][col] = true;
            numberOfOpenSites++;
            int index = xyTo1D(row, col);
            if (row > 0 && grid[row-1][col]) {uf.union(index, xyTo1D(row-1, col));}
            if (row < N - 1 && grid[row+1][col]){uf.union(index, xyTo1D(row+1, col));}
            if (col > 0 && grid[row][col-1]) {uf.union(index, xyTo1D(row, col-1));}
            if (col < N - 1 && grid[row][col+1]){uf.union(index, xyTo1D(row, col+1));}
        }


    }
    public boolean isOpen(int row, int col) {
        Legel(row, col);
        return grid[row][col];
    }
    public boolean isFull(int row, int col) {
        Legel(row, col);
        return isOpen(row, col)&&uf.connected(xyTo1D(row, col), N*N);
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        for (int i = 0; i < N; i++) {
            if(isFull(N-1,i)) {return true;}
        }
        return false;
    }

}


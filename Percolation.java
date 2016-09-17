
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] maps;
	private int dim;
	private WeightedQuickUnionUF weiQuickUnionUF;
	   public Percolation(int n) {  // create n-by-n grid, with all sites blocked
		   if (n <= 0) {
			   throw new IllegalArgumentException();
		   }
		   maps = new int[n][n];
		   dim = n;
		   weiQuickUnionUF = new WeightedQuickUnionUF(n*n+2);
		   for (int i = 0; i < n; ++i) {
			   maps[i] = new int[n];
		   }
	   }         
	   public void open(int i, int j) {  // open site (row i, column j) if it is not open already
		   if (i < 1 || j < 1 || i > dim || j > dim) {
			   throw new IndexOutOfBoundsException();
		   }
		   if (maps[i-1][j-1] == 0) {
			   maps[i-1][j-1] = 1;
			   if (i == 1) {
				   weiQuickUnionUF.union((i-1)*dim+j-1+1, 0);
			   }
			   if (i == dim) {
				   weiQuickUnionUF.union((i-1)*dim+j-1+1, dim*dim+1);
			   }
			   if (j < dim) {
				   if (maps[i-1][j] == 1) {
					   weiQuickUnionUF.union((i-1)*dim+j-1+1, (i-1)*dim+j+1);
				   }
			   }
			   if (j > 1) {
				   if (maps[i-1][j-2] == 1) {
					   weiQuickUnionUF.union((i-1)*dim+j-1+1, (i-1)*dim+j-2+1);
				   }
			   }
			   if (i > 1) {
				   if (maps[i-2][j-1] == 1) {
					   weiQuickUnionUF.union((i-1)*dim+j-1+1, (i-2)*dim+j-1+1);
				   }
			   }
			   if (i < dim) {
				   if (maps[i][j-1] == 1) {
					   weiQuickUnionUF.union((i-1)*dim+j-1+1, (i)*dim+j-1+1);
				   }
			   }
		   }
		   
	   }      
	   public boolean isOpen(int i, int j) {  // is site (row i, column j) open?
		   if (i < 1 || j < 1 || i > dim || j > dim) {
			   throw new IndexOutOfBoundsException();
		   }
		   if (maps[i-1][j-1] == 1) {
			   return true;
		   } else {
			   return false;
		   }
			   
	   }   
	   
	   public boolean isFull(int i, int j) { // is site (row i, column j) full?
		   if (i < 1 || j < 1 || i > dim || j > dim) {
			   throw new IndexOutOfBoundsException();
		   }
		   if (!isOpen(i, j)) {
			   return false;
		   }
		   
		   if (weiQuickUnionUF.connected((i-1)*dim+j-1+1, 0)) {
			   return true;
		   }
		   
		   return false;
	   }   
	   public boolean percolates() { // does the system percolate?

			   
		   return weiQuickUnionUF.connected(0,dim*dim+1);
	   }            

	   public static void main(String[] args) {
		   Percolation p = new Percolation(6);
		   
		   p.open(1, 6);
		   p.open(2, 6);
		   p.open(3, 6);
		   p.open(4, 6);
		   p.open(5, 6);
		   p.open(5, 5);
		   p.open(4, 4);
		   System.out.println(p.isFull(4, 4));
		   
		   p.open(3, 4);
		   p.open(2, 4);
		   p.open(2, 3);
		   p.open(2, 2);
		   p.open(2, 1);
		   p.open(3, 1);
		   p.open(4, 1);
		   p.open(5, 1);
		   p.open(5, 2);
		   p.open(6, 2);
		   p.open(5, 4);
		 
		   System.out.println(p.percolates());
	   }  // test client (optional)
	}
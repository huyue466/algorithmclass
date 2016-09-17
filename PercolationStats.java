
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double meancnt;
	private double stdcnt;
	private double confiLo;
	private double confiHi;
	public PercolationStats(int n, int trials) { // perform trials independent experiments on an n-by-n grid
		if (n <= 0 || trials <= 0) {
			 throw new IllegalArgumentException();
		}
		double [] res = new double[trials]; 
		   for (int k = 0; k < trials; ++k) {
			   Percolation  p = new Percolation(n);
			   int cnt = 0;
			   while (!p.percolates()) {
				   int i = StdRandom.uniform(n) + 1;
				   int j = StdRandom.uniform(n) + 1;
				   boolean st = p.isOpen(i, j);
				   if (!st) {
					   p.open(i, j);
					   ++cnt;
				   }
			   }
			   res[k] = cnt/(double) (n*n);
			   meancnt = StdStats.mean(res);
			   stdcnt = StdStats.stddev(res);
			   confiLo = meancnt-1.96*stdcnt/Math.sqrt(trials);
			   confiHi = meancnt+1.96*stdcnt/Math.sqrt(trials);
		   }
	}
	public double mean() { // sample mean of percolation threshold
		
		return meancnt;
	}                        
	public double stddev() { // sample standard deviation of percolation threshold
		
		return stdcnt;
	}                      
	public double confidenceLo() { // low  endpoint of 95% confidence interval
		
		return confiLo; 
	}                
	public double confidenceHi() { // high endpoint of 95% confidence interval
		
		return confiHi; 
	}                

	public static void main(String[] args) {  // test client (described below)
		Integer n = Integer.valueOf(args[0]);
		Integer m = Integer.valueOf(args[1]);
		PercolationStats pstats = new PercolationStats(n, m);
		System.out.println("mean                    = " + pstats.mean());
		System.out.println("stddev                  = " + pstats.stddev());
		System.out.println("95% confidence interval = " + pstats.confidenceLo() + " " + pstats.confidenceHi());
	}  
}

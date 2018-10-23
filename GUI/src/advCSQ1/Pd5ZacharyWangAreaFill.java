package advCSQ1;

//name:
//date:

import java.util.Scanner;
import java.io.*;

public class Pd5ZacharyWangAreaFill {
	public static char[][] grid = null;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Filename: ");
		String filename = sc.next();
		grid = read(filename);
		display(grid);
		System.out.print("\nEnter ROW COL to fill from: ");
		int row = sc.nextInt();
		int col = sc.nextInt();
		fill(grid, row, col, grid[row][col]);
		display(grid);
		sc.close();
	}

	public static char[][] read(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner scanner = new Scanner(file);
		char[][] grid = new char[scanner.nextInt()][scanner.nextInt()];
		scanner.nextLine();
		int count = 0;
		while (scanner.hasNextLine())
			grid[count++] = scanner.nextLine().toCharArray();
		return grid;
	}

	public static void display(char[][] g) {
		for (char[] row : grid) {
			for (char c : row)
				System.out.print(c);
			System.out.println();
		}
	}

	public static void fill(char[][] g, int r, int c, char ch) // recursive method
	{
		return;
	}
}
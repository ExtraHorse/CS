

//name: Zachary Wang
//date: 10/23/18

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
		File file = new File("C:\\Users\\Zachary Wang\\Documents\\CS\\" + filename);
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

	public static void fill(char[][] g, int r, int c, char ch) {
		char current;
		try {
			current = g[r][c];
		} catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
		if(current != ch)
			return;
		g[r][c] = '*';
		fill(g, r + 1, c, ch);
		fill(g, r - 1, c, ch);
		fill(g, r, c + 1, ch);
		fill(g, r, c - 1, ch);
	}
}

/*
 * 
 * Filename: area.txt
xxxx............................
...xx...........................
..xxxxxxxxxxxx..................
..x.........xxxxxxx.............
..x...........0000xxxx..........
..xxxxxxxxxxxx0..000............
..xxxxxxxxx...0...00.....0000000
..........xx.......0000000000000
.....xxxxxxxxx........0.........
....xx.................00000....
....xx.....................00...
.....xxxxxxxxxxxxxxxxxx....00...
......................xx...00...
.......................xxxx00000
............................0000

Enter ROW COL to fill from: 0 1
****............................
...**...........................
..************..................
..*.........*******.............
..*...........0000****..........
..************0..000............
..*********...0...00.....0000000
..........**.......0000000000000
.....*********........0.........
....**.................00000....
....**.....................00...
.....******************....00...
......................**...00...
.......................****00000
............................0000

Filename: area.txt
xxxx............................
...xx...........................
..xxxxxxxxxxxx..................
..x.........xxxxxxx.............
..x...........0000xxxx..........
..xxxxxxxxxxxx0..000............
..xxxxxxxxx...0...00.....0000000
..........xx.......0000000000000
.....xxxxxxxxx........0.........
....xx.................00000....
....xx.....................00...
.....xxxxxxxxxxxxxxxxxx....00...
......................xx...00...
.......................xxxx00000
............................0000

Enter ROW COL to fill from: 0 8
xxxx****************************
...xx***************************
..xxxxxxxxxxxx******************
..x.........xxxxxxx*************
..x...........0000xxxx**********
..xxxxxxxxxxxx0..000************
..xxxxxxxxx...0...00*****0000000
..........xx.......0000000000000
.....xxxxxxxxx........0.........
....xx.................00000....
....xx.....................00...
.....xxxxxxxxxxxxxxxxxx....00...
......................xx...00...
.......................xxxx00000
............................0000

 */

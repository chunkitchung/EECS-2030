
/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.
 READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS

I hereby declare that all the work that was required to 
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution 
with anybody. I affirm that I have read and understood
 the Senate Policy on Academic honesty at 
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a 
result of committing plagiarism in this assignment.

BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS.

Full Name:Chun-Kit Chung
Student Number:217125329
Course Section:A
*/

package Assignment1;

import java.util.*;

/**
 * 
 * @author EECS2030 Team
 *
 */

public class Map {
	boolean[][] map;
	private int row;
	private int column;
	private boolean getCaught;

	/**
	 * This is the constructor that constructs the city map, which is a grid of row
	 * by column.
	 * 
	 * @param row    is the number of east-west streets of the city
	 * @param column is the number of north-south streets of the city
	 */
	public Map(int row, int column) {
		// Please implement the constructor
		this.row = row;
		this.column = column;
		this.map = new boolean[this.row][this.column];
	}

	/**
	 * This method checks the correctness of the input parameters. If the
	 * preconditions are not met an exception is thrown, otherwise depending to the
	 * direction, it calls one of the four recursive functions of goSouthWest,
	 * goSouthEast, goNorthWest and goNorthEast.
	 * 
	 * @param startRow is the starting row of the path
	 * @param startCol is the starting column of the path
	 * @param destRow  is the destination row
	 * @param destCol  is the destination column
	 * @param path     is the path that is constructed while the recursive method is
	 *                 called. In first round, it will be "".
	 * @return returns a string representing the path to the destination. The format
	 *         of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre the integer parameters should be in the range of the city grid.(i.e. [0,
	 *      N) if N is the number of east-west streets and [0, M) if M is the number
	 *      of north-south streets.)
	 * @exception IllegalArgumentException if any of the precondition did not meet.
	 */
	public String getPath(int startRow, int startCol, int destRow, int destCol, String path) {
		// Please complete this method
		// you should decide on what should be returned. This return statement is here
		// to avoid compiler error.
		// if statement to check if the starting and destination points are within the
		// map
		if (startRow < 0) {
			throw new IllegalArgumentException();
		} else if (startCol < 0) {
			throw new IllegalArgumentException();
		} else if (destRow < 0) {
			throw new IllegalArgumentException();
		} else if (destCol < 0) {
			throw new IllegalArgumentException();
		} else if (startRow > this.row) {
			throw new IllegalArgumentException();
		} else if (startCol > this.column) {
			throw new IllegalArgumentException();
		} else if (destRow > this.row) {
			throw new IllegalArgumentException();
		} else if (destCol > this.column) {
			throw new IllegalArgumentException();
		}

		// if statement to call one of the four methods, depending on the direction that
		// the car is moving
		if (startRow <= destRow && startCol <= destCol) {
			return goNorthEast(startRow, startCol, destRow, destCol, path);
		} else if (startRow <= destRow && startCol >= destCol) {
			return goSouthEast(startRow, startCol, destRow, destCol, path);
		} else if (startRow >= destRow && startCol >= destCol) {
			return goSouthWest(startRow, startCol, destRow, destCol, path);
		} else {
			return goNorthWest(startRow, startCol, destRow, destCol, path);
		}
	}

	/**
	 * This method returns a path from the source (startRow, startCol) to the
	 * destination (destRow, destCol). Please note that the returning path does not
	 * include the starting point.
	 * 
	 * @param startRow is the starting row of the path
	 * @param startCol is the starting column of the path
	 * @param destRow  is the destination row
	 * @param destCol  is the destination column
	 * @param path     is the path that is constructed while the recursive method is
	 *                 called. In first round, it will be "".
	 * @return returns a string representing the path to the destination. The format
	 *         of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow >= destRow </code> and
	 *      <code> startCol >= destCol </code>
	 */

	private String goSouthWest(int startRow, int startCol, int destRow, int destCol, String path) {
		// Please complete this method
		// you should decide on what should be returned. This return statement is here
		// to avoid compiler error.mnb
		if (startRow > destRow) { // checks if startRow > destRow
			startRow--; // go left
			path += " (" + startRow + "," + startCol + ")"; // adds point to path
			// continues to call the function until startCol == destCol
			return goSouthWest(startRow, startCol, destRow, destCol, path);
		} else if (startCol > destCol) { // checks if startCol > destCol
			startCol--;// go down
			path += " (" + startRow + "," + startCol + ")"; // adds point to path
			// continues to call the function until startCol == destCol
			return goSouthWest(startRow, startCol, destRow, destCol, path);
		}
		return path; // returns path from the source to the destination
	}

	/**
	 * This method returns a path from the source (startRow, startCol) to the
	 * destination (destRow, destCol). Please note that the returning path does not
	 * include the starting point.
	 * 
	 * @param startRow is the starting row of the path
	 * @param startCol is the starting column of the path
	 * @param destRow  is the destination row
	 * @param destCol  is the destination column
	 * @param path     is the path that is constructed while the recursive method is
	 *                 called. In first round, it will be "".
	 * @return returns a string representing the path to the destination. The format
	 *         of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow >= destRow </code> and
	 *      <code> startCol <= destCol </code>
	 */
	private String goSouthEast(int startRow, int startCol, int destRow, int destCol, String path) {
		// Please complete this method
		// you should decide on what should be returned. This return statement is here
		// to avoid compiler error.
		if (startRow < destRow) { // checks if startRow < destRow
			startRow++;// go right
			path += " (" + startRow + "," + startCol + ")"; // adds point to path
			// continues to call the function until startCol == destCol
			return goSouthEast(startRow, startCol, destRow, destCol, path);
		} else if (startCol > destCol) { // checks if startCol > destCol
			startCol--;// go down
			path += " (" + startRow + "," + startCol + ")"; // adds point to path
			// continues to call the function until startCol == destCol
			return goSouthEast(startRow, startCol, destRow, destCol, path);
		}
		return path; // returns path from the source to the destination
	}

	/**
	 * This method returns a path from the source (startRow, startCol) to the
	 * destination (destRow, destCol). Please note that the returning path does not
	 * include the starting point.
	 * 
	 * @param startRow is the starting row of the path
	 * @param startCol is the starting column of the path
	 * @param destRow  is the destination row
	 * @param destCol  is the destination column
	 * @param path     is the path that is constructed while the recursive method is
	 *                 called. In first round, it will be "".
	 * @return returns a string representing the path to the destination. The format
	 *         of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow <= destRow </code> and
	 *      <code> startCol >= destCol </code>
	 */
	private String goNorthEast(int startRow, int startCol, int destRow, int destCol, String path) {
		// Please complete this method
		// you should decide on what should be returned. This return statement is here
		// to avoid compiler error.
		if (startRow < destRow) { // checks if startRow < destRow
			startRow++;// go right
			path += " (" + startRow + "," + startCol + ")"; // adds point to path
			// continues to call the function until startCol == destCol
			return goNorthEast(startRow, startCol, destRow, destCol, path);
		} else if (startCol < destCol) { // checks if startCol < destCol
			startCol++;// go up
			path += " (" + startRow + "," + startCol + ")"; // adds point to path
			// continues to call the function until startCol == destCol
			return goNorthEast(startRow, startCol, destRow, destCol, path);
		}
		return path; // returns path from the source to the destination
	}

	/**
	 * This method returns a path from the source (startRow, startCol) to the
	 * destination (destRow, destCol). Please note that the returning path does not
	 * include the starting point.
	 * 
	 * @param startRow is the starting row of the path
	 * @param startCol is the starting column of the path
	 * @param destRow  is the destination row
	 * @param destCol  is the destination column
	 * @param path     is the path that is constructed while the recursive method is
	 *                 called. In first round, it will be "".
	 * @return returns a string representing the path to the destination. The format
	 *         of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow >= destRow </code> and
	 *      <code> startCol <= destCol </code>
	 */
	private String goNorthWest(int startRow, int startCol, int destRow, int destCol, String path) {
		// Please complete this method
		// you should decide on what should be returned. This return statement is here
		// to avoid compiler error.
		if (startRow > destRow) { // checks if startRow > destRow
			startRow--;// go left
			path += " (" + startRow + "," + startCol + ")"; // adds point to path
			// continues to call the function until startCol == destCol
			return goNorthWest(startRow, startCol, destRow, destCol, path);
		} else if (startCol < destCol) { // checks if startCol < destCol
			startCol++;// go up
			path += " (" + startRow + "," + startCol + ")"; // adds point to path
			// continues to call the function until startCol == destCol
			return goNorthWest(startRow, startCol, destRow, destCol, path);
		}
		return path; // returns path from the source to the destination
	}

	/**
	 * This method find a path from (startRow, startCol) to a border point of the
	 * city. Please note that the starting point should be included in the path.
	 * 
	 * @param startRow is the starting row of the path
	 * @param startCol is the starting column of the path
	 * @return is a path from (starting row, staring col) to a border point of the
	 *         city. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 */

	public String findPath(int startRow, int startCol) {
		// Please complete this method
		// you should decide on what should be returned. This return statement is here
		// to avoid compiler error.
		String path = "";
		path += " (" + startRow + "," + startCol + ")"; // starts path with starting point
		path = findPathHelper(startRow, startCol, path);
		if (this.getCaught == false) {// if getCaught == false meaning it escaped
			return path;
		} else {
			this.getCaught = false;// if getCaught == true meaning it got caught
			return findPath(startRow, startCol);
		}
	}

	public String findPathHelper(int startRow, int startCol, String path) {
		// checks if car escape
		if (startRow == (this.row - 1) || startCol == (this.column - 1) || startRow == 0 || startCol == 0) {
			this.getCaught = false;// if getCaught == false meaning it escaped
			return path;
		} else if (map[startRow][startCol] == true) {
			this.getCaught = true;// if getCaught == true meaning it got caught
			this.map = new boolean[this.row][this.column];
			return path;
		} else {// if it hasn't gotten caught or hasn't escaped

			map[startRow][startCol] = true; // keeps track of which intersection has been passed
			Random random = new Random();
			int randDirection = random.nextInt(4); // creates random direction for the car

			switch (randDirection) {
			case 0:
				startCol++; // go up
				path += " (" + startRow + "," + startCol + ")"; // adds point to path
				// continues to call the function until car is caught or has escaped
				return findPathHelper(startRow, startCol, path);
			case 1:
				startCol--; // go down
				path += " (" + startRow + "," + startCol + ")"; // adds point to path
				// continues to call the function until car is caught or has escaped
				return findPathHelper(startRow, startCol, path);
			case 2:
				startRow++; // go right
				path += " (" + startRow + "," + startCol + ")"; // adds point to path
				// continues to call the function until car is caught or has escaped
				return findPathHelper(startRow, startCol, path);
			default:
				startRow--; // go left
				path += " (" + startRow + "," + startCol + ")"; // adds point to path
				// continues to call the function until car is caught or has escaped
				return findPathHelper(startRow, startCol, path);
			}
		}
	}
} // end of class

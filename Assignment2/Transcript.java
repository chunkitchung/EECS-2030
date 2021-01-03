package Assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;

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
Student Number: 217125329
Course Section: A
*/

/**
 * This class generates a transcript for each student, whose information is in
 * the text file.
 * 
 *
 */

public class Transcript {
	private ArrayList<Object> grade = new ArrayList<Object>();
	private File inputFile;
	private String outputFile;

	/**
	 * This the the constructor for Transcript class that initializes its instance
	 * variables and call readFie private method to read the file and construct
	 * this.grade.
	 * 
	 * @param inFile  is the name of the input file.
	 * @param outFile is the name of the output file.
	 */
	public Transcript(String inFile, String outFile) {
		inputFile = new File(inFile);
		outputFile = outFile;
		grade = new ArrayList<Object>();
		this.readFile();

	}// end of Transcript constructor

	/**
	 * This method reads a text file and add each line as an entry of grade
	 * ArrayList.
	 * 
	 * @exception It throws FileNotFoundException if the file is not found.
	 */
	private void readFile() {
		Scanner sc = null;
		try {
			sc = new Scanner(inputFile);
			while (sc.hasNextLine()) {
				grade.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	} // end of readFile

	/**
	 * This method creates and returns an ArrayList, whose element is an object of
	 * class Student. The object at each element is created by aggregating ALL the
	 * information, that is found for one student in the grade Arraylist of class
	 * Transcript. (i.e. if the text file contains information about 9 students,
	 * then the array list will have 9 elements.
	 * 
	 */
	public ArrayList<Student> buildStudentArray() {
		/*
		 * Declare and instantiate all data types used to create objects
		 */
		// attributes for student
		ArrayList<Student> listOfStudents = new ArrayList<Student>();
		ArrayList<Double> studentGrades = new ArrayList<Double>();
		ArrayList<Integer> weights = new ArrayList<Integer>();
		String name = "";
		String studentID = "";
		// attributes for course
		int credit = 0;
		String code = "";
		ArrayList<Course> courseTaken = new ArrayList<Course>();
		// helper
		String input = "";
		String temp = "";
		boolean existsStudent = false;
		int sep;
		ArrayList<Object> errors = new ArrayList<Object>();
		// attribute for assessment
		ArrayList<Assessment> assignments = new ArrayList<Assessment>();
		// for every line scanned
		for (Object o : this.grade) {
			input = (String) o;
			if (validFormat(input) && input.length() > 15) {
				// reset values for every line
				existsStudent = false;
				assignments = new ArrayList<Assessment>();
				courseTaken = new ArrayList<Course>();
				studentGrades = new ArrayList<Double>();
				weights = new ArrayList<Integer>();

				// all course codes have length 8 (4 letters + 4 digits)
				code = input.substring(0, 8);
				// after the first comma, the course credit is of length 1
				credit = Integer.parseInt(input.substring(9, 10));
				// the student id after the second comma is of length 4 (4 digits)
				studentID = input.substring(11, 15);
				// the name is at the end of input
				name = input.substring(input.lastIndexOf(',') + 1, input.length());
				// this loop collects the grades and weights
				for (int i = 15; i < input.length(); i++) {
					// this if statement adds weight
					if ((input.charAt(i) == 'P' || input.charAt(i) == 'E') && Character.isDigit(input.charAt(i + 1))) {
						sep = 0;// Separator
						// this case is for double digit weights
						if (input.charAt(i + 3) == '(') {
							sep = i + 3;
						}
						// this case is for single digit weights
						else {
							sep = i + 2;
						}
						assignments.add(
								Assessment.getInstance(input.charAt(i), Integer.parseInt(input.substring(i + 1, sep))));
						weights.add(Integer.parseInt(input.substring(i + 1, sep)));

					}
					// this if statement adds the grade in parentheses
					else if (input.charAt(i) == '(') {
						i++;
						temp = "";
						while (input.charAt(i) != ')') {
							temp += input.charAt(i);
							i++;
						}
						studentGrades.add(Double.parseDouble(temp));
					}

				} // end of for loop that collecting weights and grades

				// end of collecting values

				// this for loop checks if student already exists in the list, then simply adds
				// a course to existing student
				if (listOfStudents != null) {
					for (Student s : listOfStudents) {
						if (s.getStudentID().compareTo(studentID) == 0) {
							try {
								existsStudent = true;
								s.addCourse(new Course(code, assignments, credit));
								s.addGrade(studentGrades, weights);
							} catch (InvalidTotalException e) {
								errors.add((e.getMessage()));
							}
							break;
						}
					} // end for loop for creating courses for existing students in list
				}
				// this if statement checks the state if student is 'new'
				if (existsStudent == false) {
					try {
						// create a course and student. then add the grades for that course
						// and adds the student to the list
						courseTaken.add(new Course(code, assignments, credit));
						Student s = new Student(studentID, name, courseTaken);
						s.addGrade(studentGrades, weights);
						listOfStudents.add(s);
					} catch (InvalidTotalException e) {
						errors.add((e.getMessage()));
					}
				} // end of if statement
			} // end if statement that checks format
			else {
				if (input.length() > 7) {
					errors.add("Invalid entry format for Name: "
							+ input.substring(input.lastIndexOf(',') + 1, input.length()) + ",Course: "
							+ input.substring(0, input.indexOf(',')));
				} else {
					errors.add("Invalid entry format");
				}
			}
		} // end big for loop that computes each line that was scanned
		for (Object o : errors) {
			grade.add(o);
		}
		return listOfStudents;
	} // end of buildStudentArray

	/**
	 * This method prints the transcript for the given list of students.
	 * 
	 * @param ArrayList<Students> is the list of students.
	 */
	public void printTranscript(ArrayList<Student> students) {
		sort(students);
		String output = "";
		String code = "";
		double grade = 0.0;
		// collects the exception statements in the list
		for (Object o : this.grade) {
			String s = (String) o;
			if (s.contains(",") == false)
				output += (s + "\n");
		}
		for (Student s : students) {
			output += (s.getName() + "\t" + s.getStudentID() + "\n");
			output += ("--------------------\n");
			for (int i = 0; i < s.getCourseList().size(); i++) {
				code = s.getCourseList().get(i).getCode();
				grade = s.getFinalGrade().get(i);
				output += (code + "\t" + grade + "\n");
			}
			output += ("--------------------\n");
			output += ("GPA: " + s.weightedGPA() + "\n\n");
		} // end for
			// print to txt file
		printToText(output);

	} // end of printTranscript

	/**
	 * sort the list of students based on their student number
	 * 
	 * @param students
	 */
	private void sort(ArrayList<Student> students) {
		for (int i = 0; i < students.size(); i++) {
			for (int j = 0; j < i; j++) {
				Student temp;
				int first = Integer.parseInt(students.get(i).getStudentID());
				int second = Integer.parseInt(students.get(j).getStudentID());
				if (first < second) {
					temp = students.get(i);
					students.set(i, students.get(j));
					students.set(j, temp);
				}
			}
		}
	}

	/**
	 * takes output string and prints it to a txt file
	 * 
	 * @param output
	 */
	private void printToText(String output) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(outputFile);
			writer.write(output);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// end printToText()

	/**
	 * This method checks if the input is valid
	 * 
	 * @param line
	 * @return
	 */
	private boolean validFormat(String line) {
		return (line.charAt(8) == ',' && line.charAt(10) == ',' && line.charAt(15) == ',');

	}

} // end of Transcript

class Assessment {

	private char type;
	private int weight;

	private Assessment() {
		type = ' ';
		weight = 0;
	}

	private Assessment(char type, int weight) {
		this.type = type;
		this.weight = weight;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public static Assessment getInstance(char type, int weight) {
		return new Assessment(type, weight);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Assessment assessment = (Assessment) obj;
		// returns true if the instance variables have the same value
		if (assessment.type == this.type && assessment.weight == this.weight) {
			return true;
		}
		return false;
	}
}

class Course {

	private String code;
	private ArrayList<Assessment> assignment;
	private double credit;

	public Course() {
		code = "";
		assignment = new ArrayList<Assessment>();
		credit = 0.0;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getCredit() {
		return credit;
	}

	public Course(String code, ArrayList<Assessment> assignment, double credit) {
		this.code = code;
		ArrayList<Assessment> assignmentCopy = new ArrayList<Assessment>();

		for (Assessment assessment : assignment) { // deep copy of assignments
			assignmentCopy.add(Assessment.getInstance(assessment.getType(), assessment.getWeight()));
		}
		this.assignment = assignmentCopy;
		this.credit = credit;
	}

	public ArrayList<Assessment> getAssignment() {
		return assignment;
	}

	public Course(Course course) {
		code = new String(course.code);
		ArrayList<Assessment> aCopy = new ArrayList<Assessment>();
		for (Assessment a : course.getAssignment()) {
			aCopy.add(Assessment.getInstance(a.getType(), a.getWeight()));
		}
		this.assignment = aCopy;
		credit = course.credit;
	}

	public boolean equals(Object obj) {
		Course other = (Course) obj;
		if (other != null && other instanceof Course) {
			// returns true if the instance variables have the same value
			if (this.code == other.code && this.assignment == other.assignment && this.credit == other.credit) {
				return true;
			}
		}
		return false;
	}
}

class InvalidTotalException extends Exception {
	public InvalidTotalException() {
		super();
	}

	public InvalidTotalException(String message) {
		super(message);
	}
}

class Student {
	private String studentID;
	private String name;
	private ArrayList<Course> courseTaken;
	private ArrayList<Double> finalGrade;

	/**
	 * Class that defines a student (default constructor)
	 */
	public Student() {
		studentID = "";
		name = "";
		courseTaken = new ArrayList<Course>();
		finalGrade = new ArrayList<Double>();
	}// end of Student() default constructor

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Course> getCourseTaken() {
		return courseTaken;
	}

	public void setCourseTaken(ArrayList<Course> courseTaken) {
		this.courseTaken = courseTaken;
	}

	public ArrayList<Double> getFinalGrade() {
		ArrayList<Double> finalGradeCopy = new ArrayList<Double>();
		for (Double c : finalGrade) {
			finalGradeCopy.add((c));
		}
		return finalGradeCopy;
	}

	public void setFinalGrades(ArrayList<Double> finalGrade) {
		for (Double c : finalGrade) {
			this.finalGrade.add((c));
		}
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public ArrayList<Course> getCourseList() {
		ArrayList<Course> courseTakenCopy = new ArrayList<Course>();
		for (Course c : courseTaken) {
			courseTakenCopy.add(new Course(c));
		}
		return courseTakenCopy;
	}

	public void setCourseList(ArrayList<Course> courseList) {
		for (Course c : courseList) {
			this.courseTaken.add(new Course(c));
		}
	}

	/**
	 * Class that defines a student (overloaded constructor)
	 * 
	 * @param String            studentID is the student's ID
	 * @param String            name is the student's name
	 * @param ArrayList<Course> courseTaken is the course taken by student
	 */
	public Student(String studentID, String name, ArrayList<Course> courseTaken) {
		this.studentID = studentID;
		this.name = name;
		ArrayList<Course> courseTakenCopy = new ArrayList<Course>();
		for (Course c : courseTaken) {
			courseTakenCopy.add(new Course(c));
		}
		this.courseTaken = courseTakenCopy;
		finalGrade = new ArrayList<Double>();
	}// end of Student overloaded constructor

	/**
	 * This method gets an array list of the grades and their weights, computes the
	 * true value of the grade based on its weight and add it to finalGrade
	 * attribute. In case the sum of the weight was not 100, or the sum of grade was
	 * greater 100, it throws InvalidTotalException, which is an exception that
	 * should be defined by you.
	 * 
	 * @param ArrayList<Double>  grades is the student's grades
	 * @param ArrayList<Integer> weights is the student's weight of that grade
	 * @exception it throws InvalidTotalException if sum of weight or grade is
	 *               greater or less than 100.
	 */
	public void addGrade(ArrayList<Double> grades, ArrayList<Integer> test) throws InvalidTotalException {
		int sumOfWeight = 0;
		double sumOfGrade = 0;
		for (int i = 0; i < test.size(); i++) { // calculates total weight
			sumOfWeight += test.get(i);
		}
		for (int i = 0; i < test.size(); i++) { // calculates grade
			sumOfGrade += grades.get(i) * (test.get(i) * 0.01);
		}
		/*
		 * following segment of code is to round to one decimal place
		 */
		int round = (int) Math.pow(10, 1);
		sumOfGrade = (double) Math.round(sumOfGrade * round) / round;
		finalGrade.add(sumOfGrade);
		if (sumOfWeight != 100 || sumOfGrade > 100) {
			throw new InvalidTotalException("INVALID TOTAL: " + name + "'s Sum of weights not 100 for "
					+ courseTaken.get(courseTaken.size() - 1).getCode() + "!!!"); // throw exception
		}
		if (sumOfGrade > 100) {
			throw new InvalidTotalException("INVALID TOTAL" + name + "'s Sum of grades not 100 for "
					+ courseTaken.get(courseTaken.size()) + "!!!"); // throw exception
		}

	}// end of addGrade()

	/**
	 * This method that computes the GPA.
	 */
	public double weightedGPA() {
		int totalCredits = 0;
		int gradePoint = 0;
		double totalPoints = 0;
		double GPA = 0.0;
		for (int k = 0; k < finalGrade.size(); k++) {
			totalCredits += courseTaken.get(k).getCredit(); // calculates total credits received
			if (finalGrade.get(k) >= 90) { // if statement for specific grade point corresponding to grade received
				gradePoint = 9;
				totalPoints += gradePoint * courseTaken.get(k).getCredit(); // calculates total points received
			} else if (finalGrade.get(k) >= 80 && finalGrade.get(k) <= 89.99) {
				gradePoint = 8;
				totalPoints += gradePoint * courseTaken.get(k).getCredit();
			} else if (finalGrade.get(k) >= 75 && finalGrade.get(k) <= 79.99) {
				gradePoint = 7;
				totalPoints += gradePoint * courseTaken.get(k).getCredit();
			} else if (finalGrade.get(k) >= 70 && finalGrade.get(k) <= 74.99) {
				gradePoint = 6;
				totalPoints += gradePoint * courseTaken.get(k).getCredit();
			} else if (finalGrade.get(k) >= 65 && finalGrade.get(k) <= 69.99) {
				gradePoint = 5;
				totalPoints += gradePoint * courseTaken.get(k).getCredit();
			} else if (finalGrade.get(k) >= 60 && finalGrade.get(k) <= 64.99) {
				gradePoint = 4;
				totalPoints += gradePoint * courseTaken.get(k).getCredit();
			} else if (finalGrade.get(k) >= 55 && finalGrade.get(k) <= 59.99) {
				gradePoint = 3;
				totalPoints += gradePoint * courseTaken.get(k).getCredit();
			} else if (finalGrade.get(k) >= 50 && finalGrade.get(k) <= 54.99) {
				gradePoint = 2;
				totalPoints += gradePoint * courseTaken.get(k).getCredit();
			} else if (finalGrade.get(k) >= 47 && finalGrade.get(k) <= 49.99) {
				gradePoint = 1;
				totalPoints += gradePoint * courseTaken.get(k).getCredit();
			} else {
				gradePoint = 0;
				totalPoints += gradePoint * courseTaken.get(k).getCredit();
			}
		}
		GPA = (totalPoints / totalCredits); // calculation of GPA
		/*
		 * following segment of code is to round to one decimal place
		 */
		int round = (int) Math.pow(10, 1);
		GPA = (double) Math.round(GPA * round) / round;
		return GPA;

	}// end of weightedGPA()

	public void addCourse(Course course) {
		courseTaken.add(course);
	}

}// end of Student

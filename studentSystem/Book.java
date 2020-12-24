package studentSystem;



public class Book implements Comparable<Book>{
		
		
		/**
		 * The yearPublished field is an int 
		 * that holds the book's year when it is published 
		 */
		private int yearPublished;
		
		/**
		 * The title field 
		 * references a String object that holds the title of the book
		 */
		private String title;
		
		
		/**
		 * The course that this book for, e.g., "EECS2030" 
		 * 
		*/
		private  String courseCode; 
		
		
		private int edition;
		
	
		/**
		 * custom constructor 
		 * Initializes this book to have specific values for 
		 * its fields
		 *  
		 * @param title  the book title 
		 * @param yearPublished the year of book title
		 * @param price  price of this book 
		 * 
		 */
		public Book(String title, int ed, int yearPublished, String courseID) {
			
			this.yearPublished=yearPublished;
			this.title=title;
			this.edition = ed;
			this.courseCode = courseID;
			
		}

		
			
		/**
		 * Returns the year  (yearPublished) field value 
		 * @return the year published field value of this book
		 */
		public int getyearPublished()
	    {
	        return this.yearPublished;
	    }
		
		
	    /**
	     *  Returns the  title (title) field value 
		 * @return the title field value of this book
	     */
	    public String gettitle ()
	    {
	        return this.title;
	    }
	    
		
		/**
		 * Returns the price value of this book .
		 * 
		 * @return the  price value of this book
		 */
		public String getCourseCode() {
			return this.courseCode;
		}


		public String toString() {
			return "(" + this.title + ", ed:" + this.edition + ", " + this.yearPublished + ", course:" + this.courseCode + ")";
		}
		
		 
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((courseCode == null) ? 0 : courseCode.hashCode());
			result = prime * result + edition;
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			result = prime * result + yearPublished;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Book other = (Book) obj;
			if (courseCode == null) {
				if (other.courseCode != null)
					return false;
			} else if (!courseCode.equals(other.courseCode))
				return false;
			if (edition != other.edition)
				return false;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			if (yearPublished != other.yearPublished)
				return false;
			return true;
		}

		public int compareTo(Book object) {
			if (this.getyearPublished() > object.getyearPublished()) {
				return 1;
			}
			if (this.getyearPublished() < object.getyearPublished()) {
				return -1;
			}
			else {
				return this.getCourseCode().compareTo(object.getCourseCode());
			}
		}
		/**
		 * This class defines logic equality of Book objects, as follows:
		 * two books are considered equal to each other if they have the same year, same title, 
		 * same course code and same edition. 
		 */
		 
		

		/**
		 * This class defines the natural ordering of Book objects, as follows:
		 * When 'this' book is compared with the specified book, 
		 * 'this' book is "less than" the specified book if its publishing year is earlier than that of the specified book.
		 * and is "greater than" the specified book if the publishing year is after that of the specified book.
		 *
		 * If the years are the same, then they are further compared based on lexicographical ordering of courseCode.
		 * 'this' book is "less than" the specified book if its courseCode lexicographically precedes that of the specified book,
		 * and is "greater than" the specified book if its courseCode lexicographically follows that of the specified book.
		 * E.g., "EECS1710" lexicographically precedes "EECS1720", which in turn precedes "EECS2030"
		 * 
		 * The result is 0 if the courseCodes are (also) equal.
		 */
		
		
		 
	}

		


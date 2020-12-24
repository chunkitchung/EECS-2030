package studentSystem;

import java.util.Objects;

public class Prof implements Comparable<Prof> {
	private String name;
	private String id;
	private int year; // year of employment

	public Prof(String name, String id, int year) {
		this.name = name;
		this.id = id;
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String toString() {
		return "[name:" + this.name + ", id:" + this.id + ", year:" + this.year + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + year;
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
		Prof other = (Prof) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	public int compareTo(Prof object) {
 		if (this.year > object.year) {
			return 1;
		}
		if (this.year < object.year) {
			return -1;
		}
		else {
			return this.id.compareTo(object.id);
		}
	}

	/**
	 * this class defines the logic equality of Prof objects, as follows: / two
	 * profs are considered equal to each other if they have the same id and same
	 * year of employment.
	 **/

	/**
	 * This class defined the natural ordering of Prof objects, as follows: When
	 * 'this' Prof is compared with the specified Prof, 'this' Prof is "less than"
	 * the specified Prof if his year of employment is less than that of the
	 * specified Prof, and is "greater than" the specified Prof if his year of
	 * employment is greater than that of the specified Prof. If the years are the
	 * same, then they are further compared based on their ids. 'this' Prof is "less
	 * than" the specified Prof if his id lexicographically precedes that of the
	 * specified Prof. and is "greater than" the specified Prof if his id
	 * lexicographically follows that of the specified Prof. E.g., "yu1719"
	 * lexicographically precedes "yu1765", which precedes "yu4203".
	 * 
	 * The comparison result is zero if the ids are (also) equal.
	 * 
	 * 
	 **/

}

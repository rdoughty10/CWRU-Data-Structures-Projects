
public class Course {
	
	//Class fields
	private String courseID;
	private String courseName;
	private int capacity;
	
	//constructor for a course --> allows creation of course with ID, name, and capacity
	public Course(String courseID, String courseName, int capacity) {
		if(capacity < 0) {
			throw new IllegalArgumentException("Capacity is too small!");
		}
		if(courseID==null) {
			throw new IllegalArgumentException("No Course ID");
		}
		if(courseName==null) {
			throw new IllegalArgumentException("No Course Name");
		}
		this.courseID = courseID;
		this.courseName = courseName;
		this.capacity = capacity;
	}

	//getters and setters (I don't actually use all of them but that's okay)
	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}

package heap_organizers;

/**
 * This class creates songs for the Tasklist class.
 * All task have a task name and date.
 * 
 * @author Ryan
 */
public class Task implements Comparable<Task>{

	/**
	 * Stores the name of the task.
	 */
	private String taskName;
	
	/**
	 * Stores the date for the task.
	 */
	private String date;
	
	/**
	 * Creates a new task with given name and date.
	 * @param n The name of the new task.
	 * @param d The date for the new task.
	 */
	public Task(String n, String d){
		taskName = n;
		date = d;
	}
	
	/**
	 * Creates a new task from an old task with a new date.
	 * @param t The old task to create the new one from.
	 * @param d The new date for the task.
	 */
	public Task(Task t, String d){
		taskName = t.taskName;
		date = d;
	}

	/**
	 * Compares two tasks based on their date then title.
	 * @param The task to compare this to.
	 * @return An int, negative if they are in order, 0 if equal, positive if out of order.
	 */
	@Override
	public int compareTo(Task t) {
		
		int thisMonth = 0;
		int tMonth = 0; 
		int thisDay = 0;
		int tDay = 0;
		int thisYear = 0;
		int tYear = 0;
		String thisTime = (date.substring(date.length() - 5, date.length()));
		String tTime = (t.date.substring(t.date.length() - 5, t.date.length()));
		int first = 0;
		int second = 0;
		
		// gets the month, day, and year values for this Task
		for(int i = 0; i < date.length(); i++){
			if(date.charAt(i) == '/' && i < 3){
				thisMonth = Integer.valueOf(date.substring(0, i));
				first = i;
			}
			else if(date.charAt(i) == '/' && i < 6){
				thisDay = Integer.valueOf(date.substring(first + 1, i));
				second = i;
			}
			else if(date.charAt(i) == ' '){
				thisYear = Integer.valueOf(date.substring(second + 1, i));
			}
		}
		
		// gets the month, day, and year values for Task t
		for(int i = 0; i < t.date.length(); i++){
			if(t.date.charAt(i) == '/' && i < 3){
				tMonth = Integer.valueOf(t.date.substring(0, i));
				first = i;
			}
			else if(t.date.charAt(i) == '/' && i < 6){
				tDay = Integer.valueOf(t.date.substring(first + 1, i));
				second = i;
			}
			else if(t.date.charAt(i) == ' '){
				tYear = Integer.valueOf(t.date.substring(second + 1, i));
				
			}
		}
		
		// compares the different values to determine which is soonest
		if(thisYear != tYear)
			return -(thisYear - tYear);
		if(thisMonth != tMonth)
			return -(thisMonth - tMonth);
		if(thisDay != tDay)
			return -(thisDay - tDay);
		if(thisTime.compareTo(tTime) != 0)
			return -thisTime.compareTo(tTime);
		return -this.taskName.compareTo(t.taskName);
	}
	
	/**
	 * Gets a string with this task's name and date.
	 * @return The String with the task's name and date.
	 */
	@Override
	public String toString(){
		return taskName +" (" +date +")";
	}
}

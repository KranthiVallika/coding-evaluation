package com.aa.act.interview.org;



import java.util.Optional;

public abstract class Organization {

	// root position to hold the entire organization structure
	private Position root;

	// static id to keep tracking of the identification numbers
	private static int id;

	public Organization() {
		root = createOrganization();
	}

	protected abstract Position createOrganization();

	/**
	 * hire the given person as an employee in the position that has that title
	 *
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */
	public Optional<Position> hire(Name person, String title) {
		addNewHire(root, person, title);
		displayDetails(root, person, title);
		return Optional.of(root);
	}

	/**
	 * Adds a new hire
	 * 
	 * @param position
	 * @param person
	 * @param title
	 */
	private void addNewHire(Position position, Name person, String title) {
		if (position.getTitle().equals(title)) {
			position.setEmployee(Optional.of(new Employee(++id, new Name(person.getFirst(), person.getLast()))));
		}

	}

	/**
	 * Displays details
	 * 
	 * @param pos
	 * @param person
	 * @param title
	 */
	private void displayDetails(Position pos, Name person, String title) {
		for (Position p : pos.getDirectReports()) {
			addNewHire(p, person, title);
			displayDetails(p, person, title);
		}
	}

	@Override
	public String toString() {
		return printOrganization(root, "");
	}

	/**
	 * Prints organization info
	 * 
	 * @param pos
	 * @param prefix
	 * @return
	 */
	private String printOrganization(Position pos, String prefix) {
		StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
		for (Position p : pos.getDirectReports()) {
			sb.append(printOrganization(p, prefix + "\t"));
		}
		return sb.toString();
	}
}
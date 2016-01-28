package examples.neo4j.domain;

public class User {
	public enum GENDER {
		MALE, FEMAIL
	}

	private long id;
	private String name;
	private GENDER gender;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GENDER getGender() {
		return gender;
	}

	public void setGender(GENDER gender) {
		this.gender = gender;
	}

}

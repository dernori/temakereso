package temakereso.helper;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TopicStatus {
	
	OPEN("szabad"),
	RESERVED("foglalt"),
	DONE("elkészített");
	
	private String name;
	
	private TopicStatus(String name) {
		this.name = name;
	}
	
	public String getId() {
		return name();
	}
	
	public String getName() {
		return name;
	}

}

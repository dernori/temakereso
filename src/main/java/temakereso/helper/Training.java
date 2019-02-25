package temakereso.helper;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Training {

    CORRESPONDANCE("levelező tagozat"),
    FULL_TIME("nappali tagozat"),
    PART_TIME("esti tagozat");

    private String name;

    Training(String name) {
        this.name = name;
    }

    public String getId() {
        return name();
    }

    public String getName() {
        return name;
    }
}

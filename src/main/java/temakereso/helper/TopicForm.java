package temakereso.helper;

import lombok.Data;

@Data
public class TopicForm extends Form {

    private String name;
    private String code;
    private String training;
    private String major;
    private String supervisorName;
    private String supervisorWorkplace;
    private String supervisorTitle;
    private String title;
    private String description;
    private String date;

    @Override
    public String getFileName() {
        return (name != null ? name + " - " : "") + (level == FormLevel.BSC_FORM ? "Bsc" : "Msc") + " témabejelentő.doc";
    }
}

package temakereso.helper;

import lombok.Data;

@Data
public class ConsultationForm extends Form {

    private String name;
    private String code;
    private String training;
    private String supervisorName;
    private String title;

    @Override
    public String getFileName() {
        return (name != null ? name + " - " : "") + (level == FormLevel.BSC_FORM ? "Bsc" : "Msc") + " konzultációs lap.doc";
    }
}

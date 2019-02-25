package temakereso.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.Map;

@Data
public class Form {

    private FormType type = FormType.BSC_FORM;
    private String name;
    private String neptun;    // nappali vagy esti
    private String mode;    // programtervező informatikus BSc
    private String major;
    private String supervisorName;
    private String supervisorWorkplace;
    private String supervisorTitle;
    private String title;
    private String description;
    private String date;

    public Map<String, String> convertToMap() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this, Map.class);
    }
}

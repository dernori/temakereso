package temakereso.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.Map;

@Data
public abstract class Form {

    protected FormLevel level = FormLevel.BSC_FORM;

    public Map<String, String> convertToMap() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this, Map.class);
    }

    public abstract String getFileName();
}

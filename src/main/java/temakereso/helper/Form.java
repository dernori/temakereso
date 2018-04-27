package temakereso.helper;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class Form {
	
	private FormType type = FormType.BSC_FORM;
	private String name;
	private String neptun;	// nappali vagy esti
	private String mode;	// programtervező informatikus BSc
	private String major;
	private String supervisorName;
	private String supervisorWorkplace;
	private String supervisorTitle;
	private String title;
	private String description;
	private String date;
	
	public Map<String, String> convertToMap() {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> reportMap = mapper.convertValue(this, Map.class);
		return reportMap;
	}
}

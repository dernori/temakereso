package temakereso.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import temakereso.entity.Department;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorInputDto {

    private String title;

    private Department department;

    private String workplace;

    private String phone;

    private String website;

    private String room;

    private String officeHours;

    private Boolean external;

}

package temakereso.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import temakereso.entity.Department;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDto {

    private Long id;

    private String name;

    private String title;

    private Department department;

    private String workplace;

    private String phone;

    private String website;

    private String room;

    private String officeHours;

    private Boolean external;

    private Boolean confirmed;

    private Boolean deleted;

    private AccountDto account;

}

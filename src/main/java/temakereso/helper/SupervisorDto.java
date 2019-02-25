package temakereso.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDto {

    private Long id;

    private String name;

    private String title;

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

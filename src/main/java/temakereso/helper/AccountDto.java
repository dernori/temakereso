package temakereso.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import temakereso.entity.Role;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;

    private String email;

    private String name;

    private String username;

    private List<Role> roles;

}

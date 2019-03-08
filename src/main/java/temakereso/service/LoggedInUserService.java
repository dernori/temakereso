package temakereso.service;

import temakereso.helper.AccountDto;
import temakereso.helper.StudentDto;
import temakereso.helper.SupervisorDto;

public interface LoggedInUserService {

    AccountDto getLoggedInUser();

    StudentDto getLoggedInStudent();

    SupervisorDto getLoggedInSupervisor();

    // TODO admin

}

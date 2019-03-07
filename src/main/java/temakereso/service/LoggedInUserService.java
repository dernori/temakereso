package temakereso.service;

import temakereso.helper.StudentDto;
import temakereso.helper.SupervisorDto;

public interface LoggedInUserService {

    Long getLoggedInUser();

    StudentDto getLoggedInStudent();

    SupervisorDto getLoggedInSupervisor();

    // TODO admin

}

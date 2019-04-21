package temakereso.service;

import temakereso.helper.AccountDto;
import temakereso.helper.StudentDto;
import temakereso.helper.SupervisorDto;

public interface LoggedInUserService {

    /**
     * Returns the account data of the logged in user.
     *
     * @return account data of the logged in user
     */
    AccountDto getLoggedInUser();

    /**
     * Returns the data of the logged in student.
     *
     * @return data of the logged in student
     */
    StudentDto getLoggedInStudent();

    /**
     * Returns the data of the logged in supervisor.
     *
     * @return data of the logged in supervisor
     */
    SupervisorDto getLoggedInSupervisor();

}

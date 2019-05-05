package temakereso.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import temakereso.entity.Supervisor;
import temakereso.service.implementation.SupervisorServiceImplementation;

@RunWith(MockitoJUnitRunner.class)
public class SupervisorServiceTest {

    private static Supervisor supervisorWithId;

    @InjectMocks
    private SupervisorServiceImplementation supervisorService;

    @Before
    public void setUp() {
        supervisorWithId = new Supervisor();
        supervisorWithId.setId(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenSupervisorWithIdToCreate_thenThrowsException() {
        supervisorService.createSupervisor(supervisorWithId);
    }

}

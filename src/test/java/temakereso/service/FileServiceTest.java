package temakereso.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import temakereso.repository.FileRepository;
import temakereso.service.implementation.FileServiceImplementation;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileServiceTest {

    @InjectMocks
    private FileServiceImplementation fileServiceImplementation;

    @Mock
    private FileRepository fileRepository;

    @Before
    public void setUp() {
        when(fileRepository.exists(any(Long.class))).thenReturn(false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNonExistingFileToFind_thenThrowsException() {
        fileServiceImplementation.getOneById(0L);
    }
}

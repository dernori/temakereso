package temakereso.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import temakereso.repository.ParameterRepository;
import temakereso.service.implementation.ParameterServiceImplementation;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParameterServiceTest {

    @InjectMocks
    private ParameterServiceImplementation parameterService;

    @Mock
    private ParameterRepository parameterRepository;

    @Before
    public void setUp() {
        when(parameterRepository.existsByIdentifier(any(String.class))).thenReturn(false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNonExistingIdentifier_thenThrowsException() {
        parameterService.findByIdentifier("identifier");
    }

}

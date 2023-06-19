package pl.marcin.plonski.requestcountapp.users.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.marcin.plonski.requestcountapp.users.model.User;
import pl.marcin.plonski.requestcountapp.users.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldReturnSavedUserFromDbWhenSaveExistingUserInDb() {
        UserDto userDto = new UserDto();
        userDto.setLogin("existingUser");

        User existingUser = new User();
        existingUser.setLogin("existingUser");
        existingUser.setNumberOfCalls(0);

        when(userRepository.findByLogin("existingUser")).thenReturn(existingUser);
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userService.saveUserToDB(userDto);

        assertNotNull(result);
        assertEquals("existingUser", result.getLogin());
        assertEquals(1, result.getNumberOfCalls());
    }

}
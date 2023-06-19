package pl.marcin.plonski.requestcountapp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.marcin.plonski.requestcountapp.users.model.User;
import pl.marcin.plonski.requestcountapp.users.repository.UserRepository;
import pl.marcin.plonski.requestcountapp.users.service.UserDto;
import pl.marcin.plonski.requestcountapp.users.service.UserService;
import pl.marcin.plonski.requestcountapp.users.service.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class RequestCountAppApplicationTests {

    @Test
    void contextLoads() {
    }

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

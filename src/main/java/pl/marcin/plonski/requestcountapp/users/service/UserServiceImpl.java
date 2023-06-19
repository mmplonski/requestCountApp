package pl.marcin.plonski.requestcountapp.users.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.marcin.plonski.requestcountapp.users.model.User;
import pl.marcin.plonski.requestcountapp.users.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDto getUserByLogin(String login) {
        UserDto userDto = getUserFromAPI(login);
        User user = saveUserToDB(userDto);
        return userDto;
    }

    private User saveUserToDB(UserDto user) {
        User byLogin = userRepository.findByLogin(user.getLogin());
        if (byLogin != null) {
            byLogin.incrementNumberOfCalls();
            return userRepository.save(byLogin);
        } else {
            User newUser = new User();
            newUser.setLogin(user.getLogin());
            newUser.incrementNumberOfCalls();
            return userRepository.save(newUser);
        }
    }

    private UserDto getUserFromAPI(String login) {
        String url = "https://api.github.com/users/" + login;
        WebClient.Builder builder = WebClient.builder();
        UserDto user = builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
        user.setCalculations();
        return user;
    }
}

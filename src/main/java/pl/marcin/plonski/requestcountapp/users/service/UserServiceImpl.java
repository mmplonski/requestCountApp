package pl.marcin.plonski.requestcountapp.users.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import pl.marcin.plonski.requestcountapp.users.exception.UserError;
import pl.marcin.plonski.requestcountapp.users.exception.UserException;
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
        saveUserToDB(userDto);
        return userDto;
    }

    public User saveUserToDB(UserDto user) {
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
        try {
            UserDto user = builder.build()
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .block();
            user.setCalculations();
            return user;
        } catch (WebClientException e) {
            throw new UserException(UserError.USER_NOT_FOUND);
        }

    }
}

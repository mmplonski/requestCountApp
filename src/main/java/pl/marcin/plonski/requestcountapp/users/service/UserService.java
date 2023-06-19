package pl.marcin.plonski.requestcountapp.users.service;

import pl.marcin.plonski.requestcountapp.users.model.User;

public interface UserService {

    UserDto getUserByLogin(String login);
}

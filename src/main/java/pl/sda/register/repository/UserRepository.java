package pl.sda.register.repository;

import org.springframework.stereotype.Repository;
import pl.sda.register.exception.DuplicatedUserNameException;
import pl.sda.register.exception.UserNotFoundException;
import pl.sda.register.model.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private Set<User> users = initializeUsers();

    private Set<User> initializeUsers() {
        return new HashSet<>(Arrays.asList(new User("login", "Captain", "Jack")));
    }

    public Set<String> findAllUserNames(String firstName, boolean matchExact) {
        if (firstName == null) {
            return users.stream().map(User::getUsername).collect(Collectors.toSet());
        }

        Set<String> filtered;
        if (matchExact) {
            filtered = users
                    .stream()
                    .filter(u -> u.getFirstName().equalsIgnoreCase(firstName))
                    .map(User::getUsername)
                    .collect(Collectors.toSet());
        } else {
            filtered = users
                    .stream()
                    .filter(u -> u.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
                    .map(User::getUsername)
                    .collect(Collectors.toSet());
        }
        return filtered;
    }

    public User findUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny()
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found"));
    }

    public void addUser(User user) {

        User user1 = users.stream()
                .filter(u -> u.getUsername().equals(user.getUsername()))
                .findAny()
                .orElse(null);

        if (user1 == null) {
            users.add(user);
        } else {
            throw new DuplicatedUserNameException("Username: (" + user.getUsername() + ") already exists");
        }
    }

    public void removeUser(User user) {
        if (user != null) {
            users.remove(user);
        }
    }

    public void updateUser(User user) {
        User foundUser = findUserByUsername(user.getUsername());
        users.remove(foundUser);
        users.add(user);
    }
}
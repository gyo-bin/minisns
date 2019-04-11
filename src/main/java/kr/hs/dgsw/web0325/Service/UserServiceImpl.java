package kr.hs.dgsw.web0325.Service;

import kr.hs.dgsw.web0325.Domain.User;
import kr.hs.dgsw.web0325.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> view(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User add(User user) {
        return userRepository.findByEmail(user.getEmail()).map(found -> (User) null).orElse(userRepository.save(user));
    }

    @Override
    public User update(long id, User user) {
        return view(id).map(found -> {
            found.setName(Optional.ofNullable(user.getName().isEmpty() ? null : user.getName()).orElse(found.getName()));
            found.setEmail(Optional.ofNullable(user.getEmail().isEmpty() ? null : user.getEmail()).orElse(found.getEmail()));
            found.setStoredPath(Optional.ofNullable(user.getStoredPath()).orElse(found.getStoredPath()));
            found.setOriginalPath(Optional.ofNullable(user.getOriginalPath()).orElse(found.getOriginalPath()));
            return userRepository.save(found);
        }).orElse(null);
    }

    @Override
    public boolean delete(long id) {
        return userRepository.findById(id).map(
                user -> {
                    userRepository.delete(user);
                    return true;
                }).orElse(false);
    }
}
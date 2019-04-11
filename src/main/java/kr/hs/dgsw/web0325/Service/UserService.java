package kr.hs.dgsw.web0325.Service;

import kr.hs.dgsw.web0325.Domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> list();
    Optional<User> view(long id);
    User add(User user);
    User update(long id, User user);

    boolean delete(long id);
}
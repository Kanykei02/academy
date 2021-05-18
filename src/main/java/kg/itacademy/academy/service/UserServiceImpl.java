package kg.itacademy.academy.service;

import kg.itacademy.academy.entity.User;
import kg.itacademy.academy.entity.UserRole;
import kg.itacademy.academy.model.AuthModel;
import kg.itacademy.academy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleService userRoleService;


    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public User saveWithPasswordEncode(User user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_ADMIN");
        userRole.setUser(user);
        userRoleService.save(userRole);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public String getTokenByAuthModel(AuthModel authModel) {
        String authResult = "";
        User user = findByUsername(authModel.getUsername());
        if(user == null) authResult = "Неверный логин/пароль";
        else {
            if(passwordEncoder.matches(authModel.getPassword(), user.getPassword())) {
                String loginPassPair = user.getUsername() + ":" + authModel.getPassword();
                authResult = "Basic " + Base64.getEncoder().encodeToString(loginPassPair.getBytes());
            } else authResult = "Неверный логин/пароль";
        }
        return authResult;
    }

    @Override
    public List<User> getAllUsers(){
        try {
            System.out.println("Пользователь: " + SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (NullPointerException ignored){}

        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }



    @Override
    public User deleteById(Long id){
        User user = findById(id);
        if (user != null){
            userRepository.delete(user);
            return user;
        }
        return null;
    }

    @Override
    public List<User> deleteAllUsers(){
        List<User> user = getAllUsers();
        if(user != null){
            userRepository.deleteAll(user);
        }
        return null;
    }
}


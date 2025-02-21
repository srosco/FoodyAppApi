package com.example.api.service;

import com.example.api.PasswordUtils;
import com.example.api.dto.UserDto;
import com.example.api.model.User;
import com.example.api.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  BankAccountService bankAccountService;

    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getMail(), bankAccountService.convertToDto(user.getBankAccount()));
    }

    private User convertToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getMail(), userDto.getPassword(),userDto.getSalt() );
    }

    public UserDto getUser(final Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDto(user);
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    public void deleteUser(final Long id){
        userRepository.deleteById(id);
    }

    public UserDto saveUser(UserDto userDto) throws Exception{
        User user = convertToEntity(userDto);
        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(userDto.getPassword(), salt);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        User savedUser = userRepository.save(user);
        savedUser.setBankAccount(bankAccountService.createBankAccount(user));
        userRepository.save(savedUser);
        return convertToDto(savedUser);
    }

    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setMail(userDto.getMail());
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    public User findByMail(String mail) {
        var user = userRepository.findByMail(mail);
        return user.orElse(null);
    }

    public static int findIt(int[] a) {
        return Arrays.stream(a).filter(num -> num % 2 != 0).findAny().orElse(0);

    }


    public static int[] arrayDiff(int[] a, int[] b) {
        return Arrays.stream(a).filter(num -> Arrays.stream(b).noneMatch(bNum -> bNum == num)).toArray();
    }
}

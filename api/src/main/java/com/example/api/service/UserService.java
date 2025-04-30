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

    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getSalt(), user.getAimedMaccros(), user.getCurrentMaccros(), user.getAimedCarbohydrates(), user.getAimedFibers(), user.getAimedProteins(), user.getAimedCalories(), user.getCartId());
    }

    private User convertToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getPassword(), userDto.getSalt(), userDto.getAimedMaccros(), userDto.getCurrentMaccros(), userDto.getAimedCarbohydrates(), userDto.getAimedFibers(), userDto.getAimedProteins(), userDto.getAimedCalories(), userDto.getCartId());
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

    public UserDto saveUser(UserDto userDto) throws Exception {
        User user = convertToEntity(userDto);
        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(userDto.getPassword(), salt);
        
        // Setting fields with hashed password and salt
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        user.setCurrentMaccros(userDto.getCurrentMaccros());
        user.setAimedMaccros(userDto.getAimedMaccros());
        user.setAimedCarbohydrates(userDto.getAimedCarbohydrates());
        user.setAimedFibers(userDto.getAimedFibers());
        user.setAimedProteins(userDto.getAimedProteins());
        user.setAimedCalories(userDto.getAimedCalories());
        user.setCartId(userDto.getCartId());
        
        // Save user to the repository
        User savedUser = userRepository.save(user);  // Save once
        return convertToDto(savedUser);
    }

    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setCurrentMaccros(userDto.getCurrentMaccros());
        user.setAimedCarbohydrates(userDto.getAimedCarbohydrates());
        user.setAimedFibers(userDto.getAimedFibers());
        user.setAimedProteins(userDto.getAimedProteins());
        user.setAimedCalories(userDto.getAimedCalories());
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    public User findByEmail(String email) {
        var user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    public static int findIt(int[] a) {
        return Arrays.stream(a).filter(num -> num % 2 != 0).findAny().orElse(0);

    }


    public static int[] arrayDiff(int[] a, int[] b) {
        return Arrays.stream(a).filter(num -> Arrays.stream(b).noneMatch(bNum -> bNum == num)).toArray();
    }
}

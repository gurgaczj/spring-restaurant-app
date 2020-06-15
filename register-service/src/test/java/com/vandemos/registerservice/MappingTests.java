package com.vandemos.registerservice;

import com.vandemos.registerservice.dao.*;
import com.vandemos.registerservice.dto.*;
import com.vandemos.registerservice.model.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MappingTests {

    @Test
    public void daoToDtoTests(){
        Address address = new Address();
        address.setId(1L);
        address.setAparmentNumber(Short.valueOf("12"));
        address.setCity("asd");
        address.setFlatNumber(Short.valueOf("133"));
        address.setHouseNumber(Short.valueOf("15"));
        address.setPostalCode("12-123");
        address.setStreet("asffff");

        AddressDto addressDto = address.toDto();

        assertEquals(address.getCity(), addressDto.getCity());
        assertEquals(address.getAparmentNumber(), addressDto.getAparmentNumber());
        assertEquals(address.getFlatNumber(), addressDto.getFlatNumber());
        assertEquals(address.getHouseNumber(), addressDto.getHouseNumber());
        assertEquals(address.getPostalCode(), addressDto.getPostalCode());
        assertEquals(address.getStreet(), addressDto.getStreet());

        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setAddress(address);
        userInfo.setFirstName("imie");
        userInfo.setLastName("nazwisko");
        userInfo.setPhoneNumber(123123123);

        UserInfoDto userInfoDto = userInfo.toDto();

        assertEquals(userInfo.getFirstName(), userInfoDto.getFirstName());
        assertEquals(userInfo.getLastName(), userInfoDto.getLastName());
        assertEquals(userInfo.getPhoneNumber(), userInfoDto.getPhoneNumber());
        assertNotNull(userInfoDto.getAddress());
        assertEquals(userInfo.getAddress().getCity(), userInfoDto.getAddress().getCity());


        Role role = new Role();
        role.setRoleEnum(RoleEnum.ADMIN);

        RoleDto roleDto = role.toDto();

        assertEquals(role.getRoleEnum().name(), roleDto.getRoleEnum().name());

        Registration registration = new Registration();
        registration.setId(1L);
        registration.setRegistrationDate(LocalDateTime.now());
        registration.setHash("asdasd");
        registration.setActivationDate(LocalDateTime.MAX);

        RegistrationDto registrationDto = registration.toDto();

        assertEquals(registration.getRegistrationDate(), registrationDto.getRegistrationDate());
        assertEquals(registration.getHash(), registrationDto.getHash());
        assertEquals(registration.getActivationDate(), registrationDto.getActivationDate());

        User user = new User();
        user.setId(1L);
        user.setActivated(true);
        user.setEnabled(false);
        user.setEmail("mail@mail.com");
        user.setUsername("uname");
        user.setPassword("pass");
        user.setRegistration(registration);
        user.setRole(role);
        user.setUserInfo(userInfo);

        UserDto userDto = user.toDto();

        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertNotNull(userDto.getRegistration());
        assertNotNull(userDto.getUserInfo());
        assertNotNull(userDto.getRole());
    }
}

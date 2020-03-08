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
        AddressDao addressDao = new AddressDao();
        addressDao.setId(1L);
        addressDao.setAparmentNumber(Short.valueOf("12"));
        addressDao.setCity("asd");
        addressDao.setFlatNumber(Short.valueOf("133"));
        addressDao.setHouseNumber(Short.valueOf("15"));
        addressDao.setPostalCode("12-123");
        addressDao.setStreet("asffff");

        AddressDto addressDto = addressDao.toDto();

        assertEquals(addressDao.getCity(), addressDto.getCity());
        assertEquals(addressDao.getAparmentNumber(), addressDto.getAparmentNumber());
        assertEquals(addressDao.getFlatNumber(), addressDto.getFlatNumber());
        assertEquals(addressDao.getHouseNumber(), addressDto.getHouseNumber());
        assertEquals(addressDao.getPostalCode(), addressDto.getPostalCode());
        assertEquals(addressDao.getStreet(), addressDto.getStreet());

        UserInfoDao userInfoDao = new UserInfoDao();
        userInfoDao.setId(1L);
        userInfoDao.setAddress(addressDao);
        userInfoDao.setFirstName("imie");
        userInfoDao.setLastName("nazwisko");
        userInfoDao.setPhoneNumber(123123123);

        UserInfoDto userInfoDto = userInfoDao.toDto();

        assertEquals(userInfoDao.getFirstName(), userInfoDto.getFirstName());
        assertEquals(userInfoDao.getLastName(), userInfoDto.getLastName());
        assertEquals(userInfoDao.getPhoneNumber(), userInfoDto.getPhoneNumber());
        assertNotNull(userInfoDto.getAddress());
        assertEquals(userInfoDao.getAddress().getCity(), userInfoDto.getAddress().getCity());


        RoleDao roleDao = new RoleDao();
        roleDao.setRoleEnum(RoleEnum.ADMIN);

        RoleDto roleDto = roleDao.toDto();

        assertEquals(roleDao.getRoleEnum().name(), roleDto.getRoleEnum().name());

        RegistrationDao registrationDao = new RegistrationDao();
        registrationDao.setId(1L);
        registrationDao.setRegistrationDate(LocalDateTime.now());
        registrationDao.setHash("asdasd");
        registrationDao.setActivationDate(LocalDateTime.MAX);

        RegistrationDto registrationDto = registrationDao.toDto();

        assertEquals(registrationDao.getRegistrationDate(), registrationDto.getRegistrationDate());
        assertEquals(registrationDao.getHash(), registrationDto.getHash());
        assertEquals(registrationDao.getActivationDate(), registrationDto.getActivationDate());

        UserDao userDao = new UserDao();
        userDao.setId(1L);
        userDao.setActivated(true);
        userDao.setEnabled(false);
        userDao.setEmail("mail@mail.com");
        userDao.setUsername("uname");
        userDao.setPassword("pass");
        userDao.setRegistration(registrationDao);
        userDao.setRole(roleDao);
        userDao.setUserInfo(userInfoDao);

        UserDto userDto = userDao.toDto();

        assertEquals(userDao.getEmail(), userDto.getEmail());
        assertEquals(userDao.getUsername(), userDto.getUsername());
        assertNotNull(userDto.getRegistration());
        assertNotNull(userDto.getUserInfo());
        assertNotNull(userDto.getRole());
    }
}

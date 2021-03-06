package com.springboot.userservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.userservice.entity.User;
import com.springboot.userservice.repository.UserRepository;
import com.springboot.userservice.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    private final Long USER_ID = 2L;
    private final String USER_NAME = "test";
    private User user;
    private List<User> userList;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp(){
        user = new User();
        user.setId(USER_ID);
        user.setUserName(USER_NAME);
        userList = new ArrayList<>();
        userList.add(user);
    }

    @Test
    public void get_all_users_test(){
        // given
        when(userRepository.findAll()).thenReturn(userList);

        // when
        List<User> foundUsers = userService.getAllUsers();

        // then
        assertEquals(foundUsers.get(0).getUserName(), USER_NAME);
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void get_user_by_id_test(){
        // given
        when(userRepository.getOne(anyLong())).thenReturn(user);

        // when
        User foundUser = userService.getUserById(USER_ID);

        // then
        assertEquals(foundUser.getUserName(), USER_NAME);
        Mockito.verify(userRepository, Mockito.times(1)).getOne(anyLong());
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    
}

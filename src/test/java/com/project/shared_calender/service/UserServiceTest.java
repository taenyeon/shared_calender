package com.project.shared_calender.service;


import com.project.shared_calender.domain.user.constant.Gender;
import com.project.shared_calender.domain.user.constant.UserType;
import com.project.shared_calender.domain.user.dto.User;
import com.project.shared_calender.domain.user.dto.UserSimple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;


import static org.junit.jupiter.api.Assertions.*;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
//@Transactional
@Nested
@DisplayName("회원 서비스 검증")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local")
class UserServiceTest {

    Logger log = (Logger) LoggerFactory.getLogger(UserServiceTest.class);
    User user;
    long id;
    private String password;
    private final UserService userService;

    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("test@gmail.com")
                .type(UserType.SERVER)
                .name("test")
                .phoneNumber("01011111111")
                .gender(Gender.MALE)
                .build();
        id = 2;
        password = "test12";
    }

    @Test
    void join() {
        Boolean isCreated = userService.save(user, password);
        assertTrue(isCreated);
    }

    @Test
    void getById(){
        User user = userService.getById(id);
        log.info("user : {}", user);
        assertEquals(2, user.getId());
    }

    @Test
    void getSimpleById(){
        UserSimple userSimple = userService.getSimpleById(id);
        log.info("userSimple : {}", userSimple);
        assertEquals(2, userSimple.getId());
    }
}

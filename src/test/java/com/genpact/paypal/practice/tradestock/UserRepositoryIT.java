package com.genpact.paypal.practice.tradestock;

import com.genpact.paypal.practice.tradestock.repository.UserRepository;
import com.genpact.paypal.practice.tradestock.model.User;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TradestockApplication.class)
@AutoConfigureMockMvc
public class UserRepositoryIT {

    //entities
    private User user;
    private DateTime createdTime;
    private DateTime updatedTime;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        createdTime = new DateTime();
        updatedTime = new DateTime();
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(userRepository).isNotNull();
    }

    @Test
    public void should_find_no_of_users_if_repository_is_empty() {
        List<User> users = userRepository.findAll();
        assertThat(users).isEmpty();
    }

    @Test
    public void should_store_a_user() {
        user = userRepository.save(new User("Kavana", "CM", "kavanacm@gmail.com", "password", "India", createdTime,
                updatedTime));
        assertThat(user).hasFieldOrPropertyWithValue("firstName", "Kavana");
        assertThat(user).hasFieldOrPropertyWithValue("lastName", "CM");
        assertThat(user).hasFieldOrPropertyWithValue("email", "kavanacm@gmail.com");
        assertThat(user).hasFieldOrPropertyWithValue("country", "India");
        assertThat(user).hasFieldOrPropertyWithValue("createdTime", createdTime);
        assertThat(user).hasFieldOrPropertyWithValue("updatedTime", updatedTime);
    }

    @Test
    public void should_find_all_users() {
        user = userRepository.save(new User("Kavana", "CM", "kavanacm@gmail.com", "password", "India", createdTime,
                updatedTime));
        user = userRepository.save(new User("Kavana", "CM", "kavanacm@gmail.com", "password", "India", createdTime,
                updatedTime));
        user = userRepository.save(new User("Kavana", "CM", "kavanacm@gmail.com", "password", "India", createdTime,
                updatedTime));
        user = userRepository.save(new User("Kavana", "CM", "kavanacm@gmail.com", "password", "India", createdTime,
                updatedTime));

        Iterable<User> users = userRepository.findAll();

        assertThat(users).hasSize(4);
    }

    @Test
    public void should_filter_users_by_some_field() {
        user = userRepository.save(new User("Kavana", "CM", "kavanacm@gmail.com", "password", "India", createdTime,
                updatedTime));
        user = userRepository.save(new User("Kavana", "CM", "kavanacm@gmail.com", "password", "India", createdTime,
                updatedTime));
        user = userRepository.save(new User("Kavana", "CM", "kavanacm@gmail.com", "password", "Usa", createdTime,
                updatedTime));
        user = userRepository.save(new User("Kavana", "CM", "kavanacm@gmail.com", "password", "Usa", createdTime,
                updatedTime));

        Iterable<User> users = userRepository.findAllByCountry("usa");

        assertThat(users).hasSize(2);
    }

    @Test
    public void shoud_find_user_by_id() {
        user = userRepository.save(new User("Kavana", "CM", "kavanacm@gmail.com", "password", "India", createdTime,
                updatedTime));
        Long userId = user.getId();

        User userById = userRepository.findOneById(userId);
        assertThat(userById).hasFieldOrPropertyWithValue("id", userId);
    }

    @Test
    public void shoud_delete_user_by_id() {
        user = userRepository.save(new User("Kavana", "CM", "kavanacm@gmail.com", "password", "India", createdTime,
                updatedTime));
        Long userId = user.getId();

        userRepository.deleteById(userId);
        assertThat(userRepository.findOneById(userId)).isNull();
    }

    @Test
    public void shoud_partial_update_user_by_id() {
        user = userRepository.save(new User("Kavana", "CM", "kavanacm@gmail.com", "password", "India", createdTime,
                updatedTime));
        Long userId = user.getId();

        User userById = userRepository.findOneById(userId);
        userById.setFirstName("Sinchana");
        assertThat(userRepository.save(userById)).hasFieldOrPropertyWithValue("firstName", "Sinchana");
    }

}

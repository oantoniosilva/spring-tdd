package com.antonio.springtdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class RegisterApplicationTests {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@Test
	void should_register_user_successfully() {
		User userBeforeSave = new User( null, "John", "1234", LocalDate.of(1993, 5, 14));
		User userAfterSave = userBeforeSave;
		userAfterSave.setId("1");
		Mockito.when(userRepository.save(userBeforeSave)).thenReturn(userAfterSave);

		var user = userService.register(userBeforeSave);

		Mockito.verify(userRepository).save(userBeforeSave);
		Assertions.assertEquals(userAfterSave, user);
	}

}

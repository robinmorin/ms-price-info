package org.test.capitole;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class ApplicationTests {

    @Test
    void testMain() {
        // Arrange and Act
        assertDoesNotThrow(() -> Application.main(new String[] {}));
    }

}

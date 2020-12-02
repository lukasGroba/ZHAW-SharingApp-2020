package ch.zhaw.mas;

import ch.zhaw.mas.sharingApp.clientSite.firstTestProjectSetup.TestFileEuro;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFileEuroTest {
    private TestFileEuro two;

    @BeforeEach
    public  void setUp() throws Exception {
        two = new TestFileEuro(2.0);
    }

    @Test
    public void testAdding() throws Exception {
        two.add(two);
        assertEquals(new TestFileEuro(4.0).getAmount(), two.getAmount(), 0.01);
    }

    @Test
    public void testInvalid() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            two.add(new TestFileEuro(-2.0));
        });

    }
}
import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static OffByN offBy5 = new OffByN(5);

    // Your tests go here.
    @Test
    public void testequalChars() {
        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('f', 'a'));
        assertTrue(offBy5.equalChars('r', 'w'));
        assertFalse(offBy5.equalChars('a', 'e'));
        assertFalse(offBy5.equalChars('a', 'z'));
        assertFalse(offBy5.equalChars('a', 'a'));
    }
}

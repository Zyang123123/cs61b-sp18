import static org.junit.Assert.*;

import org.junit.Test;
public class FlikTest {
    @Test
    public void testFlik() {
        int a = 127;
        int b = 127;
        assertTrue(Flik.isSameNumber(a, b));
        //int值不能超过-128~127
    }
}

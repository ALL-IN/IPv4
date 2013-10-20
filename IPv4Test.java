

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Author: Aleksey Alekseenko
 * Date: 21.10.13
 */
public class IPv4Test {
    @Test
    public void testRelation() throws Exception {
        assertEquals("SUBSET", IPv4.relation("192.168.0.1/12", "12.12.1.0/0"));
        assertEquals("SUPERSET", IPv4.relation("12.12.1.0/0", "192.168.0.1/12"));
        assertEquals("DISJOINT", IPv4.relation("12.12.1.0/32", "192.168.0.1/30"));
        assertEquals("EQUALS", IPv4.relation("12.12.1.0/32", "12.12.1.0/32"));
    }
}

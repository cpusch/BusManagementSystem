import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

@RunWith(JUnit4.class)
public class BusManagementTest {
    // ~ Constructor ........................................................
    @Test
    public void testConstructor() {
        new BusManagement();
    }
}

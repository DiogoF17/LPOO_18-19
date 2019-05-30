package com.aor.ghostrumble;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ApplicationTest {

    @Test
    public void testGetGameTypeLanterna() {
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextInt()).thenReturn(0);
        assertEquals(0, Application.getGameType(scanner));
    }

    @Test
    public void testGetGameTypeSwing() {
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextInt()).thenReturn(1);
        assertEquals(1, Application.getGameType(scanner));
    }

}

package coisa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CalculadoraTest {
    @Test
    public void adiciona01() {
        Calculadora c = new Calculadora();
        int x = c.adiciona(5);
        assertEquals(5,x);
    }
    @Test
    public void adiciona02() {
        Calculadora c = new Calculadora();
        int x = c.adiciona(2,2);
        assertEquals(4,x);
    }
    @Test
    public void adiciona03() {
        Calculadora c = new Calculadora();
        int x = c.adiciona(3,2);
        assertEquals(5,x);
    }
    @Test
    public void subtrai01() {
        Calculadora c = new Calculadora();
        int x = c.subtrai(0,0);
        assertEquals(0,x);
    }
}
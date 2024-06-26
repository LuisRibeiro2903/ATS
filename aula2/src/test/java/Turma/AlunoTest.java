package Turma;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    Aluno a;

    @BeforeEach
    public void setup(){
        a = new Aluno();
    }

    @Test
    public void setNomeTest() {
        a.setNome("Miguel");
        assertEquals("Miguel", a.getNome(), "Nome do aluno incorreto");
    }

    @Test
    void setNotaTest() {
        assertThrows(NotaInvalidaException.class, () ->  a.setNota("ATS", 49), "Nota inválida aceite");
        assertThrows(NotaInvalidaException.class, () ->  a.setNota("ATS", -620), "Nota inválida aceite");
        try {
            a.setNota("ATS", 20);
            assertEquals(a.getNota("ATS"), 20, "Nota atual não corresponde á inserida por 'setNota', 20");
            a.setNota("ATS", 0);
            assertEquals(a.getNota("ATS"), 0, "Nota atual não corresponde á inserida por 'setNota', 0");
        } catch (NotaInvalidaException e) {
            fail("NotaInvalidaException no teste setNotaTes");
        }
    }

    @Test
    void equalsTest() {
        assertEquals(a, a);
        assertFalse(a.equals(2));
    }

    @Test
    void incrementaNotaTest() {
        try {
            a.setNota("ATS", 10);
            a.incrementaNota("ATS", 5);
            assertEquals(15, a.getNota("ATS"), 0.05, "Nota inválida após incremento");
        }
        catch (NotaInvalidaException e){
            fail ("Turma.NotaInvalidaException no teste incrementaNotaTest");
        }

    }
}
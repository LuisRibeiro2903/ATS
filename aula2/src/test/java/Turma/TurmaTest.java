package Turma;

import Turma.Aluno;
import Turma.AlunoInexistenteException;
import Turma.NotaInvalidaException;
import Turma.Turma;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurmaTest {

    Aluno a, b, c;
    Turma t;

    @BeforeEach
    public void setup(){
        a = new Aluno("Alfredo", "A00000");
        b = new Aluno("Bernardo", "A00001");
        c = new Aluno("Carlos", "A00002");
        t = new Turma();
    }

    @Test
    void addAlunoTest() {
        t.addAluno(a);
        assertTrue(t.containsAluno("A00000"), "Turma.Aluno inserido nao se encontra na turma");
    }

    @Test
    void getAlunoTest() {
        t.addAluno(a);
        t.addAluno(b);
        try {
            Aluno r = t.getAluno("A00000");
            assertEquals(a, r, "Alfredo inserido e obtido de turma nao e igual ao original");
            Aluno g = t.getAluno("A00001");
            assertNotEquals(a, g, "Alunos considerados iguais quando não o são") ;
        }
        catch (AlunoInexistenteException e){
            fail("Turma.AlunoInexistenteException no teste getAlunoTest");
        }
    }

    @Test
    void mediaTest() {
        try {
            a.setNota("ATS", 10);
            b.setNota("ATS", 12);
            c.setNota("ATS", 8);
            t.addAluno(a);
            t.addAluno(b);
            t.addAluno(c);
            assertEquals(10.0, t.media(), 0.05, "Media calculada com valor incorreto");
        }
        catch (NotaInvalidaException e){
            fail("Turma.NotaInvalidaException no teste mediaException");
        }

    }

    @Test
    void reprovadosTest(){
        try {
            a.setNota("ATS", 10);
            a.setNota("CG", 14);
            t.addAluno(a);
            assertFalse(t.reprovados().contains(a), "Turma.Aluno reprovado quando não devia estar");
            b.setNota("ATS", 9);
            b.setNota("CG", 9);
            t.addAluno(b);
            assertTrue(t.reprovados().contains(b), "Nenhum aluno reprovado quando devia");
            c.setNota("ATS", 10);
            c.setNota("CG", 10);
            t.addAluno(c);
            assertFalse(t.reprovados().contains(c), "Aluno reprovado quando não devia");

        } catch (NotaInvalidaException e) {
            fail("Turma.NotaInvalidaException no teste reprovadosTest");
        }
    }

    @Test
    void removeAlunoTest(){
        try {
            a.setNota("ATS", 10);
            b.setNota("ATS", 12);
            c.setNota("ATS", 8);
            t.addAluno(a);
            t.addAluno(b);
            t.addAluno(c);
            t.removeAluno("A00002");
            assertFalse(t.containsAluno("A00002"), "Turma.Aluno não removido com sucesso");
            assertThrows(AlunoInexistenteException.class, () -> t.removeAluno("A100608"), "Turma.Aluno não existente removido?");
        } catch (NotaInvalidaException e) {
            fail("Turma.NotaInvalidaException no teste removeAlunoTest");
        } catch (AlunoInexistenteException e) {
            fail("Turma.AlunoInexistenteException no teste removeAlunoTest");
        }
    }

    @Test
    void setAlunosTest (){
        List<Aluno> lista_de_alunos = new ArrayList<>();
        lista_de_alunos.add(a);
        lista_de_alunos.add(b);
        lista_de_alunos.add(c);
        t.setAlunos(lista_de_alunos);
        try{
            assertEquals(a, t.getAluno("A00000"));
            assertEquals(b, t.getAluno("A00001"));
            assertEquals(c, t.getAluno("A00002"));
        } catch (AlunoInexistenteException e) {
            fail();
        }

    }
}
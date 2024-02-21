package Turma.src.main.java;

import java.util.HashMap;
import java.util.Map;

public class Aluno
{

    private String Nome;
    private String Num_mecanografico;
    private Map<String, Integer> Notas;

    public Aluno()
    {
        Nome = "";
        Num_mecanografico = "";
        Notas = new HashMap<>();
    }

    public Aluno (String nome, String num_mecanografico)
    {
        Nome = nome;
        Num_mecanografico = num_mecanografico;
        Notas = new HashMap<>();
    }

    public Aluno (String nome, String num_mecanografico, Map<String, Integer> notas)
    {
        Nome = nome;
        Num_mecanografico = num_mecanografico;
        Notas = notas;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getNumero() {
        return Num_mecanografico;
    }

    public String getNome() {
        return Nome;
    }

    public Integer getNota(String cadeira) {
        return Notas.get(cadeira);
    }

    public void setNota(String cadeira, int nota) throws NotaInvalidaException
    {
        if (nota > 20 || nota < 0)
            throw new NotaInvalidaException();
        this.Notas.put(cadeira, nota);
    }

    public void incrementaNota(String cadeira, int incremento) throws NotaInvalidaException
    {
        int nota_atual = this.Notas.get(cadeira);
        int nova_nota = nota_atual + incremento;
        this.setNota(cadeira, nova_nota);
    }

    public double media ()
    {
        int sum = 0;
        for(Integer nota : this.Notas.values())
        {
            sum += nota;
        }
        return (double) sum / this.Notas.size();
    }

    @Override
    public Aluno clone()
    {
        return new Aluno(this.Nome, this.Num_mecanografico, this.Notas);
    }

    @Override
    public boolean equals (Object a)
    {
        if (this == a)
            return true;
        if ((a == null) || (this.getClass() != a.getClass()))
            return false;
        Aluno aluno = (Aluno) a;
        return(this.Nome.equals(aluno.getNome()) && this.Num_mecanografico.equals(aluno.getNumero()));
    }
}

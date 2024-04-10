package Turma;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Turma {

    private List<Aluno> alunos;

    public Turma(){
        alunos = new ArrayList<>();
    }

    public void setAlunos(List<Aluno> novosAlunos){
        alunos = novosAlunos.stream().map(Aluno::clone).collect(Collectors.toList());
    }

    public void addAluno(Aluno a){
        if (!alunos.contains(a)) alunos.add(a.clone());
    }

    public boolean containsAluno(String numero) {
        return alunos.stream().anyMatch(x -> x.getNumero().equals(numero));
    }

    public void removeAluno(String numero) throws AlunoInexistenteException {
        Iterator<Aluno> it = alunos.iterator();
        boolean removido = false;
        while (!removido && it.hasNext()){
            Aluno aluno = it.next();
            if (aluno.getNumero().equals(numero)){
                it.remove();
                removido = true;
            }
        }
        if (!removido) throw new AlunoInexistenteException();
    }

    public Aluno getAluno(String numero) throws AlunoInexistenteException {
         return alunos.stream().filter(x -> x.getNumero().equals(numero)).findFirst().orElseThrow(AlunoInexistenteException::new).clone();
    }

    public double media(){
        double media = 0.0;
        for (Aluno a : alunos){
            media += a.media();
        }
        return media / this.alunos.size();
    }

    public List<Aluno> reprovados(){
        List<Aluno> r = new ArrayList<>();
        for (Aluno a : alunos) {
            if (a.media() < 10) r.add(a.clone());
        }
        return r;
    }

}



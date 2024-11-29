package src;

import java.util.ArrayList;

public class Curso {
    private String nomeCurso;
    private Professor professor;
    private ArrayList<Aluno> alunos;

    public Curso(String nomeCurso, Professor professor) {
        this.nomeCurso = nomeCurso;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    @Override
    public String toString() {
        return "Curso: " + nomeCurso + ", Professor: " + professor.getNome();
    }

    public void listarAlunos() {
        System.out.println("\nAlunos matriculados no curso " + nomeCurso + ":");
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }
}

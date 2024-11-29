package src;

public class Aluno {
    private String nome;
    private String cpf;
    private String matricula;
    private Curso curso;

    public Aluno(String nome, String cpf, String matricula) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.curso = null;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        String cursoNome = (curso != null) ? curso.getNomeCurso() : "Não matriculado em nenhum curso";
        return "Aluno: " + nome + " | CPF: " + cpf + " | Matrícula: " + matricula + " | Curso: " + cursoNome;
    }
}

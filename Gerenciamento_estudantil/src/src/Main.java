package src;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Aluno> alunos = new ArrayList<>();
        ArrayList<Professor> professores = new ArrayList<>();
        ArrayList<Curso> cursos = new ArrayList<>();

        while (true) {
            System.out.println("\n===== Gerenciamento Estudantil =====");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Professor");
            System.out.println("3. Cadastrar Curso");
            System.out.println("4. Matricular Aluno em Curso");
            System.out.println("5. Exibir Alunos");
            System.out.println("6. Exibir Professores");
            System.out.println("7. Exibir Cursos");
            System.out.println("8. Pesquisar, Editar ou Excluir Aluno");
            System.out.println("9. Pesquisar, Editar ou Excluir Professor");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1: // Cadastrar Aluno
                    System.out.print("Nome do aluno: ");
                    String nomeAluno = scanner.nextLine();
                    System.out.print("CPF do aluno: ");
                    String cpfAluno = scanner.nextLine();
                    System.out.print("Matrícula do aluno: ");
                    String matricula = scanner.nextLine();
                    alunos.add(new Aluno(nomeAluno, cpfAluno, matricula));
                    System.out.println("Aluno cadastrado com sucesso!");
                    break;

                case 2: // Cadastrar Professor
                    System.out.print("Nome do professor: ");
                    String nomeProfessor = scanner.nextLine();
                    System.out.print("CPF do professor: ");
                    String cpfProfessor = scanner.nextLine();
                    System.out.print("Especialidade do professor: ");
                    String especialidade = scanner.nextLine();
                    professores.add(new Professor(nomeProfessor, cpfProfessor, especialidade));
                    System.out.println("Professor cadastrado com sucesso!");
                    break;

                case 3: // Cadastrar Curso
                    if (professores.isEmpty()) {
                        System.out.println("Não há professores cadastrados. Cadastre um professor primeiro.");
                        break;
                    }
                    System.out.print("Nome do curso: ");
                    String nomeCurso = scanner.nextLine();
                    System.out.println("Selecione o professor responsável:");
                    for (int i = 0; i < professores.size(); i++) {
                        System.out.println((i + 1) + ". " + professores.get(i).getNome());
                    }
                    int professorIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consumir a quebra de linha
                    if (professorIndex >= 0 && professorIndex < professores.size()) {
                        Curso novoCurso = new Curso(nomeCurso, professores.get(professorIndex));
                        cursos.add(novoCurso);
                        System.out.println("Curso cadastrado com sucesso!");
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;

                case 4: // Matricular Aluno em Curso
                    if (alunos.isEmpty() || cursos.isEmpty()) {
                        System.out.println("Não há alunos ou cursos cadastrados.");
                        break;
                    }
                    System.out.println("Selecione o aluno para matricular:");
                    for (int i = 0; i < alunos.size(); i++) {
                        System.out.println((i + 1) + ". " + alunos.get(i).getNome());
                    }
                    int alunoIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consumir a quebra de linha

                    System.out.println("Selecione o curso:");
                    for (int i = 0; i < cursos.size(); i++) {
                        System.out.println((i + 1) + ". " + cursos.get(i).getNomeCurso());
                    }
                    int cursoIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consumir a quebra de linha

                    if (alunoIndex >= 0 && alunoIndex < alunos.size() && cursoIndex >= 0 && cursoIndex < cursos.size()) {
                        Aluno aluno = alunos.get(alunoIndex);
                        Curso curso = cursos.get(cursoIndex);
                        aluno.setCurso(curso); // Vincula o aluno ao curso
                        curso.adicionarAluno(aluno); // Adiciona o aluno ao curso
                        System.out.println("Aluno matriculado no curso com sucesso!");
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;

                case 5: // Exibir Alunos
                    if (alunos.isEmpty()) {
                        System.out.println("Nenhum aluno cadastrado.");
                    } else {
                        System.out.println("\n=== Alunos Cadastrados ===");
                        for (Aluno aluno : alunos) {
                            System.out.println(aluno); // Mostra o aluno e o curso ao qual está vinculado
                        }
                    }
                    break;

                case 6: // Exibir Professores
                    if (professores.isEmpty()) {
                        System.out.println("Nenhum professor cadastrado.");
                    } else {
                        System.out.println("\n=== Professores Cadastrados ===");
                        for (Professor professor : professores) {
                            System.out.println(professor);
                        }
                    }
                    break;

                case 7: // Exibir Cursos
                    if (cursos.isEmpty()) {
                        System.out.println("Nenhum curso cadastrado.");
                    } else {
                        System.out.println("\n=== Cursos Cadastrados ===");
                        for (Curso curso : cursos) {
                            System.out.println(curso);
                        }
                    }
                    break;

                case 8: // Pesquisar, Editar ou Excluir Aluno
                    pesquisarEditarExcluirAluno(scanner, alunos);
                    break;

                case 9: // Pesquisar, Editar ou Excluir Professor
                    pesquisarEditarExcluirProfessor(scanner, professores);
                    break;

                case 0: // Sair
                    System.out.println("Saindo... Até logo!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void pesquisarEditarExcluirAluno(Scanner scanner, ArrayList<Aluno> alunos) {
        System.out.println("\n=== Pesquisa de Aluno ===");
        System.out.print("Digite o nome ou matrícula do aluno: ");
        String busca = scanner.nextLine();

        Aluno alunoEncontrado = null;
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(busca) || aluno.getMatricula().equalsIgnoreCase(busca)) {
                alunoEncontrado = aluno;
                break;
            }
        }

        if (alunoEncontrado != null) {
            System.out.println("Aluno encontrado:");
            System.out.println(alunoEncontrado);
            System.out.println("\n1. Editar Aluno");
            System.out.println("2. Excluir Aluno");
            System.out.println("0. Cancelar");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (escolha) {
                case 1:
                    System.out.print("Novo nome do aluno (ou pressione Enter para manter): ");
                    String novoNome = scanner.nextLine();
                    if (!novoNome.isEmpty()) {
                        alunoEncontrado.setNome(novoNome);
                    }

                    System.out.print("Novo CPF do aluno (ou pressione Enter para manter): ");
                    String novoCpf = scanner.nextLine();
                    if (!novoCpf.isEmpty()) {
                        alunoEncontrado.setCpf(novoCpf);
                    }

                    System.out.println("Aluno atualizado com sucesso!");
                    break;

                case 2:
                    alunos.remove(alunoEncontrado);
                    System.out.println("Aluno excluído com sucesso!");
                    break;

                case 0:
                    System.out.println("Operação cancelada.");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private static void pesquisarEditarExcluirProfessor(Scanner scanner, ArrayList<Professor> professores) {
        System.out.println("\n=== Pesquisa de Professor ===");
        System.out.print("Digite o nome ou CPF do professor: ");
        String busca = scanner.nextLine();

        Professor professorEncontrado = null;
        for (Professor professor : professores) {
            if (professor.getNome().equalsIgnoreCase(busca) || professor.getCpf().equalsIgnoreCase(busca)) {
                professorEncontrado = professor;
                break;
            }
        }

        if (professorEncontrado != null) {
            System.out.println("Professor encontrado:");
            System.out.println(professorEncontrado);
            System.out.println("\n1. Editar Professor");
            System.out.println("2. Excluir Professor");
            System.out.println("0. Cancelar");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (escolha) {
                case 1:
                    System.out.print("Novo nome do professor (ou pressione Enter para manter): ");
                    String novoNome = scanner.nextLine();
                    if (!novoNome.isEmpty()) {
                        professorEncontrado.setNome(novoNome);
                    }

                    System.out.print("Nova especialidade do professor (ou pressione Enter para manter): ");
                    String novaEspecialidade = scanner.nextLine();
                    if (!novaEspecialidade.isEmpty()) {
                        professorEncontrado.setEspecialidade(novaEspecialidade);
                    }

                    System.out.println("Professor atualizado com sucesso!");
                    break;

                case 2:
                    professores.remove(professorEncontrado);
                    System.out.println("Professor excluído com sucesso!");
                    break;

                case 0:
                    System.out.println("Operação cancelada.");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } else {
            System.out.println("Professor não encontrado.");
        }
    }
}

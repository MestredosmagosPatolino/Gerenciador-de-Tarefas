public class Tarefa {
    private String nome;
    private boolean concluida;

    public Tarefa(String nome) {
        this.nome = nome;
        this.concluida = false;
    }

    public String getNome() {
        return nome;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void concluir() {
        this.concluida = true;
    }

    @Override
    public String toString() {
        return (concluida ? "[✔] " : "[ ] ") + nome;
    }
}

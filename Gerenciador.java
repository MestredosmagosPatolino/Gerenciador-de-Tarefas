import java.util.ArrayList;
import java.io.*;

public class Gerenciador {

    private ArrayList<Tarefa> tarefas;

    public Gerenciador() {
        tarefas = new ArrayList<>();
        carregarTarefas();
    }

    private String arquivo = " tarefas.txt";

    public void adicionarTarefa(String nome) {
        tarefas.add(new Tarefa(nome));
        SalvarTarefas();
    }

    public void concluirTarefa(int index) {
        tarefas.get(index).concluir();
    }

    public ArrayList<Tarefa> getTarefas() {
        return tarefas;
    }

    public int contarPendentes() {
        int count = 0;
        for (Tarefa t : tarefas) {
            if (!t.isConcluida()) {
                count++;
            }
        }
        return count;
    }

    public void SalvarTarefas() {
        try {
            FileWriter writer = new FileWriter(arquivo);

            for (Tarefa t : tarefas) {
                writer.write(t.getNome() + "," + t.isConcluida() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

public void carregarTarefas() 
{
    try {
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;

        while ((linha = reader.readLine()) != null) {

            String[] partes = linha.split(",");

            String nome = partes[0];
            boolean concluida = Boolean.parseBoolean(partes[1]);

            Tarefa t = new Tarefa(nome);

            if (concluida) {
                t.concluir();
            }

            tarefas.add(t);
        }

        reader.close();

    } catch (IOException e) {
        e.printStackTrace();
    }

}
 }

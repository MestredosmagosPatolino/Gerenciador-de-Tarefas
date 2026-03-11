import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tela extends JFrame {

    private Gerenciador gerenciador;
    private DefaultListModel<Tarefa> modeloLista;
    private JList<Tarefa> lista;

    public Tela() {
        gerenciador = new Gerenciador();

        setTitle("Gerenciador de Tarefas");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloLista = new DefaultListModel<>();
        lista = new JList<>(modeloLista);

        JScrollPane scroll = new JScrollPane(lista);

        JTextField campoTarefa = new JTextField();

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnConcluir = new JButton("Concluir");

        btnAdicionar.addActionListener(e -> {
            String texto = campoTarefa.getText();
            if (!texto.isEmpty()) {
                gerenciador.adicionarTarefa(texto);
                atualizarLista();
                campoTarefa.setText("");
            }
        });

        btnConcluir.addActionListener(e -> {
            int index = lista.getSelectedIndex();
            if (index != -1) {
                gerenciador.concluirTarefa(index);
                atualizarLista();
                verificarPendentes();
            }
        });

        JPanel painel = new JPanel(new BorderLayout());
        painel.add(campoTarefa, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);

        JPanel botoes = new JPanel();
        botoes.add(btnAdicionar);
        botoes.add(btnConcluir);

        painel.add(botoes, BorderLayout.SOUTH);

        add(painel);
    }

    private void atualizarLista() {
        modeloLista.clear();
        for (Tarefa t : gerenciador.getTarefas()) {
            modeloLista.addElement(t);
        }
    }

    private void verificarPendentes() {
        int pendentes = gerenciador.contarPendentes();
        if (pendentes > 0) {
            JOptionPane.showMessageDialog(this,
                    "⚠ Você tem " + pendentes + " tarefas pendentes!");
        }
    }
}
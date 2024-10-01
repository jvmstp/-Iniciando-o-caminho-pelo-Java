package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaRepo {
    private List<PessoaFisica> listaPessoasFisicas;

    public PessoaFisicaRepo() {
        this.listaPessoasFisicas = new ArrayList<>();
    }

    public void inserir(PessoaFisica pessoaFisica) {
        listaPessoasFisicas.add(pessoaFisica);
    }

    public void alterar(PessoaFisica pessoaFisica) {
        for (int i = 0; i < listaPessoasFisicas.size(); i++) {
            if (listaPessoasFisicas.get(i).getId() == pessoaFisica.getId()) {
                listaPessoasFisicas.set(i, pessoaFisica);
                return;
            }
        }
        System.out.println("Pessoa física não encontrada para alteração.");
    }

    public void excluir(int id) {
        for (int i = 0; i < listaPessoasFisicas.size(); i++) {
            if (listaPessoasFisicas.get(i).getId() == id) {
                listaPessoasFisicas.remove(i);
                return;
            }
        }
        System.out.println("Pessoa física não encontrada para exclusão.");
    }

    public PessoaFisica obter(int id) {
        for (PessoaFisica pessoaFisica : listaPessoasFisicas) {
            if (pessoaFisica.getId() == id) {
                return pessoaFisica;
            }
        }
        System.out.println("Pessoa física não encontrada.");
        return null;
    }

    public List<PessoaFisica> obterTodos() {
        return listaPessoasFisicas;
    }
    
    public void persistir(String nomeArquivo) {
        try (FileOutputStream fos = new FileOutputStream(nomeArquivo);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(listaPessoasFisicas);
            System.out.println("Dados de Pessoa Fisica Armazenados.");
        } catch (IOException ex) {
            System.out.println("Erro ao armazenar dados no arquivo: " + ex.getMessage());
        }
    }
    
    public void recuperar(String nomeArquivo) {
        try (FileInputStream fis = new FileInputStream(nomeArquivo);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            listaPessoasFisicas = (List<PessoaFisica>) ois.readObject();
            System.out.println("Dados de Pessoa Fisica Recuperados.");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Erro ao recuperar dados do arquivo: " + ex.getMessage());
        }
    }
}
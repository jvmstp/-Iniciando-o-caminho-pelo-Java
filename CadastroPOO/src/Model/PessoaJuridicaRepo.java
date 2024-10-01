package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaRepo {
    private List<PessoaJuridica> listaPessoasJuridicas;
    
    public PessoaJuridicaRepo() {
        this.listaPessoasJuridicas = new ArrayList<>();
    }
    
    public void inserir(PessoaJuridica pessoaJuridica) {
        listaPessoasJuridicas.add(pessoaJuridica);
    }
    
    public void alterar(PessoaJuridica pessoaJuridica) {
        for (int i = 0; i < listaPessoasJuridicas.size(); i++) {
            if (listaPessoasJuridicas.get(i).getId() == pessoaJuridica.getId()) {
                listaPessoasJuridicas.set(i, pessoaJuridica);
                return;
            }
        }
        System.out.println("Pessoa juridica não encontrada para alteraçãoas.");
    }
    
    public void excluir(int id) {
        for (int i = 0; i < listaPessoasJuridicas.size(); i ++) {
            if (listaPessoasJuridicas.get(i).getId() == id) {
                listaPessoasJuridicas.remove(i);
                return;
            }
        }
        System.out.println("Pessoa juridica não encontrada para exclusão.");
    }
    
    public PessoaJuridica obter(int id) {
        for (PessoaJuridica pessoaJuridica : listaPessoasJuridicas) {
            if (pessoaJuridica.getId() == id) {
                return pessoaJuridica;
            }
        }
        System.out.println("Pessoa juridica não encontrada.");
        return null;
    }
    
    public List<PessoaJuridica> obterTodos() {
        return listaPessoasJuridicas;
    }
    
    public void persistir(String nomeArquivo) {
        try (FileOutputStream fos = new FileOutputStream(nomeArquivo);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(listaPessoasJuridicas);
            System.out.println("Dados de Pessoa Juridica Armazenados.");
        } catch (IOException ex) {
            System.out.println("Erro ao persistir dados no arquivo: " + ex.getMessage());
        }
    }
    
    public void recuperar(String nomeArquivo) {
        try (FileInputStream fis = new FileInputStream(nomeArquivo);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            listaPessoasJuridicas = (List<PessoaJuridica>) ois.readObject();
            System.out.println("Dados de Pessoa Juridica Recuperados.");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Erro ao recuperar dados do arquivo: " + ex.getMessage());
        }
    }
}
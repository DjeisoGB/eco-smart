package com.mycompany.sistemareciclagem;

import java.util.ArrayList;
import java.io.*;
import java.time.LocalDate;

public class ArquivoUtilitarios {
    
    // Salva os dados de todos os usuários no arquivo usuarios.txt
    public static void salvarUsuarios(ArrayList<Usuario> usuarios) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt"));
            // Percorre a lista de usuários e grava suas informações no arquivo
            for(Usuario u : usuarios) {
                writer.write(u.getNome() + ";" + u.getEmail() + ";" + u.getSenha() + ";" + u.getPontos());

                writer.newLine();
            }
            
            writer.close();
            
        } catch(IOException e){
            System.out.println("Erro ao salvar arquivo.");
        }
        
    }
    
    // Carrega os usuários salvos no arquivo usuarios.txt
    public static ArrayList<Usuario> carregarUsuarios() {

        // Lista onde serão armazenados os usuários carregados do arquivo
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"));
            String linha;
            // Lê o arquivo linha por linha
            while((linha = reader.readLine()) != null) {
                // Separa os dados utilizando ";" como delimitador
                String[] dados = linha.split(";");

                String nome = dados[0];
                String email = dados[1];
                String senha = dados[2];
                
                int pontos = Integer.parseInt(dados[3]);
                
                // Cria um novo objeto Usuario com as informações lidas
                Usuario usuario = new Usuario(nome, email, senha);
                // Restaura a pontuação do usuário
                usuario.adicionarPontos(pontos);
                usuarios.add(usuario);
            }

            reader.close();

        } catch(IOException e) {
            System.out.println("Arquivo ainda não existe.");
        }

        return usuarios;
    }
    
    // Salva o histórico de descartes de todos os usuários
    public static void salvarHistorico(ArrayList<Usuario> usuarios) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("historico.txt"));
            // Percorre todos os usuários
            for(Usuario u : usuarios) {
                // Percorre todo o histórico de descartes de cada usuário
                for(RegistroDescarte r : u.getHistorico()) {
                    // Grava no arquivo: email;resíduo;quantidade;pontos;data
                    writer.write(u.getEmail() + ";" + r.getResiduo().getNome() + ";" + r.getQuantidade() + ";" + r.getPontosGerados() + ";" + r.getData());
                    writer.newLine();
                }
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("Erro ao salvar histórico.");
        }
    }
    
    // Carrega o histórico de descartes salvo no arquivo historico.txt
    public static void carregarHistorico(ArrayList<Usuario> usuarios, ArrayList<Residuo> residuos) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("historico.txt"));

            String linha;
            // Lê o arquivo linha por linha
            while((linha = reader.readLine()) != null) {

                // Divide a linha em campos utilizando ";" como separador
                String[] dados = linha.split(";");

                String email = dados[0];
                String nomeResiduo = dados[1];
                int quantidade = Integer.parseInt(dados[2]);
                int pontos = Integer.parseInt(dados[3]);
                LocalDate data = LocalDate.parse(dados[4]);

                Usuario usuarioEncontrado = null;
                
                // Procura o usuário correspondente ao e-mail armazenado
                for(Usuario u : usuarios) {
                    if (u.getEmail().equals(email)) {
                        usuarioEncontrado = u;
                        break;
                    }
                }

                Residuo residuoEncontrado = null;

                // Procura o resíduo correspondente ao nome armazenado
                for(Residuo r : residuos) {
                    if(r.getNome().equals(nomeResiduo)) {
                        residuoEncontrado = r;
                        break;
                    }
                }
                // Caso o usuário e o resíduo sejam encontrados, recria o registro de descarte
                if(usuarioEncontrado != null && residuoEncontrado != null) {
                    RegistroDescarte registro = new RegistroDescarte(usuarioEncontrado, residuoEncontrado, quantidade, pontos, data);
                    // Adiciona o registro ao histórico do usuário
                    usuarioEncontrado.getHistorico().add(registro);
                }
            }
            reader.close();

        } catch(IOException e) {
            System.out.println("Histórico não encontrado.");
        }
    }
}

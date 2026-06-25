package com.mycompany.sistemareciclagem;

import java.util.ArrayList;
import java.io.*;
import java.time.LocalDate;

public class ArquivoUtilitarios {
    
    public static void salvarUsuarios(ArrayList<Usuario> usuarios) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt"));
            //vai pegar as informaçoes do usuario e vai colocar no txt
            for(Usuario u : usuarios) {
                writer.write(u.getNome() + ";" + u.getEmail() + ";" + u.getSenha() + ";" + u.getPontos());

                writer.newLine();
            }
            
            writer.close();
            
        } catch(IOException e){
            System.out.println("Erro ao salvar arquivo.");
        }
        
    }
    
    public static ArrayList<Usuario> carregarUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"));
            String linha;
            while((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");

                String nome = dados[0];
                String email = dados[1];
                String senha = dados[2];
                
                int pontos = Integer.parseInt(dados[3]);
                
                Usuario usuario = new Usuario(nome, email, senha);
                usuario.adicionarPontos(pontos);
                usuarios.add(usuario);
            }

            reader.close();

        } catch(IOException e) {
            System.out.println("Arquivo ainda não existe.");
        }

        return usuarios;
    }
    
    public static void salvarHistorico(ArrayList<Usuario> usuarios) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("historico.txt"));
            for(Usuario u : usuarios) {
                for(RegistroDescarte r : u.getHistorico()) {
                    writer.write(u.getEmail() + ";" + r.getResiduo().getNome() + ";" + r.getQuantidade() + ";" + r.getPontosGerados() + ";" + r.getData());
                    writer.newLine();
                }
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("Erro ao salvar histórico.");
        }
    }
    
    public static void carregarHistorico(ArrayList<Usuario> usuarios, ArrayList<Residuo> residuos) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("historico.txt"));

            String linha;

            while((linha = reader.readLine()) != null) {

                String[] dados = linha.split(";");

                String email = dados[0];
                String nomeResiduo = dados[1];
                int quantidade = Integer.parseInt(dados[2]);
                int pontos = Integer.parseInt(dados[3]);
                LocalDate data = LocalDate.parse(dados[4]);

                Usuario usuarioEncontrado = null;

                for(Usuario u : usuarios) {
                    if (u.getEmail().equals(email)) {
                        usuarioEncontrado = u;
                        break;
                    }
                }

                Residuo residuoEncontrado = null;

                for(Residuo r : residuos) {
                    if(r.getNome().equals(nomeResiduo)) {
                        residuoEncontrado = r;
                        break;
                    }
                }

                if(usuarioEncontrado != null && residuoEncontrado != null) {
                    RegistroDescarte registro = new RegistroDescarte(usuarioEncontrado, residuoEncontrado, quantidade, pontos, data);
                    usuarioEncontrado.getHistorico().add(registro);
                }
            }
            reader.close();

        } catch(IOException e) {
            System.out.println("Histórico não encontrado.");
        }
    }
}

package com.mycompany.sistemareciclagem;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaReciclagem {

    public static void main(String[] args) {
        
        
        Scanner sc = new Scanner(System.in);

        ArrayList<Usuario> usuarios = ArquivoUtilitarios.carregarUsuarios();
        ArrayList<Residuo> residuos = carregarResiduos();
        ArrayList<Recompensa> recompensas = carregarRecompensas();
        ArrayList<Ecoponto> ecopontos = carregarEcopontos(residuos);
        ArquivoUtilitarios.carregarHistorico(usuarios, residuos);
        
        int opcao;

        do {
            System.out.println("\n===== ECO SMART =====");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastro");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Email: ");
                    String emailLogin = sc.nextLine();
                    System.out.print("Senha: ");
                    String senhaLogin = sc.nextLine();
                    Usuario usuarioLogado = null;
                    for (Usuario u : usuarios) {
                        if (u.getEmail().equals(emailLogin) && u.getSenha().equals(senhaLogin)) {
                            usuarioLogado = u;
                            break;
                        }
                    }
                    
                    if (usuarioLogado != null) {
                        System.out.println("\nBem-vindo " + usuarioLogado.getNome());
                        menuPrincipal(sc, usuarioLogado, residuos, usuarios, recompensas, ecopontos);//login
                    } else {
                        System.out.println("Email ou senha inválidos!");
                    }
                    break;

                case 2:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
                    Usuario usuario = new Usuario(nome, email, senha);
                    usuarios.add(usuario);
                    ArquivoUtilitarios.salvarUsuarios(usuarios);
                    System.out.println("Usuário cadastrado!");
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");

            }
        } while(opcao != 0);

        sc.close();
        
    }
    
    public static void menuPrincipal( Scanner sc, Usuario usuarioLogado, ArrayList<Residuo> residuos, ArrayList<Usuario> usuarios, ArrayList<Recompensa> recompensas, ArrayList<Ecoponto> ecopontos) {
        int opcao;
        do {
            System.out.println(
                    "\n===== MENU PRINCIPAL =====");

            System.out.println("Usuário: " + usuarioLogado.getNome());
            System.out.println("Pontos: "  + usuarioLogado.getPontos());
 
            System.out.println();

            System.out.println("1 - Consultar Resíduo");
            System.out.println("2 - Ecopontos");
            System.out.println("3 - Registrar Descarte");
            System.out.println("4 - Meu Perfil");
            System.out.println("5 - Ranking");
            System.out.println("6 - Recompensas");
            System.out.println("0 - Logout");

            System.out.print("\nEscolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    consultarResiduo(sc, residuos, usuarioLogado, usuarios);
                    break;

                case 2:
                    mostrarEcopontos(ecopontos);
                    break;

                case 3:
                    registrarDescarte(sc, residuos, usuarioLogado, usuarios);
                    break;

                case 4:
                    mostrarPerfil(usuarioLogado);
                    break;

                case 5:
                    mostrarRanking(usuarios);
                    break;

                case 6:
                    mostrarRecompensas(sc,usuarioLogado,recompensas);
                    break;

                case 0:
                    System.out.println("Logout realizado!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
            
        } while (opcao != 0);
    }
    
    public static ArrayList<Residuo> carregarResiduos() {

        ArrayList<Residuo> residuos = new ArrayList<>();
        
        //PAPEL
        residuos.add(new ResiduoComum("Jornal", TipoResiduo.PAPEL, CorLixeira.AZUL, "Papel utilizado para notícias e leitura.", "Coleta Seletiva Comum"));
        residuos.add(new ResiduoComum("Revista", TipoResiduo.PAPEL, CorLixeira.AZUL, "Revistas e materiais impressos.", "Coleta Seletiva Comum"));
        residuos.add(new ResiduoComum( "Papelão", TipoResiduo.PAPEL, CorLixeira.AZUL, "Caixas e embalagens de papelão.", "Coleta Seletiva Comum"));
        residuos.add(new ResiduoComum("Folha A4", TipoResiduo.PAPEL, CorLixeira.AZUL, "Folhas de papel para impressão.", "Coleta Seletiva Comum"));
        //PLASTICO
        residuos.add(new ResiduoComum("Garrafa PET", TipoResiduo.PLASTICO, CorLixeira.VERMELHA, "Garrafa plástica reciclável.", "Coleta Seletiva Comum"));
        residuos.add(new ResiduoComum("Sacola Plástica", TipoResiduo.PLASTICO, CorLixeira.VERMELHA, "Sacolas plásticas limpas.", "Coleta Seletiva Comum"));
        residuos.add(new ResiduoComum("Embalagem de Shampoo", TipoResiduo.PLASTICO, CorLixeira.VERMELHA, "Frascos plásticos de higiene.", "Coleta Seletiva Comum"));
        residuos.add(new ResiduoComum("Pote de Margarina", TipoResiduo.PLASTICO, CorLixeira.VERMELHA, "Pote plástico reciclável.", "Coleta Seletiva Comum"));
        //METAL
        residuos.add(new ResiduoComum("Lata de Alumínio", TipoResiduo.METAL, CorLixeira.AMARELA, "Latas de refrigerante e bebidas.", "Coleta Seletiva Comum"));
        residuos.add(new ResiduoComum( "Lata de Conserva", TipoResiduo.METAL, CorLixeira.AMARELA, "Latas metálicas de alimentos.", "Coleta Seletiva Comum"));
        //VIDRO
        residuos.add(new ResiduoComum("Garrafa de Vidro", TipoResiduo.VIDRO, CorLixeira.VERDE, "Garrafas de bebidas em vidro.","Coleta Seletiva Comum"));
        residuos.add(new ResiduoComum("Pote de Vidro", TipoResiduo.VIDRO, CorLixeira.VERDE,"Potes utilizados para alimentos.","Coleta Seletiva Comum"));
        //ELETRONICO
        residuos.add(new ResiduoEletronico("Pilha", "Não deve ser descartada no lixo comum.", "Ecoponto Centro"));
        residuos.add(new ResiduoEletronico("Bateria","Necessita descarte especializado.","Ecoponto Centro"));
        residuos.add(new ResiduoEletronico("Celular", "Equipamento eletrônico com componentes recicláveis.", "Ecoponto Centro"));
        residuos.add(new ResiduoEletronico("Notebook","Equipamento eletrônico com peças recicláveis.", "Ecoponto Centro"));
        return residuos;
    }
    
    public static void registrarDescarte(Scanner sc, Usuario usuarioLogado, Residuo residuoSelecionado, ArrayList<Usuario> usuarios) {

        System.out.print("Quantidade descartada: ");
        int quantidade = sc.nextInt();
        sc.nextLine();

        if(quantidade <= 0) {
            System.out.println("Quantidade inválida!");
            return;
        }
        
        int pontos = residuoSelecionado.calcularPontos(quantidade);

        RegistroDescarte registro = new RegistroDescarte(usuarioLogado, residuoSelecionado, quantidade, pontos);

        usuarioLogado.getHistorico().add(registro);

        usuarioLogado.adicionarPontos(pontos);
        ArquivoUtilitarios.salvarHistorico(usuarios);
        ArquivoUtilitarios.salvarUsuarios(usuarios);
        
        System.out.println("\nDescarte registrado com sucesso!");
        System.out.println("Material: " + residuoSelecionado.getNome());
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Você ganhou " + pontos + " pontos!");
        System.out.println("Total de pontos: " + usuarioLogado.getPontos());
    }
    
    public static void registrarDescarte(Scanner sc, ArrayList<Residuo> residuos, Usuario usuarioLogado, ArrayList<Usuario> usuarios) {

        System.out.println("\n===== REGISTRAR DESCARTE =====");
        TipoResiduo[] tipos = TipoResiduo.values();

        for(int i = 0; i < tipos.length; i++) {
            System.out.println((i + 1) + " - " + tipos[i]);
        }

        System.out.print("\nEscolha a categoria: ");
        int opcaoCategoria = sc.nextInt();
        sc.nextLine();

        if(opcaoCategoria < 1 ||opcaoCategoria > tipos.length) {
            System.out.println("Categoria inválida!");
            return;
        }

        TipoResiduo categoriaEscolhida = tipos[opcaoCategoria - 1];

        ArrayList<Residuo> filtrados = new ArrayList<>();

        int contador = 1;

        System.out.println("\n===== MATERIAIS =====");

        for(Residuo r : residuos) {
            if(r.getTipo() == categoriaEscolhida) {
                filtrados.add(r);
                System.out.println(contador + " - " + r.getNome());
                contador++;
            }
        }

        if(filtrados.isEmpty()) {
            System.out.println("Nenhum material encontrado.");
            return;
        }

        System.out.print("\nEscolha: ");

        int opcaoMaterial = sc.nextInt();
        sc.nextLine();

        if(opcaoMaterial < 1 || opcaoMaterial > filtrados.size()) {
            System.out.println("Material inválido!");
            return;
        }

        Residuo residuoSelecionado = filtrados.get(opcaoMaterial - 1);

        registrarDescarte(sc, usuarioLogado, residuoSelecionado, usuarios);
    }
    
    public static void consultarResiduo(Scanner sc, ArrayList<Residuo> residuos, Usuario usuarioLogado, ArrayList<Usuario> usuarios) {
        System.out.println("\n===== CATEGORIAS =====");
        
        TipoResiduo[] tipos = TipoResiduo.values();
        
        for(int i = 0; i < tipos.length; i++) {
            System.out.println((i + 1) + " - " + tipos[i]);
        }

        System.out.print("\nEscolha: ");

        int opcaoCategoria = sc.nextInt();
        sc.nextLine();

        if(opcaoCategoria < 1 || opcaoCategoria > tipos.length) {
            System.out.println("Categoria inválida!");
            return;
        }
        TipoResiduo categoriaEscolhida = tipos[opcaoCategoria - 1];
        
        ArrayList<Residuo> filtrados = new ArrayList<>();

        System.out.println("\n===== MATERIAIS =====");
        
        int contador = 1;
        
        for(Residuo r : residuos) {
            if(r.getTipo() == categoriaEscolhida) {
                filtrados.add(r);
                System.out.println(contador + " - " + r.getNome());
                contador++;
            }
        }
        
        if(filtrados.isEmpty()) {
            System.out.println("Nenhum resíduo cadastrado nessa categoria.");
            return;
        }
        System.out.print("\nEscolha: ");

        int opcaoMaterial = sc.nextInt();
        sc.nextLine();
        
        if(opcaoMaterial < 1 || opcaoMaterial > filtrados.size()) {
            System.out.println("Material inválido!");
            return;
        }
        Residuo residuoSelecionado = filtrados.get(opcaoMaterial - 1);
        
        System.out.println("\n===== INFORMAÇÕES =====");
        System.out.println("Nome: " + residuoSelecionado.getNome());
        System.out.println("Categoria: " + residuoSelecionado.getTipo());
        System.out.println("Cor da Lixeira: " + residuoSelecionado.getCorLixeira());
        System.out.println("Local de descarte: " + residuoSelecionado.getLocalDescarte());
        System.out.println("Descrição: " + residuoSelecionado.getDescricao());
        System.out.println("Pontos por unidade: "+ residuoSelecionado.calcularPontos(1));
        System.out.println("\nDeseja registrar um descarte?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        int resposta = sc.nextInt();
        sc.nextLine();

        //REGISTRAR DESCARTE DEPOIS DA CONSULTA
        if(resposta == 1) {
            registrarDescarte(sc, usuarioLogado, residuoSelecionado, usuarios);
        }
    }
    
    public static void mostrarPerfil(Usuario usuarioLogado) {

        System.out.println("\n===== MEU PERFIL =====");

        System.out.println("Nome: " + usuarioLogado.getNome());
        System.out.println("Email: " + usuarioLogado.getEmail());
        System.out.println("Pontos: " + usuarioLogado.getPontos());

        System.out.println("\n===== HISTÓRICO =====");
        
        if(usuarioLogado.getHistorico().isEmpty()) {
            System.out.println("Nenhum descarte registrado.");
            return;
        }

        for(RegistroDescarte r : usuarioLogado.getHistorico()) {
            System.out.println("----------------------");
            System.out.println("Data: " + r.getData());
            System.out.println("Material: " + r.getResiduo().getNome());
            System.out.println("Quantidade: " + r.getQuantidade());
            System.out.println("Pontos: " + r.getPontosGerados());
        }
    }
    
    public static void mostrarRanking(ArrayList<Usuario> usuarios) {
        System.out.println("\n===== RANKING =====");

        if(usuarios.isEmpty()){
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        
        ArrayList<Usuario> ranking = new ArrayList<>(usuarios);

        ranking.sort((u1, u2) ->Integer.compare( u2.getPontos(),u1.getPontos()));

        int posicao = 1;

        for(Usuario u : ranking) {
            System.out.println(posicao + "° - " + u.getNome() + " | " + u.getPontos()+ " pontos");
            posicao++;
        }
    }
    
    public static ArrayList<Recompensa> carregarRecompensas() {

        ArrayList<Recompensa> recompensas = new ArrayList<>();

        recompensas.add(new Recompensa("Cupom 5%","Cupom de desconto em lojas parceiras.",100));
        recompensas.add(new Recompensa("Cupom 10%","Cupom de desconto maior para usuários ativos.",250));
        recompensas.add(new Recompensa("Caneca Sustentável","Caneca reutilizável personalizada Eco Smart.",500));
        recompensas.add(new Recompensa("Kit Ecológico","Kit com ecobag e garrafa reutilizável.",1000));
        return recompensas;
    }
    
    public static void mostrarRecompensas(Scanner sc, Usuario usuarioLogado, ArrayList<Recompensa> recompensas) {

        System.out.println("\n===== RECOMPENSAS =====");
        System.out.println("Seus pontos: " + usuarioLogado.getPontos());

        int contador = 1;

        for(Recompensa r : recompensas) {

            System.out.println("\n" + contador + " - " + r.getNome());
            System.out.println("Descrição: " + r.getDescricao());
            System.out.println("Custo: " + r.getCustoPontos() + " pontos");

            if(usuarioLogado.getPontos() >= r.getCustoPontos()) {
                System.out.println("Status: Disponível");
            } else{
                System.out.println("Status: Pontos insuficientes");
            }

            contador++;
        }
        
        System.out.println("\n0 - Voltar");
        System.out.print("Escolha uma recompensa: ");

        int opcao = sc.nextInt();
        sc.nextLine();
        
        if(opcao == 0){
            return;
        }

        if(opcao < 1 || opcao > recompensas.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        Recompensa recompensaSelecionada = recompensas.get(opcao - 1);
        
        if(usuarioLogado.getPontos() < recompensaSelecionada.getCustoPontos()) {
            System.out.println("\nPontos insuficientes!");
            return;
        }
        
        System.out.println("\nDeseja confirmar o resgate?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        System.out.print("Escolha: ");
        
        int confirmar = sc.nextInt();
        sc.nextLine();
        
        if(confirmar != 1) {
            System.out.println("Resgate cancelado!");
            return;
        }
        
        usuarioLogado.removerPontos(recompensaSelecionada.getCustoPontos());
        
        System.out.println("\nRecompensa resgatada com sucesso!");
        System.out.println("Recompensa: " + recompensaSelecionada.getNome());
        System.out.println("Pontos restantes: "+ usuarioLogado.getPontos());
    }
    
    public static ArrayList<Ecoponto> carregarEcopontos(ArrayList<Residuo> residuos) {

        ArrayList<Ecoponto> ecopontos = new ArrayList<>();

        Ecoponto centro = new Ecoponto("Ecoponto Centro","Av. Rio Branco, 100");
        Ecoponto camobi = new Ecoponto("Ecoponto Camobi", "Faixa Nova, 200");

        for(Residuo r : residuos) {
            if(r.getTipo() == TipoResiduo.ELETRONICO) {
                centro.adicionarResiduo(r);
            } else{
                camobi.adicionarResiduo(r);
            }
        }

        ecopontos.add(centro);
        ecopontos.add(camobi);

        return ecopontos;
    }
    
    public static void mostrarEcopontos(ArrayList<Ecoponto> ecopontos) {
        System.out.println("\n===== ECOPONTOS =====");

        for(Ecoponto e : ecopontos) {
            System.out.println("\nNome: " + e.getNome());
            System.out.println("Endereço: "+ e.getEndereco());
            System.out.println("Resíduos aceitos:");
            for(Residuo r : e.getResiduosAceitos()){
                System.out.println("- " + r.getNome());
            }
        }
    }
}

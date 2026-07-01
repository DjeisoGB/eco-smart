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

            opcao = lerInteiro(sc);

            switch (opcao) {
                //LOGIN DE USUARIO
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
                //CADASTRO DE USUARIO
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

            opcao = lerInteiro(sc);

            switch (opcao) {
                // CONSULTAR RESIDUO
                case 1:
                    consultarResiduo(sc, residuos, usuarioLogado, usuarios);
                    break;
                //MOSTRA ECOPONTOS
                case 2:
                    mostrarEcopontos(ecopontos);
                    break;
                //REGISTRA DESCARTE
                case 3:
                    registrarDescarte(sc, residuos, usuarioLogado, usuarios);
                    break;
                //PERFIL
                case 4:
                    mostrarPerfil(usuarioLogado);
                    break;
                //MOSTRA O RANKING
                case 5:
                    mostrarRanking(usuarios);
                    break;
                //RECOMPENSAS
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
        //ORGANICO
        residuos.add(new ResiduoComum("Casca de Banana(200g)", TipoResiduo.ORGANICO, CorLixeira.MARROM, "Resíduo orgânico biodegradável.", "Composteira ou coleta orgânica"));
        residuos.add(new ResiduoComum("Restos de Comida(700g)", TipoResiduo.ORGANICO,CorLixeira.MARROM, "Sobras de alimentos.", "Composteira ou coleta orgânica"));
        residuos.add(new ResiduoComum("Borra de Café ou Erva de Chimarrão(500g)", TipoResiduo.ORGANICO, CorLixeira.MARROM, "Excelente para compostagem.", "Composteira"));
        //ELETRONICO
        residuos.add(new ResiduoEletronico("Pilha", "Não deve ser descartada no lixo comum.", "Ecoponto Centro"));
        residuos.add(new ResiduoEletronico("Bateria","Necessita descarte especializado.","Ecoponto Centro"));
        residuos.add(new ResiduoEletronico("Celular", "Equipamento eletrônico com componentes recicláveis.", "Ecoponto Centro"));
        residuos.add(new ResiduoEletronico("Notebook","Equipamento eletrônico com peças recicláveis.", "Ecoponto Centro"));
        //PERIGOSO
        residuos.add(new ResiduoPerigoso("Lâmpada Fluorescente", "Contém mercúrio.", "Ecoponto Centro"));
        residuos.add(new ResiduoPerigoso("Tinta", "Produto químico.", "Ecoponto Centro"));
        residuos.add(new ResiduoPerigoso("Óleo de Motor", "Não deve ser descartado no esgoto.", "Ecoponto Centro"));
        residuos.add(new ResiduoPerigoso("Agrotóxicos", "Necessitam descarte especializado.", "Ecoponto Centro"));
        return residuos;
    }
    
    public static void registrarDescarte(Scanner sc, Usuario usuarioLogado, Residuo residuoSelecionado, ArrayList<Usuario> usuarios) {

        // Solicita a quantidade do material descartado
        System.out.print("Quantidade descartada: ");
        int quantidade = sc.nextInt();
        sc.nextLine();
        
        // Verifica se a quantidade informada é válida
        if(quantidade <= 0) {
            System.out.println("Quantidade inválida!");
            return;
        }
        
        // Calcula os pontos obtidos de acordo com o tipo e a quantidade do resíduo
        int pontos = residuoSelecionado.calcularPontos(quantidade);

        // Cria um registro do descarte realizado
        RegistroDescarte registro = new RegistroDescarte(usuarioLogado, residuoSelecionado, quantidade, pontos);

        // Adiciona o registro ao histórico do usuário
        usuarioLogado.getHistorico().add(registro);

        // Atualiza a pontuação do usuário
        usuarioLogado.adicionarPontos(pontos);
        // Salva as alterações nos arquivos TXT
        ArquivoUtilitarios.salvarHistorico(usuarios);
        ArquivoUtilitarios.salvarUsuarios(usuarios);
        
        // Exibe uma confirmação do descarte realizado
        System.out.println("\nDescarte registrado com sucesso!");
        System.out.println("Material: " + residuoSelecionado.getNome());
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Você ganhou " + pontos + " pontos!");
        System.out.println("Total de pontos: " + usuarioLogado.getPontos());
    }
    
    public static void registrarDescarte(Scanner sc, ArrayList<Residuo> residuos, Usuario usuarioLogado, ArrayList<Usuario> usuarios) {

        // Exibe todas as categorias de resíduos disponíveis
        System.out.println("\n===== REGISTRAR DESCARTE =====");
        TipoResiduo[] tipos = TipoResiduo.values();

        for(int i = 0; i < tipos.length; i++) {
            System.out.println((i + 1) + " - " + tipos[i]);
        }

        // Solicita ao usuário a categoria desejada
        System.out.print("\nEscolha a categoria: ");
        int opcaoCategoria = lerInteiro(sc);

        // Verifica se a categoria escolhida é válida
        if(opcaoCategoria < 1 ||opcaoCategoria > tipos.length) {
            System.out.println("Categoria inválida!");
            return;
        }

        // Obtém a categoria selecionada
        TipoResiduo categoriaEscolhida = tipos[opcaoCategoria - 1];

        // Lista que armazenará apenas os resíduos da categoria escolhida
        ArrayList<Residuo> filtrados = new ArrayList<>();

        int contador = 1;

        System.out.println("\n===== MATERIAIS =====");

         // Filtra e exibe apenas os materiais da categoria selecionada
        for(Residuo r : residuos) {
            if(r.getTipo() == categoriaEscolhida) {
                filtrados.add(r);
                System.out.println(contador + " - " + r.getNome());
                contador++;
            }
        }

        // Caso não existam materiais nessa categoria
        if(filtrados.isEmpty()) {
            System.out.println("Nenhum material encontrado.");
            return;
        }

        // Solicita ao usuário a escolha do material
        System.out.print("\nEscolha: ");

        int opcaoMaterial = lerInteiro(sc);

        // Verifica se o material informado é válido
        if(opcaoMaterial < 1 || opcaoMaterial > filtrados.size()) {
            System.out.println("Material inválido!");
            return;
        }

        // Recupera o material escolhido
        Residuo residuoSelecionado = filtrados.get(opcaoMaterial - 1);
        // Chama o método responsável por concluir o registro do descarte
        registrarDescarte(sc, usuarioLogado, residuoSelecionado, usuarios);
    }
    
    public static void consultarResiduo(Scanner sc, ArrayList<Residuo> residuos, Usuario usuarioLogado, ArrayList<Usuario> usuarios) {
        
        // Exibe todas as categorias de resíduos disponíveis
        System.out.println("\n===== CATEGORIAS =====");
        
        TipoResiduo[] tipos = TipoResiduo.values();
        
        for(int i = 0; i < tipos.length; i++) {
            System.out.println((i + 1) + " - " + tipos[i]);
        }
        
        // Solicita ao usuário a categoria desejada
        System.out.print("\nEscolha: ");

        int opcaoCategoria = lerInteiro(sc);

        // Verifica se a categoria informada é válida
        if(opcaoCategoria < 1 || opcaoCategoria > tipos.length) {
            System.out.println("Categoria inválida!");
            return;
        }
        // Obtém a categoria escolhida
        TipoResiduo categoriaEscolhida = tipos[opcaoCategoria - 1];
        
        // Lista que armazenará apenas os resíduos da categoria selecionada
        ArrayList<Residuo> filtrados = new ArrayList<>();

        System.out.println("\n===== MATERIAIS =====");
        
        int contador = 1;
        
        // Filtra e exibe os materiais pertencentes à categoria escolhida
        for(Residuo r : residuos) {
            if(r.getTipo() == categoriaEscolhida) {
                filtrados.add(r);
                System.out.println(contador + " - " + r.getNome());
                contador++;
            }
        }
        
        // Verifica se existem materiais cadastrados nessa categoria
        if(filtrados.isEmpty()) {
            System.out.println("Nenhum resíduo cadastrado nessa categoria.");
            return;
        }
        // Solicita ao usuário o material desejado
        System.out.print("\nEscolha: ");

        int opcaoMaterial = lerInteiro(sc);
        
        // Valida a opção escolhida
        if(opcaoMaterial < 1 || opcaoMaterial > filtrados.size()) {
            System.out.println("Material inválido!");
            return;
        }
        
        // Recupera o resíduo selecionado
        Residuo residuoSelecionado = filtrados.get(opcaoMaterial - 1);
        
        // Exibe todas as informações sobre o resíduo
        System.out.println("\n===== INFORMAÇÕES =====");
        System.out.println("Nome: " + residuoSelecionado.getNome());
        System.out.println("Categoria: " + residuoSelecionado.getTipo());
        System.out.println("Cor da Lixeira: " + residuoSelecionado.getCorLixeira());
        System.out.println("Local de descarte: " + residuoSelecionado.getLocalDescarte());
        System.out.println("Descrição: " + residuoSelecionado.getDescricao());
        System.out.println("Pontos por unidade: "+ residuoSelecionado.calcularPontos(1));
        // Pergunta ao usuário se deseja registrar o descarte imediatamente
        System.out.println("\nDeseja registrar um descarte?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        int resposta = sc.nextInt();
        sc.nextLine();

        //Caso a resposta seja positiva, registra o descarte
        if(resposta == 1) {
            registrarDescarte(sc, usuarioLogado, residuoSelecionado, usuarios);
        }
    }
    
    public static void mostrarPerfil(Usuario usuarioLogado) {

        // Exibe as informações básicas do usuário
        System.out.println("\n===== MEU PERFIL =====");
        // Mostra nome, email, pontuação e quantidade de descartes realizados
        System.out.println("Nome: " + usuarioLogado.getNome());
        System.out.println("Email: " + usuarioLogado.getEmail());
        System.out.println("Pontos: " + usuarioLogado.getPontos());
        System.out.println("Total de descartes: " + usuarioLogado.getHistorico().size());

        // Exibe o histórico de descartes do usuário
        System.out.println("\n===== HISTÓRICO =====");     
        // Verifica se o usuário já realizou algum descarte
        if(usuarioLogado.getHistorico().isEmpty()) {
            System.out.println("Nenhum descarte registrado.");
            return;
        }
        // Percorre o histórico e mostra todas as informações de cada descarte
        for(RegistroDescarte r : usuarioLogado.getHistorico()) {
            System.out.println("----------------------");
            System.out.println("Data: " + r.getData());
            System.out.println("Material: " + r.getResiduo().getNome());
            System.out.println("Quantidade: " + r.getQuantidade());
            System.out.println("Pontos: " + r.getPontosGerados());
            System.out.println("Local de descarte: " + r.getLocalDescarte());
        }
        
    }
    
    public static void mostrarRanking(ArrayList<Usuario> usuarios) {
        // Exibe o ranking de usuários ordenado pela pontuação
        System.out.println("\n===== RANKING =====");

        // Verifica se existe algum usuário cadastrado
        if(usuarios.isEmpty()){
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        
        // Cria uma cópia da lista para não alterar a ordem original
        ArrayList<Usuario> ranking = new ArrayList<>(usuarios);

        // Ordena os usuários do maior para o menor número de pontos
        ranking.sort((u1, u2) ->Integer.compare( u2.getPontos(),u1.getPontos()));

        int posicao = 1;

        // Exibe a classificação dos usuários
        for(Usuario u : ranking) {
            System.out.println(posicao + "° - " + u.getNome() + " | " + u.getPontos()+ " pontos");
            posicao++;
        }
    }
    
    public static ArrayList<Recompensa> carregarRecompensas() {

        // Cria e cadastra todas as recompensas disponíveis no sistema
        ArrayList<Recompensa> recompensas = new ArrayList<>();

        recompensas.add(new Recompensa("Cupom 5%","Cupom de desconto em lojas parceiras.",1000, "Secretaria Municipal do Meio Ambiente"));
        recompensas.add(new Recompensa("Cupom 10%","Cupom de desconto maior para usuários ativos.",2000, "Secretaria Municipal do Meio Ambiente"));
        recompensas.add(new Recompensa("Caneca Sustentável","Caneca reutilizável personalizada Eco Smart.",5000, "Ecoponto Centro"));
        recompensas.add(new Recompensa("Kit Ecológico","Kit com ecobag e garrafa reutilizável.",7000, "Ecoponto Camobi"));
        return recompensas;
    }
    
    public static void mostrarRecompensas(Scanner sc, Usuario usuarioLogado, ArrayList<Recompensa> recompensas) {

        // Exibe as recompensas disponíveis para resgate
        System.out.println("\n===== RECOMPENSAS =====");
        // Mostra a quantidade de pontos do usuário
        System.out.println("Seus pontos: " + usuarioLogado.getPontos());

        int contador = 1;

        // Percorre todas as recompensas cadastradas
        for(Recompensa r : recompensas) {

            System.out.println("\n" + contador + " - " + r.getNome());
            System.out.println("Descrição: " + r.getDescricao());
            System.out.println("Custo: " + r.getCustoPontos() + " pontos");

            // Verifica se o usuário possui pontos suficientes para resgatar a recompensa
            if(usuarioLogado.getPontos() >= r.getCustoPontos()) {
                System.out.println("Status: Disponível");
            } else{
                System.out.println("Status: Pontos insuficientes");
            }

            contador++;
        }
        
        System.out.println("\n0 - Voltar");
        // Solicita que o usuário escolha uma recompensa
        System.out.print("Escolha uma recompensa: ");

        int opcao = lerInteiro(sc);
        
        if(opcao == 0){
            return;
        }

        // Verifica se a opção escolhida é válida
        if(opcao < 1 || opcao > recompensas.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        // Obtém a recompensa selecionada
        Recompensa recompensaSelecionada = recompensas.get(opcao - 1);
        
        // Confere novamente se há pontos suficientes para o resgate
        if(usuarioLogado.getPontos() < recompensaSelecionada.getCustoPontos()) {
            System.out.println("\nPontos insuficientes!");
            return;
        }
        // Solicita confirmação antes de efetuar o resgate
        System.out.println("\nDeseja confirmar o resgate?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        System.out.print("Escolha: ");
        
        int confirmar = lerInteiro(sc);
        
        if(confirmar != 1) {
            System.out.println("Resgate cancelado!");
            return;
        }
        
        // Desconta os pontos do usuário após a confirmação
        usuarioLogado.removerPontos(recompensaSelecionada.getCustoPontos());
        
        System.out.println("\nRecompensa resgatada com sucesso!");
        System.out.println("Recompensa: " + recompensaSelecionada.getNome());
        System.out.println("Pontos restantes: " + usuarioLogado.getPontos());
        System.out.println("Local de retirada: " + recompensaSelecionada.getLocalRetirada());
    }
    
    public static ArrayList<Ecoponto> carregarEcopontos(ArrayList<Residuo> residuos) {

        // Cria os ecopontos cadastrados no sistema
        ArrayList<Ecoponto> ecopontos = new ArrayList<>();

        Ecoponto centro = new Ecoponto("Ecoponto Centro","Av. Rio Branco, 100");
        Ecoponto camobi = new Ecoponto("Ecoponto Camobi", "Faixa Nova, 200");

        // Adiciona os resíduos aos ecopontos de acordo com seu tipo
        for(Residuo r : residuos) {
            if(r.getTipo() == TipoResiduo.ELETRONICO) {
                centro.adicionarResiduo(r);
            } else{
                camobi.adicionarResiduo(r);
            }
        }

        // Adiciona os ecopontos na lista que será retornada
        ecopontos.add(centro);
        ecopontos.add(camobi);

        return ecopontos;
    }
    
    public static void mostrarEcopontos(ArrayList<Ecoponto> ecopontos) {
        // Exibe todos os ecopontos cadastrados
        System.out.println("\n===== ECOPONTOS =====");

        // Percorre cada ecoponto mostrando suas informações
        for(Ecoponto e : ecopontos) {
            System.out.println("\nNome: " + e.getNome());
            System.out.println("Endereço: "+ e.getEndereco());
            System.out.println("Resíduos aceitos:");
            // Lista todos os resíduos aceitos pelo ecoponto
            for(Residuo r : e.getResiduosAceitos()){
                System.out.println("- " + r.getNome());
            }
        }
    }
    
    //Serve para fazer a leitura da opção que o usuario escolher
    public static int lerInteiro(Scanner sc) {

        while (!sc.hasNextInt()) {
            System.out.println("Entrada inválida! Digite apenas números.");
            sc.nextLine();
        }

        int numero = sc.nextInt();
        sc.nextLine();

        return numero;
    }
}

Sobre o projeto:
O Eco Smart é um sistema desenvolvido em Java com o objetivo de incentivar a coleta seletiva por meio de um sistema de pontuação e recompensas. O usuário pode consultar informações sobre resíduos, registrar descartes, acumular pontos, acompanhar seu histórico e resgatar recompensas. O projeto foi desenvolvido utilizando os conceitos de Programação Orientada a Objetos (POO) e persistência de dados através de arquivos TXT.

Funcionalidades:
* Cadastro de usuários
* Login
* Consulta de resíduos
* Registro de descarte
* Sistema de pontuação
* Histórico de descartes
* Ranking de usuários
* Recompensas com resgate por pontos
* Consulta de ecopontos
* Persistência dos dados utilizando arquivos TXT

Tecnologias utilizadas:
* Java
* Programação Orientada a Objetos
* ArrayList
* Enum
* Herança
* Polimorfismo
* Classes Abstratas
* Manipulação de Arquivos (BufferedReader e BufferedWriter)
* LocalDate

Funcionamento:
1. O usuário realiza o cadastro.
2. Efetua login.
3. Consulta um resíduo.
4. Visualiza informações sobre descarte.
5. Registra o descarte.
6. Recebe pontos automaticamente.
7. O descarte é armazenado no histórico.
8. Os dados são salvos em arquivos TXT.
9. O usuário pode consultar seu perfil, ranking e resgatar recompensas.

O sistema utiliza dois arquivos TXT:
usuarios.txt, onde ele armazena coisas como:
* Nome
* Email
* Senha
* Pontos
* onde ele fica no seguinte formato: Nome;Email;Senha;Pontos
e o historico.txt, que armazena:
* Email do usuário
* Nome do resíduo
* Quantidade
* Pontos obtidos
* Data
* ficando no seguinte formato: Email;Residuo;Quantidade;Pontos;Data

Conceitos de POO utilizados: encapsulamento, herança, polimorfismo, abstração

Uso de Inteligência Artificial:
Durante o desenvolvimento deste projeto, foi utilizada a ferramenta ChatGPT como apoio. Ela foi empregada para auxiliar na procura, indicações e como utilizar bibliotecas como java.time e java.io.
Perguntas feitas para a ferramenta e suas respostas:
PERGUNTA:
"bibliotecas para mostrar momento que algo feito em java sem hora"
RESPOSTA:
Se você quer mostrar apenas o momento em que algo foi feito (data), sem exibir a hora, existem algumas bibliotecas muito usadas em Java.
1. java.time (Recomendado)
Desde o Java 8, a melhor opção é a API nativa java.time, sem precisar instalar bibliotecas.
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Main {
    public static void main(String[] args) {
        LocalDate data = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(data.format(formato));
    }
}
Saída:
26/06/2026
Se você quiser guardar apenas a data em um objeto:
LocalDate dataCriacao = LocalDate.now();
2. java.util.Date (mais antiga)
Se precisar usar as classes antigas:
import java.text.SimpleDateFormat;
import java.util.Date;
public class Main {
    public static void main(String[] args) {
        Date agora = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(formato.format(agora));
    }
}
3. Biblioteca Joda-Time
Era muito utilizada antes do Java 8, mas hoje praticamente foi substituída por java.time.
Exemplo:
LocalDate data = LocalDate.now();
System.out.println(data);
É necessário adicionar a dependência da biblioteca.

PERGUNTA:
"bibliotecas para mexer com arquivos em java"
RESPOSTA:
Se você quer trabalhar com arquivos em Java, existem bibliotecas nativas e algumas bibliotecas externas muito populares.

1. java.io (clássica)
É a biblioteca tradicional para leitura e escrita de arquivos.
Principais classes:
File
FileReader
FileWriter
BufferedReader
BufferedWriter
PrintWriter
Exemplo:
import java.io.FileWriter;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("arquivo.txt");
            writer.write("Olá, mundo!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

2. java.nio.file (Recomendado)
Introduzida no Java 7, é mais moderna, rápida e fácil de usar.
Principais classes:
Files
Path
Paths
Exemplo para escrever:
import java.nio.file.Files;
import java.nio.file.Path;
public class Main {
    public static void main(String[] args) throws Exception {
        Path caminho = Path.of("arquivo.txt");
        Files.writeString(caminho, "Olá, mundo!");
    }
}
Exemplo para ler:
String texto = Files.readString(Path.of("arquivo.txt"));
System.out.println(texto);

3. Scanner
Muito usado para ler arquivos linha por linha.
import java.io.File;
import java.util.Scanner;
Scanner sc = new Scanner(new File("arquivo.txt"));
while (sc.hasNextLine()) {
    System.out.println(sc.nextLine());
}
sc.close();

4. ObjectInputStream e ObjectOutputStream
Para salvar e recuperar objetos Java (serialização).
ObjectOutputStream out =
    new ObjectOutputStream(new FileOutputStream("dados.dat"));
    
5. Apache Commons IO (biblioteca externa)
Facilita várias operações com arquivos.
Exemplos:
Copiar arquivos
Mover arquivos
Ler e escrever com uma única linha
Excluir diretórios inteiros
FileUtils.copyFile(origem, destino);

6. Apache POI
Quando o objetivo é manipular arquivos do Microsoft Office.
Permite trabalhar com:
Excel (.xlsx)
Word (.docx)
PowerPoint (.pptx)

7. OpenCSV
Para ler e escrever arquivos CSV.
CSVReader reader = new CSVReader(new FileReader("dados.csv"));


Desenvolvedores:
* Djeiso Gabriel Bernardy
* Mateus Cremonese Wolff
* Murilo Mello da Silva
* Augusto Castanheira Paranhos

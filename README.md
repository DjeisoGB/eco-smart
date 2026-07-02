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
Perguntas feitas para a ferramenta:
"bibliotecas para mostrar momento que algo feito em java sem hora"
"bibliotecas para mexer com arquivos em java"
"analise se existe a possibilidade de o codigo quebrar nesta parte"

Uma observação rapida:
Adicionamos as opções -Dstdout.encoding=UTF-8 e -Dstderr.encoding=UTF-8 nas VM options do NetBeans para que o terminal utilizasse a codificação UTF-8, permitindo a exibição correta de caracteres em português, como acentos e o cedilha.
para fazer isso basta ir no projeto, clicar com o direito, ir em propriedades, depois na opção run e colocar nas VM options o comando *-Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8*

Desenvolvedores:
* Djeiso Gabriel Bernardy
* Mateus Cremonese Wolff
* Murilo Mello da Silva
* Augusto Castanheira Paranhos

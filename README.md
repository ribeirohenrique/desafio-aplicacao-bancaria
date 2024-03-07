## desafio-aplicacao-bancaria
### Descrição
Repositório para armazenamento do código referente ao desafio Accenture de criação de uma aplicação bancária utilizando os conceitos aprendidos durante o curso de Java



### Solicitação
Faça uma aplicação bancária que permita transferências de valores. O sistema deve conter as seguintes funcionalidades (mínimas):

- Cadastro de conta bancária;
- Numero da conta, numero agencia, cliente, saldo, limite, tipo da conta;
- Depósito;
- Retirada (saque);
- Alteração de limite;
- Transferências;
- Pensem em limitar o valor conforme o horário;
- Exportação de histórico de transações (CSV);
- A aplicação deve conter um menu via terminal para seleção da operação desejada.

### Considerações
- Perceba que a descrição do sistema foi feita de forma genérica, propositalmente para encorajar a extensão de funcionalidades conforme a sua necessidade;
- As funcionalidades descritas acima são básicas e mandatórias para o funcionamento e aceite da entrega;
- Utilize o máximo de conceitos abordados durante o curso. Ex: menus com Scanner, boas práticas de nomenclatura, herança, listas, interfaces, trabalho com arquivos, etc;
- Não há necessidade de persistência em bancos de dados. Pensem numa estrutura utilizando listas/mapas em memória para armazenamento;
- Os relacionamentos entre as classes (entidades do sistema) ficam ao seu critério. Utilizem quantas classes e atributos julgarem necessário para a modelagem;
- Sigam o princípio: baixo acoplamento, alta coesão;
- Para estruturar seu código, imagine a aplicação como um entregável que possa ser evoluído sem a necessidade de grande refatoração. Ex: não tenho um banco de dados hoje ou uma API Rest para acesso às operações, mas posso construir um módulo sem afetar O DOMÍNIO do sistema (classes de negócio e entidades);
- Pensem que toda operação repetitiva pode ter sua própria classe ou método, como apresentação das informações na tela (ou input), que pode ter dados como parâmetros.
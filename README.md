# pettingCore

Nesse arquivo, serão apresentadas as configurações necessárias para executar o projeto PettingCore com sucesso.

Inicialmente, informamos que para efeito de testes recomenda-se o uso da seguinte IDE, por mais que o projeto tenha sido desenvolvido para uso em qualquer que aceite desenvolvimento na linguagem de programação Java:

* IntelliJ (Community)

Após abrir o projeto, teremos alguns passos a seguir para que complete a configuração. São eles:

1. Baixar e configurar JDK
2. Instalar plugins na IDE
3. Gerar arquivos para QueryDSL
4. Executar projeto


## JDK

Clicando nesse [link](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), baixe a versão "Java SE Development Kit 8u172" correspondente com seu sistema operacional.

Em seguida na IDE:

1. Vá em "File > Project Structure..."
2. No menu "Project", clique em "New... > JDK"
3. Na nova janela, selecione o caminho até a pasta de nome "jdk1.8.0_172" baixada anteriormente
4. Clique em "OK" e "OK" novamente

Durante alguns minutos, seu projeto estará finalizando as configurações restantes para esse passo. Assim que a barra de progresso abaixo completar, siga para os seguintes passos.

## Instalar Plugins

Dentre os plugins que é recomendado instalar, um dos principais é o Lombok. Para isso, siga esses passos:

1. Vá em "File > Settings..."
2. No menu "Plugins", clique em "Browse repositories..."
3. Nessa janela, pesquise "Lombok Plugin"
4. Clique em "Install", e em seguida "Accept"
5. Após a instalação, clique em "Restart IntelliJ IDEA" e "OK"
6. Clique em "OK" novamente e "Restart"
7. Finalmente espere sua IDE reinicializar

Durante alguns minutos, seu projeto estará finalizando as configurações restantes para esse passo. Assim que a barra de progresso abaixo completar, siga para os seguintes passos.


## Gerar arquivos QueryDSL

Para executar esse passo devemos executar alguns comandos do nosso gerenciador de dependências, o Maven.

Siga os passos abaixo:

1. Vá em "View > Tool Windows > Maven Projects"
2. Caso a aba que tenha se aberto esteja vazia, clique nas setas azuis "Reimport All Maven Projects"
3. Expanda "pettingCore" e "Lifecycle", e clique duas vezes em "clean"
4. Na aba abaixo, você poderá acompanhar o progresso do comando
5. Espere "BUILD SUCCESS" ou "Process finished with exit code 0" aparecerem
6. No menu anterior ("Maven Projects"), clique em "install"
7. Espere "BUILD SUCCESS" ou "Process finished with exit code 0" aparecerem novamente


## Executar o projeto

Para que seja possível rodar o projeto, devemos seguir os passos a baixo:

1. Vá em "View > Tool Windows > Maven Projects"
2. Caso a aba que tenha se aberto esteja vazia, clique nas setas azuis "Reimport All Maven Projects"
3. Expanda "pettingCore", "Plugins", e "spring-boot" e clique duas vezes em "spring-boot:run"
4. Aguarde até que a linha "Started Application" apareça

Pronto! Sua aplicação está agora sendo executada e escutando na porta 8080.

Caso queira testar acesse o endpoint do Swagger colocando o seguinte endereço na URL de seu navegador:
http://localhost:8080/swagger-ui.html
# Aplicativo lista repositório git hub



 Desenvolveu-se as funcionalidades de:
  
  	Lista de repositório: Responsável por exibir a lista dos repositórios mais populares de Java;
  	Lista de pull request: Responsável por exibir a lista de pull request de um repositório selecionado na tela que lista os repositórios;
        Visualização do pull request no browser: Responsável por exibir a página do Pull Request selecionado na tela que lista os pull request.
        
        Aplicou-se testes unitários na classe de view model para os casos de sucesso e erro das requisições. 
  		

Arquitetura utilizada:

  	Utilizou-se a arquitetura de componentes do google, com as camadas de:
  
  	View: Contém as activitys;
  	Viewmodel: Camada responsável por fazer a ligação da camada de view com a camada de repository;
        UseCase: responsável por chamar os métodos de requisição da camada de repositor.
  	Repository: Camada responsável por acessar o webservice;   	
	
  

Bibliotecas utilizadas:

 Além das bibliotecas de design do google, foram utilizadas as seguintes bibliotecas:  
 
  

  	Dagger2: Utilizada para a injeção de independencia do aplicativo;
  
 	Gson: Utilizada para a converção dos objetos;
  
  	Retrofit: Utilizada para as requisições ao web service;
         
        RxKotlin: para chamadas assincronas 
  
  
	
	
  
  
Requisitos minimos:
  
  	Android Studio 3.5
 

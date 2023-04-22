/* ***************************************************************
* Autor............: Antonio Vinicius Silva Dutra
* Matricula........: 202110810
* Inicio...........: 19/04/2023
* Ultima alteracao.: 22/04/2023
* Nome.............: SecondTrain.java
* Funcao...........: executa os comandos de thread do primeiro trem (trem vermelho), e controla as animacoes do trem
*************************************************************** */
package model;
import control.*;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class SecondTrain extends Thread{
  private MainControl mainController;
  private ImageView redTrain;
  private int speed = 6;
  private int positionX;
  private int positionY;

  /********************************************************************
  * Metodo: SecondTrain()
  * Funcao: construtor da classe
  * Parametros: objeto MainControl
  * Retorno: void
  ****************************************************************** */
  public SecondTrain(MainControl mainController) {
    this.mainController = mainController;
    this.redTrain = mainController.getImageViewRedTrain();
    this.speed = 6;
  }//fim do metodo SecondTrain()

  /********************************************************************
  * Metodo: run()
  * Funcao: executa um loop infinito e dentro desse loop eh responsavel por movimentar o trem para a posicao correta nos trilhos.
  Movimenta os trens na tela de acordo com a posicao e solucao escolhida pelo usuario.
  Alem disso, verifica as condicoes utilizando variavel de travamento ou estrita alternancia da ponte: 
  se ela esta livre para passagem do trem ou nao. Utiliza os metodos moveUp(), moveDown(), moveRight() e moveLeft() que, atraves da 
  classe Platform.runLater() atualiza a posicao do trem na tela
  * Parametros: nenhum
  * Retorno: void
  ****************************************************************** */
  public void run(){
    while(true){//loop infinito para os trens   
      switch(mainController.getSolution()){//verificando a solucao escolhida pelo usuario
      case(1)://Posicoes utilizando variavel de travamento
        switch(mainController.getPosition()){
          case(1):
            positionX = 0;
      
            Platform.runLater( () -> redTrain.setX(positionX=90));//setando a posicao inicial do trem no eixo X
            Platform.runLater( () -> redTrain.setY(positionY));

            moveLeft(-110);
            rotate(360);
            //Inicio Regiao Critica
            secondBridgeCheck();
            mainController.setSecondFree(1);//fechando o segundo tunel

            moveUp(-60);
            rotate(270);
            moveLeft(-285);
            rotate(180);
            moveDown(5);
            rotate(270);
            //Fim Regiao Critica (tunel 2)
            mainController.setSecondFree(0);//liberando o segundo tunel
          
            moveLeft(-430);
            rotate(360);

            //inicio regiao critica
            firstBridgeCheck();
            mainController.setFirstFree(1);//fechando o primeiro tunel
          
            moveUp(-60);
            rotate(270);
            moveLeft(-605);
            rotate(180);
            moveDown(5);
            rotate(270);
            //Fim regiao critica 
            mainController.setFirstFree(0);//liberando o primeiro tunel
      
            moveLeft(-800);
          break;
          case(2):
            positionX = 0;
            rotate(90);
            Platform.runLater( () -> redTrain.setX(positionX=-800));//setando a posicao inicial do trem no eixo X
            Platform.runLater( () -> redTrain.setY(positionY=0));

            moveRight(-604);
            rotate(360);
            //Inicio Regiao Critica
            firstBridgeCheck();
            mainController.setFirstFree(1);//fechando o primeiro tunel
            moveUp(-58);
      
            rotate(90);
            moveRight(-440);
            rotate(180);
            mainController.setFirstFree(0);//liberando o primeiro tunel
            moveDown(10);
            rotate(90);
            //Fim Regiao Critica
      
            moveRight(-270);
            rotate(360);
            
            //inicio regiao critica
            secondBridgeCheck();
            mainController.setSecondFree(1);//fechando o segundo tunel
            moveUp(-55);
            rotate(90);
            moveRight(-120);
            rotate(180);
            mainController.setSecondFree(0);//liberando o segundo tunel
            moveDown(5);
            rotate(90);
            moveRight(100);
          break;
          case(3):
            positionX = 0;
            rotate(90);
            Platform.runLater( () -> redTrain.setX(positionX=-800));//setando a posicao inicial do trem no eixo X
            Platform.runLater( () -> redTrain.setY(positionY=-125));//setando a posicao inicial do trem no eixo Y
  
            moveRight(-604);
            rotate(180);
           //Inicio regiao critica
            firstBridgeCheck();
            mainController.setFirstFree(1);//fechando o primeiro tunel
            moveDown(-58);
        
            rotate(90);
            moveRight(-440);
            rotate(360);
            moveUp(-125);
            rotate(90);
            //Fim regiao critica
            mainController.setFirstFree(0);//liberando o primeiro tunel
        
            moveRight(-270);
            rotate(180);
  
            //inicio regiao critica
            secondBridgeCheck();
            mainController.setSecondFree(1);//fechando o segundo tunel
            moveDown(-58);
            rotate(90);
            moveRight(-120);
            rotate(360);
            moveUp(-125);
            rotate(90);
            //fim regiao critica
            mainController.setSecondFree(0);//liberando o segundo tunel
            moveRight(100);
          break;
          case(4):
            positionX = 0;
      
            Platform.runLater( () -> redTrain.setX(positionX=90));//setando a posicao inicial do trem no eixo X
            Platform.runLater( () -> redTrain.setY(positionY));
  
            moveLeft(-110);
            rotate(360);
            //Inicio Regiao Critica
            secondBridgeCheck();
            mainController.setSecondFree(1);//fechando o segundo tunel

            moveUp(-60);
            rotate(270);
            moveLeft(-285);
            rotate(180);
            moveDown(5);
            rotate(270);
            //Fim Regiao Critica (tunel 2)
            mainController.setSecondFree(0);//liberando o segundo tunel

            moveLeft(-430);
            rotate(360);

            //inicio regiao critica
            firstBridgeCheck();
            mainController.setFirstFree(1);//fechando o primeiro tunel

            moveUp(-60);
            rotate(270);
            moveLeft(-605);
            rotate(180);
            moveDown(5);
            rotate(270);
            //Fim regiao critica 
            mainController.setFirstFree(0);//liberando o primeiro tunel
      
            moveLeft(-800);
          break;
          default:
            System.out.println("ERRO!");
          break;
        }
        break;
      case (2)://Posicoes utilizando estrita alternância
        switch(mainController.getPosition()){
          case(1):
            positionX = 0;
      
            Platform.runLater( () -> redTrain.setX(positionX=90));//setando a posicao inicial do trem no eixo X
            Platform.runLater( () -> redTrain.setY(positionY));

            moveLeft(-110);
            rotate(360);
          
            //Inicio Regiao Critica
            alternateSecond();
          
            moveUp(-60);
            rotate(270);
            moveLeft(-285);
            rotate(180);
            mainController.setSecondTurn(0);//liberando a entrada na segunda ponte
            moveDown(5);
            rotate(270);
            //Fim Regiao Critica (tunel 2)
          
            moveLeft(-430);
            rotate(360);
          
            //Inicio Regiao Critica
            alternateFirst();
          
            moveUp(-60);
            rotate(270);
            moveLeft(-605);
            rotate(180);
            moveDown(5);
            rotate(270);
            mainController.setFirstTurn(0);//liberando a entrada na primeira ponte
      
            moveLeft(-800);
            break;
          case(2):
            positionX = 0;
            rotate(90);
            Platform.runLater( () -> redTrain.setX(positionX=-800));//setando a posicao inicial do trem no eixo X
            Platform.runLater( () -> redTrain.setY(positionY=0));

            moveRight(-604);
            rotate(360);
            //Inicio Regiao Critica
            alternateFirst();

            moveUp(-58);
      
            rotate(90);
            moveRight(-430);
            rotate(180);
            mainController.setFirstTurn(0);//liberando o primeiro tunel
            moveDown(10);
            rotate(90);
            //Fim Regiao Critica
      
            moveRight(-285);
            rotate(360);

            //inicio regiao critica
            alternateSecond();

            moveUp(-55);
            rotate(90);
            moveRight(-120);
            rotate(180);
            mainController.setSecondTurn(0);//liberando o segundo tunel
            moveDown(5);
            rotate(90);
            moveRight(100);
            break;
          case(3):
            positionX = 0;
            rotate(90);
            Platform.runLater( () -> redTrain.setX(positionX=-800));//setando a posicao inicial do trem no eixo X
            Platform.runLater( () -> redTrain.setY(positionY=-125));//setando a posicao inicial do trem no eixo Y

            mainController.setSecondTurn(0);
  
            moveRight(-604);
            rotate(180);
            //Inicio Regiao Critica
            alternateFirst();

            moveDown(-58);
            rotate(90);
            moveRight(-420);
            rotate(360);
            moveUp(-125);
            rotate(90);
            //Fim Regiao Critica
            mainController.setFirstTurn(0);//liberando o primeiro tunel
        
            moveRight(-290);
            rotate(180);
  
            //inicio regiao critica
            alternateSecond();

            moveDown(-58);
            rotate(90);
            moveRight(-120);
            rotate(360);
            moveUp(-125);
            rotate(90);
            //fim regiao critica
            mainController.setSecondTurn(0);//liberando o segundo tunel
            moveRight(100);
            break;
          case(4):
            positionX = 0;
            Platform.runLater( () -> redTrain.setX(positionX=90));//setando a posicao inicial do trem no eixo X
            Platform.runLater( () -> redTrain.setY(positionY));

            mainController.setFirstTurn(0);
            moveLeft(-110);
            rotate(360);
            //Inicio Regiao Critica
            alternateSecond();

            moveUp(-60);
            rotate(270);
            moveLeft(-285);
            rotate(180);
            moveDown(5);
            rotate(270);
            //Fim regiao critica
            mainController.setSecondTurn(0);//liberando o segundo tunel

            moveLeft(-430);
            rotate(360);

            //inicio regiao critica
            alternateFirst();

            moveUp(-60);
            rotate(270);
            moveLeft(-605);
            rotate(180);
            moveDown(5);
            rotate(270);
            //Fim regiao critica 
            mainController.setFirstTurn(0);//liberando o primeiro tunel
      
            moveLeft(-800);
          break;
          default:
            System.out.println("ERRO!");
          break;
        }
        break;
      }
    }
  }//fim do metodo run()

  /********************************************************************
  * Metodo: firstBridgeCheck()
  * Funcao: loop que verifica se a ponte da esquerda esta livre para uso, utilizando uma variavel do MainControl: variavel de travamento lock
  se a ponte estiver ocupada, ou seja, se a variavel for igual a 1, a thread vai esperar 1 milissegundo usando o Thread.sleep(1)
  * Parametros: nenhum
  * Retorno: void
  ****************************************************************** */
  public void firstBridgeCheck(){ //var travamento
    while(mainController.itsFirstFree() == 1){
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        System.out.println("Erro de exceção!");
      }
    }
  }//fim do metodo firstBridgeCheck()

  /********************************************************************
  * Metodo: secondBridgeCheck()
  * Funcao: loop que verifica se a ponte da direita esta livre para uso, utilizando uma variavel do MainControl: variavel de travamento lock
  se a ponte estiver ocupada, ou seja, se a variavel for igual a 1, a thread vai esperar 1 milissegundo usando o Thread.sleep(1)
  * Parametros: nenhum
  * Retorno: void
  ****************************************************************** */
  public void secondBridgeCheck(){ //var travamento
    while(mainController.itsSecondFree() == 1){
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        System.out.println("Erro de exceção!");
      }
    }
  }//fim do metodo secondBridgeCheck()

    /********************************************************************
  * Metodo: alternateFirst()
  * Funcao: O metodo implementa o conceito de estrita alternancia. Nesse caso, a thread aguarda ate que seja sua vez de entrar
  na ponte da esquerda, ou seja, na regiao critica da esquerda. Atraves de uma variavel de controle firstTurn na MainControl que,
  enquanto ela for diferente de 1, o trem nao pode entrar na regiao critica.
  Quando a condicao do while for satisfeita, a thread eh notificada pelo metodo notifyAll()
  * Parametros: nenhum
  * Retorno: void
  ****************************************************************** */
  public synchronized void alternateFirst(){ //estrita alternancia
    while(mainController.getFirstTurn() != 1){
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        System.out.println("Erro de exceção!");
      }
    }
    notifyAll();
  }//fim do metodo alternateFirst()

  /********************************************************************
  * Metodo: alternateSecond()
  * Funcao: O metodo implementa o conceito de estrita alternancia. Nesse caso, a thread aguarda ate que seja sua vez de entrar
  na ponte da direita, ou seja, na regiao critica da direita. Atraves de uma variavel de controle secondTurn na MainControl que,
  enquanto ela for diferente de 1, o trem nao pode entrar na regiao critica.
  Quando a condicao do while for satisfeita, a thread eh notificada pelo metodo notifyAll()
  * Parametros: nenhum
  * Retorno: void
  ****************************************************************** */
  public synchronized void alternateSecond(){ //estrita alternancia
    while(mainController.getSecondTurn() != 1){
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        System.out.println("Erro de exceção!");
      }
    }
    notifyAll();
  }//fim do metodo alternateSecond()
  
   /********************************************************************
  * Metodo: rotate()
  * Funcao: recebe um int de um angulo e utiliza a classe Platform para rotacionar o imageView blackTrain
  * Parametros: int angulo que vai ser passado no run()
  * Retorno: void
  ****************************************************************** */
  public void rotate(int angle){
    Platform.runLater( () -> redTrain.setRotate(angle));
  }//fim do metodo rotate()

  /********************************************************************
  * Metodo: moveRight()
  * Funcao: responsavel por mover a imagem do trem vermelho para a direita usando a classe Platform
  * Parametros: int coordX = coordenada que informa para onde o trem deve se mover
  * Retorno: void
  ****************************************************************** */
  public void moveRight(int coordX){
    while(positionX != coordX){
      try {
        Thread.sleep(speed);
      } catch (InterruptedException e) {
        System.out.println("Erro de exceção!");
      }
      positionX++;
      Platform.runLater( () -> redTrain.setX(positionX));
    }
  }//fim do metodo moveRight()

  /********************************************************************
  * Metodo: moveLeft()
  * Funcao: responsavel por mover a imagem do trem vermelho para a esquerda usando a classe Platform
  * Parametros: int coordX = coordenada que informa para onde o trem deve se mover
  * Retorno: void
  ****************************************************************** */
  public void moveLeft(int coordX){
    while(positionX != coordX){
      try {
        Thread.sleep(speed);
      } catch (InterruptedException e) {
        System.out.println("Erro de exceção!");
      }
      positionX--;
      Platform.runLater( () -> redTrain.setX(positionX));
    }
  }//fim do metodo moveLeft()

  /********************************************************************
  * Metodo: moveDown()
  * Funcao: responsavel por mover a imagem do trem vermelho para baixo usando a classe Platform
  * Parametros: int coordY = coordenada que informa para onde o trem deve se mover
  * Retorno: void
  ****************************************************************** */
  public void moveDown(int coordY){
    while(positionY != coordY){
      try {
        Thread.sleep(speed);
      } catch (InterruptedException e) {
        System.out.println("Erro de exceção!");
      }
      positionY++;
      Platform.runLater( () -> redTrain.setY(positionY));
    }
  }//fim do metodo moveDown()

  /********************************************************************
  * Metodo: moveUp()
  * Funcao: responsavel por mover a imagem do trem vermelho para cima usando a classe Platform
  * Parametros: int coordY = coordenada que informa para onde o trem deve se mover
  * Retorno: void
  ****************************************************************** */
  public void moveUp(int coordY){
    while(positionY != coordY){
      try {
        Thread.sleep(speed);
      } catch (InterruptedException e) {
        System.out.println("Erro de exceção!");
      }
      positionY--;
      Platform.runLater( () -> redTrain.setY(positionY));
    }
  }//fim do metodo moveUp()

  /********************************************************************
  * Metodo: speedUp()
  * Funcao: aumenta a velocidade do trem. Quanto menor o valor do speed, maior a velocidade.
  No metodo, enquanto a velocidade for > 3, eh possivel aumentar a velocidade.
  * Parametros: nenhum
  * Retorno: void
  ****************************************************************** */
  public void speedUp(){
    if(speed > 3){
      speed -= 3;
    }
  }//fim do metodo speedUp()

  /********************************************************************
  * Metodo: slowDown()
  * Funcao: diminui a velocidade do trem. Quanto maior o valor do speed, menor a velocidade.
  No metodo, enquanto a velocidade for <= 30, eh possivel diminuir a velocidade.
  * Parametros: nenhum
  * Retorno: void
  ****************************************************************** */
  public void slowDown(){
    if(speed <= 30){
      speed += 3;
    }
  }//fim do metodo slowDown()
}//fim da classe SecondTrain

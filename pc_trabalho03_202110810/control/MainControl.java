/* ***************************************************************
* Autor............: Antonio Vinicius Silva Dutra
* Matricula........: 202110810
* Inicio...........: 18/04/2023
* Ultima alteracao.: 22/04/2023
* Nome.............: MainControl.java
* Funcao...........: gerencia e controla a GUI do fxml, inicia as threads e controla métodos da GUI
*************************************************************** */
package control;
import model.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MainControl implements Initializable{
  //Instanciando todos os ImageView utilizados
  @FXML private ImageView blackTrain;
  @FXML private ImageView redTrain;
  @FXML private ImageView backgroundThomas;
  @FXML private ImageView backgroundField;
  @FXML private ImageView blackTrainSpeed;
  @FXML private ImageView redTrainSpeed;
  @FXML private ImageView speedControlImage;

  //Instanciando todos os botoes utilizados
  @FXML private Button Iniciar;
  @FXML private Button continueButton;
  @FXML private Button speedUpBlackTrain;
  @FXML private Button slowDownBlackTrain;
  @FXML private Button speedUpRedTrain;
  @FXML private Button slowDownRedTrain;
  
  //Instanciando todos os choiceBox
  @FXML private ChoiceBox<String> solutionBox;
  @FXML private ChoiceBox<String> positionBox;
 
   //Instanciando todos os text
  @FXML private Text textBlack;
  @FXML private Text textRed;

  //Instanciando duas string que vao receber os valores selecionados nos choiceBox
  private String selectedSolution = "";
  private String selectedPosition = "";

  //Instanciando dois inteiros que vao ser utilizados nos switch - case das threads
  private int position;
  private int solution;

  //variaveis de travamento
  private int lockOne = 0;//ponte livre
  private int lockTwo = 0;//ponte livre

  //estrita alternancia
  int firstTurn = 0;
  int secondTurn = 0;

  //Instanciando as threads que serao iniciadas
  private FirstTrain trainOne;
  private SecondTrain trainTwo;
  
  /********************************************************************
  * Metodo: createThreads()
  * Funcao: cria as duas threads dos trens e chama o metodo startThreads() para iniciar as threads criadas
  * Parametros: nenhum
  * Retorno: void
  ****************************************************************** */
  public void createThreads(){
    trainOne = new FirstTrain(this);//cria a Thread do trem preto
    trainTwo = new SecondTrain(this);//cria a Thread do trem vermelho
    startThreads();
  }//fim do metodo createThreads
  
  /********************************************************************
  * Metodo: startThreads()
  * Funcao: inicia as threads
  * Parametros: nenhum
  * Retorno: void
  ****************************************************************** */
  public void startThreads(){
    trainOne.start();
    trainTwo.start();
  }//fim do metodo startThreads()

  /********************************************************************
  * Metodo: startButton()
  * Funcao: desabilita o botao iniciar e habilita as choiceBox e o botao continuar
  * Parametros: ActionEvent event
  * Retorno: void
  ****************************************************************** */
  @FXML
  void startButton(ActionEvent event) {
    Iniciar.setDisable(true);
    Iniciar.setOpacity(0);
    solutionBox.setDisable(false);
    solutionBox.setOpacity(1);
    positionBox.setDisable(false);
    positionBox.setOpacity(1);
    continueButton.setDisable(false);
    continueButton.setOpacity(1);
  }//fim do metodo startButton()

  /********************************************************************
  * Metodo: continueButton)
  * Funcao: desabilita o botao continuar e a choice box, chamando o metodo createThreads para iniciar as threads
  e iniciar os trens de acordo com as opcoes do choiceBox. Faz uma verificao caso nao sejam escolhidas todas as opcoes
  dos ChoiceBox, criando um Alert.
  * Parametros: ActionEvent event
  * Retorno: void
  ****************************************************************** */
  @FXML
  public void continueButton(ActionEvent event) {
    if (!selectedSolution.isEmpty() && !selectedPosition.isEmpty()) {
      solutionBox.setDisable(true);
      solutionBox.setOpacity(0);
      positionBox.setDisable(true);
      positionBox.setOpacity(0);
      continueButton.setDisable(true);
      continueButton.setOpacity(0);

      createThreads();
      
      backgroundField.setDisable(false);
      backgroundField.setOpacity(1);
      blackTrain.setDisable(false);
      blackTrain.setOpacity(1);
      redTrain.setDisable(false);
      redTrain.setOpacity(1);

      blackTrainSpeed.setDisable(false);
      blackTrainSpeed.setOpacity(1);
      redTrainSpeed.setDisable(false);
      redTrainSpeed.setOpacity(1);
      textRed.setDisable(false);
      textRed.setOpacity(1);
      textBlack.setDisable(false);
      textBlack.setOpacity(1);
      speedUpBlackTrain.setDisable(false);
      speedUpBlackTrain.setOpacity(1);
      speedUpRedTrain.setDisable(false);
      speedUpRedTrain.setOpacity(1);
      slowDownBlackTrain.setDisable(false);
      slowDownBlackTrain.setOpacity(1);
      slowDownRedTrain.setDisable(false);
      slowDownRedTrain.setOpacity(1);
      speedControlImage.setDisable(false);
      speedControlImage.setOpacity(1);
    } else {
      //Mostra uma mensagem de erro caso o usuario nao tenha selecionado as opcoes corretamente
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("ERRO!");
      alert.setHeaderText("Opções inválidas!");
      alert.setContentText("Por favor, selecione uma solução e uma posição inicial.");
      alert.showAndWait();
    }
  }//fim do metodo continueButton()

  /********************************************************************
  * Metodo: initialize()
  * Funcao: desativa todos os elementos graficos exceto pelo primeiro background e o botao iniciar ate que esse botao iniciar
  seja clicado, define as opcoes disponiveis na choiceBox, seleciona o elemento vazio na choice box e atribui funcoes para
  os botoes de aumentar e diminuir velocidade
  * Parametros: URL location, resources
  * Retorno: void
  ****************************************************************** */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //Desabilitando os botoes ate que o botao iniciar seja clicado
    solutionBox.setDisable(true);
    positionBox.setDisable(true);
    continueButton.setDisable(true);
    backgroundField.setDisable(true);
    blackTrain.setDisable(true);
    redTrain.setDisable(true);
    blackTrainSpeed.setDisable(true);
    redTrainSpeed.setDisable(true);
    textRed.setDisable(true);
    textBlack.setDisable(true);
    speedUpBlackTrain.setDisable(true);
    speedUpRedTrain.setDisable(true);
    slowDownBlackTrain.setDisable(true);
    slowDownRedTrain.setDisable(true);
    speedControlImage.setDisable(true);

    //Define as solucoes disponiveis na ChoiceBox de solucoes
    ObservableList<String> solutionOptions = FXCollections.observableArrayList("", "SOLUCAO 1: VARIAVEL DE TRAVAMENTO", 
    "SOLUCAO 2: ESTRITA ALTERNANCIA");
    solutionBox.setItems(solutionOptions);
    solutionBox.getSelectionModel().selectFirst();

    //Define as posicoes disponiveis na ChoiceBox de posicoes
    ObservableList<String> positionOptions = FXCollections.observableArrayList("", "OS DOIS TRENS COMECAM NA DIREITA", "OS DOIS TRENS COMECAM NA ESQUERDA", 
    "TREM VERMELHO NA ESQUERDA E TREM PRETO NA DIREITA", "TREM PRETO NA ESQUERDA E TREM VERMELHO NA DIREITA");
    positionBox.setItems(positionOptions);
    positionBox.getSelectionModel().selectFirst();

    //Adiciona listeners para as ChoiceBox
    solutionBox.setOnAction(event -> solutionList(event));
    positionBox.setOnAction(event -> positionList(event));

    speedUpBlackTrain.setOnMouseClicked(Event ->{
      trainOne.speedUp();//aumenta velocidade do trem preto
    });

    slowDownBlackTrain.setOnMouseClicked(Event ->{
      trainOne.slowDown();//diminui velocidade do trem preto
    });

    speedUpRedTrain.setOnMouseClicked(Event ->{
      trainTwo.speedUp();//aumenta velocidade do trem vermelho
    });

    slowDownRedTrain.setOnMouseClicked(Event ->{
      trainTwo.slowDown();//diminui velocidade do trem vermelho
    });
  }//fim do metodo initialize()

  //getters and setters
  public String getSelectedSolution() {
    return selectedSolution;
  }
  public String getSelectedPosition() {
    return selectedPosition;
  }
  public ImageView getImageViewBlackTrain() {
    return blackTrain;
  }
  public ImageView getImageViewRedTrain() {
    return redTrain;
  }
  public int getFirstTurn(){
    return firstTurn;
  }
  public int getSecondTurn(){
    return secondTurn;
  }
  public void setPosition(int posicao){
    this.position = posicao;
  }

  //Abaixo operacoes com as variaveis de travamento e estrita alternancia
  public int itsFirstFree() {
    return lockOne;//verifica se a regiao critica 1 esta livre
  }
  public int itsSecondFree() {
    return lockTwo; //verifica se a regiao critica 2 esta livre
  }
  public void setFirstFree(int lock) {
    this.lockOne = lock;//define a regiao critica 1 como livre
  }
  public void setSecondFree(int lock) {
    this.lockTwo = lock;//define a regiao critica 2 como livre
  }
  public void setFirstTurn(int turn) {
    this.firstTurn = turn; //troca o valor da vez/turn
  }public void setSecondTurn(int turn) {
    this.secondTurn = turn;//troca o valor da vez/turn
  }

  /********************************************************************
  * Metodo: positionList()
  * Funcao: atribui o valor da posicao selecionada no choiceBox para a variavel selectedPosition
  * Parametros: ActionEvent event
  * Retorno: void
  ****************************************************************** */
  @FXML
  void positionList(ActionEvent event) {
    selectedPosition = positionBox.getSelectionModel().getSelectedItem();
  }//fim do metodo positionList()

  /********************************************************************
  * Metodo: solutionList()
  * Funcao: atribui o valor da solucao selecionada no choiceBox para a variavel selectedSolution
  * Parametros: ActionEvent event
  * Retorno: void
  ****************************************************************** */
  @FXML
  void solutionList(ActionEvent event) {
    selectedSolution = solutionBox.getSelectionModel().getSelectedItem();
  }//fim do metodo solutionList()
  
  /********************************************************************
  * Metodo: getPosition()
  * Funcao: atribui valores inteiros para as opcoes de posicao escolhida pelo usuario a fim de 
  passar esses valores para o switch - case dentro das threads
  * Parametros: nenhum
  * Retorno: void
  ****************************************************************** */
  public int getPosition(){
    if(selectedPosition == "OS DOIS TRENS COMECAM NA DIREITA"){
      position = 1;
    } else if(selectedPosition == "OS DOIS TRENS COMECAM NA ESQUERDA") {
      position = 2;
    } else if (selectedPosition == "TREM VERMELHO NA ESQUERDA E TREM PRETO NA DIREITA"){
      position = 3;
    } else if(selectedPosition == "TREM PRETO NA ESQUERDA E TREM VERMELHO NA DIREITA"){
      position = 4;
    } else {
      System.out.println("Deu ruim!");
    }
    return position;
  }//fim do metodo getPosition()

  /********************************************************************
  * Metodo: getSolution()
  * Funcao: atribui valores inteiros para as opcoes de solucao escolhida pelo usuario a fim de 
  passar esses valores para o switch - case dentro das threads
  * Parametros: nenhum
  * Retorno: void
  ****************************************************************** */
  public int getSolution(){
    if(selectedSolution == "SOLUCAO 1: VARIAVEL DE TRAVAMENTO"){
      solution = 1;
    } else if(selectedSolution == "SOLUCAO 2: ESTRITA ALTERNANCIA") {
      solution = 2;
    } else {
      System.out.println("Deu ruim!");
    }
    return solution;
  }//fim do metodo getSolution
}//fim da classe MainControl

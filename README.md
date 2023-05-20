# Trains-Problem
Aplicação realizada para fins educacionais no estudo de criação de processos/threads e utilização de variável de travamento e estrita alternância no estudo de sistemas operacionais na matéria de programação concorrente.
O problema dos trens consiste na ideia de que dois trens não podem acessar a mesma região crítica, no caso, uma ponte de via única. 
No problema proposto, se dois trens atravessarem a ponte ao mesmo tempo eles vão bater. Na programação concorrente existem duas soluções
para tal problema: variável de travamento e estrita alternância.

## Sobre as soluções:
- Variável de travamento: é uma variável compartilhada que indica se a via está ocupada ou livre. Quando o valor da variavel é 
igual a 0, significa que a via está livre para que um trem possa acessar a região crítica. Caso contrário, o valor da variável será igual
a 1 e o trem vai aguardar o outro trem atravessar a ponte e após isso, o valor da variável vai mudar novamente. 
- Estrita alternância: é um algoritmo que garante que os trens irão se alternar para acessar a via de forma segura, evitando colisões. O algoritmo pode ser implementado utilizando um loop while e uma espécia de ''variável de travamento''.

## Funcionalidades:

- Simulação de trilhos de trem com diferentes posições utilizando diferentes métodos de solução
- Controlador de velocidade
- Interface gráfica (Java8 JavaFX)

## Aplicação em execução:
![Layout](https://github.com/oantoniovinicius/Trains-Problem/blob/main/resources/executionGif.gif)
Solução utilizada na demonstração: estrita alternância.

## Autores:
- [@oantoniovinicius](https://www.github.com/oantoniovinicius)

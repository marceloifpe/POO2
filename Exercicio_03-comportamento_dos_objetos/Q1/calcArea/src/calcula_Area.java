//Exercício: considerando o método abaixo, quais das chamadas
// // de método listadas à direita são corretas?
/*
Nessa letra (a), a chamada da função está correta, pois é esperado dois valores inteiros de parâmetro
e na segunda parte de converter short para int também é válido, pois é um tipo de número inteiro
que vai de -32.768 a 32.767. que está dentro do escopo de valores do tipo short não tendo risco de perder valores.
a)
int a = calcArea(7, 12);
short c = 7;
calcArea(c, 15);
Nessa letra(c), a chamada de método também está correta, pois é feito a chamada de dois valores
inteiro mesmo sem o resultado ser armazenado.
c)
calcArea(2, 3);
*/


class calcula_Area {

    public int calcArea(int height, int width) {
        return height * width;
    }

    public static void main(String[] args) {
        calcula_Area calcula = new calcula_Area();

        System.out.println("\n--- Letra 'a' ---");
        int a = calcula.calcArea(7, 12);
        System.out.println("Parte 1 (a): O resultado de calcArea(7, 12) é: " + a);
        short c = 7;
        calcula.calcArea(c, 15);
        System.out.println("Parte 2 (a): A chamada calcArea(c, 15) com a variável short '" + c + "' também é válida.");


        System.out.println("\n--- Letra 'c' ---");
        calcula.calcArea(2, 3);
        System.out.println("Opção (c): A chamada calcArea(2, 3) é feita, mas seu resultado não é guardado.");
        int result = calcula.calcArea(2, 3);
        System.out.println("Caso fosse guardado o resultado da opção (c), seria: " + result);
    }
}

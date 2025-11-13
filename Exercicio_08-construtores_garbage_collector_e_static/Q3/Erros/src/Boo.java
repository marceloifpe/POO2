public class Boo {
    public Boo(int i) { }
    public Boo(String s) { }
    public Boo(String s, int i) { }
}

/*
(3)                        =>   (a)symbol: constructor Boo(int, java.lang.String)

(4)                        =>   (b)symbol: constructor Boo(java.lang.String, java.lang.String)

(6)                        =>   (c)symbol: constructor Boo()

A correção do construtor 3 seria colocar um super no abri e fecha chaves com os parâmetros corretos da classe pai,
já no construtor 4 a solução é implementar na classe Boo construtor que aceite parâmetros duas como Strings para corrigir o erro da classe SonOfBoo.
No construtor 6 a correção seria fazer a chamada correta na passagem de parâmteros, só precisaria inverter os parâmetros na chamada para
fazer a ligação com o construtor da classe Boo que é pai ou fazer a alteração de ordem de parâmetros direto no construtor da classe pai boo.
*/
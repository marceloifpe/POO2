// class Clock {
//     String time;

//     void setTime(String t) {
//         time = t;
//     }

//     void getTime() {
//         return time;
//     }
// }

class Clock {
    String time;

    void setTime(String t) {
        time = t;
    }

    String getTime() {
        return time;
    }
}
/*
Nesse arquivo B não compila o código, pois o método getTime é do tipo void e não tem retorno, quando é declarado um return no método
ele sempre espera que não seja do tipo void e como na linha String tod = c.getTime(); já está tentando atribuir esse valor retornado a uma variável do tipo String,
a solução mais fácil e correta seria mudar o tipo do método getTime de void para String, Com isso o código rodaria normalmente.
 */
// Exercício: Veja o que acontece quando inserimos 1,1,1 no
// SimpleStartupGame na imagem abaixo. Explique a causa desse
// bug acontece.

/*
O problema é o seguinte o palpite do jogador é repetido várias vezes, porque no código não tem modificação de posição e nem remove.
Para resolver o bug deveria ter um método que que verifica e faz a remoção da lista com o número acertado pelo jogador,
Com isso após ele acertar uma vez o número, o próximo acerto seria um outro número diferente.
*/
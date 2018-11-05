package br.ufmg.reuso.negocio.aspectos;

import javax.swing.*;
import br.ufmg.reuso.negocio.jogo.Jogo;
import br.ufmg.reuso.negocio.jogador.Jogador;
import br.ufmg.reuso.negocio.mesa.Modulo;

import java.util.List;
import java.util.ArrayList;

aspect LogAspect {

    pointcut publicMethodExecuted(): execution(public * *(..));

    after(): publicMethodExecuted() {
        System.out.printf("Entrando no metodo: %s. \n", thisJoinPoint.getSignature());

        Object[] arguments = thisJoinPoint.getArgs();
        for (int i = 0; i < arguments.length; i++) {
            Object argument = arguments[i];
            if (argument != null) {
                System.out.printf("Tipo do argumento: %s seu valor: %s. \n", argument.getClass().toString(), argument);
            }
        }
        System.out.printf("Saida do metodo: %s. \n", thisJoinPoint.getSignature());
    }
}

class ContadorJogador{
    int artefato_bom;
    int artefato_ruim;
    int capacidade_produtiva;
    int artefatos;
    String _nome;
    int modulo_atual;

    ContadorJogador(String nome){
        artefato_bom = 0;
        artefato_ruim = 0;
        artefatos = 0;
        capacidade_produtiva = 0;
        _nome = nome;
        modulo_atual = 0;
    }
    int getModuloAtual()
    {
        return modulo_atual;
    }
    void incrementaModuloAtual()
    {
        modulo_atual +=1;
    }

    String getNome()
    {
        return _nome;
    }
    void resetaArtefatos(){
        artefatos =0;
    }

    void IncrementaArtefatos(){
        artefatos +=1;
    }
    int getArtefatos()
    {
        return artefatos;
    }

    void setArtefatoBom(){
        artefato_bom += 1;
    }
    int getArtefato_bom(){
        return artefato_bom;
    }
    void setArtefatoRuim(){
        artefato_ruim += 1;
    }
    int getArtefatoRuin(){
        return artefato_ruim;
    }
    void setCapacidadeProdutiva(int capacidade){
        capacidade_produtiva += capacidade;
    }
    int getCapacidadeProdutiva(){
        return capacidade_produtiva;
    }
}

aspect FeedAspect {

    List<ContadorJogador> lista_jogadores = new ArrayList<>();
    Modulo[] modulos_criados;
    int[] tamanho_total_modulos;
    int alerta_habilitado = 0;

    pointcut habilitarAlerta(): execution(* retornaHabilitarAlerta(..));
    after(): habilitarAlerta()
    {
        Object[] arguments = thisJoinPoint.getArgs();
        alerta_habilitado = (int)arguments[0];
    }
    pointcut printInserirArtefato(): execution(* inserirArtefato(..));

    after(): printInserirArtefato(){
        //Inserir novo artefato criado a partir do evento - Associar ao jogador que inseriu novo artefato no board
        if(alerta_habilitado == 1)
        {
            Object[] arguments = thisJoinPoint.getArgs();
            for (int i = 0; i < arguments.length; i++) {
                Object argument = arguments[i];
                if (argument != null && i == 0) {
                    Jogador jogador_teste = (Jogador) argument;
                    //JOptionPane.showMessageDialog(null,jogador_teste.getNome());
                    //JOptionPane.showMessageDialog(null,"Tipo do argumento: "+ argument.getClass().toString() +" seu valor: " + argument);

                    if (lista_jogadores.isEmpty() == true) {
                        ContadorJogador novo_jogador = new ContadorJogador(jogador_teste.getNome());
                        novo_jogador.IncrementaArtefatos();
                        lista_jogadores.add(novo_jogador);
                    } else {
                        boolean jogador_inserido = false;
                        for (int j = 0; j < lista_jogadores.size(); j++) {
                            if (lista_jogadores.get(j).getNome() == jogador_teste.getNome()) {
                                lista_jogadores.get(j).IncrementaArtefatos();
                                jogador_inserido = true;
                            }
                        }
                        if (jogador_inserido == false) {
                            ContadorJogador novo_jogador = new ContadorJogador(jogador_teste.getNome());
                            novo_jogador.IncrementaArtefatos();
                            lista_jogadores.add(novo_jogador);
                        }
                    }
                }
            }
        }
    }
    //Utilizado para armazenar os modulos de um projeto
    pointcut getModulosProjeto(): execution(* retornaModulos(..));
    after(): getModulosProjeto(){
        if(alerta_habilitado == 1) {
            Object[] arguments = thisJoinPoint.getArgs();

            modulos_criados = new Modulo[arguments.length];
            tamanho_total_modulos = new int[arguments.length];

            //inicializando os modulos
            for (int i = 0; i < arguments.length; i++) {
                modulos_criados[i] = new Modulo();
                tamanho_total_modulos[i] = 0;
            }

            for (int j = 0; j < arguments.length; j++) {
                modulos_criados[j] = (Modulo) arguments[j];
                //JOptionPane.showMessageDialog(null,"Modulos:" + modulos_criados[j].getAjudas() + " " + modulos_criados[j].getCodigos() );
                tamanho_total_modulos[j] = modulos_criados[j].getCodigos() + modulos_criados[j].getAjudas() + modulos_criados[j].getDesenhos() + modulos_criados[j].getRastros() + modulos_criados[j].getRequisitos();
            }
        }
    }
    //Utilizado para imprimir a alert box com a possivel taxa de completudo de um modulo
    pointcut printStatusJogadores(): execution(* exibirDefault(..));
    after(): printStatusJogadores(){
        if(alerta_habilitado == 1) {
            String texto = "";
            for (int j = 0; j < lista_jogadores.size(); j++) {
                int artefatos_aux = lista_jogadores.get(j).getArtefatos();
                int tamanho_aux = tamanho_total_modulos[lista_jogadores.get(j).modulo_atual];
                double porcent_compl = Math.round(((double) artefatos_aux / (double) tamanho_aux) * 100);
                texto += lista_jogadores.get(j).getNome() + " pode ter completado " + porcent_compl
                        + "% do modulo " + (lista_jogadores.get(j).modulo_atual + 1) + "!\n";
            }
            JOptionPane.showMessageDialog(null, texto);
        }
    }
    //Utilizado para resetar contagem quando modulo for integrado
    pointcut incrementaModuloJogador(): execution(* retornaJogadorIntegrado(..));
    after():incrementaModuloJogador(){
        if(alerta_habilitado == 1) {
            Object[] arguments = thisJoinPoint.getArgs();
            Jogador jogador_integrado = (Jogador) arguments[0];

            for (int j = 0; j < lista_jogadores.size(); j++) {
                if (lista_jogadores.get(j).getNome() == jogador_integrado.getNome()) {
                    lista_jogadores.get(j).incrementaModuloAtual();
                    lista_jogadores.get(j).resetaArtefatos();
                }
            }
        }
    }

}
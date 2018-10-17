/*
 * Federal University of Minas Gerais 
 * Department of Computer Science
 * Simules-SPL Project
 *
 * Created by Michael David
 * Date: 16/07/2011
 */

package br.ufmg.reuso.negocio.baralho;

import java.util.Random;

import br.ufmg.reuso.negocio.carta.Artefato;

/**
 * @author Michael David
 * modificado por Marina (trabalho de reuso de software 2016/1):
 * herdando da classe abstrata BaralhoArtefatos
 */
public class BaralhoArtefatosRuins extends BaralhoArtefatos {

	private static final double PERCENTUAL_ARTEFATOS_RUINS_QUALIDADE_RUIM = 0.6;

	public BaralhoArtefatosRuins() {
		super();
	}

	public BaralhoArtefatosRuins(int pNumeroArtefatosAtual) {
		super(pNumeroArtefatosAtual);
	}

	@Override
	public Artefato[] iniciarArtefatos() {
		Artefato[] ruins = new Artefato[getNumeroArtefatosAtual()];
		for (int i = 0; i < ((int) (PERCENTUAL_ARTEFATOS_RUINS_QUALIDADE_RUIM * getNumeroArtefatosAtual())); i++) {
			/*
			 * iniciando o comeco do vetor com artefatos ruins com bug
			 */
			ruins[i] = new Artefato(true, true);
		}

		for (int i = ((int) (PERCENTUAL_ARTEFATOS_RUINS_QUALIDADE_RUIM
				* getNumeroArtefatosAtual())); i < getNumeroArtefatosAtual(); i++) {
			/*
			 * iniciando o final do vetor com artefatos ruins sem bug
			 */
			ruins[i] = new Artefato(false, true);
		}

		return ruins;
	}

	@Override
	public void embaralhar() {
		Random sorteio = new Random();
		for (int primeiro = 0; primeiro < getNumeroArtefatosAtual(); primeiro++) {
			int segundo = sorteio.nextInt(getNumeroArtefatosAtual());
			Artefato temporario = listaArtefatos[primeiro];
			listaArtefatos[primeiro] = listaArtefatos[segundo];
			listaArtefatos[segundo] = temporario;
		}
	}

	/**
	 * Distribui uma artefato ruim
	 */
	@Override
	public Artefato darArtefato() {
		/*
		 * Determina se ainda ha artefato a ser distribuido
		 */
		if (listaArtefatos[currentArtifact] != null) {
			/*
			 * Diminui o numero de artefatos que o baralho contem
			 */
			setNumeroArtefatosAtual(getNumeroArtefatosAtual() - 1);

			Artefato temporario = listaArtefatos[currentArtifact];
			listaArtefatos[currentArtifact] = null;

			/*
			 * Incrementa indice do proximo artefato a ser distribuido
			 */
			this.currentArtifact++;

			return temporario;
		} else {
			/* Retorna nulo para indicar que baralho esta vazio */
			return null;
		}
	}

	@Override
	public void recolherArtefato(Artefato artefatoDevolvido) {
		/*
		 * atualizando variavel do artefato para entrada no baralho auxiliar
		 */
		artefatoDevolvido.setArtefatoInspecionado(false);

		/*
		 * colocando artefato no baralho
		 */
		listaArtefatos[getNumeroArtefatosAtual()] = artefatoDevolvido;

		/*
		 * adicionando numero de artefatos ao baralho que recolhe
		 */
		setNumeroArtefatosAtual(getNumeroArtefatosAtual() + 1);
	}
}
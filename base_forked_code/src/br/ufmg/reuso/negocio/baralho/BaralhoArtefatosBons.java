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
public class BaralhoArtefatosBons extends BaralhoArtefatos {

	private static final double PERCENTUAL_ARTEFATOS_RUINS_QUALIDADE_BOA = 0.2;

	public BaralhoArtefatosBons() {
		super();
	}

	public BaralhoArtefatosBons(int pNumeroArtefatosAtual) {
		super(pNumeroArtefatosAtual);
	}

	@Override
	public Artefato[] iniciarArtefatos() {
		Artefato[] bons = new Artefato[getNumeroArtefatosAtual()];
		for (int i = 0; i < ((int) (PERCENTUAL_ARTEFATOS_RUINS_QUALIDADE_BOA * getNumeroArtefatosAtual())); i++) {
			/*
			 * iniciando o comeco do vetor com artefatos bons com bug (20%
			 * deles)
			 */
			bons[i] = new Artefato(true, false);
		}

		for (int i = ((int) (PERCENTUAL_ARTEFATOS_RUINS_QUALIDADE_BOA
				* getNumeroArtefatosAtual())); i < getNumeroArtefatosAtual(); i++) {
			/*
			 * iniciando o final do vetor com artefatos bons sem bug (80% deles)
			 */
			bons[i] = new Artefato(false, false);
		}

		return bons;
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
	 * Distribui uma artefato bom
	 */
	@Override
	public Artefato darArtefato() {
		/* Determina se ainda ha artefato a ser distribuido */
		if (listaArtefatos[currentArtifact] != null) {
			/* Diminui o numero de artefatos que o baralho contem */
			setNumeroArtefatosAtual(getNumeroArtefatosAtual() - 1);

			Artefato temporario = listaArtefatos[currentArtifact];
			listaArtefatos[currentArtifact] = null;

			/* Incrementa indice do proximo artefato a ser distribuido */
			this.currentArtifact++;

			return temporario;
		} else {
			/* Retorna nulo para indicar que baralho esta vazio */
			return null;
		}

	}

	@Override
	public void recolherArtefato(Artefato artefatoDevolvido) {
		/* atualizando variavel do artefato para entrada no baralho auxiliar */
		artefatoDevolvido.setArtefatoInspecionado(false);

		/* colocando artefato no baralho */
		listaArtefatos[getNumeroArtefatosAtual()] = artefatoDevolvido;

		/* adicionando numero de artefatos ao baralho que recolhe */
		setNumeroArtefatosAtual(getNumeroArtefatosAtual() + 1);
	}
}
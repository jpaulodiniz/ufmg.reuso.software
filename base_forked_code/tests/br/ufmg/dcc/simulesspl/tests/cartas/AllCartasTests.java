package br.ufmg.dcc.simulesspl.tests.cartas;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ufmg.dcc.simulesspl.tests.cartas.ArtefatoTest;
import br.ufmg.dcc.simulesspl.tests.cartas.CartaEngenheiroTest;
import br.ufmg.dcc.simulesspl.tests.cartas.CartaProblemaTest;
import br.ufmg.dcc.simulesspl.tests.cartas.CartaoProjetoTest;

@RunWith(Suite.class)
@SuiteClasses({ ArtefatoTest.class, CartaConceitoTest.class,
		CartaEngenheiroTest.class, CartaEngenheiroTest.class,
		CartaoProjetoTest.class, CartaProblemaTest.class, 
		InteracaoJogoCartaArtefatoTest.class, 
		InteracaoJogoCartaConceitoTest.class,
		InteracaoJogoCartaEngenheiroDeSoftwareTest.class,
		InteracaoJogoCartaProblema.class,
		InteracaoJogoCartasTest.class,
		InteracaoTabuleiroCartaEngenheiroTest.class,
		InteracaoMesaCartaArtefato.class})
public class AllCartasTests {

}

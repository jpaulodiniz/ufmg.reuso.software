package br.ufmg.dcc.simulesspl.tests.cartas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import cartas.CartaConceito;

public class CartaConceitoTest {

	private CartaConceito carta;
	
	@Before
	public void setUp() throws Exception {		
		carta = new CartaConceito();		
	}

	@After
	public void tearDown() throws Exception {
		carta = null;
		
	}

	@Test
	public void testInstanciaCartaConceito() {		
		assertNotNull(carta);		
	}

	@Test
	public void testInserirEfeito() {
		carta.inserirEfeito();
		fail("Método ainda não implementado!");
	}

	@Test
	public void testGetDuracaoEfeito() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDuracaoEfeito() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCustoEfeito() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCustoEfeito() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReferenciaBibliografica() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetReferenciaBibliografica() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetQuantidadePrimeiroEfeito() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetQuantidadePrimeiroEfeito() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetQuantidadeSegundoEfeito() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetQuantidadeSegundoEfeito() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTipoPrimeiroEfeito() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTipoPrimeiroEfeito() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTipoSegundoEfeito() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTipoSegundoEfeito() {
		fail("Not yet implemented");
	}

}

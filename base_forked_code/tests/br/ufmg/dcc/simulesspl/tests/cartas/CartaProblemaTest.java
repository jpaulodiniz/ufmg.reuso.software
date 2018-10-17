package br.ufmg.dcc.simulesspl.tests.cartas;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cartas.CartaProblema;

public class CartaProblemaTest {

	CartaProblema carta;
	
	@Before
	public void setUp() throws Exception {
		carta = new CartaProblema(null, null, null, null, 0, null, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	@After
	public void tearDown() throws Exception {
		carta = null;
	}

	@Test
	public void testMostrarCarta() {
		carta.mostrarCarta();
	}

	@Test
	public void testCartaProblema() {
		assertNotNull(carta);
	}

	@Test
	public void testInserirEfeito() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReferenciaBibliografica() {
		carta.setReferenciaBibliografica("TESTE");
		assertEquals("TESTE", carta.getReferenciaBibliografica());
	}

	@Test
	public void testSetReferenciaBibliografica() {
		carta.setReferenciaBibliografica("TESTE");
		assertEquals("TESTE", carta.getReferenciaBibliografica());
		
	}

	@Test
	public void testGetDuracaoEfeito() {
		carta.setDuracaoEfeito(5) ;
		assertEquals(5, carta.getDuracaoEfeito() );
	}

	@Test
	public void testSetDuracaoEfeito() {
		carta.setDuracaoEfeito(5) ;
		assertEquals(5, carta.getDuracaoEfeito() );
	}

	@Test
	public void testGetCondicaoProblema() {
		carta.setCondicaoProblema("Condition")  ;
		assertEquals("Condition", carta.getCondicaoProblema() );
	}

	@Test
	public void testSetCondicaoProblema() {
		carta.setCondicaoProblema("Condition")  ;
		assertEquals("Condition", carta.getCondicaoProblema() );
	}

	@Test
	public void testGetQuantidadePrimeiroEfeito() {
		carta.setQuantidadePrimeiroEfeito(5) ;
		assertEquals(5, carta.getQuantidadePrimeiroEfeito());
	}

	@Test
	public void testSetQuantidadePrimeiroEfeito() {
		carta.setQuantidadePrimeiroEfeito(5) ;
		assertEquals(5, carta.getQuantidadePrimeiroEfeito());
	}

	@Test
	public void testGetQuantidadeSegundoEfeito() {
		carta.setQuantidadeSegundoEfeito(5) ;
		assertEquals(5, carta.getQuantidadeSegundoEfeito());
	}

	@Test
	public void testSetQuantidadeSegundoEfeito() {
		carta.setQuantidadeSegundoEfeito(5) ;
		assertEquals(5, carta.getQuantidadeSegundoEfeito());
	}

	@Test
	public void testGetTipoPrimeiroEfeito() {
		carta.setTipoPrimeiroEfeito(1)  ;
		assertEquals(1, carta.getTipoPrimeiroEfeito());
	}

	@Test
	public void testSetTipoPrimeiroEfeito() {
		carta.setTipoPrimeiroEfeito(1)  ;
		assertEquals(1, carta.getTipoPrimeiroEfeito());
	}

	@Test
	public void testGetTipoSegundoEfeito() {
		carta.setTipoSegundoEfeito(1)  ;
		assertEquals(1, carta.getTipoSegundoEfeito());
	}

	@Test
	public void testSetTipoSegundoEfeito() {
		carta.setTipoSegundoEfeito(1)  ;
		assertEquals(1, carta.getTipoSegundoEfeito());
	}

	@Test
	public void testGetQuantidadePrimeiraCondicao() {
		carta.setQuantidadePrimeiraCondicao(2) ;
		assertEquals(2, carta.getQuantidadePrimeiraCondicao());
	}

	@Test
	public void testSetQuantidadePrimeiraCondicao() {
		carta.setQuantidadePrimeiraCondicao(2) ;
		assertEquals(2, carta.getQuantidadePrimeiraCondicao());
	}

	@Test
	public void testGetQuantidadeSegundaCondicao() {
		carta.setQuantidadeSegundaCondicao(2) ;
		assertEquals(2, carta.getQuantidadeSegundaCondicao());
	}

	@Test
	public void testSetQuantidadeSegundaCondicao() {
		carta.setQuantidadeSegundaCondicao(2) ;
		assertEquals(2, carta.getQuantidadeSegundaCondicao());
	}

	@Test
	public void testGetTipoPrimeiraCondicao() {
		carta.setTipoPrimeiraCondicao(1) ;
		assertEquals(1, carta.getTipoPrimeiraCondicao());
	}

	@Test
	public void testSetTipoPrimeiraCondicao() {
		carta.setTipoPrimeiraCondicao(1) ;
		assertEquals(1, carta.getTipoPrimeiraCondicao());
	}

	@Test
	public void testGetTipoSegundaCondicao() {
		carta.setTipoSegundaCondicao(1) ;
		assertEquals(1, carta.getTipoSegundaCondicao());
	}

	@Test
	public void testSetTipoSegundaCondicao() {
		carta.setTipoSegundaCondicao(1) ;
		assertEquals(1, carta.getTipoSegundaCondicao());
	}

}

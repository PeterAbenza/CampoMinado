package br.com.cod3r.cm.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.execao.ExplosaoException;

public class CampoTeste {

	private Campo campo;
	
	@BeforeEach // pra cada metodo ele vai chamar essa função;	
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}
	
	@Test // '@Test' para dizer que é um teste JUnit;
	void testeVizinhoDistancia1Esquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado); // 'assertTrue' o resultado tem que ser true;
	}
	
	@Test 
	void testeVizinhoDistancia1Direita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado); 
	}
	
	@Test 
	void testeVizinhoDistancia1EmCima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado); 
	}
	 
	@Test 
	void testeVizinhoDistancia1EmBaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado); 
	}
	
	@Test 
	void testeVizinhoDistancia2Diagonal() {
		Campo vizinho = new Campo(2, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado); 
	}
	
	@Test 
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	@Test 
	void testeMarcacaoPadrao() {
		assertFalse(campo.isMarcado());
	}
	
	@Test 
	void testeAlterarMarcacao() {
		campo.alterarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test 
	void testeAlterarMarcacaoDuasVezes() {
		campo.alterarMarcacao();
		campo.alterarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test 
	void testeAbrirSemMinaNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test 
	void testeAbrirSemMinaMarcado() {
		campo.alterarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test 
	void testeAbrirComMinaMarcado() {
		campo.alterarMarcacao();
		campo.Minar();
		assertFalse(campo.abrir());
	}
	
	@Test 
	void testeAbrirComMinaNaoMarcado() {
		campo.Minar();
		
		assertThrows(ExplosaoException.class, () -> { // assertThrows ve se a execesão que lançou é correta;
			campo.abrir();
		});
	}
	
	@Test 
	void testeAbrirComVizinho() {
		Campo vizinho = new Campo(2, 2);
		Campo vizinhoDoVizinho = new Campo(1, 1);
		
		vizinho.adicionarVizinho(vizinhoDoVizinho);
		
		campo.adicionarVizinho(vizinho);
		
		campo.abrir();
		
		assertTrue(vizinhoDoVizinho.isAberto() && vizinho.isAberto());
	}
	
	@Test 
	void testeAbrirComVizinho2() {
		Campo vizinho = new Campo(2, 2);
		Campo vizinho2 = new Campo(2, 2);
		vizinho2.Minar();
		
		Campo vizinhoDoVizinho = new Campo(1, 1);
		vizinho.adicionarVizinho(vizinhoDoVizinho);
		vizinho.adicionarVizinho(vizinho2);
		
		campo.adicionarVizinho(vizinho);
		
		campo.abrir();
		
		assertTrue(vizinhoDoVizinho.isAberto() && !vizinho.isFechado());
	}
	
	@Test 
	void testeObjetivoAlcancadoMarcado() {
		campo.alterarMarcacao();
		campo.Minar();
		
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test 
	void testeObjetivoAlcancadoAberto() {
		campo.abrir();
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test 
	void testeToString() {
		campo.abrir();
		campo.alterarMarcacao();
		
		if(campo.isMarcado()) {
			assertEquals("x", campo.toString());
		} else if(campo.isAberto() && campo.isMarcado()) {
			assertEquals("*", campo.toString());
		} else if(campo.isAberto() && campo.minasNaVizinhanca() > 0) {
			assertEquals("x", campo.toString());
		} else if(campo.isAberto()) {
			assertEquals(" ", campo.toString());
		} else {
			assertEquals("?", campo.toString());
		}
	}
	
	@Test 
	void testeReset() {
		campo.abrir();
		campo.reiniciar();
		assertFalse(campo.isAberto());
	}
}

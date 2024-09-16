package br.com.cod3r.cm.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}

package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.cod3r.cm.execao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;

//  por default o boolean é false;
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;

	private List<Campo> Vizinhos = new ArrayList<>();

	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha; // '!=' for diferente;
		boolean colunaDiferente = coluna != vizinho.coluna; // se linha e coluna for diferente é diagonal;
		boolean diagonal = linhaDiferente && colunaDiferente;	

		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaLinha + deltaColuna;

		if (deltaGeral == 1 && !diagonal) {
			Vizinhos.add(vizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {
			Vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
	}

	void alterarMarcacao() {
		if (!aberto) { // não aberto;
			marcado = !marcado; // se tiver marcado retonar false se não true;
		}
	}

	boolean abrir() {
		if (!aberto && !marcado) { // não aberto e nem marcado;
			aberto = true;

			if (minado) {
				// se tiver bomba quebra o fluxo usando uma exeção;
				throw new ExplosaoException();
			}

			if (vizinhosSeguros()) {
				Vizinhos.forEach(v -> v.abrir());
			}

			return true;
		} else {
			return false;
		}
	}

	boolean vizinhosSeguros() {
		return Vizinhos.stream().noneMatch(v -> v.minado); // 'noneMatch' nenhum vizinho pode estar minado;
	}
	
	public boolean isMarcado() {
		return marcado;
	}
}

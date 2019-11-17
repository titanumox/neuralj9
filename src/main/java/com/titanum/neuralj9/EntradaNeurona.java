package com.titanum.neuralj9;

import java.util.List;

public class EntradaNeurona extends Neurona {

	public EntradaNeurona() {
		super();
	}

	public Neurona iniciar(List<Double> entradas, FuncionActivacion funcionActivacion) {
		super.setBias(0.0);
		super.setFuncionActivacion(FuncionActivacion.LINEAR);
		iniciar(entradas, super.getFuncionActivacion());
		try {
			this.getPesos().set(0, 1.0);
			this.getPesos().set(1, 0.0);
		} catch (IndexOutOfBoundsException e) {
			this.getPesos().add(1.0);
			this.getPesos().add(0.0);
		}
		return this;
	}
}

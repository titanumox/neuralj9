package com.titanum.neuralj9;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public abstract class CapaNeuronal {

	private int numeroNeuronas;
	protected List<Neurona> neuronas;
	private FuncionActivacion funcionActivacion;
	protected CapaNeuronal capaAnterior;
	protected CapaNeuronal capaPosterior;
	protected List<Double> entradas;
	private List<Double> salidas;
	private int numeroEntradas;
	protected int id;

	public CapaNeuronal(int numeroNeuronas) {
		this.numeroNeuronas = numeroNeuronas;
		this.neuronas = new ArrayList<Neurona>(numeroNeuronas);
		this.salidas = new ArrayList<>(numeroNeuronas);
	}

	public CapaNeuronal(int numeroNeuronas, FuncionActivacion funcionActivacion) {
		this.funcionActivacion = funcionActivacion;
		this.numeroNeuronas = numeroNeuronas;
		this.neuronas = new ArrayList<Neurona>(numeroNeuronas);
		this.salidas = new ArrayList<>(numeroNeuronas);
	}

	public CapaNeuronal iniciar(List<Double> entradas) {
		this.entradas = entradas;
		if (this.numeroNeuronas >= 0) {
			this.neuronas = IntStream.rangeClosed(0, this.numeroNeuronas)
					.mapToObj(i -> Neurona.instancia().iniciar(i,entradas, this.funcionActivacion))
					.collect(Collectors.toList());
		}

		return this;
	}

	protected void calcular() {
		if (!entradas.isEmpty() && !neuronas.isEmpty()) {
			salidas = neuronas.stream().map(Neurona::calcular).collect(Collectors.toList());
			System.out.println("capa id "+this.getId()+" salidas " +salidas);
		}
	}

}

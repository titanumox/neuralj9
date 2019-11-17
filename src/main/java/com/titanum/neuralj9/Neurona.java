package com.titanum.neuralj9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class Neurona {

	private List<Double> pesos;
	private List<Double> entradas;
	private Double salida;
	private Double salidaAntesActivacion;
	private int numeroEntradas;
	private Double bias = 1.0;
	private FuncionActivacion funcionActivacion;
	private int id;

	protected Neurona() {
	}

	public static Neurona instancia() {
		return new Neurona();
	}

	public Neurona iniciar(int id,List<Double> entradas, FuncionActivacion funcionActivacion) {
		this.id = id;
		this.funcionActivacion = funcionActivacion;
		inicializarArreglos(entradas);
		return this;
	}

	private void inicializarArreglos(List<Double> entradas) {
		this.numeroEntradas = entradas.size();
		this.pesos = new ArrayList<>(numeroEntradas + 1);
		this.entradas = entradas;
		if (this.numeroEntradas > 0) {
			this.pesos = ThreadLocalRandom.current().doubles(numeroEntradas).boxed().collect(Collectors.toList());
		}
	}

	public void ingresarEntradas(List<Double> entradas) {
		if (entradas.size() != numeroEntradas) {
			inicializarArreglos(entradas);
		}
		this.entradas = entradas;
	}

	public Double calcular() {
		salidaAntesActivacion = 0.0;
		if (numeroEntradas > 0) {
			if (!entradas.isEmpty() && !pesos.isEmpty()) {
				salidaAntesActivacion = IntStream.rangeClosed(0, numeroEntradas)
						.mapToDouble(i -> (i == numeroEntradas) ? bias : entradas.get(i) * pesos.get(i)).sum();
			}
		}
		salida = funcionActivacion.calcular(salidaAntesActivacion);
		System.out.println(" neurona id "+this.id + " salida "+salida);
		return salida;
	}

}

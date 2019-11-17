package com.titanum.neuralj9;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RedNeuronal {

	private CapaEntradaNeuronal capaEntradaNeuronal;
	private List<CapaInternaNeuronal> capaInternaNeuronal;
	private CapaSalidaNeuronal capaSalidaNeuronal;
	private int numeroCapasIntenas;
	private int numeroNeuronasPorCapa;
	private List<Double> entradas;
	private List<Double> salidas;
	private List<FuncionActivacion> funciones;

	private RedNeuronal() {

	}

	public static RedNeuronal instancia() {
		return new RedNeuronal();
	}

	public RedNeuronal inicializar(int numeroCapasIntenas, int numeroNeuronasPorCapa, List<Double> entradas, int numeroSalidas,
			List<FuncionActivacion> funciones) {
		this.numeroCapasIntenas = numeroCapasIntenas;
		this.entradas = entradas;
		this.numeroNeuronasPorCapa = numeroNeuronasPorCapa;
		this.funciones = funciones;
		this.capaInternaNeuronal = IntStream.range(0, this.numeroCapasIntenas).mapToObj(
				i -> new CapaInternaNeuronal(i, this.numeroNeuronasPorCapa, Collections.emptyList(), this.funciones.get(i)))
				.collect(Collectors.toList());
		IntStream.range(0, this.numeroCapasIntenas - 1).forEach(i -> {
			System.out.println(i);
			if ((i + 1) < capaInternaNeuronal.size()) {
				capaInternaNeuronal.get(i).setCapaPosterior(capaInternaNeuronal.get(i + 1));
			}
		});
		this.capaEntradaNeuronal = new CapaEntradaNeuronal(entradas.size()).iniciar(this.entradas);
		this.capaEntradaNeuronal.setCapaPosterior(capaInternaNeuronal.get(0));
		this.capaInternaNeuronal.get(0).setCapaAnterior(this.capaEntradaNeuronal);
		this.capaSalidaNeuronal = new CapaSalidaNeuronal(numeroSalidas);
		this.capaSalidaNeuronal.setCapaAnterior(this.capaInternaNeuronal.get(numeroCapasIntenas - 1));
		this.capaInternaNeuronal.get(numeroCapasIntenas - 1).setCapaPosterior(this.capaSalidaNeuronal);

		return this;
	}

	public void calcular() {
		System.out.println("capa entrada");
		this.capaEntradaNeuronal.calcular();
		System.out.println("capa interna");
		this.capaInternaNeuronal.forEach(this::calcularCapa);
		this.capaSalidaNeuronal.iniciar(capaSalidaNeuronal.getCapaAnterior().getSalidas());
		System.out.println("capa salida");
		this.capaSalidaNeuronal.calcular();
		this.salidas = capaSalidaNeuronal.getSalidas();

	}

	private void calcularCapa(CapaInternaNeuronal capa) {
		capa.setEntradas(capa.getCapaAnterior().getSalidas());
		capa.calcular();
	}

	public List<Double> getSalidas() {
		return this.salidas;
	}

}

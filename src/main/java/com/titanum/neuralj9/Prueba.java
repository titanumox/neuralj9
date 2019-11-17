package com.titanum.neuralj9;

import java.util.Arrays;

public class Prueba {

	public static void main(String[] args) {

		// System.out.println(Neurona.instancia().iniciar(Arrays.asList(1d,2.d),
		// FuncionActivacion.HIPERTAN).calcular());

		RedNeuronal red = RedNeuronal.instancia()
				.inicializar(
						1, // numero de capas internas
						3, // numero de neuronas por capa
						Arrays.asList(1.5, 0.5), // datos de entrada
						1, // numero de salidas
						Arrays.asList(FuncionActivacion.SIGMOID) // funcion de activacion por capa
				);
		red.calcular();
		//System.out.println(red.getSalidas());
		//red.setEntradas(Arrays.asList(1.0, 2.1));
		//red.calcular();
		//System.out.println(red.getSalidas());
	}

}

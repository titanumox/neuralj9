package com.titanum.neuralj9;

import java.util.function.Function;

public enum FuncionActivacion{
	
	HIPERTAN(x->(1.0-Math.exp(-1.0*x))/(1.0+Math.exp(-1.0*x))),
	LINEAR(x->1.0*x),
	SIGMOID(x->(1.0/(1.0+Math.exp(-1.0*x)))),
	STEP(x->x<0?0.0:1.0);
	
	private Function<Double, Double> funcion;
	
	FuncionActivacion(final Function<Double,Double> funcion){
		this.funcion = funcion;
	}	

	public Double calcular(Double x) {
		return funcion.apply(x);
	}


}

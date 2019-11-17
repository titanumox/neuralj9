package com.titanum.neuralj9;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CapaSalidaNeuronal extends CapaNeuronal {

	public CapaSalidaNeuronal(int numeroNeuronas) {
		super(numeroNeuronas, FuncionActivacion.LINEAR);
		this.setCapaAnterior(null);
	}

	public CapaSalidaNeuronal iniciar(List<Double> entradas) {
		this.entradas = entradas;
		super.neuronas = IntStream.range(0, this.getNumeroNeuronas())
				.mapToObj(i -> EntradaNeurona.instancia().iniciar(i,entradas, FuncionActivacion.LINEAR))
				.collect(Collectors.toList());
		return this;
	}
	
   @Override
   public void setCapaPosterior(CapaNeuronal capa) {
	   this.capaPosterior = null;
   }
   
   @Override
   public void setCapaAnterior(CapaNeuronal capa) {
	   this.capaAnterior = capa;
	   if(capa!=null && capa.getCapaPosterior()!=null && !capa.getCapaPosterior().equals(this)) {
		   capa.setCapaPosterior(this);
	   }
   }	
   

}

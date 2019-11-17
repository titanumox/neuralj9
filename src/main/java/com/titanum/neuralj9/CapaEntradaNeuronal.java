package com.titanum.neuralj9;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CapaEntradaNeuronal extends CapaNeuronal {

	public CapaEntradaNeuronal(int numeroNeuronas) {
		super(numeroNeuronas, FuncionActivacion.LINEAR);
		this.setCapaAnterior(null);
	}

	public CapaEntradaNeuronal iniciar(List<Double> entradas) {
		this.entradas = entradas;
		super.neuronas = IntStream.rangeClosed(0, this.getNumeroNeuronas())
				.mapToObj(i -> EntradaNeurona.instancia().iniciar(i,entradas, FuncionActivacion.LINEAR))
				.collect(Collectors.toList());
		return this;
	}
	
   @Override
   public void setCapaPosterior(CapaNeuronal capa) {
	   this.capaPosterior =capa;
	   if(capa!=null && capa.getCapaAnterior()!=null &&!capa.getCapaAnterior().equals(this)) {
		   capa.setCapaAnterior(this);
	   }
   }
   
   @Override
   public void setCapaAnterior(CapaNeuronal capa) {
	   this.capaAnterior = null;
   }

}

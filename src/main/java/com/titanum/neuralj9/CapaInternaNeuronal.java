package com.titanum.neuralj9;

import java.util.List;


public class CapaInternaNeuronal extends CapaNeuronal {

	public CapaInternaNeuronal(int id, int numeroNeuronas, List<Double> entradas, FuncionActivacion funcionActivacion) {
		super(numeroNeuronas, funcionActivacion);
		this.id = id;
		iniciar(entradas);
	}
	
	   @Override
	   public void setCapaPosterior(CapaNeuronal capa) {
		   this.capaPosterior = capa;
		   if(capa!=null && capa.getCapaAnterior()!=null && !capa.getCapaAnterior().equals(this)) {
			   capa.setCapaAnterior(this);
		   }
	   }
	   
	   @Override
	   public void setCapaAnterior(CapaNeuronal capa) {
		   this.capaAnterior = capa;
		   if(capa!=null && capa.getCapaPosterior()!=null && !capa.getCapaPosterior().equals(this)) {
			   capa.setCapaPosterior(this);
		   }
	   }	

}

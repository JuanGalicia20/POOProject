package com.project.app.aplicacionmovil;

import java.util.ArrayList;

public class Dia{
	//Atributos
	private String name;
	private ArrayList<String> listaPlan;

	//Constructor
	public Dia(String name){
		this.name = name;
	}

	//get&set
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	//añadir plan
	public void addPlan(String x){
		listaPlan.add(x);
	}
}
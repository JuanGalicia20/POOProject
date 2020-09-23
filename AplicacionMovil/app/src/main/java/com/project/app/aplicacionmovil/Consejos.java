package com.project.app.aplicacionmovil;

/**
* KellyVirtual - Consejos
* @author JoseDeLaVega
* @version 22/09/2020
*/

public class Consejos{
	//Atributos
	private Categoria cat1;
	private Categoria cat2;
	private Categoria cat3;
	private Categoria cat4;
	private Categoria cat5;

	//Constructor
	public Consejos(Categoria cat1, Categoria cat2, Categoria cat3, Categoria cat4, Categoria cat5){
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.cat3 = cat3;
		this.cat4 = cat4;
		this.cat5 = cat5;
	}

	//Métodos
	public Categoria getCat1(){
		return cat1;
	}
	public Categoria getCat2(){
		return cat2;
	}
	public Categoria getCat3(){
		return cat3;
	}
	public Categoria getCat4(){
		return cat4;
	}
	public Categoria getCat5(){
		return cat5;
	}

	public void setCat1(Categoria newCat){
		cat1 = newCat;
	}
	public void setCat2(Categoria newCat){
		cat2 = newCat;
	}
	public void setCat3(Categoria newCat){
		cat3 = newCat;
	}
	public void setCat4(Categoria newCat){
		cat4 = newCat;
	}
	public void setCat5(Categoria newCat){
		cat5 = newCat;
	}

}
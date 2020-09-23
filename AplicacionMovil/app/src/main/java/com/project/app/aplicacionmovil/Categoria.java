/** 
* KellyVirtual - Categoria
* @author JoseDeLaVega
* @version 22/09/2020
*/

import math.util.Random;
import math.util.ArrayList;

public class Categoria{
	//Atributos
	private String nombreCategoria;
	private ArrayList<Preguntas> listaPreguntas;
	Random generador = new Random();
	//Constructor
	public Categoria(String name){
		nombreCategoria = name;
		listaPreguntas = new ArrayList<Peguntas>();
	}
	//Métodos
	public String getNombreCategoria(){
		return nombreCategoria;
	}
	public ArrayList<Pregunta> getListaPreguntas(){
		return listaPreguntas;
	}

	public void setNombreCategoria(String name){
		nombreCategoria = name;
	}

	public void addPregunta(Pregunta newPregunta){
		listaPreguntas.add(newPregunta);
	}

	public Pregunta getPregunta(){
		int n = listaPreguntas.size();
		int x = generador.nextInt(n);
		return listaPreguntas.get(x);
	}


}
package Bataille;

import java.util.ArrayList;

public class Joueur {
	//Attributs - Un joueur a : un nom, un paquet de cartes et un compteur de points //
	private String nom; // nom du joueur //
	private ArrayList<Carte> paquet; // paquet de cartes du joueur //
	private int points; // points du joueur //
	
	//Constructeur de Joueur //
	public Joueur(String nom) {
		this.nom = nom;
		this.points = 0;
		this.paquet = new ArrayList<Carte>();
	}
	
	//Getters
	public String getNom() {  return nom; }
	public ArrayList<Carte> getPaquet() {  return paquet; }
	public int getPoints() {  return points; }
	
	//Setters 
	public void gagnerPoints() {
		points += 1;
    }
	
	//Tirer une carte de son paquet (la premi�re) SI il en a une
	public Carte tirerCarte() {
		if (!paquet.isEmpty()) {
			Carte carteTiree = paquet.get(0); //On prend la premi�re carte du paquet
			return carteTiree; //retourne la carte tir�e
		}else {
			return null; //aucune carte dans le paquet...(d�faite ?)
		}
	}
		
	//M�thode d'ajout d'une carte dans son propre paquet
	public void ajouterCarte(Carte carte) {
        paquet.add(carte);
    }
	
	//M�thode qui permet d'enlever la carte du paquet du joueur 
	public void removeCarte(Carte carte) {
        paquet.remove(carte);
    }
	
	// On r��crit la m�thode toString pour la Classe Carte //
		@Override
	    public String toString() {
	        return (nom+ " a " +points+ " points."); //ex : 2 de Coeur //
	    }
}

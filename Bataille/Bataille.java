package Bataille;

import java.util.ArrayList;
import java.util.Collections;

public class Bataille {

	public static void main(String[] args) {
		//CREATION DES JOUEURS 
		Joueur joueur1 = new Joueur("Victor"); //Cr�ation d'un joueur 1
		Joueur joueur2 = new Joueur("L�a"); //Cr�ation d'un joueur 2
		///////////////////////////////////////
		
		
		
		//CREATION DES CARTES DU JEU
		ArrayList<Carte> paquetPrincipal = new ArrayList<Carte>(); //Cr�ation du paquet de carte vide
		for (String couleur : Carte.couleurs) { //boucles des couleurs (ici 4)
            for (int i = 0; i <= Carte.noms.length -1; i++) { //boucles de cr�ation des cartes (ici 13 par couleur)
                Carte carte = new Carte(Carte.noms[i], couleur, Carte.valeurs[i]); //Cr�ation d'une carte
                paquetPrincipal.add(carte); //On ajoute la carte cr��e au paquet principal
            }
        }
		//System.out.println("Le paquet principal: " +paquetPrincipal); //On affiche le paquet principal
		///////////////////////////////////////
		
		
		
		//MELANGE DES CARTES DU PAQUET
		Collections.shuffle(paquetPrincipal); //M�lange des cartes du paquet principale
		//System.out.println("Le paquet principal m�lang�: " +paquetPrincipal); //On affiche le paquet principal m�lang�
		///////////////////////////////////////
		
		
		
		//DISTRIBUTION DES CARTES
		for (int i = 0; i < paquetPrincipal.size(); i += 2) { //Boucle qui va prendre 2 cartes � chaque fois � fin de distribuer toutes les cartes
            Carte carte1 = paquetPrincipal.get(i); //R�cup�ration de la premi�re carte
            Carte carte2 = paquetPrincipal.get(i + 1); //R�cup�ration de la carte qui suit la premi�re

            joueur1.ajouterCarte(carte1); //On ajoute la premi�re carte au paquet du joueur 1
            joueur2.ajouterCarte(carte2); //On ajoute la carte seconde au paquet du joueur 2
        }
		///////////////////////////////////////
		
		
		
		//BOUCLE DU JEU 
		boolean boucle = true; //Variable qui servira pour la boucle
		ArrayList<Carte> cartesEnJeu = new ArrayList<>(); //Collection qui servira en cas d'�galit� � stocker les cartes pouvant �tre perdues

		while (boucle == true){ //Tant qu'on peut faire la boucle (qu'un paquet n'est pas vide)
			// COMPARER DEUX CARTES //
			Carte carteTireeJ1 = joueur1.tirerCarte(); //Le joueur 1 tire une carte
			Carte carteTireeJ2 = joueur2.tirerCarte(); //Le joueur 2 tire une carte
			
			int comparaison = carteTireeJ1.compareTo(carteTireeJ2); //On stocke dans une variable le r�sultat de la m�thode compareTo
			
			if (comparaison < 0) { //LE JOUEUR 2 L'EMPORTE
			    System.out.println("Le " +carteTireeJ1+ " est plus petit que le " +carteTireeJ2); //On affiche que le joueur 2 gagne la carte du joueur 1
			    joueur1.removeCarte(carteTireeJ1); //On retire la carte du paquet du joueur 1
			    joueur2.ajouterCarte(carteTireeJ1); //On ajoute la carte perdu au paquet du joueur 2
			    
			    joueur2.removeCarte(carteTireeJ2); //On retire la carte que l'on avait en premi�re position 
			    joueur2.ajouterCarte(carteTireeJ2); //On place la carte qui �tait en premi�re position � la derni�re 
			    
			    if(cartesEnJeu.size() > 0) { //Si il y a des cartes dans le paquet des �galit�s
			    	for(Carte carteWin : cartesEnJeu) { //Pour chaque carte contenue dans le paquet
			    		joueur2.ajouterCarte(carteWin); //On ajoute la carte du paquet de mise en jeu au paquet du joueur 2
			    	}
			    	cartesEnJeu.clear(); //On enl�ve toutes les cartes qui �taient misent en jeu
			    }
			    joueur2.gagnerPoints(); //Donne un point au joueur 2
			} 
			
			else if (comparaison > 0) { //LE JOUEUR 1 L'EMPORTE
			    System.out.println("Le " +carteTireeJ1+ " est plus grand que le " + carteTireeJ2); //On affiche que le joueur 1 gagne la carte du joueur 2
			    joueur2.removeCarte(carteTireeJ2); //On retire la carte du paquet du joueur 2
			    joueur1.ajouterCarte(carteTireeJ2); //On ajoute la carte perdu au paquet du joueur 1
			    
			    joueur1.removeCarte(carteTireeJ1); //On retire la carte que l'on avait en premi�re position 
			    joueur1.ajouterCarte(carteTireeJ1); //On place la carte qui �tait en premi�re position � la derni�re position 
			    
			    if(cartesEnJeu.size() > 0) { //Si il y a des cartes dans le paquet des �galit�s
			    	for(Carte carteWin : cartesEnJeu) { //Pour chaque carte contenue dans le paquet
			    		joueur1.ajouterCarte(carteWin); //On ajoute la carte du paquet de mise en jeu au paquet du joueur 1
			    	}
			    	cartesEnJeu.clear(); //On enl�ve toutes les cartes qui �taient misent en jeu
			    }
			    joueur1.gagnerPoints(); //Donne un point au joueur 1
			} 
			
			else { //IL Y A EGALITE
			    System.out.println(carteTireeJ1 + " est �gale au " + carteTireeJ2); //On affiche qu'il y a une �galit�
			    
			    if(joueur1.getPaquet().size() == 1) { //SI LE JOUEUR 1 N'A PLUS QU'UNE CARTE
			    	
			    	cartesEnJeu.add(carteTireeJ2); //La carte du joueur 2 est ajout� au paquet de mise en jeu
			    	joueur2.removeCarte(carteTireeJ2); //Sa carte est donc retir� de son paquet
			    	
			    	Carte carteTireeJ2Egal = joueur2.tirerCarte(); //Le joueur 2 tire une carte qu'il ne connaitra pas
			    	
			    	cartesEnJeu.add(carteTireeJ2Egal); //On ajoute cette carte au paquet de mise en jeu
			    	joueur2.removeCarte(carteTireeJ2Egal); //Sa carte est donc aussi retir� dde son paquet
			    	
			    }else if(joueur1.getPaquet().size() == 2) { //SI LE JOUEUR 1 N'A PLUS QUE 2 CARTE
			    	
			    	cartesEnJeu.add(carteTireeJ1); //La carte du joueur 1 est ajout� au paquet de mise en jeu
			    	cartesEnJeu.add(carteTireeJ2); //Sa carte est donc retir� de son paquet
			    	
			    	joueur1.removeCarte(carteTireeJ1); //On retire la carte tir�e du paquet du joueur 1
			    	joueur2.removeCarte(carteTireeJ2); //On retire la carte tir�e du paquet du joueur 2
			    	
			    	Carte carteTireeJ2Egal = joueur2.tirerCarte(); //Le joueur 2 tire une carte qu'il ne connaitra pas
			    	
			    	cartesEnJeu.add(carteTireeJ2Egal); //On ajoute cette carte au paquet de mise en jeu
			    	joueur2.removeCarte(carteTireeJ2Egal); //Sa carte est donc aussi retir� de son paquet
			    	
			    }else if(joueur2.getPaquet().size() == 1) { //SI LE JOUEUR 2 N'A PLUS QU'UNE CARTE
			    	
			    	cartesEnJeu.add(carteTireeJ1); //La carte du joueur 1 est ajout� au paquet de mise en jeu
			    	joueur1.removeCarte(carteTireeJ1); //Sa carte est donc retir� de son paquet
			    	
			    	Carte carteTireeJ1Egal = joueur1.tirerCarte(); //Le joueur 1 tire une carte qu'il ne connaitra pas
			    
			    	cartesEnJeu.add(carteTireeJ1Egal); //On ajoute cette carte au paquet de mise en jeu
			    	joueur2.removeCarte(carteTireeJ1Egal); //Sa carte est donc aussi retir� dde son paquet
			    	
			    }else if(joueur2.getPaquet().size() == 2) { //SI LE JOUEUR 2 N'A PLUS QUE 2 CARTE
			    	
			    	cartesEnJeu.add(carteTireeJ1); //La carte du joueur 1 est ajout� au paquet de mise en jeu
			    	cartesEnJeu.add(carteTireeJ2); //La carte du joueur 2 est ajout� au paquet de mise en jeu
			    	
			    	joueur1.removeCarte(carteTireeJ1); //On retire la carte tir�e du paquet du joueur 1
			    	joueur2.removeCarte(carteTireeJ2); //On retire la carte tir�e du paquet du joueur 2
			    	
			    	Carte carteTireeJ1Egal = joueur1.tirerCarte(); //Le joueur 1 tire une carte qu'il ne connaitra pas
			    	
			    	cartesEnJeu.add(carteTireeJ1Egal); //On ajoute cette carte au paquet de mise en jeu
			    	joueur1.removeCarte(carteTireeJ1Egal); //Sa carte est donc aussi retir� de son paquet
			    	
			    }else { //SI LES JOUEURS ONT AU MOINS 3 CARTES CHACUN
			    	
			    	cartesEnJeu.add(carteTireeJ1); //La carte du joueur 1 est ajout� au paquet de mise en jeu
				    cartesEnJeu.add(carteTireeJ2); //La carte du joueur 2 est ajout� au paquet de mise en jeu
				    
				    joueur1.removeCarte(carteTireeJ1); //On retire la carte tir�e du paquet du joueur 1
				    joueur2.removeCarte(carteTireeJ2); //On retire la carte tir�e du paquet du joueur 2
				    
				    Carte carteTireeJ1Egal = joueur1.tirerCarte(); //Le joueur 1 tire une carte qu'il ne connaitra pas
					Carte carteTireeJ2Egal = joueur2.tirerCarte(); //Le joueur 2 tire une carte qu'il ne connaitra pas
					
					cartesEnJeu.add(carteTireeJ1Egal); //On ajoute la carte non connue du joueur 1 au paquet de mise en jeu
				    cartesEnJeu.add(carteTireeJ2Egal); //On ajoute la carte non connue du joueur 2 au paquet de mise en jeu
				    
				    joueur1.removeCarte(carteTireeJ1Egal); //On enl�ve la carte non connue du paquet du joueur 1
				    joueur2.removeCarte(carteTireeJ2Egal); //On enl�ve la carte non connue du paquet du joueur 2
			    } 
			    
			    
			    System.out.println("Cartes mises en jeu lors de l'�galit�: "+cartesEnJeu); //On affiche le paquet de carte � gagner !
			}
			///////////////////////////////////////
			
			
			
			//AFFICHAGE DES PAQUETS DES JOUEURS
			System.out.println("Cartes du joueur 1:" +joueur1.getPaquet()); //On affiche le paquet de carte du joueur 1
			System.out.println("Cartes du joueur 2:" +joueur2.getPaquet()+"\n"); //On affiche le paquet de carte du joueur 2
			///////////////////////////////////////
			
			
			
			//VERIFICATION DES PAQUETS (SI VIDE ALORS FIN DE PARTIE)
			if(joueur1.getPaquet().size() == 0 || joueur2.getPaquet().size() == 0) { //Si un des joueurs n'a plus de cartes � jouer
				boucle = false; //On passe la boucle a false pour en sortir
			}
			///////////////////////////////////////
			
			//AFFICHAGE DES SCORES
			System.out.println(joueur1.getNom()+ " a : " +joueur1.getPoints()+ " points."); //Affichage des points du joueur 1
			System.out.println(joueur2.getNom()+ " a : " +joueur2.getPoints()+ " points.\n"); //Affichage des points du joueur 2
			///////////////////////////////////////
			
		}
		///////////////////////////////////////
		
		
		
		//QUI GAGNE ?
		if(joueur1.getPaquet().size() == 0) { //Si le paquet du joueur 1 est vide
			System.out.println(joueur2.getNom()+ " gagne avec: " +joueur2.getPoints()+ " points."); //On affiche que le joueur 2 gagne
		}
		else if(joueur2.getPaquet().size() == 0){ //Si le paquet du joueur 2 est vide
			System.out.println(joueur1.getNom()+ " gagne avec: " +joueur1.getPoints()+ " points."); //On affiche que le joueur 1 gagne
		}
		///////////////////////////////////////
	}

}

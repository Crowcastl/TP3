package joueur;

/**
 * Définition du Joueur.
 * 
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP2
 */

import java.util.*;

/**
 * Cette classe représente le joueur humain. Elle surcharge le
 * personnage abstrait pour le déplacement et propose une méthode
 * pour mettre à jours la visibilité des cases en fonction de la vision.
 * 
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP2
 */


import dongon.Case;
import equipements.AbstractEquipement;
import equipements.Arme;
import equipements.Armure;
import equipements.Casque;
import equipements.Potion;
import personnage.AbstractPersonnage;
import physique.Direction;
import physique.Position;

public class Joueur extends AbstractPersonnage {
	
	private final int PROFONDEUR_VISION = 2;
	private boolean mouvement = true;
	
	//Ajout de la collection qui contiendra tous les �quipements ramass�s par le joueur
	private List<AbstractEquipement> equipements = new ArrayList<AbstractEquipement>();

	/**
	 * Construceur par paramètre
	 * @param pos, position du joueur
	 */
	public Joueur() {
		pointDeVie=100;
		pointDeVieMax=100;
	}

	/**
	 * surcharge de la méthode pour déplacer le joueur dans la direction donnée
	 * @param direction(int), direction du mouvement
	 */
	public void seDeplacer(int direction){
		
		if(mouvement) {
			// se déplacer
			super.seDeplacer(direction);
			
			// mise à jour de la vision
			mettreAJourVision();
		}
	}
	

	/**
	 * surcharge de la méthode pour placer le joueur à sa case de départ
	 * @param caseCourante(Case), case courante
	 */
	public void setCase(Case caseCourante){
		
		// assigne la case
		super.setCase(caseCourante);

		// mise à jour de la vision
		mettreAJourVision();
	}
	
	/**
	 * méthode qui mets à jour la vision
	 */
	private void mettreAJourVision(){
		
		// rend visible la case courante
		super.caseCourante.setDecouverte(true);
		
		// dans toutes les directions
		for(int i=0;i<Direction.NB_DIRECTIONS;i++){
			
			// dévoile les voisins jusqu'à la profondeur de la vision
			Case voisin = super.caseCourante.getVoisin(i);
			for(int j=0;j<PROFONDEUR_VISION;j++){
				if(voisin!=null){
					voisin.setDecouverte(true);
					voisin = voisin.getVoisin(i);
				}
			}
		}
	}

	public void setMouvement(boolean etat){
		this.mouvement = etat;
	}
	

	/**
	 * Remise à zéro du joueur
	 * - remet les points de vie à max
	 * - vide équipement
	 */
	public void remiseAZero(){
		this.pointDeVie = this.pointDeVieMax;
		
		//vider la liste d'equipements (vider la collection)
		for (int i=0;i<this.equipements.size();i++)
		{
			this.equipements.remove(i);
		}
	}
	
	/**
	 * Methode qui recoit une reference a la piece d'equipement a ramasser et qui:
	 * - indique quil nest plus au sol avec la mutatrice
	 * - ajoute la reference a la collection (voir collection dans les attributs)
	 */
	public void membre(AbstractEquipement equipement)
	{
		//indique quil nest plus au sol avec la mutatrice
		equipement.setAuSol(false);
		
		//ajoute la reference a la collection
		this.equipements.add(equipement);
	}
}

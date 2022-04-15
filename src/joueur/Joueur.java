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
	
	//3 variables membres
	//reference au casque equipe
	Casque casqueEquipe = new Casque(pos);
	//reference a l'armure equipee
	Armure armureEquipe = new Armure(pos);
	//reference a l'arme equipee
	Arme armeEquipe = new Arme(pos);

	/**
	 * Construceur par paramètre
	 * @param pos, position du joueur
	 */
	public Joueur() {
		pointDeVie=100; //CHANGER POUR 100 !!!!!!
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
			
			System.out.println(equipements.toString());
			
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
	 * - vide la collection d'equipement
	 */
	public void remiseAZero(){
		this.pointDeVie = this.pointDeVieMax;
		
		//vider la liste d'equipements (vider la collection)
		this.equipements.clear();
	}
	
	/**
	 * Methode qui recoit une reference a la piece d'equipement a ramasser et qui:
	 * - indique quil nest plus au sol avec la mutatrice
	 * - ajoute la reference a la collection (voir collection dans les attributs)
	 */
	public void membre(AbstractEquipement equipement)
	{
		//ajoute la reference a la collection
		if(equipement.estAusol())
		{
			this.equipements.add(equipement);
			
		}
		//indique quil nest plus au sol avec la mutatrice
		equipement.setAuSol(false);
		
		
	
	}
	
	/**
	 * Methode informatrice qui retourne une reference sur la collection d'equipements   
	 */              
	public List<AbstractEquipement> getEquipements()
	{
		return this.equipements;
	}
	
	/**
	 * Methode informatrice qui retourne une reference sur le casque equipe
	 */
	public Casque getCasqueEquipe()
	{
		return this.casqueEquipe;
	}
	
	/**
	 * Methode informatrice qui retourne une reference sur l'armure equipee
	 */
	public Armure getArmureEquipe()
	{
		return this.armureEquipe;
	}
	
	/**
	 * Methode informatrice qui retourne une reference sur l'arme equipee
	 */
	public Arme getArmeEquipee()
	{
		return this.armeEquipe;
	}
	
	/**
	 * Methode qui permet d'equiper une piece d'equipement recu en parametre
	 */
	public void equiper(AbstractEquipement equipement)
	{
		//identification du type de la piece d'equipement (instanceof)
		//les seuls types d'equipements a identifier sont: casque, armure, arme
		if(equipement instanceof Casque)
		{
			//change l'equipement equipe en question
			this.casqueEquipe = (Casque) equipement;
		}
		
		else if (equipement instanceof Armure)
		{
			//change l'equipement equipe en question
			this.armureEquipe = (Armure) equipement;
		}
		
		else if (equipement instanceof Arme)
		{
			//change l'equipement equipe en question
			this.armeEquipe = (Arme) equipement;
		}
		
		//remet la valeur de la variable membre armure a 0
		this.armureEquipe.setValeur(0);
		
		//assigne a armure la somme des valeurs obtenues des equipements de defense (arme et casque)
		this.armureEquipe.setValeur(this.armeEquipe.getValeur()+this.casqueEquipe.getValeur());
		
		//remet la valeur de la variable membre bonusAttaque a 0
		this.bonusAttaque = 0;
		
		//remet la valeur de la variable membre bonusDefance a 0
		this.bonusDeffance = 0;
		//assigne a bonusAttaque la valeur de l'arme equipee, s'il y en a une
		if(armeEquipe != null)
			this.bonusAttaque = armeEquipe.getValeur();
		//assigne a bonusDeffance la valeur du casque et de l'armuse s'il y en as
		if(casqueEquipe != null)
			this.bonusDeffance += casqueEquipe.getValeur();
		if(armureEquipe != null)
			this.bonusDeffance += armureEquipe.getValeur();
	}
	
	/**
	 * Methode qui trouve la premiere potion dans la collection d'equipements, l'enleve et remet les points de vie a point de vie max.
	 * NOTE: Si cette methode est appelee mais qu'il n'y a aucune potion elle n'a aucun effet
	 */
	public void utiliserPotion()
	{
		for(int i=0;i<this.equipements.size();i++)
		{
			if(this.equipements.get(i) instanceof Potion)
			{
				this.equipements.remove(i);
				this.pointDeVie = pointDeVieMax;
			}
		}
	}
	
	/**
	 * Methode qui enleve l'equipement s'il y a une remise a zero
	 * Faire un appel a equiper(null) pour r�g�n�rer les calculs
	 */
	public void remiserAZero()
	{
		this.armeEquipe = null;
		this.armureEquipe = null;
		this.casqueEquipe = null;
		equiper(null);
	}
}

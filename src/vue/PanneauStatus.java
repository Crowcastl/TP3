package vue;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import equipements.AbstractEquipement;
import modele.PlanDeJeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.Queue;

import observer.MonObserver;

public class PanneauStatus extends JPanel implements MonObserver, ItemListener{
	//Général
	Dimension dimention;
	PlanDeJeu planDejeu = PlanDeJeu.getInstance();
	//Pour début fin de combat
	private boolean compteurCombat = false;
	//Pour savoir nouveau niveau
	private int niveauCourant = 0;
	private int niveauPrecedent = 0;
	//Pour savoir si ramasser items
	private int itemsJoueurCourant = 0;
	private int itemsJoueurPrecedent = 0;
	private int nbreNouveauItems = 0;
	//Pour compter le nombre d'ennemis  tuée
	private int nbreEnnemisMorts = 0;
	//Temps
	private int compteurTemps = 0;
	//Panneaux Principaux
	private PanneauStatusHaut pStatusHaut;
	private PanneauStatusMilieu pStatusMilieu;
	private PanneauStatusBas pStatusBas;
	
	
	
	public PanneauStatus(Dimension dimention) {
		//Initialisation panneau Status
		this.setLayout(new GridLayout(3,1));
		
		this.dimention = new Dimension();
		this.dimention.setSize(dimention.width/3,dimention.height);
		this.setPreferredSize(this.dimention);
		
		pStatusHaut = new PanneauStatusHaut();
		pStatusMilieu = new PanneauStatusMilieu();
		pStatusBas = new PanneauStatusBas(this.dimention);
		
		this.add(pStatusHaut);
		this.add(pStatusMilieu);
		this.add(pStatusBas);
		
		planDejeu.attacherObserver(this);
		
		
		pStatusMilieu.getBoutonPotion().addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		         planDejeu.getJoueur().utiliserPotion();
		         ajouterMessageFile("Joueur prend une potion!");
		         pStatusMilieu.mettreAJoursInfo();
		    }
		}); 
		
		
		
		pStatusMilieu.getComboArme().addItemListener(new ItemListener () {
			public void itemStateChanged(ItemEvent event) {
				
				if (event.getStateChange() == ItemEvent.SELECTED) {
					Object armeItem = event.getItem();
					planDejeu.getJoueur().equiper((AbstractEquipement) armeItem);
					ajouterMessageFile("Joueur équipe "+ armeItem);
					pStatusMilieu.mettreAJoursInfo();
				}
			}		    
		});
		
		pStatusMilieu.getComboArmure().addItemListener(new ItemListener () {
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				Object armureItem = event.getItem();
				planDejeu.getJoueur().equiper((AbstractEquipement) armureItem);
				ajouterMessageFile("Joueur équipe "+ armureItem);
				pStatusMilieu.mettreAJoursInfo();
			}
		}		    
	});

		pStatusMilieu.getComboCasque().addItemListener(new ItemListener () {
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				Object casqueItem = event.getItem();
				planDejeu.getJoueur().equiper((AbstractEquipement) casqueItem);
				ajouterMessageFile("Joueur équipe "+ casqueItem);
				pStatusMilieu.mettreAJoursInfo();
			}
		}		    
	});
	}

	public void ajouterMessageFile(String message) {
		pStatusBas.ajouterMessageFile(message);
	}

	
	@Override
	public void avertir() {
		repaint();
		//Pour le temps
		compteurTemps +=2;
		//Pour savoir si récupérer nouveau items
		itemsJoueurCourant = planDejeu.getJoueur().getEquipements().size();
		
		if(itemsJoueurCourant != itemsJoueurPrecedent)
		{
			nbreNouveauItems = itemsJoueurCourant - itemsJoueurPrecedent;
			while(nbreNouveauItems >0)
			{
				ajouterMessageFile("Joueur ramasse équipement");
				nbreNouveauItems--;
			}
			itemsJoueurPrecedent = itemsJoueurCourant;
		}
		//Pour savoir si on est en début ou fin de combat
		if(planDejeu.estEnCombat() && compteurCombat == false)
		{
			ajouterMessageFile("Debut de combat");
			compteurCombat = true;
		}
		if(!planDejeu.estEnCombat() && compteurCombat == true)
		{
			ajouterMessageFile("Fin combat");
			compteurCombat = false;
			nbreEnnemisMorts++;
		}
		//Pour savoir si nouveau niveau
		niveauCourant= planDejeu.getNiveau();
		if(niveauCourant != niveauPrecedent)
		{
			ajouterMessageFile("Nouveau Niveau!");
			niveauPrecedent = niveauCourant;
		}
		
		//Actualiser le tout 
		pStatusHaut.mettreAJoursInfo(nbreEnnemisMorts);
		pStatusMilieu.mettreAJoursInfo();
		pStatusBas.mettreAJoursInfo();
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			Object item = event.getItem();
			planDejeu.getJoueur().equiper((AbstractEquipement) item);
		}
	} 
	
	

	
}

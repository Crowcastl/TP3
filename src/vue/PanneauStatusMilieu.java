package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import creature.AbstractCreature;
import equipements.AbstractEquipement;
import equipements.Arme;
import equipements.Armure;
import equipements.Casque;
import equipements.Potion;
import modele.PlanDeJeu;
import observer.MonObserver;

public class PanneauStatusMilieu extends JPanel {

	//Général
	private Dimension dimention;
	private PlanDeJeu planDejeu = PlanDeJeu.getInstance();
	
	private JPanel pHero;
	private JPanel pEquipement;
	private int compteurPotion = 0;
	//Deffance
	private JLabel deffTot = new JLabel("Défence totale: "+ planDejeu.getJoueur().getArmure());
	private JLabel casqueTxt = new JLabel("Casque: ");
	private JComboBox<AbstractEquipement> casqueCombo = new JComboBox<AbstractEquipement>();//Pas sur type
	private JLabel armureTxt = new JLabel("armure: ");
	private JComboBox <AbstractEquipement> armureCombo= new JComboBox<AbstractEquipement>();//Pas sur type
		//Attaque
	private JLabel attTot = new JLabel("Attaque totale: " + planDejeu.getJoueur().getForce());
	private JLabel armeTxt = new JLabel("Arme: ");
	private JComboBox<AbstractEquipement> armeCombo = new JComboBox<AbstractEquipement>();//Pas sur type
		//Potions
	private JLabel nbPotion = new JLabel("NB Potions = "+ compteurPotion);
	private JButton boutonPotion = new JButton("Utiliser Potion");
	

	
	
	private List<AbstractEquipement> equipement =  planDejeu.getJoueur().getEquipements();
	
	public  PanneauStatusMilieu() {
		//Ini panneau
		this.setLayout(new GridLayout(1,2));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//Ini Hero
		pHero = new JPanel();
		try {
			configImageHero();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		//Ini pEquipement
		pEquipement = new JPanel();
		pEquipement.setLayout(new GridLayout(10,1));
			//Caractéristique  (manque celle de JComboBox)
		
		boutonPotion.setEnabled(compteurPotion>0?true:false);
			//Ajout dans pEquipement
		pEquipement.add(deffTot);
		pEquipement.add(casqueTxt);
		pEquipement.add(casqueCombo);
		pEquipement.add(armureTxt);
		pEquipement.add(armureCombo);
		pEquipement.add(attTot);
		pEquipement.add(armeTxt);
		pEquipement.add(armeCombo);
		pEquipement.add(nbPotion);
		pEquipement.add(boutonPotion);
		
		casqueCombo.removeAllItems();
		armureCombo.removeAllItems();
		armeCombo.removeAllItems();
		
		
		//Ajout sur Panneau Milieu
		this.add(pHero);
		this.add(pEquipement);
		
		
		
		//PAS SUR OU METTRE CELA PEUT ETRE DOIT CHANGER DE PLACE
		
	}
	
	public void configImageHero() throws IOException{
		BufferedImage image = ImageIO.read(new File("images/arrowtotheKNee.png"));
		pHero.add(new JLabel(new ImageIcon(image)));
	}
	
	public void mettreAJoursInfo() {
		attTot.setText("Attaque totale: "+ planDejeu.getJoueur().getForce());
		deffTot.setText("Défence totale: "+ planDejeu.getJoueur().getArmure());
		nbPotion.setText("NB Potions = "+ compteurPotion);
		casqueCombo.setSelectedItem(planDejeu.getJoueur().getCasqueEquipe());
		armureCombo.setSelectedItem(planDejeu.getJoueur().getArmureEquipe());
		armeCombo.setSelectedItem(planDejeu.getJoueur().getArmureEquipe());
		
		
		casqueCombo.removeAllItems();
		armureCombo.removeAllItems();
		armeCombo.removeAllItems();
		compteurPotion = 0;
		
		 ListIterator<AbstractEquipement> iterateur = equipement.listIterator();	
		 
		 while(iterateur.hasNext()){
			 AbstractEquipement equipementEvaluer = iterateur.next();
			 
			 if(equipementEvaluer instanceof Casque)
			 {
				 casqueCombo.addItem(equipementEvaluer);
			 }
			 else if(equipementEvaluer instanceof Armure) 
			 {
				 armureCombo.addItem(equipementEvaluer);
			 }
			 else if(equipementEvaluer instanceof Arme)
			 {
				 armeCombo.addItem(equipementEvaluer);
			 }
			 else if(equipementEvaluer instanceof Potion)
			 {
				compteurPotion++; 
			 }
			 else
			 {
				 System.out.println("problème dans la répartition des équipements");
			 }
			 
		 }
		boutonPotion.setEnabled(compteurPotion>0?true:false);
		
		
		
		
	 }
	
	public JButton getBoutonPotion() {
		return boutonPotion;
	}

	
	public JComboBox getComboArme() {
		return this.armeCombo;	
	}
	
	public JComboBox getComboArmure() {
		return this.armureCombo;	
	}
	
	public JComboBox getComboCasque() {
		return this.casqueCombo;	
	}
}




























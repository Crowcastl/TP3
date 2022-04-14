package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.PlanDeJeu;
import observer.MonObserver;

public class PanneauStatusMilieu extends JPanel implements MonObserver{

	//Général
	Dimension dimention;
	PlanDeJeu planDejeu = PlanDeJeu.getInstance();
	
	private JPanel pHero;
	private JPanel pEquipement;
	
	//Deffance
	JLabel deffTot = new JLabel("Défence totale: X");
	JLabel casqueTxt = new JLabel("Casque: ");
	JComboBox<String> casqueCombo = new JComboBox<String>();//Pas sur type
	JLabel armureTxt = new JLabel("armure: ");
	JComboBox <String> armureCombo= new JComboBox<String>();//Pas sur type
		//Attaque
	JLabel attTot = new JLabel("Attaque totale: X");
	JLabel armeTxt = new JLabel("Arme: ");
	JComboBox<String> armeCombo = new JComboBox<String>();//Pas sur type
		//Potions
	JLabel nbPotion = new JLabel("NB Potions = X");
	JButton boutonPotion = new JButton("Utiliser Potion");
	
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
		boutonPotion.setEnabled(false);//Pas sur
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
		
		//Ajout sur Panneau Milieu
		this.add(pHero);
		this.add(pEquipement);
	}
	
	public void configImageHero() throws IOException{
		BufferedImage image = ImageIO.read(new File("images/hero.png"));
		pHero.add(new JLabel(new ImageIcon(image)));
	}
	
	@Override
	public void avertir() {
		// TODO Auto-generated method stub
		
	}

}

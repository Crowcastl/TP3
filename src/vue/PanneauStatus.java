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

import modele.PlanDeJeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import observer.MonObserver;

public class PanneauStatus extends JPanel implements MonObserver{
	//Général
	Dimension dimention;
	PlanDeJeu planDejeu = PlanDeJeu.getInstance();
	
	//Panneaux Principaux
	private JPanel pStatusHaut;
	private JPanel pStatusMilieu;
	private JPanel pHero;
	private JPanel pEquipement;
	private JPanel pStatusBas;
	
	//Panneaux Haut
	JLabel lable = new JLabel("Leeroy Jenkins", SwingConstants.CENTER);
	JProgressBar barreVie = new JProgressBar();
	JLabel numNiveau = new JLabel("Niveau: X", SwingConstants.CENTER);
	JLabel nbEnnemieAff = new JLabel("NB Ennemis Tues: X", SwingConstants.CENTER);
	JLabel tempsEcou = new JLabel("Temps de jeu: X secondes", SwingConstants.CENTER);
	
	//Panneaux Milieu 
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
	
	
	public PanneauStatus(Dimension dimention) {
		//Initialisation panneau Status
		this.setLayout(new GridLayout(3,1));
		
		this.dimention = new Dimension();
		this.dimention.setSize(dimention.width/3,dimention.height);
		this.setPreferredSize(this.dimention);
		
		
		iniPanneauHaut();
		iniPanneauMilieu();
		iniPanneauBas();
		
		
		
		
	}
	public void iniPanneauHaut() {
		//Initialisation du panneau
		pStatusHaut = new JPanel();
		pStatusHaut.setLayout(new GridLayout(5,1));
		pStatusHaut.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		
		//Spécification components
		Font styleTxt = new Font("Times New Roman",Font.BOLD | Font.ITALIC,24);
		lable.setFont(styleTxt);
		
		barreVie.setForeground(Color.GREEN);
		barreVie.setBackground(Color.RED);
		
		//Ajout components au panneau
		pStatusHaut.add(lable);
		pStatusHaut.add(barreVie);
		pStatusHaut.add(numNiveau);
		pStatusHaut.add(nbEnnemieAff);
		pStatusHaut.add(tempsEcou);
		
		
		this.add(pStatusHaut);
	}
	
	public void iniPanneauMilieu() {
		//Ini panneau
		pStatusMilieu = new JPanel();
		pStatusMilieu.setLayout(new GridLayout(1,2));
		pStatusMilieu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
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
		boutonPotion.setDefaultCapable(false);//Pas sur
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
		pStatusMilieu.add(pHero);
		pStatusMilieu.add(pEquipement);
		this.add(pStatusMilieu);
	}
	
	public void iniPanneauBas() {
		
	}
	
	public void configImageHero() throws IOException{
		BufferedImage image = ImageIO.read(new File("images/hero.png"));
		pHero.add(new JLabel(new ImageIcon(image)));
	}
	
	@Override
	public void avertir() {
		repaint();
	}
	
	
}

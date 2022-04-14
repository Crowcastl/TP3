package observer;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import creature.AbstractCreature;
import creature.*;
import modele.GestionnaireCombat;
import personnage.AbstractPersonnage;

public class popUpCombat extends JFrame implements MonObserver, WindowListener{

	private AbstractPersonnage hero;
	private AbstractCreature creature;
	private GestionnaireCombat gestionnaireCombat;
	private JTextArea jTexte = new JTextArea(16,20);
	private JScrollPane jScroll = new JScrollPane(jTexte);
	private JPanel jPanel = new JPanel();
	
	public popUpCombat(AbstractPersonnage joueur, AbstractCreature ennemi, GestionnaireCombat gestionnaire) {
		hero = joueur;
		creature = ennemi;
		gestionnaireCombat = gestionnaire;
		configFrame();
		try {
			configImageHero();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		configBoiteMessage();
		try {
			configImageCreature(creature);
		}catch (IOException e) {
			e.printStackTrace();
		}
		requestFocus();
		setVisible(true);
	}
	
	public void configFrame() {
		Container container = this.getContentPane();
		this.setLayout(new BorderLayout());
		//Insérer le jPanel dans le frame
		this.add(jPanel, BorderLayout.CENTER);
		//Instorer la grandeur du frame et sa position
		this.setLocation(600,300);
		this.setSize(800,400);
		
		//Mettre le jPanel comme un gridlayout
		jPanel.setLayout(new GridLayout(0,3));
		//this.setResizable(false); //Optionnel à voir plus tard
		
		
		this.addWindowListener(this);//pas sur mais l'intention: JFrame est ajouter au listenner dans le popUpCombat
		
		
	}
	
	public void configImageHero() throws IOException {
		
		BufferedImage image = ImageIO.read(new File("images/FightingHero.png"));
		jPanel.add(new JLabel(new ImageIcon(image)));
		

	}
	
	public void configBoiteMessage() {
		jTexte.setSize(245,400);
		jTexte.setEditable(false);
		//initialiser la référence membre prévu à cet effet?jsp quoi faire
		//jScroll.add(jTexte);//peut être besoin de consulté liens du prof si s'affiche pas
		jScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//initialiser la référence membre prévu à cet effet?jsp quoi faire
		jPanel.add(jScroll);
		
	}
	
	public void configImageCreature(AbstractCreature ennemi) throws IOException{
		if (ennemi instanceof Araigne)
		{
			
			BufferedImage image = ImageIO.read(new File("images/spooder.png"));
			jPanel.add(new JLabel(new ImageIcon(image)));
			
		}
		else if (ennemi instanceof Dragon)
		{
			
			BufferedImage image = ImageIO.read(new File("images/betterDragon.png"));
			jPanel.add(new JLabel(new ImageIcon(image)));
			
		}
		else if (ennemi instanceof Minotaure)
		{
			
			BufferedImage image = ImageIO.read(new File("images/Cow.png"));
			jPanel.add(new JLabel(new ImageIcon(image)));
			
		}
	}
	
	
	@Override
	public void avertir() {
		String texte = gestionnaireCombat.getMsg();
		jTexte.setText(texte);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Object source = e.getSource();
		if(source == this) {
			gestionnaireCombat.combatTermine();
		}
		
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}

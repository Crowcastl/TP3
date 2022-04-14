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
	//G�n�ral
	Dimension dimention;
	PlanDeJeu planDejeu = PlanDeJeu.getInstance();
	
	//Panneaux Principaux
	private PanneauStatusHaut pStatusHaut;
	private PanneauStatusMilieu pStatusMilieu;
	private JPanel pHero;
	private JPanel pEquipement;
	private PanneauStatusBas pStatusBas;
	
	
	public PanneauStatus(Dimension dimention) {
		//Initialisation panneau Status
		this.setLayout(new GridLayout(3,1));
		
		this.dimention = new Dimension();
		this.dimention.setSize(dimention.width/3,dimention.height);
		this.setPreferredSize(this.dimention);
		
		pStatusHaut = new PanneauStatusHaut();
		pStatusMilieu = new PanneauStatusMilieu();
		pStatusBas = new PanneauStatusBas();
		
		this.add(pStatusHaut);
		this.add(pStatusMilieu);
		this.add(pStatusBas);
	}

	@Override
	public void avertir() {
		repaint();
	}
	
	
}
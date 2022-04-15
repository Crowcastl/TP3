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

import observer.MonObserver;

public class PanneauStatus extends JPanel implements MonObserver, ItemListener{
	//Général
	Dimension dimention;
	PlanDeJeu planDejeu = PlanDeJeu.getInstance();
	
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
		         avertir();
		    }
		}); 
		
		
		
		pStatusMilieu.getComboArme().addItemListener(new ItemListener () {
			public void itemStateChanged(ItemEvent event) {
				
				if (event.getStateChange() == ItemEvent.SELECTED) {
					Object armeItem = event.getItem();
					planDejeu.getJoueur().equiper((AbstractEquipement) armeItem);
					avertir();
				}
			}		    
		});
		
		pStatusMilieu.getComboArmure().addItemListener(new ItemListener () {
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				Object armureItem = event.getItem();
				planDejeu.getJoueur().equiper((AbstractEquipement) armureItem);
				avertir();
			}
		}		    
	});

		pStatusMilieu.getComboCasque().addItemListener(new ItemListener () {
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				Object casqueItem = event.getItem();
				planDejeu.getJoueur().equiper((AbstractEquipement) casqueItem);
				avertir();
			}
		}		    
	});
	}

	@Override
	public void avertir() {
		repaint();
		pStatusHaut.mettreAJoursInfo();
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

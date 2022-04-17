package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import modele.PlanDeJeu;
import observer.MonObserver;

public class PanneauStatusBas extends JPanel {

	//Général
	Dimension dimention;
	PlanDeJeu planDejeu = PlanDeJeu.getInstance();
	
	private Queue<String> fileMessage = new LinkedList<String>();

	private JTextArea jTexte;
	private JScrollPane jScroll;
	
	public PanneauStatusBas(Dimension dimention) {
		this.setLayout(new GridLayout(1,0));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.dimention = new Dimension(dimention);

		
		jTexte = new JTextArea();
		jTexte.setCaretPosition(jTexte.getDocument().getLength());// Pour mettre la scroll bar toujours en bas
		jTexte.setEditable(false);
		
		jScroll = new JScrollPane(jTexte);
//		jScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);// Pour faire défiler le scroll automatiquement, fct pas
		this.add(jScroll);		
	}
	
	public void ajouterMessageFile(String message) {
		fileMessage.offer(message);
	}
	
	
public void mettreAJoursInfo() {
	String console;
	System.out.println(fileMessage);
	while(!fileMessage.isEmpty())
	{
		console = fileMessage.poll();
		jTexte.setText(jTexte.getText()+ console+"\n");
	}
	
	}
	

}

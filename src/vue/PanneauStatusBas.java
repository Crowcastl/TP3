package vue;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Queue;

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
		this.dimention = new Dimension(dimention);
		this.dimention.setSize(dimention.width,dimention.height);
		
		jTexte = new JTextArea(dimention.width/30, dimention.height/19);
		jTexte.setSize(this.dimention);
	
		jTexte.setEditable(false);
		
		jScroll = new JScrollPane(jTexte);
		jScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScroll.setSize(this.dimention);
		
		this.add(jScroll);		
	}
	
	public void ajouterMessageFile(String message) {
		fileMessage.offer(message);
	}
	
	
public void mettreAJoursInfo() {
	String console;
	
	while(!fileMessage.isEmpty())
	{
		console = fileMessage.poll();
		jTexte.setText(jTexte.getText()+ console+"\n");
	}
	
	}
	

}

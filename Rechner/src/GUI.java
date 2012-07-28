import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;



public class GUI extends JFrame{
	StringTableModel loModel = new StringTableModel();
	// Anfang Komponenten für das Eingabefeld
	final JTextField eingabeFeld = new JTextField("Eingabe einer Zahl");
	final JTable table = new JTable();
	public GUI() {	

		
		//Fenster-Objekt
		final JFrame window = new JFrame();
		//Titel des Fensters
		window.setTitle("Rechner");
		//Größe des Fensters
		window.setSize(500,300);
		//Menü
		window.setMenuBar(this.getMenuBar());
		
		//Schließverhalten des Fensters
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		

		
		table.setModel(loModel);
		
		eingabeFeld.setHorizontalAlignment(eingabeFeld.LEFT);
		
		eingabeFeld.selectAll();
		eingabeFeld.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!eingabeFeld.getText().equals("")) {
					StringTableModel loModel = (StringTableModel)table.getModel();
					loModel.addEingabe(eingabeFeld.getText());
					eingabeFeld.setText("");
				}
			}
		});
		//Füge Eingabefeld zum Frame hinzu
		window.add(eingabeFeld, BorderLayout.NORTH);
		
		
		//Füge die Tabelle zu Scrollpane hinzu
		JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setLayout(new ScrollPaneLayout());
		table.setFillsViewportHeight(true);
		
		window.add(scrollPane, BorderLayout.CENTER);
		    
		
		//Darstellung des Fensters
		window.setVisible(true);
	}
	/**
	* @param args
	*/
	public static void main(String[] args) {
	GUI gui = new GUI();
	
	}
	
	/**
	 * Gibt die Menübar zurück
	 */
	public MenuBar getMenuBar () {
	
		//Menü erstellen
		MenuBar menulist = new MenuBar();
		Menu datei = new Menu("Datei");
		
		//open
		MenuItem open = new MenuItem("Open");
		datei.add(open);
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			System.out.println("File opened");
			String filename = "d:/datum.ser";
			load(filename);
			}
		});
		
		
		//save
		MenuItem save = new MenuItem("Save");
		datei.add(save);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("File saved");
				String filename = "d:/datum.ser";
				save(filename);
			}
		});
		
		//exit
		MenuItem exit = new MenuItem("Exit");
		datei.add(exit);
		
		//Actionlistener für exit
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exit");
				System.exit(0);
			}
			});
		menulist.add(datei);
		return menulist;
	}
	
	/**
	 * Speichert die Tabelle
	 * @param filename
	 */
	public void save(String filename) {
		try {
			System.out.println("Hinzufügen");
			FileOutputStream output = new FileOutputStream(filename);
			ObjectOutputStream object = new ObjectOutputStream(output);
				object.writeObject(loModel);
			object.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	
	/**
	 * Lädt die gespeichterte Tabelle
	 * @param filename
	 */
	public void load(String filename) {
		try {
			FileInputStream input = new FileInputStream(filename);
			ObjectInputStream object = new ObjectInputStream(input);
			loModel.clear();
			repaint();
			loModel = (StringTableModel) object.readObject();	
			System.out.println(loModel.getRowCount());
			table.setModel(loModel);
			repaint();
			input.close();
		} catch (IOException e) {
		} catch (ClassNotFoundException ex) {
		}
	}
	
	public void add() {
		
	}

}
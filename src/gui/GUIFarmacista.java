package gui;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import boundary.BFarmacista;

import javax.swing.JComboBox;

public class GUIFarmacista {

	JFrame frame;
	private String usernameFarmacista;
	private JComboBox<String> comboBoxGiorno;
	private JComboBox<String> comboBoxMese;
	private JComboBox<String> comboBoxAnno;
	private JComboBox<String> comboBoxGiorno2;
	private JComboBox<String> comboBoxMese2;
	private JComboBox<String> comboBoxAnno2;
	private JComboBox<String> esitoVaccinazione;
	private BFarmacista farmacista=new BFarmacista();
	private JTextField textField_usernameFarmacista;
	private JTextField textField_emailCliente;
	private JTextField textField_emailCliente2;
	private JTextField textUsername_Farmacista;


//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUIFarmacista window = new GUIFarmacista("531Farmacista");
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	public GUIFarmacista(String username) {
		this.usernameFarmacista=username;
		initialize();

	}


	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Dialog", Font.BOLD, 24));
		frame.getContentPane().setBackground(new Color(64, 0, 128));
		frame.setBounds(100, 100, 1170, 776);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		

		JLabel lblGiorno = new JLabel("Giorno");
		lblGiorno.setForeground(new Color(255, 255, 255));
		lblGiorno.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblGiorno.setBounds(803, 66, 78, 13);
		frame.getContentPane().add(lblGiorno);
		
		
		String[] giorniDelMese=new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		
		comboBoxGiorno= new JComboBox<>();
		comboBoxGiorno.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxGiorno.setModel(new DefaultComboBoxModel<>(giorniDelMese));
		comboBoxGiorno.setMaximumRowCount(31);
		comboBoxGiorno.setBounds(803, 89, 78, 21);
//		comboBoxGiorno.addActionListener((ActionListener) this);
		frame.getContentPane().add(comboBoxGiorno);
		
		
		String[] mesi=new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		
		comboBoxMese = new JComboBox<>();
		comboBoxMese.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxMese.setMaximumRowCount(12);
		comboBoxMese.setModel(new DefaultComboBoxModel<>(mesi));
		comboBoxMese.setBounds(910, 89, 78, 21);
//		comboBoxMese.addActionListener((ActionListener) this);
		frame.getContentPane().add(comboBoxMese);
		
		comboBoxAnno = new JComboBox<>();
		comboBoxAnno.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxAnno.setModel(new DefaultComboBoxModel<>(new String[] {"2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		comboBoxAnno.setMaximumRowCount(7);
		comboBoxAnno.setBounds(1015, 89, 78, 21);
//		comboBoxAnno.addActionListener((ActionListener) this);
		frame.getContentPane().add(comboBoxAnno);
		

		JLabel lblMese = new JLabel("Mese");
		lblMese.setForeground(Color.WHITE);
		lblMese.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblMese.setBounds(911, 443, 77, 13);
		frame.getContentPane().add(lblMese);
		
		JLabel lblAnno = new JLabel("Anno");
		lblAnno.setForeground(Color.WHITE);
		lblAnno.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblAnno.setBounds(1015, 443, 74, 13);
		frame.getContentPane().add(lblAnno);

		
		JLabel lblGiorno2 = new JLabel("Giorno");
		lblGiorno2.setForeground(new Color(255, 255, 255));
		lblGiorno2.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblGiorno2.setBounds(803, 437, 78, 13);
		frame.getContentPane().add(lblGiorno2);
		
		
		
		comboBoxGiorno2= new JComboBox<>();
		comboBoxGiorno2.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxGiorno2.setModel(new DefaultComboBoxModel<>(giorniDelMese));
		comboBoxGiorno2.setMaximumRowCount(31);
		comboBoxGiorno2.setBounds(803, 460, 78, 21);
//		comboBoxGiorno.addActionListener((ActionListener) this);
		frame.getContentPane().add(comboBoxGiorno2);
		
				
		comboBoxMese2 = new JComboBox<>();
		comboBoxMese2.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxMese2.setMaximumRowCount(12);
		comboBoxMese2.setModel(new DefaultComboBoxModel<>(mesi));
		comboBoxMese2.setBounds(911, 460, 78, 21);
//		comboBoxMese.addActionListener((ActionListener) this);
		frame.getContentPane().add(comboBoxMese2);
		
		comboBoxAnno2 = new JComboBox<>();
		comboBoxAnno2.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxAnno2.setModel(new DefaultComboBoxModel<>(new String[] {"2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		comboBoxAnno2.setMaximumRowCount(7);
		comboBoxAnno2.setBounds(1015, 460, 78, 21);
//		comboBoxAnno.addActionListener((ActionListener) this);
		frame.getContentPane().add(comboBoxAnno2);
		

		JLabel lblMese2 = new JLabel("Mese");
		lblMese2.setForeground(Color.WHITE);
		lblMese2.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblMese2.setBounds(910, 66, 77, 13);
		frame.getContentPane().add(lblMese2);
		
		JLabel lblAnno2 = new JLabel("Anno");
		lblAnno2.setForeground(Color.WHITE);
		lblAnno2.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblAnno2.setBounds(1015, 66, 74, 13);
		frame.getContentPane().add(lblAnno2);
		
		
		JTextArea textArea_motivazione = new JTextArea();
		textArea_motivazione.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea_motivazione.setBounds(410, 552, 687, 46);
		frame.getContentPane().add(textArea_motivazione);
		
		
		JTextArea textArea_datiAnamnestici = new JTextArea();
		textArea_datiAnamnestici.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea_datiAnamnestici.setBounds(410, 270, 683, 46);
		frame.getContentPane().add(textArea_datiAnamnestici);
		
		
		String [] esitoVacc= {"Negativo","Positivo"};
		esitoVaccinazione = new JComboBox<>();
		esitoVaccinazione.setFont(new Font("Dialog", Font.PLAIN, 16));
		esitoVaccinazione.setModel(new DefaultComboBoxModel<>(esitoVacc));
		esitoVaccinazione.setBounds(32, 554, 255, 27);
		esitoVaccinazione.setMaximumRowCount(2);
		frame.getContentPane().add(esitoVaccinazione);
		esitoVaccinazione.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(esitoVaccinazione.getSelectedItem().toString().equals("Negativo")) {
					textArea_motivazione.setEditable(true);	
		    	}
		    	else if(esitoVaccinazione.getSelectedItem().toString().equals("Positivo")){
		    		textArea_motivazione.setEditable(false);
		        	textArea_motivazione.setText("");
		    	}
			}
		});
		
		JLabel lblUsernameFarmacista = new JLabel("Username Farmacista");
		lblUsernameFarmacista.setForeground(new Color(255, 255, 255));
		lblUsernameFarmacista.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblUsernameFarmacista.setBounds(32, 63, 255, 19);
		frame.getContentPane().add(lblUsernameFarmacista);


//		JTextField textField_nomeFarmacia = new JTextField();
//		textField_nomeFarmacia = new JTextField();
//		textField_nomeFarmacia.setFont(new Font("Dialog", Font.PLAIN, 16));
//		textField_nomeFarmacia.setColumns(10);
//		textField_nomeFarmacia.setEditable(false);
//		textField_nomeFarmacia.setBounds(32, 90, 255, 19);
//		try {
//			nomeFarmacia=farmacista.getNomeFarmacia(usernameFarmacista);
//		} catch (OperationException e) {
//			e.printStackTrace();
//		}
		
		
		textField_usernameFarmacista = new JTextField();
		textField_usernameFarmacista.setBackground(new Color(223, 223, 223));
		textField_usernameFarmacista.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_usernameFarmacista.setColumns(10);
		textField_usernameFarmacista.setEditable(false);
		textField_usernameFarmacista.setBounds(32, 90, 255, 19);
		textField_usernameFarmacista.setText(usernameFarmacista);

		frame.getContentPane().add(textField_usernameFarmacista);
		
		
		
		
		JLabel lblErroreStampa = new JLabel("");
		lblErroreStampa.setBackground(new Color(146, 18, 37));
		lblErroreStampa.setForeground(new Color(210, 0, 0));
		lblErroreStampa.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblErroreStampa.setBounds(32, 112, 1061, 33);		
		frame.getContentPane().add(lblErroreStampa);
		
		
		JLabel lblEseguitoCorrStampaAppuntamenti = new JLabel("");
		lblEseguitoCorrStampaAppuntamenti.setBackground(new Color(146, 18, 37));
		lblEseguitoCorrStampaAppuntamenti.setForeground(new Color(192, 192, 192));
		lblEseguitoCorrStampaAppuntamenti.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEseguitoCorrStampaAppuntamenti.setBounds(32,123,1065,22);		
		frame.getContentPane().add(lblEseguitoCorrStampaAppuntamenti);
		
		
		JLabel lblErroreInsPaziente = new JLabel("");
		lblErroreInsPaziente.setBackground(new Color(146, 18, 37));
		lblErroreInsPaziente.setForeground(new Color(210, 0, 0));
		lblErroreInsPaziente.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblErroreInsPaziente.setBounds(32, 326, 1061, 33);		
		frame.getContentPane().add(lblErroreInsPaziente);
		
		
		JLabel lblEseguitoCorrInsPaziente = new JLabel("");
		lblEseguitoCorrInsPaziente.setBackground(new Color(146, 18, 37));
		lblEseguitoCorrInsPaziente.setForeground(new Color(192, 192, 192));
		lblEseguitoCorrInsPaziente.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEseguitoCorrInsPaziente.setBounds(28,337,1061,22);		
		frame.getContentPane().add(lblEseguitoCorrInsPaziente);
		
		
		JLabel lblErroreInsVaccinazione = new JLabel("");
		lblErroreInsVaccinazione.setBackground(new Color(146, 18, 37));
		lblErroreInsVaccinazione.setForeground(new Color(210, 0, 0));
		lblErroreInsVaccinazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblErroreInsVaccinazione.setBounds(32, 608, 1061, 33);		
		frame.getContentPane().add(lblErroreInsVaccinazione);
		
		
		JLabel lblEseguitoCorrInsVaccinazione = new JLabel("");
		lblEseguitoCorrInsVaccinazione.setBackground(new Color(146, 18, 37));
		lblEseguitoCorrInsVaccinazione.setForeground(new Color(192, 192, 192));
		lblEseguitoCorrInsVaccinazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEseguitoCorrInsVaccinazione.setBounds(32,619,1061,22);		
		frame.getContentPane().add(lblEseguitoCorrInsVaccinazione);
		
		
		JLabel lblStampaAppuntamenti = new JLabel("Stampa Appuntamenti");
		lblStampaAppuntamenti.setForeground(Color.WHITE);
		lblStampaAppuntamenti.setFont(new Font("Dialog", Font.BOLD, 26));
		lblStampaAppuntamenti.setBounds(435, 33, 285, 29);
		frame.getContentPane().add(lblStampaAppuntamenti);

		
		
		JLabel lblEmailCliente = new JLabel("E-mail Cliente");
		lblEmailCliente.setForeground(Color.WHITE);
		lblEmailCliente.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEmailCliente.setBounds(32, 241, 255, 19);
		frame.getContentPane().add(lblEmailCliente);
		
		
		textField_emailCliente = new JTextField();
		textField_emailCliente.setFont(new Font("InserisciTurni", Font.PLAIN, 16));
		textField_emailCliente.setColumns(10);
		textField_emailCliente.setBounds(32, 273, 255, 19);
		frame.getContentPane().add(textField_emailCliente);
		
		
		textField_emailCliente2 = new JTextField();
		textField_emailCliente2.setFont(new Font("InserisciTurni", Font.PLAIN, 16));
		textField_emailCliente2.setColumns(10);
		textField_emailCliente2.setBounds(410, 461, 255, 19);
		frame.getContentPane().add(textField_emailCliente2);
		
		JLabel lblInserisciDatiAnamnestici = new JLabel("Inserisci Dati Anamnestici");
		lblInserisciDatiAnamnestici.setForeground(Color.WHITE);
		lblInserisciDatiAnamnestici.setFont(new Font("Dialog", Font.BOLD, 26));
		lblInserisciDatiAnamnestici.setBounds(410, 179, 335, 29);
		frame.getContentPane().add(lblInserisciDatiAnamnestici);
		
		
		JLabel lblInserisciDatiVaccinazione = new JLabel("Inserisci Dati Vaccinazione");
		lblInserisciDatiVaccinazione.setForeground(Color.WHITE);
		lblInserisciDatiVaccinazione.setFont(new Font("Dialog", Font.BOLD, 26));
		lblInserisciDatiVaccinazione.setBounds(411, 388, 334, 29);
		frame.getContentPane().add(lblInserisciDatiVaccinazione);

		
		JButton btnStampa = new JButton("Stampa");
		btnStampa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnStampa.setBounds(32, 153, 125, 21);
		frame.getContentPane().add(btnStampa);
		btnStampa.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		        try {
		        	lblEseguitoCorrStampaAppuntamenti.setText("");
		        	lblErroreStampa.setText("");
		        	String date=comboBoxGiorno.getSelectedItem().toString()+"/"+ comboBoxMese.getSelectedItem().toString()+"/"+comboBoxAnno.getSelectedItem().toString();
		        	String usernameFarmacista=textField_usernameFarmacista.getText();
		        	
		        	farmacista.stampaAppuntamenti(usernameFarmacista, date);

		        	lblEseguitoCorrStampaAppuntamenti.setText("Stampa effettuata");

		        }
		        catch (Exception exc) {
		        	lblErroreStampa.setText(exc.getMessage());

		            System.out.println(exc.getMessage());
		        }
			}
		});
		
		
		JButton btnInserisciDatiPaziente = new JButton("Inserisci");
		btnInserisciDatiPaziente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInserisciDatiPaziente.setBounds(32, 364, 125, 21);
		frame.getContentPane().add(btnInserisciDatiPaziente);
		btnInserisciDatiPaziente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {
		        	lblEseguitoCorrInsPaziente.setText("");
		        	lblErroreInsPaziente.setText("");
		        	String datiAnamnestici=textArea_datiAnamnestici.getText();
		        	String emailCliente=textField_emailCliente.getText();
		        	farmacista.inserisciDatiPaziente(datiAnamnestici,emailCliente);

			
		        	lblEseguitoCorrInsPaziente.setText("Dati Paziente inseriti correttamente");

		        }catch (Exception exc) {
		        	lblErroreInsPaziente.setText(exc.getMessage());
	
		            System.out.println(exc.getMessage());		            // exc.printStackTrace();
	        	}
			}
		});


		JButton btnInserisciDatiVaccinazione = new JButton("Inserisci");
		btnInserisciDatiVaccinazione.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInserisciDatiVaccinazione.setBounds(32, 651, 125, 21);
		frame.getContentPane().add(btnInserisciDatiVaccinazione);
		btnInserisciDatiVaccinazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {
		        	lblErroreInsVaccinazione.setText("");
		        	lblEseguitoCorrInsVaccinazione.setText("");
		        	String motivazione="";
		        	String esito=esitoVaccinazione.getSelectedItem().toString();
		        	if(esito.equals("Negativo")){
		        		motivazione=textArea_motivazione.getText();
//			            System.out.println(motivazione);		            // exc.printStackTrace();
		        	}
		        	String date=comboBoxGiorno2.getSelectedItem().toString()+"/"+ comboBoxMese2.getSelectedItem().toString()+"/"+comboBoxAnno2.getSelectedItem().toString();
	        		String usernameFarmacista=textUsername_Farmacista.getText();
		        	String emailCliente=textField_emailCliente2.getText();


	        		farmacista.inserisciDatiVaccinazione(date,usernameFarmacista ,emailCliente,esito, motivazione) ;
		        	lblEseguitoCorrInsVaccinazione.setText("Dati Vaccinazione inseriti correttamente");
		        }
		        catch (Exception exc) {
		        	lblErroreInsVaccinazione.setText(exc.getMessage());

		            System.out.println(exc.getMessage());		            // exc.printStackTrace();
		        }
		    }
		});
		
		
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnIndietro.setBounds(972, 651, 125, 21);
		frame.getContentPane().add(btnIndietro);
		
		JLabel lblDatiAnamnestici= new JLabel("DatiAnamnestici");
		lblDatiAnamnestici.setForeground(Color.WHITE);
		lblDatiAnamnestici.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblDatiAnamnestici.setBounds(410, 241, 255, 19);
		frame.getContentPane().add(lblDatiAnamnestici);
		
		JLabel lblEmail_Cliente = new JLabel("E-mail Cliente");
		lblEmail_Cliente.setForeground(Color.WHITE);
		lblEmail_Cliente.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEmail_Cliente.setBounds(410, 434, 255, 19);
		frame.getContentPane().add(lblEmail_Cliente);
		
		JLabel lblMotivazione = new JLabel("Motivazione");
		lblMotivazione.setForeground(Color.WHITE);
		lblMotivazione.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblMotivazione.setBounds(410, 523, 321, 19);
		frame.getContentPane().add(lblMotivazione);
		
		JLabel lblEsito = new JLabel("Esito");
		lblEsito.setForeground(Color.WHITE);
		lblEsito.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEsito.setBounds(32, 529, 255, 19);
		frame.getContentPane().add(lblEsito);
		
		JLabel lblUsername_Farmacista = new JLabel("Username Farmacista");
		lblUsername_Farmacista.setForeground(Color.WHITE);
		lblUsername_Farmacista.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblUsername_Farmacista.setBounds(28, 439, 255, 19);
		frame.getContentPane().add(lblUsername_Farmacista);
		
		textUsername_Farmacista = new JTextField();
		textUsername_Farmacista.setBackground(new Color(223, 223, 223));
		textUsername_Farmacista.setFont(new Font("Dialog", Font.PLAIN, 16));
		textUsername_Farmacista.setColumns(10);
		textUsername_Farmacista.setBounds(28, 462, 255, 19);
		textUsername_Farmacista.setText(usernameFarmacista);
		textUsername_Farmacista.setEditable(false);
		frame.getContentPane().add(textUsername_Farmacista);
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {
		        	GUIMenu windowMain = new GUIMenu();
		        	windowMain.frmgfactory.setVisible(true);
					frame.setVisible(false);
		        }catch (Exception exc) {
		            System.out.println("Errore nella creazione della finestra");
		            // exc.printStackTrace();
		        }
		    }
		});
		
//		frame.pack();

	}
}

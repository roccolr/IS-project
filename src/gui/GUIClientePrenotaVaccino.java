package gui;
import entity.*;
import exception.OperationException;
import boundary.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
//import com.jgoodies.common.base.Preconditions;
//import com.toedter.calendar.JCalendar;
//import com.toedter.calendar.JDateChooser;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GUIClientePrenotaVaccino {

	JFrame frame;
	private BCliente cliente=new BCliente();
	private JComboBox<String> comboBoxGiorno;
	private JComboBox<String> comboBoxMese;
	private JComboBox<String> comboBoxAnno;
	private JTextField textField_nomeFarmacia;
	private JTextField textField_nomeCliente;
	private JTextField textField_cognomeCliente;
	private JTextField textField_email;
//	private String date= LocalDate.now().toString();
//	public void setData(String d) {
//		date=d;
//	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIClientePrenotaVaccino window = new GUIClientePrenotaVaccino();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public GUIClientePrenotaVaccino() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(64, 0, 128));
		frame.setBounds(100, 100, 774, 701);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNomeFarmacia = new JLabel("Nome Farmacia");
		lblNomeFarmacia.setForeground(new Color(255, 255, 255));
		lblNomeFarmacia.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNomeFarmacia.setBounds(32, 201, 255, 19);
		frame.getContentPane().add(lblNomeFarmacia);

		
		textField_nomeFarmacia = new JTextField();
		textField_nomeFarmacia.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_nomeFarmacia.setColumns(10);
		textField_nomeFarmacia.setBounds(32, 225, 255, 19);
		frame.getContentPane().add(textField_nomeFarmacia);
		
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNome.setBounds(32, 86, 255, 21);
		frame.getContentPane().add(lblNome);
		
		
		textField_nomeCliente = new JTextField();
		textField_nomeCliente.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_nomeCliente.setColumns(10);
		textField_nomeCliente.setBounds(32, 109, 255, 19);
		frame.getContentPane().add(textField_nomeCliente);
		
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setForeground(new Color(255, 255, 255));
		lblCognome.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblCognome.setBounds(400, 83, 255, 24);
		frame.getContentPane().add(lblCognome);
		
		
		textField_cognomeCliente = new JTextField();
		textField_cognomeCliente.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_cognomeCliente.setColumns(10);
		textField_cognomeCliente.setBounds(400, 109, 253, 19);
		frame.getContentPane().add(textField_cognomeCliente);
		
		
		JLabel lblVaccino = new JLabel("Vaccino");
		lblVaccino.setForeground(new Color(255, 255, 255));
		lblVaccino.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblVaccino.setBounds(32, 138, 255, 20);
		frame.getContentPane().add(lblVaccino);
		
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEmail.setBounds(32, 263, 255, 13);
		frame.getContentPane().add(lblEmail);
		
		
		textField_email = new JTextField();
		textField_email.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_email.setColumns(10);
		textField_email.setBounds(32, 286, 253, 19);
		frame.getContentPane().add(textField_email);
		
		
		JTextArea textArea_Allergie = new JTextArea();
		textArea_Allergie.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea_Allergie.setBounds(32, 361, 623, 106);
		frame.getContentPane().add(textArea_Allergie);
		
		
		JLabel lblAllergie = new JLabel("Allergie");
		lblAllergie.setForeground(new Color(255, 255, 255));
		lblAllergie.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAllergie.setBounds(32, 337, 85, 19);
		frame.getContentPane().add(lblAllergie);
		
		
		JComboBox<Vaccino> comboBoxVaccino = new JComboBox<>();
		comboBoxVaccino.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxVaccino.setModel(new DefaultComboBoxModel<>(Vaccino.values()));
		comboBoxVaccino.setBounds(32, 162, 255, 21);
		frame.getContentPane().add(comboBoxVaccino);
		
		
		JLabel lblErrore = new JLabel("");
		lblErrore.setBackground(new Color(146, 18, 37));
		lblErrore.setVisible(true);
		lblErrore.setForeground(new Color(210, 0, 0));
		lblErrore.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblErrore.setBounds(32, 549, 317, 33);		
		frame.getContentPane().add(lblErrore);

//		
//		JLabel lblEseguitoCorr = new JLabel("");
//		lblEseguitoCorr.setBackground(new Color(146, 18, 37));
//		lblEseguitoCorr.setForeground(new Color(192, 192, 192));
//		lblEseguitoCorr.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lblEseguitoCorr.setBounds(32, 549, 317, 33);					
//		frame.getContentPane().add(lblEseguitoCorr);
		

		JLabel lblGiorno = new JLabel("Giorno");
		lblGiorno.setForeground(new Color(255, 255, 255));
		lblGiorno.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblGiorno.setBounds(398, 146, 78, 13);
		frame.getContentPane().add(lblGiorno);
		
		
		String[] giorniDelMese=new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		
		
		comboBoxGiorno= new JComboBox<>();
		comboBoxGiorno.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxGiorno.setModel(new DefaultComboBoxModel<>(giorniDelMese));
		comboBoxGiorno.setMaximumRowCount(31);
		comboBoxGiorno.setBounds(398, 163, 78, 21);
		frame.getContentPane().add(comboBoxGiorno);
		
		
		String[] mesi=new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		
		
		comboBoxMese = new JComboBox<>();
		comboBoxMese.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxMese.setMaximumRowCount(12);
		comboBoxMese.setModel(new DefaultComboBoxModel<>(mesi));
		comboBoxMese.setBounds(486, 163, 78, 21);
		frame.getContentPane().add(comboBoxMese);
		
		
		comboBoxAnno = new JComboBox<>();
		comboBoxAnno.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxAnno.setModel(new DefaultComboBoxModel<>(new String[] {"2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		comboBoxAnno.setMaximumRowCount(7);
		comboBoxAnno.setBounds(575, 163, 78, 21);
		frame.getContentPane().add(comboBoxAnno);
		
		
		JLabel lblMese = new JLabel("Mese");
		lblMese.setForeground(Color.WHITE);
		lblMese.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblMese.setBounds(487, 146, 77, 13);
		frame.getContentPane().add(lblMese);
		
		
		JLabel lblAnno = new JLabel("Anno");
		lblAnno.setForeground(Color.WHITE);
		lblAnno.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblAnno.setBounds(579, 145, 74, 13);
		frame.getContentPane().add(lblAnno);
		
		
		JButton btnInvia = new JButton("Invia");
		btnInvia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInvia.setBounds(32, 503, 85, 21);
		frame.getContentPane().add(btnInvia);
		btnInvia.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		        try {
		        	String date=comboBoxGiorno.getSelectedItem().toString()+"/"+ comboBoxMese.getSelectedItem().toString()+"/"+comboBoxAnno.getSelectedItem().toString();
		        	String nomeFarmacia=textField_nomeFarmacia.getText();
		        	Vaccino vacc=(Vaccino) comboBoxVaccino.getSelectedItem();
		 			String nomeCliente=textField_nomeCliente.getText();
		        	String cognomeCliente=textField_cognomeCliente.getText();
		        	String email=textField_email.getText();
		        	String allergie=textArea_Allergie.getText();
		        	
		        	cliente.prenotaVaccino(nomeFarmacia, date, vacc,nomeCliente,cognomeCliente,email,allergie);
		        	
		        	GUIMenu windowMain = new GUIMenu();
		        	windowMain.frmgfactory.setVisible(true);
					frame.setVisible(false);
		        }catch(OperationException opEx) {
		        	lblErrore.setText(opEx.getMessage());
				}
		        catch (Exception exc) {
		        	lblErrore.setText(exc.getMessage());

		            System.out.println(exc.getMessage());
		        }
		    }
		});

		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(568, 503, 85, 21);
		frame.getContentPane().add(btnAnnulla);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(219, 25, 307, 40);
		frame.getContentPane().add(panel);
		
		
		JLabel lblCancellaprenotazione = new JLabel("Prenota un Vaccino");
		lblCancellaprenotazione.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancellaprenotazione.setForeground(new Color(0, 64, 128));
		lblCancellaprenotazione.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 22));
		lblCancellaprenotazione.setBackground(Color.WHITE);
		lblCancellaprenotazione.setAlignmentX(0.5f);
		lblCancellaprenotazione.setBounds(25, 5, 257, 30);
		panel.add(lblCancellaprenotazione);
		btnAnnulla.addActionListener(new ActionListener() {
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
	}
}

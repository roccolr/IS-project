package GUI;
import boundary.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class GUIDirigente {

	JFrame frame;
	private BDirigente dirigente=new BDirigente();
	private JTextField textField_nomeFarmacia;
	private JTextField textField_NomeCapoFarmacia;
	private JTextField textField_CognomeCapoFarmacia;

	
	



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIDirigente window = new GUIDirigente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public GUIDirigente() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().setBackground(new Color(64, 0, 128));
		frame.setBounds(100, 100, 774, 701);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblNomeFarmacia = new JLabel("Nome Farmacia");
		lblNomeFarmacia.setForeground(new Color(255, 255, 255));
		lblNomeFarmacia.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNomeFarmacia.setBounds(31, 234, 255, 13);
		frame.getContentPane().add(lblNomeFarmacia);

		

		
		textField_nomeFarmacia = new JTextField();
		textField_nomeFarmacia.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_nomeFarmacia.setColumns(10);
		textField_nomeFarmacia.setBounds(31, 257, 255, 19);
		frame.getContentPane().add(textField_nomeFarmacia);
		
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNome.setBounds(31, 105, 255, 13);
		frame.getContentPane().add(lblNome);
		
		
		
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setForeground(new Color(255, 255, 255));
		lblCognome.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblCognome.setBounds(31, 163, 255, 19);
		frame.getContentPane().add(lblCognome);
		
		
		JLabel lblErrore = new JLabel("");
		lblErrore.setForeground(new Color(162, 0, 0));
		lblErrore.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblErrore.setBounds(31, 303, 670, 38);
		frame.getContentPane().add(lblErrore);
	
		
		JLabel lblEseguitoCorr = new JLabel("");
		lblEseguitoCorr.setBackground(new Color(146, 18, 37));
		lblEseguitoCorr.setForeground(new Color(192, 192, 192));
		lblEseguitoCorr.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEseguitoCorr.setBounds(20,320,798,38);		
		frame.getContentPane().add(lblEseguitoCorr);
		
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnnulla.setBounds(528, 368, 101, 21);
		frame.getContentPane().add(btnAnnulla);
		
		textField_NomeCapoFarmacia = new JTextField();
		textField_NomeCapoFarmacia.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_NomeCapoFarmacia.setColumns(10);
		textField_NomeCapoFarmacia.setBounds(31, 128, 255, 19);
		frame.getContentPane().add(textField_NomeCapoFarmacia);
		
		textField_CognomeCapoFarmacia = new JTextField();
		textField_CognomeCapoFarmacia.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_CognomeCapoFarmacia.setColumns(10);
		textField_CognomeCapoFarmacia.setBounds(31, 192, 255, 19);
		frame.getContentPane().add(textField_CognomeCapoFarmacia);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(226, 31, 307, 40);
		frame.getContentPane().add(panel);
		
		JLabel lblRegistraCapoFarmacia = new JLabel("Registra Capo Farmacia");
		lblRegistraCapoFarmacia.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistraCapoFarmacia.setForeground(new Color(0, 64, 128));
		lblRegistraCapoFarmacia.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 22));
		lblRegistraCapoFarmacia.setBackground(Color.WHITE);
		lblRegistraCapoFarmacia.setAlignmentX(0.5f);
		lblRegistraCapoFarmacia.setBounds(25, 5, 257, 30);
		panel.add(lblRegistraCapoFarmacia);
		
		JButton btnRegistra = new JButton("Registra");
		btnRegistra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegistra.setBounds(31, 368, 125, 21);
		frame.getContentPane().add(btnRegistra);
		btnRegistra.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		        try {
		        	lblEseguitoCorr.setText("");
		        	lblErrore.setText("");

		        	String nomeFarmacia=textField_nomeFarmacia.getText();
		 			String nome=textField_NomeCapoFarmacia.getText();
		        	String cognome=textField_CognomeCapoFarmacia.getText();

		        	
		        	dirigente.registraCapoFarmacia(nome, cognome,nomeFarmacia);
		        	lblEseguitoCorr.setText("Registrazione Capo Farmacia effettuata con successo!");

		        }
		        catch (Exception exc) {
		        	lblErrore.setText(exc.getMessage());

		            System.out.println(exc.getMessage());
		        }
			}
		});
		
		
		

		

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
		
//		frame.pack();

	}
}

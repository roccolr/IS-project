package GUI;
import boundary.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.Dimension;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class GUIMenu {

	public JFrame frmgfactory;

	private BDirigente dirigente=new BDirigente();
	private BCapoFarmacia capoFarmacia=new BCapoFarmacia();
	private BFarmacista farmacista=new BFarmacista();
	private JTextField textField_username=null;
	private JPasswordField passwordField=null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMenu window = new GUIMenu();
					window.frmgfactory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIMenu() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		
		
		frmgfactory = new JFrame();
		frmgfactory.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmgfactory.getContentPane().setBackground(new Color(64, 0, 128));
		frmgfactory.setTitle("5GFactory");
		frmgfactory.setBounds(100, 100, 648, 560);
		frmgfactory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmgfactory.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(234, 26, 165, 40);
		frmgfactory.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel Title = new JLabel("5GFactory");
		Title.setBounds(20, 5, 124, 30);
		panel.add(Title);
		Title.setAlignmentX(Component.CENTER_ALIGNMENT);
		Title.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 24));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setForeground(new Color(0, 64, 128));
		Title.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel = new JLabel("Seleziona una delle seguenti opzioni");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel.setBounds(185, 131, 264, 21);
		frmgfactory.getContentPane().add(lblNewLabel);
		
		JLabel lblSeiUnCliente = new JLabel("Sei un cliente?");
		lblSeiUnCliente.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSeiUnCliente.setForeground(Color.WHITE);
		lblSeiUnCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSeiUnCliente.setBounds(249, 108, 135, 21);
		frmgfactory.getContentPane().add(lblSeiUnCliente);
		
		JButton PrenotaVaccino = new JButton("Prenota Vaccino");
		PrenotaVaccino.setBounds(32, 177, 215, 21);
		frmgfactory.getContentPane().add(PrenotaVaccino);
		PrenotaVaccino.setMaximumSize(new Dimension(112, 21));
		PrenotaVaccino.setHorizontalAlignment(SwingConstants.LEFT);
		PrenotaVaccino.setBackground(new Color(240, 240, 240));
		PrenotaVaccino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {
		        	GUIClientePrenotaVaccino windowPrenotazione = new GUIClientePrenotaVaccino();
		            windowPrenotazione.frame.setVisible(true);
					frmgfactory.setVisible(false);
		        } catch (Exception exc) {
		            System.out.println("Errore nella creazione della finestra");
		            // exc.printStackTrace();
		        }
		    }
		});
		PrenotaVaccino.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		
		textField_username = new JTextField();
		textField_username.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_username.setColumns(10);
		textField_username.setBounds(32, 318, 216, 19);
		frmgfactory.getContentPane().add(textField_username);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblUsername.setBounds(32, 299, 108, 13);
		frmgfactory.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblPassword.setBounds(348, 299, 108, 13);
		frmgfactory.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 16));
		passwordField.setBounds(348, 318, 216, 19);
		frmgfactory.getContentPane().add(passwordField);
		
		JLabel lblErrore = new JLabel("");
		lblErrore.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrore.setHorizontalTextPosition(SwingConstants.CENTER);
		lblErrore.setBackground(new Color(146, 18, 37));
		lblErrore.setVisible(true);
		lblErrore.setForeground(new Color(210, 0, 0));
		lblErrore.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblErrore.setBounds(32, 365, 532, 40);		
		frmgfactory.getContentPane().add(lblErrore);	
		
		
		JButton CancellaPrenotazione = new JButton("CancellaPrenotazione");
		CancellaPrenotazione.setBounds(347, 177, 216, 21);
		frmgfactory.getContentPane().add(CancellaPrenotazione);
		CancellaPrenotazione.setMaximumSize(new Dimension(140, 21));
		CancellaPrenotazione.setHorizontalAlignment(SwingConstants.LEFT);
		CancellaPrenotazione.setBackground(new Color(240, 240, 240));
		CancellaPrenotazione.setFont(new Font("Dialog", Font.PLAIN, 16));
		CancellaPrenotazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {
		        	GUIClienteCancellaPrenotazione windowCancPrenotazione = new GUIClienteCancellaPrenotazione();
		        	windowCancPrenotazione.frame.setVisible(true);
					frmgfactory.setVisible(false);
		        } catch (Exception exc) {
		            System.out.println("Errore nella creazione della finestra");
		            // exc.printStackTrace();
		        }
		    }
		});
		
		
		JButton btnLogin = new JButton("Login ");
		btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogin.setBounds(276, 413, 82, 21);
		frmgfactory.getContentPane().add(btnLogin);
		btnLogin.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogin.setBackground(new Color(240, 240, 240));
		btnLogin.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
		        try {
		        	String username=textField_username.getText();
		        	String password=new String(passwordField.getPassword());
		        	System.out.println(username+" "+password);
		        	if(username.contains("CapoFarmacia")) {
		        		if(capoFarmacia.login(username, password)) {
				        	GUICapoFarmacia newWindow = new GUICapoFarmacia(username);
				        	newWindow.frame.setVisible(true);
				        	frmgfactory.setVisible(false);
		        		}
		        	}
		        	else if(username.contains("Farmacista")) {
		        		if(farmacista.login(username, password)) {
				        	GUIFarmacista newWindow = new GUIFarmacista(username);
				        	newWindow.frame.setVisible(true);
				        	frmgfactory.setVisible(false);
		        		}
		        	}
		        	else {
		        		if(dirigente.login(username, password)) {
				        	GUIDirigente newWindow = new GUIDirigente();
				        	newWindow.frame.setVisible(true);
				        	frmgfactory.setVisible(false);
		        		}	
		        	}
		        }
		        catch (Exception exc) {
		        	lblErrore.setText(exc.getMessage());
	
		            System.out.println(exc.getMessage());
		        }
		    }
		});
		
		
		JLabel lblLavori = new JLabel("Lavori con noi?");
		lblLavori.setForeground(Color.WHITE);
		lblLavori.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblLavori.setBounds(247, 243, 140, 19);
		frmgfactory.getContentPane().add(lblLavori);
		
		
		JLabel lblAccediAlPortale = new JLabel("Accedi al portale");
		lblAccediAlPortale.setForeground(Color.WHITE);
		lblAccediAlPortale.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblAccediAlPortale.setBounds(255, 268, 124, 21);
		frmgfactory.getContentPane().add(lblAccediAlPortale);
		
		frmgfactory.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmgfactory.getContentPane(), panel, Title, lblNewLabel, lblSeiUnCliente, PrenotaVaccino, CancellaPrenotazione, btnLogin, lblLavori, lblAccediAlPortale}));

	}
}

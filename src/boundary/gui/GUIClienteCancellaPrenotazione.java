package boundary.gui;
import exception.OperationException;
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

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GUIClienteCancellaPrenotazione{

	JFrame frame;
	private BCliente cliente=new BCliente();
	private JComboBox<String> comboBoxGiorno=null;
	private JComboBox<String> comboBoxMese=null;
	private JComboBox<String> comboBoxAnno=null;
	private JTextField textField_emailFarmacia=null;
	private JTextField textField_email=null;
//	private String date= LocalDate.now().toString();
	
	
//	public void setData(String d) {
//		date=d;
//	}


//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUIClienteCancellaPrenotazione window = new GUIClienteCancellaPrenotazione();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	public GUIClienteCancellaPrenotazione() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(64, 0, 128));
		frame.setBounds(100, 100, 774, 701);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblEmailFarmacia = new JLabel("E-mail Farmacia");
		lblEmailFarmacia.setForeground(new Color(255, 255, 255));
		lblEmailFarmacia.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEmailFarmacia.setBounds(31, 130, 255, 13);
		frame.getContentPane().add(lblEmailFarmacia);

		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEmail.setBounds(31, 186, 255, 13);
		frame.getContentPane().add(lblEmail);
		
		
		textField_emailFarmacia = new JTextField();
		textField_emailFarmacia.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_emailFarmacia.setColumns(10);
		textField_emailFarmacia.setBounds(31, 153, 277, 19);
		frame.getContentPane().add(textField_emailFarmacia);
		
		
		textField_email = new JTextField();
		textField_email.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_email.setColumns(10);
		textField_email.setBounds(31, 209, 277, 19);
		frame.getContentPane().add(textField_email);
		

		JLabel lblErrore = new JLabel("");
		lblErrore.setBackground(new Color(146, 18, 37));
		lblErrore.setVisible(true);
		lblErrore.setForeground(new Color(210, 0, 0));
		lblErrore.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblErrore.setBounds(31, 275, 599, 33);		
		frame.getContentPane().add(lblErrore);

		
//		JLabel lblEseguitoCorr = new JLabel("");
//		lblEseguitoCorr.setBackground(new Color(146, 18, 37));
//		lblEseguitoCorr.setForeground(new Color(192, 192, 192));
//		lblEseguitoCorr.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lblEseguitoCorr.setBounds(31, 275, 599, 33);					
//		frame.getContentPane().add(lblEseguitoCorr);
		

		JLabel lblGiorno = new JLabel("Giorno");
		lblGiorno.setForeground(new Color(255, 255, 255));
		lblGiorno.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblGiorno.setBounds(375, 131, 78, 13);
		frame.getContentPane().add(lblGiorno);
		
		
		String[] giorniDelMese=new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		
		comboBoxGiorno= new JComboBox<>();
		comboBoxGiorno.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxGiorno.setModel(new DefaultComboBoxModel<>(giorniDelMese));
		comboBoxGiorno.setMaximumRowCount(31);
		comboBoxGiorno.setBounds(375, 148, 78, 21);
//		comboBoxGiorno.addActionListener((ActionListener) this);
		frame.getContentPane().add(comboBoxGiorno);
		
		
		String[] mesi=new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		
		
		comboBoxMese = new JComboBox<>();
		comboBoxMese.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxMese.setMaximumRowCount(12);
		comboBoxMese.setModel(new DefaultComboBoxModel<>(mesi));
		comboBoxMese.setBounds(463, 148, 78, 21);
//		comboBoxMese.addActionListener((ActionListener) this);
		frame.getContentPane().add(comboBoxMese);
		
		
		comboBoxAnno = new JComboBox<>();
		comboBoxAnno.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxAnno.setModel(new DefaultComboBoxModel<>(new String[] {"2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		comboBoxAnno.setMaximumRowCount(7);
		comboBoxAnno.setBounds(552, 148, 78, 21);
//		comboBoxAnno.addActionListener((ActionListener) this);
		frame.getContentPane().add(comboBoxAnno);
		
		
		JLabel lblMese = new JLabel("Mese");
		lblMese.setForeground(Color.WHITE);
		lblMese.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblMese.setBounds(464, 131, 77, 13);
		frame.getContentPane().add(lblMese);
		
		
		JLabel lblAnno = new JLabel("Anno");
		lblAnno.setForeground(Color.WHITE);
		lblAnno.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblAnno.setBounds(556, 130, 74, 13);
		frame.getContentPane().add(lblAnno);
		

//		JLabel lblData = new JLabel("Data");
//		lblData.setForeground(Color.WHITE);
//		lblData.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		lblData.setBounds(360, 129, 255, 13);
//		frame.getContentPane().add(lblData);
//		
//		
//		JDateChooser dateChooser = new JDateChooser();
//		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		dateChooser.setToolTipText("");
//		dateChooser.setDateFormatString("dd MM yyyy");
//		dateChooser.setBounds(360, 146, 255, 19);
//		frame.getContentPane().add(dateChooser);
//		Date d=dateChooser.getDate();
//		d.after(d)
//
//		
//		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//	        	System.out.println(textField_cognomeCliente.getText());
//
////				String d=dateChooser.toString();
////		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
////		        LocalDate ld=LocalDate.parse(d,formatter);
////				setData(ld.toString());
//			}
//		});

		
		JButton btnCancella = new JButton("Cancella");
		btnCancella.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnCancella.setBounds(31, 354, 133, 21);
		frame.getContentPane().add(btnCancella);
		btnCancella.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		        try {

		        	String date=comboBoxGiorno.getSelectedItem().toString()+"/"+ comboBoxMese.getSelectedItem().toString()+"/"+comboBoxAnno.getSelectedItem().toString();
		        	String emailFarmacia=textField_emailFarmacia.getText();
		        	String email=textField_email.getText();

		        	cliente.cancellaAppuntamento(email, date, emailFarmacia);
		        	

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
		btnAnnulla.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnAnnulla.setBounds(531, 354, 99, 21);
		frame.getContentPane().add(btnAnnulla);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(226, 35, 307, 40);
		frame.getContentPane().add(panel);
		
		JLabel lblCancellaprenotazione = new JLabel("CancellaPrenotazione");
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


		
//		frame.pack();

	}
}

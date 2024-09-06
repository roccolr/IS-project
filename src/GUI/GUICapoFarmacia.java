package GUI;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import boundary.BCapoFarmacia;
import entity.Turno;
import exception.OperationException;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Rectangle;





public class GUICapoFarmacia {

	JFrame frame;
	private String usernameCapoFarmacia;
	private BCapoFarmacia capoFarmacia=new BCapoFarmacia();
	private String  nomeFarmacia;
	private String  nome;
	private String  cognome;
	private boolean  dipendente;
	private JTextField textField_nomeFarmacia;
	private JTextField textField_nomeFarmacista;
	private JTextField textField_cognomeFarmacista;
	private JTextField textField_CodicePrenotazione;
	private JTextField textField_NomeFarmaciaInserisciTurni;
	private JCheckBox chckbxDipendente;
	private JTable tableTurniSettimana;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUICapoFarmacia window = new GUICapoFarmacia("asfff767CapoFarmacia");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public GUICapoFarmacia(String username) {
		this.usernameCapoFarmacia=username;

		initialize();
	}


	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBounds(new Rectangle(0, 0, 0, 30));
		frame.getContentPane().setFont(new Font("Dialog", Font.BOLD, 24));
		frame.getContentPane().setBackground(new Color(64, 0, 128));
		frame.setBounds(100, 100, 1012, 777);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblNomeFarmacia = new JLabel("Nome Farmacia");
		lblNomeFarmacia.setForeground(new Color(255, 255, 255));
		lblNomeFarmacia.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNomeFarmacia.setBounds(32, 123, 255, 22);
		frame.getContentPane().add(lblNomeFarmacia);


		textField_nomeFarmacia = new JTextField();
		textField_nomeFarmacia.setBounds(new Rectangle(0, 0, 0, 30));
		textField_nomeFarmacia.setBackground(new Color(193, 193, 193));
		textField_nomeFarmacia.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_nomeFarmacia.setColumns(10);
		textField_nomeFarmacia.setBounds(32, 148, 255, 23);
		try {
			nomeFarmacia=capoFarmacia.getNomeFarmacia(usernameCapoFarmacia);
		} catch (OperationException e) {
			e.printStackTrace();
		}
		textField_nomeFarmacia.setText(nomeFarmacia);
		frame.getContentPane().add(textField_nomeFarmacia);
		
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNome.setBounds(32, 71, 255, 16);
		frame.getContentPane().add(lblNome);
		
		
		textField_nomeFarmacista = new JTextField();
		textField_nomeFarmacista.setBounds(new Rectangle(0, 0, 0, 30));
		textField_nomeFarmacista.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_nomeFarmacista.setColumns(10);
		textField_nomeFarmacista.setBounds(32, 95, 255, 23);
		frame.getContentPane().add(textField_nomeFarmacista);
		
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setForeground(new Color(255, 255, 255));
		lblCognome.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblCognome.setBounds(362, 70, 255, 19);
		frame.getContentPane().add(lblCognome);
		
		
		textField_cognomeFarmacista = new JTextField();
		textField_cognomeFarmacista.setBounds(new Rectangle(0, 0, 0, 30));
		textField_cognomeFarmacista.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_cognomeFarmacista.setColumns(10);
		textField_cognomeFarmacista.setBounds(364, 95, 253, 23);
		frame.getContentPane().add(textField_cognomeFarmacista);
		
		
		JLabel lblCodicePrenotazione = new JLabel("Codice Prenotazione");
		lblCodicePrenotazione.setForeground(new Color(255, 255, 255));
		lblCodicePrenotazione.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblCodicePrenotazione.setBounds(32, 301, 255, 23);
		frame.getContentPane().add(lblCodicePrenotazione);
		
		
		textField_CodicePrenotazione = new JTextField();
		textField_CodicePrenotazione.setBounds(new Rectangle(0, 0, 0, 30));
		textField_CodicePrenotazione.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_CodicePrenotazione.setColumns(10);
		textField_CodicePrenotazione.setBounds(32, 324, 253, 23);
		frame.getContentPane().add(textField_CodicePrenotazione);
		
		
		JLabel lblErroreRegistrazione = new JLabel("");
		lblErroreRegistrazione.setBackground(new Color(146, 18, 37));
		lblErroreRegistrazione.setVisible(true);
		lblErroreRegistrazione.setForeground(new Color(210, 0, 0));
		lblErroreRegistrazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblErroreRegistrazione.setBounds(32, 175, 582, 33);		
		frame.getContentPane().add(lblErroreRegistrazione);
		
		
		JLabel lblEseguitoCorrRegistrazione = new JLabel("");
		lblEseguitoCorrRegistrazione.setBackground(new Color(146, 18, 37));
		lblEseguitoCorrRegistrazione.setForeground(new Color(192, 192, 192));
		lblEseguitoCorrRegistrazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEseguitoCorrRegistrazione.setBounds(35, 243, 582, 33);				
		frame.getContentPane().add(lblEseguitoCorrRegistrazione);
		
		
		JLabel lblErroreCancellazione = new JLabel("");
		lblErroreCancellazione.setBackground(new Color(146, 18, 37));
		lblErroreCancellazione.setVisible(true);
		lblErroreCancellazione.setForeground(new Color(210, 0, 0));
		lblErroreCancellazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblErroreCancellazione.setBounds(35, 348, 582, 33);		
		frame.getContentPane().add(lblErroreCancellazione);
		
		
		JLabel lblEseguitoCorrCancellazione = new JLabel("");
		lblEseguitoCorrCancellazione.setBackground(new Color(146, 18, 37));
		lblEseguitoCorrCancellazione.setForeground(new Color(192, 192, 192));
		lblEseguitoCorrCancellazione.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEseguitoCorrCancellazione.setBounds(35, 348, 582, 33);					
		frame.getContentPane().add(lblEseguitoCorrCancellazione);
		
		
		JLabel lblErroreInserimento = new JLabel("");
		lblErroreInserimento.setInheritsPopupMenu(false);
		lblErroreInserimento.setBackground(new Color(146, 18, 37));
		lblErroreInserimento.setVisible(true);
		lblErroreInserimento.setForeground(new Color(210, 0, 0));
		lblErroreInserimento.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblErroreInserimento.setBounds(32, 601, 582, 33);		
		frame.getContentPane().add(lblErroreInserimento);
		
		
		JLabel lblEseguitoCorrInserimento  = new JLabel("");
		lblEseguitoCorrInserimento.setBackground(new Color(146, 18, 37));
		lblEseguitoCorrInserimento.setForeground(new Color(192, 192, 192));
		lblEseguitoCorrInserimento.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEseguitoCorrInserimento.setBounds(32, 644, 582, 33);					
		frame.getContentPane().add(lblEseguitoCorrInserimento);
		
		
		
		JLabel lblErroreVerifica = new JLabel("");
		lblErroreVerifica.setInheritsPopupMenu(false);
		lblErroreVerifica.setBackground(new Color(146, 18, 37));
		lblErroreVerifica.setVisible(true);
		lblErroreVerifica.setForeground(new Color(210, 0, 0));
		lblErroreVerifica.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblErroreVerifica.setBounds(32, 644, 582, 33);		
		frame.getContentPane().add(lblErroreVerifica);
		
		
		JLabel lblEseguitoVerifica  = new JLabel("");
		lblEseguitoVerifica.setBackground(new Color(146, 18, 37));
		lblEseguitoVerifica.setForeground(new Color(192, 192, 192));
		lblEseguitoVerifica.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEseguitoVerifica.setBounds(32, 644, 582, 33);					
		frame.getContentPane().add(lblEseguitoVerifica);
		
		
		JLabel lblDipendente = new JLabel("Dipendente della società?");
		lblDipendente.setForeground(Color.WHITE);
		lblDipendente.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblDipendente.setBounds(689, 69, 255, 21);
		frame.getContentPane().add(lblDipendente);
		
		
		chckbxDipendente = new JCheckBox("Si");
		chckbxDipendente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxDipendente.setBounds(689, 95, 45, 23);
//		chckbxDipendente.setFocusable(false);
		frame.getContentPane().add(chckbxDipendente);
		
		
		JLabel lblRegistraFarmacista = new JLabel("Registra Farmacista");
		lblRegistraFarmacista.setForeground(Color.WHITE);
		lblRegistraFarmacista.setFont(new Font("Dialog", Font.BOLD, 24));
		lblRegistraFarmacista.setBounds(258, 33, 244, 29);
		frame.getContentPane().add(lblRegistraFarmacista);
		
		
		JLabel lblNomeFarmacia_1 = new JLabel("Nome Farmacia");
		lblNomeFarmacia_1.setForeground(Color.WHITE);
		lblNomeFarmacia_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNomeFarmacia_1.setBounds(33, 507, 255, 23);
		frame.getContentPane().add(lblNomeFarmacia_1);
		
		
		textField_NomeFarmaciaInserisciTurni = new JTextField();
		textField_NomeFarmaciaInserisciTurni.setBounds(new Rectangle(0, 0, 0, 30));
		textField_NomeFarmaciaInserisciTurni.setBackground(new Color(193, 193, 193));
		textField_NomeFarmaciaInserisciTurni.setFont(new Font("InserisciTurni", Font.PLAIN, 16));
		textField_NomeFarmaciaInserisciTurni.setColumns(10);
		textField_NomeFarmaciaInserisciTurni.setEditable(false);
		textField_NomeFarmaciaInserisciTurni.setBounds(32, 536, 255, 23);
		try {
			nomeFarmacia=capoFarmacia.getNomeFarmacia(usernameCapoFarmacia);
		} catch (OperationException e) {
			e.printStackTrace();
		}
		textField_NomeFarmaciaInserisciTurni.setText(nomeFarmacia);
		frame.getContentPane().add(textField_NomeFarmaciaInserisciTurni);
		
		
		JLabel lblCancellaAppuntamento = new JLabel("Cancella Appuntamento");
		lblCancellaAppuntamento.setForeground(Color.WHITE);
		lblCancellaAppuntamento.setFont(new Font("Dialog", Font.BOLD, 24));
		lblCancellaAppuntamento.setBounds(246, 262, 278, 29);
		frame.getContentPane().add(lblCancellaAppuntamento);
		
		
		JLabel lblInserisciTurni = new JLabel("Inserisci Turni");
		lblInserisciTurni.setForeground(Color.WHITE);
		lblInserisciTurni.setFont(new Font("Dialog", Font.BOLD, 24));
		lblInserisciTurni.setBounds(291, 419, 177, 29);
		frame.getContentPane().add(lblInserisciTurni);

		
		
		
//		 class TableModel extends DefaultTableModel{
//			
//		}
		
    	
		
		JButton btnRegistra = new JButton("Registra");
		btnRegistra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegistra.setBounds(32, 212, 125, 23);
		frame.getContentPane().add(btnRegistra);
		btnRegistra.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		        try {
		        	lblEseguitoCorrRegistrazione.setText("");
		        	lblErroreRegistrazione.setText("");
					nome=textField_nomeFarmacista.getText();
	        		cognome=textField_cognomeFarmacista.getText();
    				dipendente=chckbxDipendente.isSelected();
		    		capoFarmacia.registraFarmacista(nome, cognome, dipendente,usernameCapoFarmacia);
		        	lblEseguitoCorrRegistrazione.setText("Registrazione del farmacista effettuata con successo!");
		        }
		        catch (Exception exc) {
		        	lblErroreRegistrazione.setText(exc.getMessage());

		            System.out.println(exc.getMessage());
		        }
			}
		});
		
		

		
		
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnIndietro.setBounds(828, 692, 125, 23);
		frame.getContentPane().add(btnIndietro);
		
		
		
		JButton btnCancella = new JButton("Conferma");
		btnCancella.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancella.setBounds(32, 385, 125, 23);
		frame.getContentPane().add(btnCancella);
		btnCancella.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {
		        	lblEseguitoCorrCancellazione.setText("");

		        	lblErroreCancellazione.setText("");

	        		int codicePrenotazione=Integer.parseInt(textField_CodicePrenotazione.getText());
		        	capoFarmacia.cancellaAppuntamento(codicePrenotazione) ;

		        	lblEseguitoCorrCancellazione.setText("Appuntamento cancellato correttamente!");

		        }catch(NumberFormatException  nExc){
		        	lblErroreCancellazione.setText("Errore: Inserire un codice numerico...");
		        }
		        catch (Exception exc) {
		        	lblErroreCancellazione.setText(exc.getMessage());
		            System.out.println(exc.getMessage());		            // exc.printStackTrace();
		        }
		    }
		});
		

		
//		JButton btnInserisci = new JButton("Inserisci");
//		btnInserisci.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		btnInserisci.setBounds(32, 613, 125, 23);
//		frame.getContentPane().add(btnInserisci);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setBounds(557, 468, 396, 185);
		frame.getContentPane().add(scrollPane);

		ArrayList<Integer> rows=new ArrayList<Integer>();
		for(int i=0;i<14;i++) {
			rows.add(i);
		}
		ArrayList<LocalDate> giorni=capoFarmacia.getGiorniSettimanaProssima();
		tableTurniSettimana = new JTable();
		tableTurniSettimana.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(tableTurniSettimana);
		tableTurniSettimana.setModel(new DefaultTableModel(
			new String[] {
				"Data", "Turno", "UsernameFarmacista"
			},14
			
		){   /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		@Override
	    	public boolean isCellEditable(int row, int col) {
	            if (col == 0 || col == 1|| rows.contains(row)) {
	            	
	                return false;  
	            }
	            return true; 
			}
		}
		);
		
		JLabel lblNomeFarmacia_1_1 = new JLabel("Inserire prima il nome della farmacia, poi i turni");
		lblNomeFarmacia_1_1.setForeground(Color.WHITE);
		lblNomeFarmacia_1_1.setFont(new Font("Dialog", Font.ITALIC, 16));
		lblNomeFarmacia_1_1.setBounds(32, 466, 371, 23);
		frame.getContentPane().add(lblNomeFarmacia_1_1);
		
		JButton btnConfermaTurni = new JButton("Conferma");
		btnConfermaTurni.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConfermaTurni.setBounds(32, 630, 125, 23);
		frame.getContentPane().add(btnConfermaTurni);
		
		for (int i = 0; i < 14; i++) {
			LocalDate giorno = giorni.get(i/2);
            tableTurniSettimana.setValueAt(giorno.toString(), i, 0); 
		}
		for (int i = 0; i < 14; i++) {
            if(i%2==0)tableTurniSettimana.setValueAt("Mattina", i, 1);
            else tableTurniSettimana.setValueAt("Pomeriggio", i, 1);  
//            tableTurniSettimana.setColorAt();
		}
//		for (int row:rows) {
//            tableTurniSettimana.setValueAt(turno.getUsernameFarmacista(), i, 2); 
//		}
//		tableTurniSettimana.setEnabledAt();
		
        try {
        	
        	lblEseguitoCorrInserimento.setText("");
        	lblErroreInserimento.setText("");
    		ArrayList<Turno> turni=new ArrayList<Turno>();
    		
        	String nomeFarmacia="";
//        	turni.clear();
			nomeFarmacia=textField_NomeFarmaciaInserisciTurni.getText();
//			System.out.println(nomeFarmacia);
			turni=capoFarmacia.getTurniSettimana(nomeFarmacia);
			String g=null;
			String tipo=null;
			String tipo2=null;
			Integer i=0;
//			System.out.println("rows "+rows.size());
			if (turni.size()!=0) {
//				System.out.println("turni "+turni.size());
//				System.out.println("rows "+rows.size());
				for(Turno turno:turni) {
					if(turno!=null) {
//						int cod=turno.getCodice();
//						System.out.println("turno "+turno.getCodice());
					}
				}
				for(Turno turno:turni) {
	            	if(turno!=null) {
	            		g=tableTurniSettimana.getValueAt( i, 0).toString();
	            		tipo2=tableTurniSettimana.getValueAt( i, 1).toString();

	            		if(turno.getTipo()==0)tipo="Mattina";else tipo="Pomeriggio";
//						System.out.println("rows "+rows.size());
						if(turno.getData().toString().equals(g)  && tipo.equals(tipo2)) {
	            			tableTurniSettimana.setValueAt(turno.getUsernameFarmacista(), i, 2);
							rows.add(i);

	            		}
//	            		System.out.println("giorno1 "+ g+" giorno2 "+ turno.getData().toString()+" tipo "+ tipo+" tipo2 "+tipo2);
	            	}else  {
	        			tableTurniSettimana.setValueAt("", i, 2);
						rows.remove(i);
			        }
            		i++;
	            }
			}else for(i=0;i<14 ;i++) {
    			tableTurniSettimana.setValueAt("", i, 2);

				rows.clear();
			}
//			System.out.println("rows "+rows.size());
        }catch (Exception exc) {
        	lblErroreInserimento.setText(exc.getMessage());
            System.out.println(exc.getMessage());		           
    	}
//		btnInserisci.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//		        try {
//		        	
//		        	lblEseguitoCorrInserimento.setText("");
//		        	lblErroreInserimento.setText("");
//		    		ArrayList<Turno> turni=new ArrayList<Turno>();
//		    		
//		        	String nomeFarmacia="";
////		        	turni.clear();
//        			nomeFarmacia=textField_NomeFarmaciaInserisciTurni.getText();
////        			System.out.println(nomeFarmacia);
//					turni=capoFarmacia.getTurniSettimana(nomeFarmacia);
//					String g=null;
//					String tipo=null;
//					String tipo2=null;
//					Integer i=0;
////					System.out.println("rows "+rows.size());
//					if (turni.size()!=0) {
////						System.out.println("turni "+turni.size());
////						System.out.println("rows "+rows.size());
//						for(Turno turno:turni) {
//							if(turno!=null) {
////								int cod=turno.getCodice();
////								System.out.println("turno "+turno.getCodice());
//							}
//						}
//						for(Turno turno:turni) {
//			            	if(turno!=null) {
//			            		g=tableTurniSettimana.getValueAt( i, 0).toString();
//			            		tipo2=tableTurniSettimana.getValueAt( i, 1).toString();
//
//			            		if(turno.getTipo()==0)tipo="Mattina";else tipo="Pomeriggio";
////								System.out.println("rows "+rows.size());
//								if(turno.getData().toString().equals(g)  && tipo.equals(tipo2)) {
//			            			tableTurniSettimana.setValueAt(turno.getUsernameFarmacista(), i, 2);
//									rows.add(i);
//
//			            		}
////			            		System.out.println("giorno1 "+ g+" giorno2 "+ turno.getData().toString()+" tipo "+ tipo+" tipo2 "+tipo2);
//			            	}else  {
//			        			tableTurniSettimana.setValueAt("", i, 2);
//								rows.remove(i);
//					        }
//		            		i++;
//			            }
//					}else for(i=0;i<14 ;i++) {
//	        			tableTurniSettimana.setValueAt("", i, 2);
//
//						rows.clear();
//					}
////					System.out.println("rows "+rows.size());
//		        }catch (Exception exc) {
//		        	lblErroreInserimento.setText(exc.getMessage());
//		            System.out.println(exc.getMessage());		           
//	        	}
//			}
//		});
		
		btnConfermaTurni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {
		        	lblEseguitoCorrInserimento.setText("");
		        	lblErroreInserimento.setText("");
		        	int i;
		        	String nomeFarmacia=textField_NomeFarmaciaInserisciTurni.getText();
		            ArrayList<String>usernameFarmacisti=new ArrayList<String>();
		            for(i=0;i<14 && !rows.contains(i);i++) {
//	            		System.out.println(tableTurniSettimana.getValueAt( i, 2));

		            	if(tableTurniSettimana.getValueAt( i, 2) == null)
		            		throw new OperationException("Inserisci tutti i turni...");
		            	String username=tableTurniSettimana.getValueAt( i, 2).toString();
		            	usernameFarmacisti.add(username); 
		            }
		        	capoFarmacia.inserisciTurni(usernameFarmacisti,nomeFarmacia); 

		        	lblEseguitoCorrInserimento.setText("Turni inseriti correttamente!");

		        }catch (Exception exc) {
		        	lblErroreInserimento.setText(exc.getMessage());
		            System.out.println(exc.getMessage());		            // exc.printStackTrace();
	        	}
			}
		});
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

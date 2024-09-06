//package GUI;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//
//import java.awt.Color;
//import java.awt.Font;
//
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//public class GUISelectData {
//
//
//
//	
//	public JFrame frame=null;	
//	private JComboBox<String> comboBoxGiorno;
//	private JComboBox<String> comboBoxMese;
//	private JComboBox<String> comboBoxAnno;
//	
//	public GUISelectData(JFrame f) {
//		this.frame=f;
//	}
//	
//	JLabel lblGiorno = new JLabel("Giorno");
//	lblGiorno.setForeground(new Color(255, 255, 255));
//	lblGiorno.setFont(new Font("Dialog", Font.PLAIN, 16));
//	lblGiorno.setBounds(398, 146, 78, 13);
//	frame.getContentPane().add(lblGiorno);
//	
//	
//	String[] giorniDelMese=new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
//			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
//	
//	comboBoxGiorno= new JComboBox<>();
//	comboBoxGiorno.setFont(new Font("Dialog", Font.PLAIN, 16));
//	comboBoxGiorno.setModel(new DefaultComboBoxModel<>(giorniDelMese));
//	comboBoxGiorno.setMaximumRowCount(31);
//	comboBoxGiorno.setBounds(398, 163, 78, 21);
////		comboBoxGiorno.addActionListener((ActionListener) this);
//	frame.getContentPane().add(comboBoxGiorno);
//	
//	
//	String[] mesi=new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
//	
//	comboBoxMese = new JComboBox<>();
//	comboBoxMese.setFont(new Font("Dialog", Font.PLAIN, 16));
//	comboBoxMese.setMaximumRowCount(12);
//	comboBoxMese.setModel(new DefaultComboBoxModel<>(mesi));
//	comboBoxMese.setBounds(486, 163, 78, 21);
////		comboBoxMese.addActionListener((ActionListener) this);
//	frame.getContentPane().add(comboBoxMese);
//	
//	comboBoxAnno = new JComboBox<>();
//	comboBoxAnno.setFont(new Font("Dialog", Font.PLAIN, 16));
//	comboBoxAnno.setModel(new DefaultComboBoxModel<>(new String[] {"2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
//	comboBoxAnno.setMaximumRowCount(7);
//	comboBoxAnno.setBounds(575, 163, 78, 21);
////		comboBoxAnno.addActionListener((ActionListener) this);
//	frame.getContentPane().add(comboBoxAnno);
//	
//	JLabel lblMese = new JLabel("Mese");
//	lblMese.setForeground(Color.WHITE);
//	lblMese.setFont(new Font("Dialog", Font.PLAIN, 16));
//	lblMese.setBounds(487, 146, 77, 13);
//	frame.getContentPane().add(lblMese);
//	
//	JLabel lblAnno = new JLabel("Anno");
//	lblAnno.setForeground(Color.WHITE);
//	lblAnno.setFont(new Font("Dialog", Font.PLAIN, 16));
//	lblAnno.setBounds(579, 145, 74, 13);
//	frame.getContentPane().add(lblAnno);
//		
//
//	
//
//}

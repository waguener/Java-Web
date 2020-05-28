package br.com.olgber.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.controle.util.Limpar;
import br.com.controle.util.Mascara;
import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.CadAtrasoSaidaBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;


public class CadAtrasoSaida extends JFrame {

	private JPanel frmCadAtraso;
	private JTextField txtnome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadAtrasoSaida frame = new CadAtrasoSaida();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public CadAtrasoSaida() throws ParseException {
		setResizable(false);
		setTitle("Atrasos e Sa\u00EDdas Antecipadas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 658);
		frmCadAtraso = new JPanel();
		frmCadAtraso.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmCadAtraso);
		frmCadAtraso.setLayout(null);
		Mascara masc = new Mascara();
		Limpar limpar = new Limpar();
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblNome.setBounds(46, 70, 72, 35);
		frmCadAtraso.add(lblNome);
		
		JLabel lblTurno = new JLabel("Turno:");
		lblTurno.setForeground(Color.WHITE);
		lblTurno.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblTurno.setBounds(498, 70, 72, 35);
		frmCadAtraso.add(lblTurno);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setForeground(Color.WHITE);
		lblHora.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblHora.setBounds(290, 163, 72, 35);
		frmCadAtraso.add(lblHora);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblData.setBounds(57, 163, 64, 35);
		frmCadAtraso.add(lblData);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblTipo.setBounds(511, 163, 59, 35);
		frmCadAtraso.add(lblTipo);
		
		JLabel lblMotivo = new JLabel("Motivo:");
		lblMotivo.setForeground(Color.WHITE);
		lblMotivo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblMotivo.setBounds(99, 261, 89, 35);
		frmCadAtraso.add(lblMotivo);
		
		JTextArea txtmotivo = new JTextArea();
		txtmotivo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtmotivo.setBounds(99, 295, 617, 167);
		frmCadAtraso.add(txtmotivo);
		
		txtnome = new JTextField();
		txtnome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtnome.setBounds(120, 70, 313, 35);
		frmCadAtraso.add(txtnome);
		txtnome.setColumns(10);
		
		JComboBox comboturno = new JComboBox();
		comboturno.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboturno.setModel(new DefaultComboBoxModel(new String[] {"1\u00BA Turno", "2\u00BA Turno", "3\u00BA Turno"}));
		comboturno.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		comboturno.setBounds(575, 70, 124, 35);
		frmCadAtraso.add(comboturno);
		
		JComboBox combotipo = new JComboBox();
		combotipo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		combotipo.setModel(new DefaultComboBoxModel(new String[] {"Atraso", "Sa\u00EDda Antecipada", "Retorno"}));
		combotipo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combotipo.setBounds(570, 163, 217, 35);
		frmCadAtraso.add(combotipo);
		
		JFormattedTextField txtdata = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdata.setHorizontalAlignment(SwingConstants.CENTER);
		txtdata.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdata.setBounds(120, 163, 143, 35);
		frmCadAtraso.add(txtdata);
		
		String hoje;
		hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
		txtdata.setText(hoje);
		
		JFormattedTextField txthora = new JFormattedTextField(new MaskFormatter("##:##"));
		txthora.setHorizontalAlignment(SwingConstants.CENTER);
		txthora.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txthora.setBounds(352, 163, 116, 35);
		frmCadAtraso.add(txthora);
		
		JButton btcadastrar = new JButton("");
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btcadastrar.setRolloverIcon(new ImageIcon(CadAtrasoSaida.class.getResource("/imagens/botaocadgrande.png")));
				
			}
		});
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(txtnome.getText().equals("") || txthora.getText().equals("") || txtmotivo.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Preencha os campos corretamente","Atenção",JOptionPane.WARNING_MESSAGE);
				}else{
					
				CRUD sql = new CRUD();	
				String data;
				data = masc.convertDate(txtdata.getText());
				
				CadAtrasoSaidaBean bean = new CadAtrasoSaidaBean();
				
				bean.setNome(txtnome.getText());
				bean.setTurno(comboturno.getSelectedItem().toString());
				bean.setData(data);
				bean.setHora(txthora.getText());
				bean.setTipo(combotipo.getSelectedItem().toString());
				bean.setMotivo(txtmotivo.getText());
				
				Date dia = null;
	            String dataTexto = new String(txtdata.getText());
	            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	            try {
	                    format.setLenient(false);
	                    dia = format.parse(dataTexto);
				try {
					sql.Atraso(bean);
					JOptionPane.showMessageDialog(null,"Cadastrado com Sucesso");
					limpar.Limpar(frmCadAtraso);
					txtmotivo.setText("");
					txtnome.grabFocus();
				} catch (Exception e2) {
					System.out.println(e2);
				}
	            } catch (ParseException e3) {
		     		JOptionPane.showMessageDialog(null,
		                        "Data inválida. Tente novamente!",
		                        "AVISO",
		                        JOptionPane.WARNING_MESSAGE);
		     		txtdata.setText("");
		     		txtdata.grabFocus();
		            }
				
			}}
		});
		btcadastrar.setIcon(new ImageIcon(CadAtrasoSaida.class.getResource("/imagens/botaocadastrar.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(76, 518, 133, 54);
		frmCadAtraso.add(btcadastrar);
		
		JButton btsair = new JButton("");
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(CadAtrasoSaida.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btsair.setIcon(new ImageIcon(CadAtrasoSaida.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(607, 518, 149, 68);
		frmCadAtraso.add(btsair);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CadAtrasoSaida.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(0, 0, 852, 634);
		frmCadAtraso.add(label);
		setLocationRelativeTo(null);
	}
}

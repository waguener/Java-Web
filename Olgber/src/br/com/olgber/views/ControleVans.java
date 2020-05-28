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
import br.com.olgber.bean.CadAdmBean;
import br.com.olgber.bean.CadVansBean;
import br.com.olgber.bean.NovaLinhaBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class ControleVans extends JFrame {

	private JPanel ControleVans;
	private JTextField txtmotorista;
	private JTextField txtqtdpassageiro;
	private JTextField txtresponsavel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControleVans frame = new ControleVans();
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
	public ControleVans() throws ParseException {
		setResizable(false);
		setTitle("Controle de Vans e Passageiros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 658);
		ControleVans = new JPanel();
		ControleVans.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ControleVans);
		ControleVans.setLayout(null);
		Limpar limpar = new Limpar();
		Mascara masc = new Mascara();
		
		JLabel lblMotorista = new JLabel("Motorista:");
		lblMotorista.setForeground(Color.WHITE);
		lblMotorista.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblMotorista.setBounds(27, 61, 107, 35);
		ControleVans.add(lblMotorista);
		
		txtmotorista = new JTextField();
		txtmotorista.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtmotorista.setColumns(10);
		txtmotorista.setBounds(133, 61, 403, 35);
		ControleVans.add(txtmotorista);
		
		JFormattedTextField txtdata = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdata.setHorizontalAlignment(SwingConstants.CENTER);
		txtdata.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdata.setBounds(621, 61, 149, 35);
		ControleVans.add(txtdata);
		
		String hoje;
		hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
		txtdata.setText(hoje);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblData.setBounds(559, 61, 64, 35);
		ControleVans.add(lblData);
		
		JLabel lblLinha = new JLabel("Linha:");
		lblLinha.setForeground(Color.WHITE);
		lblLinha.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblLinha.setBounds(66, 143, 64, 35);
		ControleVans.add(lblLinha);
		
		JComboBox combolinha = new JComboBox();
		combolinha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		combolinha.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combolinha.setBounds(133, 143, 229, 35);
		ControleVans.add(combolinha);
		
		CRUD sql = new CRUD();
		
		try {
			for (NovaLinhaBean list : sql.BuscaNovaLinha()) {
				combolinha.addItem(list.getLinha());
			}
		} catch (Exception e2) {
			System.out.println(e2);
		}
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblTipo.setBounds(463, 143, 58, 35);
		ControleVans.add(lblTipo);
		
		JComboBox combotipo = new JComboBox();
		combotipo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		combotipo.setModel(new DefaultComboBoxModel(new String[] {"Entrada ", "Sa\u00EDda"}));
		combotipo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combotipo.setBounds(522, 143, 142, 35);
		ControleVans.add(combotipo);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setForeground(Color.WHITE);
		lblHora.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblHora.setBounds(70, 230, 64, 35);
		ControleVans.add(lblHora);
		
		JFormattedTextField txthora = new JFormattedTextField(new MaskFormatter("##:##"));
		txthora.setHorizontalAlignment(SwingConstants.CENTER);
		txthora.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txthora.setBounds(133, 230, 112, 35);
		ControleVans.add(txthora);
		
		JLabel lblQtdPassageiros = new JLabel("QTD Passageiros:");
		lblQtdPassageiros.setForeground(Color.WHITE);
		lblQtdPassageiros.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblQtdPassageiros.setBounds(340, 230, 177, 35);
		ControleVans.add(lblQtdPassageiros);
		
		txtqtdpassageiro = new JTextField();
		txtqtdpassageiro.setHorizontalAlignment(SwingConstants.CENTER);
		txtqtdpassageiro.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtqtdpassageiro.setColumns(10);
		txtqtdpassageiro.setBounds(522, 230, 58, 35);
		ControleVans.add(txtqtdpassageiro);
		
		JLabel lblResponsvel = new JLabel("Respons\u00E1vel:");
		lblResponsvel.setForeground(Color.WHITE);
		lblResponsvel.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblResponsvel.setBounds(70, 324, 131, 35);
		ControleVans.add(lblResponsvel);
		
		txtresponsavel = new JTextField();
		txtresponsavel.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtresponsavel.setColumns(10);
		txtresponsavel.setBounds(202, 324, 403, 35);
		ControleVans.add(txtresponsavel);
		
		JButton btcadastrar = new JButton("");
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btcadastrar.setRolloverIcon(new ImageIcon(ControleVans.class.getResource("/imagens/botaocadgrande.png")));
			}
		});
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtmotorista.getText().equals("") || txthora.getText().equals("") || txtresponsavel.getText().equals("") || txtqtdpassageiro.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Preencha os campos corretamente","Atenção",JOptionPane.WARNING_MESSAGE);
				}else{
				
				CRUD sql = new CRUD();
				String data,tipo;
				data = masc.convertDate(txtdata.getText());			
				tipo = combotipo.getSelectedItem().toString();
				CadVansBean bean = new CadVansBean();
				bean.setMotorista(txtmotorista.getText());
				bean.setData(data);
				bean.setLinha(combolinha.getSelectedItem().toString());
				bean.setTipo(tipo);
				bean.setHora(txthora.getText());
				bean.setQtdpassageiros(Integer.parseInt(txtqtdpassageiro.getText()));
				bean.setResponsavel(txtresponsavel.getText());
				
				try {
					sql.Vans(bean);
					System.out.println();
					JOptionPane.showMessageDialog(null,"controle Salvo com Sucesso","SUCESSO",JOptionPane.INFORMATION_MESSAGE);		
					
					txtmotorista.setText("");
					txtresponsavel.setText("");
					txtqtdpassageiro.setText("");
					txthora.setText("");
					txtmotorista.grabFocus();
				} catch (Exception e) {
					System.out.println(e);
				}
			}}
		});
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.setIcon(new ImageIcon(ControleVans.class.getResource("/imagens/botaocadastrar.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(100, 433, 135, 59);
		ControleVans.add(btcadastrar);
		
		JButton btsair = new JButton("");
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btsair.setRolloverIcon(new ImageIcon(ControleVans.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.setIcon(new ImageIcon(ControleVans.class.getResource("/imagens/botaosair.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(559, 421, 149, 71);
		ControleVans.add(btsair);
		
		JButton btnovalinha = new JButton("");
		btnovalinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadNovaLinha tela;
				try {
					tela = new CadNovaLinha();
					tela.setVisible(true);
					dispose();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btnovalinha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnovalinha.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btnovalinha.setRolloverIcon(new ImageIcon(ControleVans.class.getResource("/imagens/btcadastrarnovalinhabig.png")));
			}
		});
		btnovalinha.setIcon(new ImageIcon(ControleVans.class.getResource("/imagens/btcadastrarnovalinha.png")));
		btnovalinha.setFocusable(false);
		btnovalinha.setContentAreaFilled(false);
		btnovalinha.setBorderPainted(false);
		btnovalinha.setBounds(326, 433, 135, 59);
		ControleVans.add(btnovalinha);
		
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ControleVans.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(-14, 0, 868, 625);
		ControleVans.add(label);
		setLocationRelativeTo(null);
	}
}

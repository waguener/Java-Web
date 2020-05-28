package br.com.olgber.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.CadArmarioBean;
import br.com.olgber.bean.CadVeiculoBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.ParseException;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadVeiculo extends JFrame {

	private JPanel contentPane;
	private JTextField txtmodelo;
	private JTable tabveiculo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadVeiculo frame = new CadVeiculo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public CadVeiculo() throws ParseException {
		setResizable(false);
		setTitle("Cadastro de Ve\u00EDculos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setForeground(Color.WHITE);
		lblMarca.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblMarca.setBounds(85, 348, 79, 35);
		contentPane.add(lblMarca);

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblModelo.setBounds(370, 348, 79, 35);
		contentPane.add(lblModelo);

		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setForeground(Color.WHITE);
		lblPlaca.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblPlaca.setBounds(159, 436, 79, 35);
		contentPane.add(lblPlaca);

		JLabel lblAno = new JLabel("Ano:");
		lblAno.setForeground(Color.WHITE);
		lblAno.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblAno.setBounds(456, 436, 52, 35);
		contentPane.add(lblAno);

		JComboBox combomarca = new JComboBox();
		combomarca.setModel(new DefaultComboBoxModel(new String[] {"Chevrolet", "Fiat", "Ford", "Volkswagen", "Hyundai"}));
		combomarca.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combomarca.setBounds(163, 348, 147, 35);
		contentPane.add(combomarca);

		txtmodelo = new JTextField();
		txtmodelo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtmodelo.setBounds(456, 348, 210, 35);
		contentPane.add(txtmodelo);
		txtmodelo.setColumns(10);

		JFormattedTextField txtplaca = new JFormattedTextField(new MaskFormatter("UUU-####"));
		txtplaca.setHorizontalAlignment(SwingConstants.CENTER);
		txtplaca.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtplaca.setBounds(232, 436, 133, 35);
		contentPane.add(txtplaca);

		JFormattedTextField txtano = new JFormattedTextField(new MaskFormatter("####"));
		txtano.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtano.setBounds(508, 436, 65, 35);
		contentPane.add(txtano);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(73, 54, 711, 206);
		contentPane.add(scrollPane);

		tabveiculo = new JTable();
		tabveiculo.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabveiculo);

		JButton btatualizar = new JButton("");
		btatualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();

				tabveiculo.setModel(modelo);

				modelo.addColumn("Marca");
				modelo.addColumn("Modelo");
				modelo.addColumn("Placa");
				modelo.addColumn("Ano");

				tabveiculo.getColumnModel().getColumn(0).setPreferredWidth(80);
				tabveiculo.getColumnModel().getColumn(1).setPreferredWidth(70);
				tabveiculo.getColumnModel().getColumn(2).setPreferredWidth(50);
				tabveiculo.getColumnModel().getColumn(3).setPreferredWidth(20);

				tabveiculo.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabveiculo
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadVeiculoBean lista : sql.BuscaVeiculo()) {
						modelo.addRow(
								new Object[] { lista.getMarca(), lista.getModelo(), lista.getPlaca(), lista.getAno() });
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		btatualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btatualizar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btatualizar.setRolloverIcon(new ImageIcon(CadVeiculo.class.getResource("/imagens/botaoatualizar.png")));
			}
		});
		btatualizar.setIcon(new ImageIcon(CadVeiculo.class.getResource("/imagens/botaoatualizar2.png")));
		btatualizar.setFocusable(false);
		btatualizar.setContentAreaFilled(false);
		btatualizar.setBorderPainted(false);
		btatualizar.setBounds(53, 273, 65, 57);
		contentPane.add(btatualizar);

		JButton btcadastrar = new JButton("");
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();

				if(txtmodelo.getText().equals("") || txtplaca.getText().equals("") || txtano.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Prencha os campos corretamente","Atenção",JOptionPane.WARNING_MESSAGE);
				}else{
				CadVeiculoBean bean = new CadVeiculoBean();

				bean.setMarca(combomarca.getSelectedItem().toString());
				bean.setModelo(txtmodelo.getText());
				bean.setPlaca(txtplaca.getText());
				bean.setAno(Integer.parseInt(txtano.getText()));
				bean.setGasolina("4");
				bean.setGas("4");
				try {
					sql.SalvaVeiculo(bean);
					JOptionPane.showMessageDialog(null, "Cadastro Efetuado com Sucesso");
				} catch (Exception e) {
					System.out.println(e);
				}

			}}
		});
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btcadastrar.setRolloverIcon(new ImageIcon(CadVeiculo.class.getResource("/imagens/botaocadgrande.png")));
			}
		});
		btcadastrar.setIcon(new ImageIcon(CadVeiculo.class.getResource("/imagens/botaocadastrar.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(61, 515, 133, 51);
		contentPane.add(btcadastrar);

		JButton btsair = new JButton("");
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(CadVeiculo.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btsair.setIcon(new ImageIcon(CadVeiculo.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(550, 515, 161, 69);
		contentPane.add(btsair);
		
		JLabel lblVeculosCadastrados = new JLabel("Ve\u00EDculos Cadastrados:");
		lblVeculosCadastrados.setForeground(Color.WHITE);
		lblVeculosCadastrados.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblVeculosCadastrados.setBounds(33, 13, 223, 35);
		contentPane.add(lblVeculosCadastrados);
		
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(
						CadVeiculo.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
				label.setBounds(-25, -27, 873, 660);
				contentPane.add(label);
	}
}

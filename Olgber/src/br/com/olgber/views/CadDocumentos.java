package br.com.olgber.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.controle.util.Limpar;
import br.com.controle.util.Mascara;
import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.CadArmarioFuncBean;
import br.com.olgber.bean.CadVeiDocumentoBean;
import br.com.olgber.bean.CadVeiculoBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CadDocumentos extends JFrame {

	private JPanel frmdocveiculo;
	private JTextField txtplaca;
	private JTextField txtnumdocumento;
	private JTable tabDocumento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadDocumentos frame = new CadDocumentos();
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
	public CadDocumentos() throws ParseException {
		setResizable(false);
		setTitle("Cadastro de Documentos de Ve\u00EDculos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 673);
		frmdocveiculo = new JPanel();
		frmdocveiculo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmdocveiculo);
		setLocationRelativeTo(null);
		Mascara masc = new Mascara();
		Limpar limpar = new Limpar();
		frmdocveiculo.setLayout(null);

		JLabel lblVeculo = new JLabel("Ve\u00EDculo:");
		lblVeculo.setBounds(46, 46, 88, 35);
		lblVeculo.setForeground(Color.WHITE);
		lblVeculo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		frmdocveiculo.add(lblVeculo);

		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setBounds(432, 46, 88, 35);
		lblPlaca.setForeground(Color.WHITE);
		lblPlaca.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		frmdocveiculo.add(lblPlaca);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(71, 142, 88, 35);
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		frmdocveiculo.add(lblTipo);

		JButton btExcluir = new JButton("");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Integer num = 0;
				num = Integer.parseInt(String
						.valueOf(tabDocumento.getModel().getValueAt(tabDocumento.getSelectedRow(), 4).toString()));

				CRUD sql = new CRUD();

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir este Documento?",
							"Atenção", JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						sql.ExcluirDocumento(num);
						JOptionPane.showMessageDialog(null,"Documento Excluído com Sucesso");
						((DefaultTableModel) tabDocumento.getModel()).removeRow(tabDocumento.getSelectedRow());  
		                tabDocumento.repaint();  
		                tabDocumento.revalidate();
					
				} 
				}catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btExcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btExcluir
				.setRolloverIcon(new ImageIcon(CadDocumentos.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btExcluir.setIcon(new ImageIcon(CadDocumentos.class.getResource("/imagens/botaoexcluir.png")));
		btExcluir.setFocusable(false);
		btExcluir.setContentAreaFilled(false);
		btExcluir.setBorderPainted(false);
		btExcluir.setBounds(327, 550, 148, 75);
		frmdocveiculo.add(btExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setBounds(34, 339, 783, 210);
		frmdocveiculo.add(scrollPane);
		
		tabDocumento = new JTable();
		tabDocumento.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		scrollPane.setViewportView(tabDocumento);
		
		JLabel lblNmeroDoDocumento = new JLabel("N\u00FAmero do Documento:");
		lblNmeroDoDocumento.setBounds(449, 142, 250, 35);
		lblNmeroDoDocumento.setForeground(Color.WHITE);
		lblNmeroDoDocumento.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		frmdocveiculo.add(lblNmeroDoDocumento);

		CRUD sql = new CRUD();

		JComboBox comboveiculo = new JComboBox();
		comboveiculo.setBounds(135, 46, 191, 35);
		comboveiculo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboveiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String veiculo;
				
				boolean placa;
				try {
					placa = sql.Veiculo(comboveiculo.getSelectedItem().toString());
					
					if (placa != false) {
						System.out.println("true");
						veiculo = sql.BuscaPlaca(comboveiculo.getSelectedItem().toString());
						txtplaca.setText(veiculo);
						
						DefaultTableModel modelo = new DefaultTableModel();
						
						tabDocumento.setModel(modelo);

						modelo.addColumn("Veiculo");
						modelo.addColumn("Placa");
						modelo.addColumn("Documento");
						modelo.addColumn("Número Documento");
						
						modelo.addColumn("ID");
						
						tabDocumento.getColumnModel().getColumn(0).setPreferredWidth(50);
						tabDocumento.getColumnModel().getColumn(1).setPreferredWidth(20);
						tabDocumento.getColumnModel().getColumn(2).setPreferredWidth(40);
						tabDocumento.getColumnModel().getColumn(3).setPreferredWidth(40);
						tabDocumento.getColumnModel().getColumn(4).setPreferredWidth(10);
						
						
						tabDocumento.setRowHeight(30);

						DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabDocumento
								.getDefaultRenderer(JLabel.class);
						renderer.setHorizontalAlignment(SwingConstants.CENTER);
						try {
							for (CadVeiDocumentoBean list : sql.BuscaDoc()) {
								modelo.addRow(new Object[] { list.getVeiculo(), list.getPlaca(), list.getTipo(), list.getNumdocumento(),
										list.getId() });
							}
						} catch (Exception e1) {
							System.out.println(e1);
						}
						
					}
				} catch (Exception e2) {
					System.out.println("Erro_Busca " + e2);
				}

			}
		});
		comboveiculo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		frmdocveiculo.add(comboveiculo);

		txtplaca = new JTextField();
		txtplaca.setBounds(509, 46, 117, 35);
		txtplaca.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtplaca.setEditable(false);
		frmdocveiculo.add(txtplaca);
		txtplaca.setColumns(10);

		JComboBox combotipo = new JComboBox();
		combotipo.setBounds(71, 178, 257, 35);
		combotipo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		combotipo.setModel(new DefaultComboBoxModel(new String[] {"RENAVAN"}));
		combotipo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		frmdocveiculo.add(combotipo);

		JLabel lblEntreComO = new JLabel("Entre com o Tipo de Documento e seus Dados");
		lblEntreComO.setBounds(112, 94, 474, 35);
		lblEntreComO.setForeground(Color.WHITE);
		lblEntreComO.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		frmdocveiculo.add(lblEntreComO);

		txtnumdocumento = new JTextField();
		txtnumdocumento.setBounds(449, 178, 264, 35);
		txtnumdocumento.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		frmdocveiculo.add(txtnumdocumento);
		txtnumdocumento.setColumns(10);

		JButton btbuscar = new JButton("");
		btbuscar.setBounds(331, 28, 71, 65);
		btbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					for (CadVeiculoBean list : sql.BuscaVeiculo()) {
						comboveiculo.addItem(list.getModelo());
						
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btbuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btbuscar.setIcon(new ImageIcon(CadDocumentos.class.getResource("/imagens/lupa.png")));
		btbuscar.setFocusable(false);
		btbuscar.setContentAreaFilled(false);
		btbuscar.setBorderPainted(false);
		frmdocveiculo.add(btbuscar);

		JButton btcadastrar = new JButton("");
		btcadastrar.setBounds(84, 550, 148, 75);
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtnumdocumento.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Preencha os campos corretamente", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else {
					
					CadVeiDocumentoBean bean = new CadVeiDocumentoBean();

					bean.setVeiculo(comboveiculo.getSelectedItem().toString());
					bean.setPlaca(txtplaca.getText());
					bean.setTipo(combotipo.getSelectedItem().toString());
					bean.setNumdocumento(txtnumdocumento.getText());
					

					try {
						sql.SalvaDocumento(bean);
						JOptionPane.showMessageDialog(null, "Documento Cadastrado com Sucesso");
						txtnumdocumento.setText("");
						combotipo.grabFocus();
						
DefaultTableModel modelo = new DefaultTableModel();
						
						tabDocumento.setModel(modelo);

						modelo.addColumn("Veiculo");
						modelo.addColumn("Placa");
						modelo.addColumn("Documento");
						modelo.addColumn("Número Documento");
						
						modelo.addColumn("ID");
						
						tabDocumento.getColumnModel().getColumn(0).setPreferredWidth(50);
						tabDocumento.getColumnModel().getColumn(1).setPreferredWidth(20);
						tabDocumento.getColumnModel().getColumn(2).setPreferredWidth(40);
						tabDocumento.getColumnModel().getColumn(3).setPreferredWidth(40);
						tabDocumento.getColumnModel().getColumn(4).setPreferredWidth(10);
						
						
						tabDocumento.setRowHeight(30);

						DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabDocumento
								.getDefaultRenderer(JLabel.class);
						renderer.setHorizontalAlignment(SwingConstants.CENTER);
						try {
							for (CadVeiDocumentoBean list : sql.BuscaDoc()) {
								modelo.addRow(new Object[] { list.getVeiculo(), list.getPlaca(), list.getTipo(), list.getNumdocumento(),
										list.getId() });
							}
						} catch (Exception e1) {
							System.out.println(e1);
						}
						
						
					} catch (Exception e2) {
						System.out.println("Erro salvar " + e2);
					}
				}
				
				
			}
		});
		
		
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btcadastrar
						.setRolloverIcon(new ImageIcon(CadDocumentos.class.getResource("/imagens/botaocadgrande.png")));
			}
		});
		btcadastrar.setIcon(new ImageIcon(CadDocumentos.class.getResource("/imagens/botaocadastrar.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		frmdocveiculo.add(btcadastrar);

		JButton btsair = new JButton("");
		btsair.setBounds(589, 550, 154, 65);
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(CadDocumentos.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setIcon(new ImageIcon(CadDocumentos.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		frmdocveiculo.add(btsair);
								
								
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 842, 657);
		label.setIcon(new ImageIcon(
		CadDocumentos.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		frmdocveiculo.add(label);
	}
}

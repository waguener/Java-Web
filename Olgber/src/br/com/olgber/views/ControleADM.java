package br.com.olgber.views;

import java.awt.BorderLayout;
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
import br.com.olgber.bean.AdmBean;
import br.com.olgber.bean.CadAdmBean;
import br.com.olgber.bean.CadArmarioFuncBean;
import br.com.olgber.bean.CadChaveBean;
import br.com.olgber.bean.CadVisitanteBean;

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
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;;

public class ControleADM extends JFrame {

	private JPanel frmADM;
	private JTable tabADM;
	private JTextField txtdata;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControleADM frame = new ControleADM();
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
	public ControleADM() throws ParseException {
		setTitle("Controle de Funcion\u00E1rios ADM");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 658);
		frmADM = new JPanel();
		frmADM.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmADM);
		frmADM.setLayout(null);
		Mascara masc = new Mascara();
		Limpar limpar = new Limpar();

		JLabel label_1 = new JLabel("Funcion\u00E1rio:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_1.setBounds(26, 13, 135, 35);
		frmADM.add(label_1);

		txtdata = new JTextField();
		txtdata.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdata.setColumns(10);
		txtdata.setBounds(561, 13, 135, 35);
		frmADM.add(txtdata);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(26, 124, 781, 354);
		frmADM.add(scrollPane);

		tabADM = new JTable();
		tabADM.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int id = 0;
				String nome, data2, entrada, saida;
				id = Integer.parseInt(String.valueOf(tabADM.getModel().getValueAt(tabADM.getSelectedRow(), 0).toString()));
				nome = tabADM.getModel().getValueAt(tabADM.getSelectedRow(), 1).toString();
				data2 = tabADM.getModel().getValueAt(tabADM.getSelectedRow(), 2).toString();
				entrada = tabADM.getModel().getValueAt(tabADM.getSelectedRow(), 3).toString();
				
			}
		});
		tabADM.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabADM);

		JComboBox combonome = new JComboBox();
		combonome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		combonome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combonome.setBounds(174, 13, 290, 35);
		frmADM.add(combonome);
		
		CRUD sql = new CRUD();
		
		try {
			for (CadAdmBean list : sql.BuscaFuncAdm()) {
				combonome.addItem(list.getNome());
			}
		} catch (Exception e2) {
			System.out.println(e2);
		}

		JLabel lblEntrada = new JLabel("Entrada:");
		lblEntrada.setForeground(Color.WHITE);
		lblEntrada.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblEntrada.setBounds(118, 76, 103, 35);
		frmADM.add(lblEntrada);

		String hoje, data;
		hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
		txtdata.setText(hoje);

		data = masc.convertDate(txtdata.getText());
		

		DefaultTableModel modelo = new DefaultTableModel();

		tabADM.setModel(modelo);

		modelo.addColumn("ID");
		modelo.addColumn("Funcionário");
		modelo.addColumn("Data");
		modelo.addColumn("Entrada");
		modelo.addColumn("Saída");

		tabADM.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabADM.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabADM.getColumnModel().getColumn(2).setPreferredWidth(40);
		tabADM.getColumnModel().getColumn(3).setPreferredWidth(40);
		tabADM.getColumnModel().getColumn(4).setPreferredWidth(40);

		tabADM.setRowHeight(30);

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabADM.getDefaultRenderer(JLabel.class);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			for (AdmBean list : sql.BuscaDataAdm(data)) {
				modelo.addRow(new Object[] { list.getId(), list.getNome(), list.getData(), list.getEntrada(),
						list.getSaida() });
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}

		JFormattedTextField txtentrada = new JFormattedTextField(new MaskFormatter("##:##"));
		txtentrada.setHorizontalAlignment(SwingConstants.CENTER);
		txtentrada.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtentrada.setBounds(219, 76, 112, 35);
		frmADM.add(txtentrada);

		JFormattedTextField txtsaida = new JFormattedTextField(new MaskFormatter("##:##"));
		txtsaida.setHorizontalAlignment(SwingConstants.CENTER);
		txtsaida.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtsaida.setBounds(132, 491, 112, 35);
		frmADM.add(txtsaida);

		JLabel lblSada = new JLabel("Sa\u00EDda:");
		lblSada.setForeground(Color.WHITE);
		lblSada.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblSada.setBounds(68, 491, 66, 35);
		frmADM.add(lblSada);

		JButton btcadastrar = new JButton("");
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtdata.getText().equals("") || txtentrada.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else {

					CRUD sql = new CRUD();
					String data;
					data = masc.convertDate(txtdata.getText());

					AdmBean bean = new AdmBean();

					bean.setNome(combonome.getSelectedItem().toString());
					bean.setData(data);
					bean.setEntrada(txtentrada.getText());
					bean.setSaida(txtsaida.getText());
				
					
						try {
							sql.SalvarAdm(bean);
							JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
							txtentrada.setText("");
							combonome.grabFocus();

							DefaultTableModel modelo = new DefaultTableModel();

							tabADM.setModel(modelo);

							modelo.addColumn("ID");
							modelo.addColumn("Funcionário");
							modelo.addColumn("Data");
							modelo.addColumn("Entrada");
							modelo.addColumn("Saída");

							tabADM.getColumnModel().getColumn(0).setPreferredWidth(10);
							tabADM.getColumnModel().getColumn(1).setPreferredWidth(100);
							tabADM.getColumnModel().getColumn(2).setPreferredWidth(40);
							tabADM.getColumnModel().getColumn(3).setPreferredWidth(40);
							tabADM.getColumnModel().getColumn(4).setPreferredWidth(40);

							tabADM.setRowHeight(30);

							DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabADM
									.getDefaultRenderer(JLabel.class);
							renderer.setHorizontalAlignment(SwingConstants.CENTER);
							try {
								for (AdmBean list : sql.BuscaDataAdm(data)) {
									modelo.addRow(new Object[] { list.getId(), list.getNome(), list.getData(),
											list.getEntrada(), list.getSaida() });
								}
							} catch (Exception e1) {
								System.out.println(e1);
							}
							tabADM.repaint();
							tabADM.revalidate();
						} catch (Exception e1) {
							System.out.println(e1 + "Erro1");
						}
					
					
				}

			}
		});
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btcadastrar
						.setRolloverIcon(new ImageIcon(ControleADM.class.getResource("/imagens/btcadentradabig.png")));
			}
		});
		btcadastrar.setIcon(new ImageIcon(ControleADM.class.getResource("/imagens/btcadentrada.png")));
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(368, 65, 135, 59);
		frmADM.add(btcadastrar);

		JButton btsair = new JButton("");
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(ControleADM.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.setIcon(new ImageIcon(ControleADM.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(547, 539, 149, 71);
		frmADM.add(btsair);

		JLabel lblData = new JLabel("Data:");
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblData.setBounds(497, 13, 72, 35);
		frmADM.add(lblData);

		JButton btcadastrarsaida = new JButton("");
		btcadastrarsaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CRUD sql = new CRUD();

				AdmBean bean = new AdmBean();
				bean.setId( Integer.parseInt(String.valueOf(tabADM.getModel().getValueAt(tabADM.getSelectedRow(), 0).toString())));
				bean.setNome(tabADM.getModel().getValueAt(tabADM.getSelectedRow(), 1).toString());
				bean.setData(tabADM.getModel().getValueAt(tabADM.getSelectedRow(), 2).toString());
				bean.setEntrada(tabADM.getModel().getValueAt(tabADM.getSelectedRow(), 3).toString());
				bean.setSaida(txtsaida.getText());
				try {
					sql.AtualizaADM(bean);
					JOptionPane.showMessageDialog(null, "Saída Cadastrada com Sucesso");
					txtsaida.setText("");
					
					DefaultTableModel modelo = new DefaultTableModel();

					tabADM.setModel(modelo);

					modelo.addColumn("ID");
					modelo.addColumn("Funcionário");
					modelo.addColumn("Data");
					modelo.addColumn("Entrada");
					modelo.addColumn("Saída");

					tabADM.getColumnModel().getColumn(0).setPreferredWidth(10);
					tabADM.getColumnModel().getColumn(1).setPreferredWidth(100);
					tabADM.getColumnModel().getColumn(2).setPreferredWidth(40);
					tabADM.getColumnModel().getColumn(3).setPreferredWidth(40);
					tabADM.getColumnModel().getColumn(4).setPreferredWidth(40);

					tabADM.setRowHeight(30);

					DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabADM
							.getDefaultRenderer(JLabel.class);
					renderer.setHorizontalAlignment(SwingConstants.CENTER);
					try {
						for (AdmBean list : sql.BuscaDataAdm(data)) {
							modelo.addRow(new Object[] { list.getId(), list.getNome(), list.getData(),
									list.getEntrada(), list.getSaida() });
						}
					} catch (Exception e1) {
						System.out.println(e1);
					}
					
				} catch (ClassNotFoundException | SQLException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btcadastrarsaida.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrarsaida.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btcadastrarsaida
						.setRolloverIcon(new ImageIcon(ControleADM.class.getResource("/imagens/btcadsaidabig.png")));
			}
		});
		btcadastrarsaida.setIcon(new ImageIcon(ControleADM.class.getResource("/imagens/btcadsaida.png")));
		btcadastrarsaida.setFocusable(false);
		btcadastrarsaida.setContentAreaFilled(false);
		btcadastrarsaida.setBorderPainted(false);
		btcadastrarsaida.setBounds(143, 539, 149, 71);
		frmADM.add(btcadastrarsaida);
		
		JButton btexcluir = new JButton("");
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int num = 0;

				num = Integer.parseInt(String
						.valueOf(tabADM.getModel().getValueAt(tabADM.getSelectedRow(), 0).toString()));

				CRUD sql = new CRUD();

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir o Usuário deste Armário",
							"Atenção", JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						sql.ExcluirAdm(num);
						JOptionPane.showMessageDialog(null,"Cadastro Excluído com Sucesso");
						DefaultTableModel modelo = new DefaultTableModel();

						tabADM.setModel(modelo);

						modelo.addColumn("ID");
						modelo.addColumn("Funcionário");
						modelo.addColumn("Data");
						modelo.addColumn("Entrada");
						modelo.addColumn("Saída");

						tabADM.getColumnModel().getColumn(0).setPreferredWidth(10);
						tabADM.getColumnModel().getColumn(1).setPreferredWidth(100);
						tabADM.getColumnModel().getColumn(2).setPreferredWidth(40);
						tabADM.getColumnModel().getColumn(3).setPreferredWidth(40);
						tabADM.getColumnModel().getColumn(4).setPreferredWidth(40);

						tabADM.setRowHeight(30);

						DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabADM
								.getDefaultRenderer(JLabel.class);
						renderer.setHorizontalAlignment(SwingConstants.CENTER);
						try {
							for (AdmBean list : sql.BuscaDataAdm(data)) {
								modelo.addRow(new Object[] { list.getId(), list.getNome(), list.getData(),
										list.getEntrada(), list.getSaida() });
							}
						} catch (Exception e1) {
							System.out.println(e1);
						}
					}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			}
					
					
		});
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btexcluir.setRolloverIcon(new ImageIcon(ControleADM.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setIcon(new ImageIcon(ControleADM.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(339, 539, 149, 71);
		frmADM.add(btexcluir);
		
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(
						ControleADM.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
				label.setBounds(0, 0, 853, 636);
				frmADM.add(label);
		setLocationRelativeTo(null);
	}
}

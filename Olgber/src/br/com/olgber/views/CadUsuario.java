package br.com.olgber.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.controle.util.Limpar;
import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.CadArmarioBean;
import br.com.olgber.bean.CadUsuarioBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Point;
import java.awt.event.MouseAdapter;

public class CadUsuario extends JFrame {

	private JPanel frmcadusuario;
	private JTextField txtsenha;
	private JTextField txtnome;
	private JTable tabusuario;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadUsuario frame = new CadUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadUsuario() {
		setResizable(false);
		setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		setTitle("Cadastro de Usu\u00E1rio");
		setRootPaneCheckingEnabled(false);
		setBounds(100, 100, 852, 658);
		frmcadusuario = new JPanel();
		frmcadusuario.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(frmcadusuario);
		frmcadusuario.setLayout(null);
		Limpar limpar = new Limpar();

		JButton btexcluir = new JButton("");
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CRUD sql = new CRUD();

				int id;
				id = Integer.parseInt(txtid.getText());
				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir este Usuário", "Atenção",
							JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						sql.ExcluirUsuario(id);
						JOptionPane.showMessageDialog(null, "Usauario Excluido com Sucesso");
						((DefaultTableModel) tabusuario.getModel()).removeRow(tabusuario.getSelectedRow());  
		                tabusuario.repaint();  
		                tabusuario.revalidate();
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
				limpar.Limpar(frmcadusuario);
				txtnome.grabFocus();
			}
		});
		btexcluir.setEnabled(false);
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btexcluir.setRolloverIcon(
						new ImageIcon(CadUsuario.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setIcon(new ImageIcon(CadUsuario.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(306, 546, 125, 45);
		frmcadusuario.add(btexcluir);

		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtid.setColumns(10);
		txtid.setBounds(135, 417, 71, 35);
		frmcadusuario.add(txtid);

		JComboBox comboBoxtipo = new JComboBox();
		comboBoxtipo.setEnabled(false);
		comboBoxtipo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxtipo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		comboBoxtipo.setModel(new DefaultComboBoxModel(new String[] { "Administrador\t", "Usu\u00E1rio" }));
		comboBoxtipo.setBounds(324, 417, 201, 35);
		frmcadusuario.add(comboBoxtipo);

		JButton btcadastrar = new JButton("");
		btcadastrar.setEnabled(false);
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btcadastrar.setRolloverIcon(new ImageIcon(CadUsuario.class.getResource("/imagens/botaocadgrande.png")));
			}
		});
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtnome.getText().equals("") || txtsenha.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Os campos Nome e Senha precisam ser preenchidos para concluir o cadastro");
					btcadastrar.setEnabled(false);
				} else {
					System.out.println("PASSOU" + txtnome.getText());

					try {

						CRUD sql = new CRUD();
						CadUsuarioBean bean = new CadUsuarioBean();
						bean.setNome(txtnome.getText());
						bean.setSenha(txtsenha.getText());
						bean.setTipo(comboBoxtipo.getSelectedItem().toString());

						sql.SalvaUsuario(bean);
						JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso", "Cadastro OK",
								JOptionPane.INFORMATION_MESSAGE);
						txtnome.setDisabledTextColor(Color.BLACK);
						txtsenha.setDisabledTextColor(Color.BLACK);

						btcadastrar.setEnabled(false);

						DefaultTableModel modelo = new DefaultTableModel();

						tabusuario.setModel(modelo);

						modelo.addColumn("ID");
						modelo.addColumn("Nome");
						modelo.addColumn("Senha");
						modelo.addColumn("Tipo");

						tabusuario.getColumnModel().getColumn(0).setPreferredWidth(20);
						tabusuario.getColumnModel().getColumn(1).setPreferredWidth(100);
						tabusuario.getColumnModel().getColumn(2).setPreferredWidth(100);
						tabusuario.getColumnModel().getColumn(3).setPreferredWidth(100);

						tabusuario.setRowHeight(30);

						DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabusuario
								.getDefaultRenderer(JLabel.class);
						renderer.setHorizontalAlignment(SwingConstants.CENTER);
						try {
							for (CadUsuarioBean list : sql.BuscaUsuarioTotal()) {
								modelo.addRow(
										new Object[] { list.getId(), list.getNome(), list.getSenha(), list.getTipo() });
							}
						} catch (Exception e2) {
							System.out.println(e2);
						}
					} catch (Exception e2) {
						System.out.println(e2);
					}

				}
			}
		});
		btcadastrar.setIcon(new ImageIcon(CadUsuario.class.getResource("/imagens/botaocadastrar.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(181, 546, 125, 45);
		frmcadusuario.add(btcadastrar);

		JButton btalterar = new JButton("");
		btalterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtnome.getText().equals("") || txtsenha.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Os campos Nome e Senha precisam ser preenchidos corretamenta para concluir a alteração");
				} else {
					CRUD sql = new CRUD();

					CadUsuarioBean bean = new CadUsuarioBean();

					bean.setNome(txtnome.getText());
					bean.setSenha(txtsenha.getText());
					bean.setTipo(comboBoxtipo.getSelectedItem().toString());
					bean.setId(Integer.parseInt(txtid.getText()));

					try {
						sql.AtualizaUsuario(bean);
						JOptionPane.showMessageDialog(null, "Usuário Alterado com Sucesso");
						txtnome.setDisabledTextColor(Color.BLACK);
						txtsenha.setDisabledTextColor(Color.BLACK);

						DefaultTableModel modelo = new DefaultTableModel();

						tabusuario.setModel(modelo);

						modelo.addColumn("ID");
						modelo.addColumn("Nome");
						modelo.addColumn("Senha");
						modelo.addColumn("Tipo");

						tabusuario.getColumnModel().getColumn(0).setPreferredWidth(20);
						tabusuario.getColumnModel().getColumn(1).setPreferredWidth(100);
						tabusuario.getColumnModel().getColumn(2).setPreferredWidth(100);
						tabusuario.getColumnModel().getColumn(3).setPreferredWidth(100);

						tabusuario.setRowHeight(30);

						DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabusuario
								.getDefaultRenderer(JLabel.class);
						renderer.setHorizontalAlignment(SwingConstants.CENTER);
						try {
							for (CadUsuarioBean list : sql.BuscaUsuarioTotal()) {
								modelo.addRow(
										new Object[] { list.getId(), list.getNome(), list.getSenha(), list.getTipo() });
							}
						} catch (Exception e1) {
							System.out.println(e1);
						}
					} catch (Exception e2) {
						System.out.println(e2);

					}
				}
			}
		});
		btalterar.setEnabled(false);
		btalterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btalterar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btalterar.setRolloverIcon(new ImageIcon(CadUsuario.class.getResource("/imagens/botaoaltgrande.png")));
			}
		});
		btalterar.setIcon(new ImageIcon(CadUsuario.class.getResource("/imagens/botaoalterar.png")));
		btalterar.setFocusable(false);
		btalterar.setContentAreaFilled(false);
		btalterar.setBorderPainted(false);
		btalterar.setBounds(431, 546, 125, 45);
		frmcadusuario.add(btalterar);

		JButton btsair = new JButton("");
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(CadUsuario.class.getResource("/imagens/botaosaegrande.png")));
			}
		});
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btsair.setIcon(new ImageIcon(CadUsuario.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(681, 535, 133, 64);
		frmcadusuario.add(btsair);

		txtsenha = new JTextField();
		txtsenha.setEnabled(false);
		txtsenha.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtsenha.setColumns(10);
		txtsenha.setBounds(464, 346, 201, 35);
		frmcadusuario.add(txtsenha);

		txtnome = new JTextField();
		txtnome.setEnabled(false);
		txtnome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtnome.setColumns(10);
		txtnome.setBounds(141, 346, 201, 35);
		frmcadusuario.add(txtnome);

		JLabel label_1 = new JLabel("Nome:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_1.setBounds(61, 346, 95, 35);
		frmcadusuario.add(label_1);

		JLabel label_2 = new JLabel("Senha:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_2.setBounds(384, 346, 79, 35);
		frmcadusuario.add(label_2);

		JLabel label0 = new JLabel("Tipo:");
		label0.setForeground(Color.WHITE);
		label0.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label0.setBounds(254, 417, 61, 35);
		frmcadusuario.add(label0);

		JButton bteditar = new JButton("");
		bteditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				txtnome.setEnabled(true);

				txtsenha.setEnabled(true);

				comboBoxtipo.setEnabled(true);
				btcadastrar.setEnabled(false);
				btexcluir.setEnabled(false);
				btalterar.setEnabled(true);
				txtnome.grabFocus();
			}
		});
		bteditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bteditar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				bteditar.setRolloverIcon(new ImageIcon(CadUsuario.class.getResource("/imagens/botaoeditargrande.png")));
			}
		});
		bteditar.setEnabled(false);
		bteditar.setIcon(new ImageIcon(CadUsuario.class.getResource("/imagens/botaoeditar.png")));
		bteditar.setBounds(555, 546, 125, 45);
		bteditar.setFocusable(false);
		bteditar.setContentAreaFilled(false);
		bteditar.setBorderPainted(false);
		frmcadusuario.add(bteditar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 97, 761, 202);
		frmcadusuario.add(scrollPane);

		tabusuario = new JTable();
		tabusuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bteditar.setEnabled(true);
				btexcluir.setEnabled(true);
				txtid.setText(
						String.valueOf(tabusuario.getModel().getValueAt(tabusuario.getSelectedRow(), 0).toString()));
				txtnome.setText(tabusuario.getModel().getValueAt(tabusuario.getSelectedRow(), 1).toString());
				txtsenha.setText(tabusuario.getModel().getValueAt(tabusuario.getSelectedRow(), 2).toString());
				comboBoxtipo
						.setSelectedItem(tabusuario.getModel().getValueAt(tabusuario.getSelectedRow(), 3).toString());
				btcadastrar.setEnabled(false);
			}
		});
		tabusuario.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabusuario);

		JButton btbusca = new JButton("");
		btbusca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();

				txtnome.setDisabledTextColor(Color.BLACK);
				txtsenha.setDisabledTextColor(Color.BLACK);

				btcadastrar.setEnabled(false);

				DefaultTableModel modelo = new DefaultTableModel();

				tabusuario.setModel(modelo);

				modelo.addColumn("ID");
				modelo.addColumn("Nome");
				modelo.addColumn("Senha");
				modelo.addColumn("Tipo");

				tabusuario.getColumnModel().getColumn(0).setPreferredWidth(20);
				tabusuario.getColumnModel().getColumn(1).setPreferredWidth(100);
				tabusuario.getColumnModel().getColumn(2).setPreferredWidth(100);
				tabusuario.getColumnModel().getColumn(3).setPreferredWidth(100);

				tabusuario.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabusuario
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadUsuarioBean list : sql.BuscaUsuarioTotal()) {
						modelo.addRow(new Object[] { list.getId(), list.getNome(), list.getSenha(), list.getTipo() });
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				limpar.Limpar(frmcadusuario);
			}
		});

		btbusca.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btbusca.setIcon(new ImageIcon(CadUsuario.class.getResource("/imagens/lupa.png")));
		btbusca.setFocusable(false);
		btbusca.setContentAreaFilled(false);
		btbusca.setBorderPainted(false);
		btbusca.setBounds(254, 27, 61, 57);
		frmcadusuario.add(btbusca);

		JLabel lblEntreComO = new JLabel("Buscar Usu\u00E1rio:");
		lblEntreComO.setForeground(Color.WHITE);
		lblEntreComO.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblEntreComO.setBounds(78, 40, 164, 35);
		frmcadusuario.add(lblEntreComO);

		JButton btinserir = new JButton("");
		btinserir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btinserir.setRolloverIcon(
						new ImageIcon(CadUsuario.class.getResource("/imagens/botaoinserirgrande.png")));
			}
		});
		btinserir.setIcon(new ImageIcon(CadUsuario.class.getResource("/imagens/botaoinserir.png")));
		btinserir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btinserir.setFocusable(false);
		btinserir.setContentAreaFilled(false);
		btinserir.setBorderPainted(false);
		btinserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtnome.grabFocus();
				btcadastrar.setEnabled(true);
				btexcluir.setEnabled(false);
				btalterar.setEnabled(false);
				bteditar.setEnabled(false);
				txtnome.setEnabled(true);
				txtsenha.setEnabled(true);
				comboBoxtipo.setEnabled(true);
				limpar.Limpar(frmcadusuario);

				CRUD sql = new CRUD();

				int id = 0;
				int result;
				try {
					id = sql.IDUsuario();
					result = id + 1;
					txtid.setText(String.valueOf(result));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btinserir.setBounds(42, 546, 144, 45);
		frmcadusuario.add(btinserir);

		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblId.setBounds(90, 417, 33, 35);
		frmcadusuario.add(lblId);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(
				CadUsuario.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(-171, 0, 1060, 636);
		frmcadusuario.add(label);

	}
}

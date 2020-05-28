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
import br.com.olgber.bean.DescartaveisBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Descartaveis extends JFrame {

	private JPanel frmDescartaveis;
	private JTextField txtjaleco;
	private JTextField txtbranca;
	private JTextField txtverde;
	private JTextField txtazul;
	private JTable tabdescartaveis;
	private JTextField txtVermelha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Descartaveis frame = new Descartaveis();
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
	public Descartaveis() throws ParseException {
		setTitle("Controle de Descart\u00E1veis");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 673);
		frmDescartaveis = new JPanel();
		frmDescartaveis.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmDescartaveis);
		frmDescartaveis.setLayout(null);

		Mascara masc = new Mascara();
		Limpar limpar = new Limpar();

		JLabel lblJaleco = new JLabel("Jaleco:");
		lblJaleco.setForeground(Color.WHITE);
		lblJaleco.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblJaleco.setBounds(50, 45, 83, 35);
		frmDescartaveis.add(lblJaleco);

		JFormattedTextField txtdata = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdata.setHorizontalAlignment(SwingConstants.CENTER);
		txtdata.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdata.setBounds(133, 100, 134, 35);
		frmDescartaveis.add(txtdata);

		String hoje, data;
		hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
		txtdata.setText(hoje);

		txtjaleco = new JTextField();
		txtjaleco.setHorizontalAlignment(SwingConstants.CENTER);
		txtjaleco.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtjaleco.setColumns(10);
		txtjaleco.setBounds(128, 45, 96, 35);
		frmDescartaveis.add(txtjaleco);

		JLabel lbljaleco = new JLabel("");
		lbljaleco.setForeground(Color.WHITE);
		lbljaleco.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lbljaleco.setBounds(113, 493, 60, 35);
		frmDescartaveis.add(lbljaleco);

		JLabel lbltbranca = new JLabel("");
		lbltbranca.setForeground(Color.WHITE);
		lbltbranca.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lbltbranca.setBounds(349, 493, 60, 35);
		frmDescartaveis.add(lbltbranca);

		JLabel lbltazul = new JLabel("");
		lbltazul.setForeground(Color.WHITE);
		lbltazul.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lbltazul.setBounds(545, 493, 60, 35);
		frmDescartaveis.add(lbltazul);

		JLabel lbltverde = new JLabel("");
		lbltverde.setForeground(Color.WHITE);
		lbltverde.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lbltverde.setBounds(770, 493, 60, 35);
		frmDescartaveis.add(lbltverde);
		
		JLabel lbltvermelha = new JLabel("");
		lbltvermelha.setForeground(Color.WHITE);
		lbltvermelha.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lbltvermelha.setBounds(207, 530, 60, 35);
		frmDescartaveis.add(lbltvermelha);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblData.setBounds(60, 100, 61, 35);
		frmDescartaveis.add(lblData);

		JLabel lblTocaBranca = new JLabel("Touca Branca:");
		lblTocaBranca.setForeground(Color.WHITE);
		lblTocaBranca.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblTocaBranca.setBounds(275, 45, 157, 35);
		frmDescartaveis.add(lblTocaBranca);

		txtbranca = new JTextField();
		txtbranca.setHorizontalAlignment(SwingConstants.CENTER);
		txtbranca.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtbranca.setColumns(10);
		txtbranca.setBounds(429, 45, 96, 35);
		frmDescartaveis.add(txtbranca);

		JLabel lblTocaVerde = new JLabel("Touca Verde:");
		lblTocaVerde.setForeground(Color.GREEN);
		lblTocaVerde.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblTocaVerde.setBounds(580, 45, 142, 35);
		frmDescartaveis.add(lblTocaVerde);

		txtverde = new JTextField();
		txtverde.setHorizontalAlignment(SwingConstants.CENTER);
		txtverde.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtverde.setColumns(10);
		txtverde.setBounds(721, 45, 96, 35);
		frmDescartaveis.add(txtverde);

		JLabel lblToucaBranca = new JLabel("Touca Azul:");
		lblToucaBranca.setForeground(Color.CYAN);
		lblToucaBranca.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblToucaBranca.setBounds(303, 100, 126, 35);
		frmDescartaveis.add(lblToucaBranca);

		txtazul = new JTextField();
		txtazul.setHorizontalAlignment(SwingConstants.CENTER);
		txtazul.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtazul.setColumns(10);
		txtazul.setBounds(429, 100, 96, 35);
		frmDescartaveis.add(txtazul);
		
		txtVermelha = new JTextField();
		txtVermelha.setHorizontalAlignment(SwingConstants.CENTER);
		txtVermelha.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtVermelha.setColumns(10);
		txtVermelha.setBounds(721, 100, 96, 35);
		frmDescartaveis.add(txtVermelha);
		
		JLabel lblToucaVermelha = new JLabel("Touca Vermelha:");
		lblToucaVermelha.setForeground(Color.RED);
		lblToucaVermelha.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblToucaVermelha.setBounds(545, 100, 174, 35);
		frmDescartaveis.add(lblToucaVermelha);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 195, 782, 251);
		frmDescartaveis.add(scrollPane);

		tabdescartaveis = new JTable();
		tabdescartaveis.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabdescartaveis);

		JButton btcadastrar = new JButton("");
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtjaleco.getText().equals("") || txtbranca.getText().equals("") || txtverde.getText().equals("")
						|| txtazul.getText().equals("") || txtVermelha.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha os campos corretamente", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else {
					CRUD sql = new CRUD();

					String data;
					data = masc.convertDate(txtdata.getText());
					DescartaveisBean bean = new DescartaveisBean();

					bean.setJaleco(Integer.parseInt(txtjaleco.getText()));
					bean.setTbranca(Integer.parseInt(txtbranca.getText()));
					bean.setTverde(Integer.parseInt(txtverde.getText()));
					bean.setTazul(Integer.parseInt(txtazul.getText()));
					bean.setTvermelha(Integer.parseInt(txtVermelha.getText()));
					bean.setData(data);
					
					try {
						sql.SalvarDescartaveis(bean);
						JOptionPane.showMessageDialog(null, "Cadastro de Descartáveis Salvo com Sucesso");
						txtjaleco.setText("");
						txtbranca.setText("");
						txtverde.setText("");
						txtazul.setText("");
						txtVermelha.setText("");
						txtjaleco.grabFocus();

						try {
							for (DescartaveisBean list : sql.BuscaTotalDescartaveis(data)) {
								lbljaleco.setText(String.valueOf(list.getJaleco()));
								lbltbranca.setText(String.valueOf(list.getTbranca()));
								lbltverde.setText(String.valueOf(list.getTverde()));
								lbltazul.setText(String.valueOf(list.getTazul()));
								lbltvermelha.setText(String.valueOf(list.getTvermelha()));
							}
						} catch (Exception e1) {
							System.out.println(e1);
						}

						DefaultTableModel modelo = new DefaultTableModel();

						tabdescartaveis.setModel(modelo);

						modelo.addColumn("ID");
						modelo.addColumn("Jalecos");
						modelo.addColumn("Toucas Brancas");
						modelo.addColumn("Toucas Verdes");
						modelo.addColumn("Toucas Azuis");
						modelo.addColumn("Toucas Vermelhas");
						modelo.addColumn("Data");

						tabdescartaveis.getColumnModel().getColumn(0).setPreferredWidth(10);
						tabdescartaveis.getColumnModel().getColumn(1).setPreferredWidth(60);
						tabdescartaveis.getColumnModel().getColumn(2).setPreferredWidth(40);
						tabdescartaveis.getColumnModel().getColumn(3).setPreferredWidth(40);
						tabdescartaveis.getColumnModel().getColumn(4).setPreferredWidth(40);
						tabdescartaveis.getColumnModel().getColumn(5).setPreferredWidth(40);
						tabdescartaveis.getColumnModel().getColumn(6).setPreferredWidth(40);

						tabdescartaveis.setRowHeight(30);

						DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabdescartaveis
								.getDefaultRenderer(JLabel.class);
						renderer.setHorizontalAlignment(SwingConstants.CENTER);
						try {
							for (DescartaveisBean list : sql.BuscaDataDescartaveis(data)) {
								modelo.addRow(new Object[] { list.getId(), list.getJaleco(), list.getTbranca(),
										list.getTverde(), list.getTazul(),list.getTvermelha(), list.getData() });
							}
						} catch (Exception e1) {
							System.out.println(e1);
						}

					} catch (Exception e2) {
						// TODO: handle exception
						System.out.println(e2);
					}
				}
			}
		});
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btcadastrar
						.setRolloverIcon(new ImageIcon(Descartaveis.class.getResource("/imagens/botaocadgrande.png")));
			}
		});
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.setIcon(new ImageIcon(Descartaveis.class.getResource("/imagens/botaocadastrar.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(313, 135, 149, 63);
		frmDescartaveis.add(btcadastrar);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data;
				data = masc.convertDate(txtdata.getText());
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();

				tabdescartaveis.setModel(modelo);

				modelo.addColumn("ID");
				modelo.addColumn("Jalecos");
				modelo.addColumn("Toucas Brancas");
				modelo.addColumn("Toucas Verdes");
				modelo.addColumn("Toucas Azuis");
				modelo.addColumn("Toucas Vermelhas");
				modelo.addColumn("Data");

				tabdescartaveis.getColumnModel().getColumn(0).setPreferredWidth(10);
				tabdescartaveis.getColumnModel().getColumn(1).setPreferredWidth(60);
				tabdescartaveis.getColumnModel().getColumn(2).setPreferredWidth(40);
				tabdescartaveis.getColumnModel().getColumn(3).setPreferredWidth(40);
				tabdescartaveis.getColumnModel().getColumn(4).setPreferredWidth(40);
				tabdescartaveis.getColumnModel().getColumn(5).setPreferredWidth(40);
				tabdescartaveis.getColumnModel().getColumn(6).setPreferredWidth(40);

				tabdescartaveis.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabdescartaveis
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (DescartaveisBean list : sql.BuscaDataDescartaveis(data)) {
						modelo.addRow(new Object[] { list.getId(), list.getJaleco(), list.getTbranca(),
								list.getTverde(), list.getTazul(),list.getTvermelha(), list.getData() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
				try {
					for (DescartaveisBean list : sql.BuscaTotalDescartaveis(data)) {
						lbljaleco.setText(String.valueOf(list.getJaleco()));
						lbltbranca.setText(String.valueOf(list.getTbranca()));
						lbltverde.setText(String.valueOf(list.getTverde()));
						lbltazul.setText(String.valueOf(list.getTazul()));
						lbltvermelha.setText(String.valueOf(list.getTvermelha()));
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setIcon(new ImageIcon(Descartaveis.class.getResource("/imagens/lupa.png")));
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBounds(49, 135, 73, 63);
		frmDescartaveis.add(button);

		JLabel lblTotalDeUso = new JLabel("Total de Uso no Dia:");
		lblTotalDeUso.setForeground(Color.WHITE);
		lblTotalDeUso.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblTotalDeUso.setBounds(30, 459, 212, 35);
		frmDescartaveis.add(lblTotalDeUso);

		JLabel label_1 = new JLabel("Jaleco:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_1.setBounds(35, 493, 83, 35);
		frmDescartaveis.add(label_1);

		JLabel label_2 = new JLabel("Touca Branca:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_2.setBounds(198, 493, 149, 35);
		frmDescartaveis.add(label_2);

		JLabel label_3 = new JLabel("Touca Verde:");
		label_3.setForeground(Color.GREEN);
		label_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_3.setBounds(632, 493, 142, 35);
		frmDescartaveis.add(label_3);

		JLabel label_4 = new JLabel("Touca Azul:");
		label_4.setForeground(Color.CYAN);
		label_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_4.setBounds(421, 493, 126, 35);
		frmDescartaveis.add(label_4);
		
		
		
		JButton btsair = new JButton("");
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(Descartaveis.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setIcon(new ImageIcon(Descartaveis.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(487, 562, 149, 63);
		frmDescartaveis.add(btsair);
		
		JButton btexcluir = new JButton("");
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String data;
				data = masc.convertDate(txtdata.getText());
				int num = 0;
				num = Integer.parseInt(String
						.valueOf(tabdescartaveis.getModel().getValueAt(tabdescartaveis.getSelectedRow(), 0).toString()));

				CRUD sql = new CRUD();

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir este Cadastro?",
							"Atenção", JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						
						sql.ExcluirDescartaveis(num);
						JOptionPane.showMessageDialog(null,"Cadastro Excluído com Sucesso");
						DefaultTableModel modelo = new DefaultTableModel();

						tabdescartaveis.setModel(modelo);

						modelo.addColumn("ID");
						modelo.addColumn("Jalecos");
						modelo.addColumn("Toucas Brancas");
						modelo.addColumn("Toucas Verdes");
						modelo.addColumn("Toucas Azuis");
						modelo.addColumn("Data");

						tabdescartaveis.getColumnModel().getColumn(0).setPreferredWidth(10);
						tabdescartaveis.getColumnModel().getColumn(1).setPreferredWidth(100);
						tabdescartaveis.getColumnModel().getColumn(2).setPreferredWidth(40);
						tabdescartaveis.getColumnModel().getColumn(3).setPreferredWidth(40);
						tabdescartaveis.getColumnModel().getColumn(4).setPreferredWidth(40);
						tabdescartaveis.getColumnModel().getColumn(5).setPreferredWidth(40);

						tabdescartaveis.setRowHeight(30);

						DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabdescartaveis
								.getDefaultRenderer(JLabel.class);
						renderer.setHorizontalAlignment(SwingConstants.CENTER);
						try {
							for (DescartaveisBean list : sql.BuscaDataDescartaveis(data)) {
								modelo.addRow(new Object[] { list.getId(), list.getJaleco(), list.getTbranca(),
										list.getTverde(), list.getTazul(), list.getData() });
							}
						} catch (Exception e1) {
							System.out.println(e1);
						}
					}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				try {
					for (DescartaveisBean list : sql.BuscaTotalDescartaveis(data)) {
						lbljaleco.setText(String.valueOf(list.getJaleco()));
						lbltbranca.setText(String.valueOf(list.getTbranca()));
						lbltverde.setText(String.valueOf(list.getTverde()));
						lbltazul.setText(String.valueOf(list.getTazul()));
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btexcluir.setRolloverIcon(new ImageIcon(Descartaveis.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setIcon(new ImageIcon(Descartaveis.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(208, 562, 149, 63);
		frmDescartaveis.add(btexcluir);
						
						JLabel lblToucaVermelha_1 = new JLabel("Touca Vermelha:");
						lblToucaVermelha_1.setForeground(Color.RED);
						lblToucaVermelha_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
						lblToucaVermelha_1.setBounds(31, 530, 174, 35);
						frmDescartaveis.add(lblToucaVermelha_1);
								
								
								
								
								
										JLabel label = new JLabel("");
										label.setHorizontalAlignment(SwingConstants.CENTER);
										label.setIcon(new ImageIcon(
												Descartaveis.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
										label.setBounds(0, 0, 842, 638);
										frmDescartaveis.add(label);
		setLocationRelativeTo(null);
	}
}

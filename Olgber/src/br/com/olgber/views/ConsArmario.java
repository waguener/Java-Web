package br.com.olgber.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.CadArmarioBean;
import br.com.olgber.bean.CadArmarioFuncBean;
import br.com.olgber.bean.CadAtrasoSaidaBean;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.print.Book;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PageRanges;
import javax.swing.DefaultComboBoxModel;

public class ConsArmario extends JFrame {

	private JPanel contentPane;
	private JTable tabnomearmario;
	private JTextField txtnome;
	private JTextField txtnumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsArmario frame = new ConsArmario();
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
	public ConsArmario() {
		setResizable(false);
		setTitle("Arm\u00E1rios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 907, 654);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JComboBox combotipo = new JComboBox();
		combotipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();
				String tipo;
				tipo = combotipo.getSelectedItem().toString();

				tabnomearmario.setModel(modelo);

				modelo.addColumn("Nome");
				modelo.addColumn("Nº do Armário");
				modelo.addColumn("Data do Cadastro");
				modelo.addColumn("Tipo");
				modelo.addColumn("ID");

				tabnomearmario.getColumnModel().getColumn(0).setPreferredWidth(200);
				tabnomearmario.getColumnModel().getColumn(1).setPreferredWidth(10);
				tabnomearmario.getColumnModel().getColumn(2).setPreferredWidth(40);
				tabnomearmario.getColumnModel().getColumn(3).setPreferredWidth(40);
				tabnomearmario.getColumnModel().getColumn(4).setPreferredWidth(10);

				tabnomearmario.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabnomearmario
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadArmarioFuncBean list : sql.BuscaTipoArmario(tipo)) {
						modelo.addRow(new Object[] { list.getNome(), list.getNarmario(), list.getData(), list.getTipo(),
								list.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
				
			}
		});
		combotipo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		combotipo.setModel(new DefaultComboBoxModel(new String[] { "Feminino", "Masculino", "Temp.Feminino" }));
		combotipo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combotipo.setBounds(617, 72, 187, 35);
		contentPane.add(combotipo);

		JLabel lblBuscaPorNome = new JLabel("Buscar pelo Nome:");
		lblBuscaPorNome.setForeground(Color.WHITE);
		lblBuscaPorNome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblBuscaPorNome.setBounds(204, 13, 192, 35);
		contentPane.add(lblBuscaPorNome);

		txtnumber = new JTextField();
		txtnumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();
				int number;
				number = Integer.parseInt(txtnumber.getText());

				tabnomearmario.setModel(modelo);

				modelo.addColumn("Nome");
				modelo.addColumn("Nº do Armário");
				modelo.addColumn("Data do Cadastro");
				modelo.addColumn("Tipo");
				modelo.addColumn("ID");

				tabnomearmario.getColumnModel().getColumn(0).setPreferredWidth(200);
				tabnomearmario.getColumnModel().getColumn(1).setPreferredWidth(10);
				tabnomearmario.getColumnModel().getColumn(2).setPreferredWidth(40);
				tabnomearmario.getColumnModel().getColumn(3).setPreferredWidth(40);
				tabnomearmario.getColumnModel().getColumn(4).setPreferredWidth(10);

				tabnomearmario.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabnomearmario
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadArmarioFuncBean list : sql.BuscaNumberArmario(number)) {
						modelo.addRow(new Object[] { list.getNome(), list.getNarmario(), list.getData(), list.getTipo(),
								list.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		txtnumber.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtnumber.setColumns(10);
		txtnumber.setBounds(268, 72, 74, 35);
		contentPane.add(txtnumber);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 120, 877, 396);
		contentPane.add(scrollPane);

		tabnomearmario = new JTable();
		tabnomearmario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabnomearmario.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabnomearmario);

		txtnome = new JTextField();
		txtnome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();
				String nome;
				nome = txtnome.getText();

				tabnomearmario.setModel(modelo);

				modelo.addColumn("Nome");
				modelo.addColumn("Nº do Armário");
				modelo.addColumn("Data do Cadastro");
				modelo.addColumn("Tipo");
				modelo.addColumn("ID");

				tabnomearmario.getColumnModel().getColumn(0).setPreferredWidth(200);
				tabnomearmario.getColumnModel().getColumn(1).setPreferredWidth(10);
				tabnomearmario.getColumnModel().getColumn(2).setPreferredWidth(40);
				tabnomearmario.getColumnModel().getColumn(3).setPreferredWidth(40);
				tabnomearmario.getColumnModel().getColumn(4).setPreferredWidth(10);

				tabnomearmario.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabnomearmario
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadArmarioFuncBean list : sql.BuscaNomeArmario(nome)) {
						modelo.addRow(new Object[] { list.getNome(), list.getNarmario(), list.getData(), list.getTipo(),
								list.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		txtnome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtnome.setBounds(397, 13, 201, 35);
		contentPane.add(txtnome);
		txtnome.setColumns(10);

		JButton btsair = new JButton("");
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btsair.setRolloverIcon(new ImageIcon(ConsArmario.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btsair.setIcon(new ImageIcon(ConsArmario.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setBounds(649, 529, 155, 57);
		contentPane.add(btsair);

		JButton btexcluir = new JButton("");
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = 0;
				int num = 0;
				String tipo;
				tipo = tabnomearmario.getModel().getValueAt(tabnomearmario.getSelectedRow(), 3).toString();
				num = Integer
						.parseInt(tabnomearmario.getModel().getValueAt(tabnomearmario.getSelectedRow(), 1).toString());
				id = Integer
						.parseInt(tabnomearmario.getModel().getValueAt(tabnomearmario.getSelectedRow(), 4).toString());
				CRUD sql = new CRUD();

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir o Usuário deste Armário",
							"Atenção", JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						sql.ExcluirFuncArmario(id);
						CadArmarioBean bean2 = new CadArmarioBean();
						bean2.setNarmario(num);
						bean2.setTipo(tipo);
						bean2.setStatus("Disponivel");
						sql.AtualizaFunciArmario(bean2);
						JOptionPane.showMessageDialog(null, "Usuário Excluido com Sucesso");
						System.out.println(num);

						((DefaultTableModel) tabnomearmario.getModel()).removeRow(tabnomearmario.getSelectedRow());
						tabnomearmario.repaint();
						tabnomearmario.revalidate();
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}

			}
		});
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btexcluir.setRolloverIcon(
						new ImageIcon(ConsArmario.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setIcon(new ImageIcon(ConsArmario.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(109, 529, 155, 57);
		contentPane.add(btexcluir);

		JLabel lblBuscarPeloNmero = new JLabel("Buscar pelo N\u00FAmero:");
		lblBuscarPeloNmero.setForeground(Color.WHITE);
		lblBuscarPeloNmero.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblBuscarPeloNmero.setBounds(56, 72, 215, 35);
		contentPane.add(lblBuscarPeloNmero);

		JLabel lblBuscarPeloTipo = new JLabel("Buscar pelo Tipo:");
		lblBuscarPeloTipo.setForeground(Color.WHITE);
		lblBuscarPeloTipo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblBuscarPeloTipo.setBounds(435, 72, 177, 35);
		contentPane.add(lblBuscarPeloTipo);

		JButton btimpressao = new JButton("");
		btimpressao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btimpressao.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btimpressao.setRolloverIcon(new ImageIcon(ConsArmario.class.getResource("/imagens/imprimirbig.png")));
			}
		});
		btimpressao.setIcon(new ImageIcon(ConsArmario.class.getResource("/imagens/imprimir.png")));
		btimpressao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(tabnomearmario.getPrintable(PrintMode.FIT_WIDTH, null, null));
				job.setJobName("Nome do Trabalho de impressão");
				PrintRequestAttributeSet printAttribute = new HashPrintRequestAttributeSet();
				printAttribute.add(new PageRanges(1, 10));
				if (job.printDialog(printAttribute)) {
					try {
						job.print();
					} catch (PrinterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		btimpressao.setFocusable(false);
		btimpressao.setContentAreaFilled(false);
		btimpressao.setBorderPainted(false);
		btimpressao.setBounds(384, 529, 155, 57);
		contentPane.add(btimpressao);

		JLabel label = new JLabel("");
		label.setForeground(Color.WHITE);
		label.setIcon(new ImageIcon(
				ConsArmario.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(0, 0, 996, 623);
		contentPane.add(label);
	}
}

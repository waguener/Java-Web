package br.com.olgber.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.controle.util.Mascara;
import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.CadAtrasoSaidaBean;
import br.com.olgber.bean.CadVisitanteBean;
import br.com.olgber.bean.OcorrenciasBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.text.ParseException;
import java.awt.Color;
import javax.swing.JFormattedTextField;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;

public class ChecarOcorrencias extends JFrame {

	private JPanel contentPane;
	private JTable tbocorrencias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChecarOcorrencias frame = new ChecarOcorrencias();
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
	public ChecarOcorrencias() throws ParseException {
		setResizable(false);
		setTitle("Ocorr\u00EAncias");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		Mascara masc = new Mascara();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 135, 698, 244);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 392, 818, 150);
		contentPane.add(scrollPane_1);
		
		JTextArea txttexto = new JTextArea();
		scrollPane_1.setViewportView(txttexto);
		txttexto.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		txttexto.setLineWrap(true);
		txttexto.setWrapStyleWord(true);
		
		

		tbocorrencias = new JTable();
		tbocorrencias.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				txttexto.setText(tbocorrencias.getModel().getValueAt(tbocorrencias.getSelectedRow(), 1).toString());
			}
		});
		tbocorrencias.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tbocorrencias);

		JLabel label_1 = new JLabel("Busca Pela Data:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_1.setBounds(522, 0, 185, 35);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("De:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_2.setBounds(408, 48, 45, 35);
		contentPane.add(label_2);

		JFormattedTextField txtdatainicial = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatainicial.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatainicial.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatainicial.setBounds(453, 48, 134, 35);
		contentPane.add(txtdatainicial);

		JLabel label_3 = new JLabel("At\u00E9:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_3.setBounds(599, 48, 45, 35);
		contentPane.add(label_3);

		JFormattedTextField txtdatafinal = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatafinal.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatafinal.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatafinal.setBounds(652, 48, 134, 35);
		contentPane.add(txtdatafinal);

		JLabel lblBuscarTodas = new JLabel("Buscar Todas:");
		lblBuscarTodas.setForeground(Color.WHITE);
		lblBuscarTodas.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblBuscarTodas.setBounds(101, 48, 145, 35);
		contentPane.add(lblBuscarTodas);

		JButton btbuscartodas = new JButton("");
		btbuscartodas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btbuscartodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();

				tbocorrencias.setModel(modelo);

				modelo.addColumn("Responsável");
				modelo.addColumn("Ocorrência");
				modelo.addColumn("Data");
				modelo.addColumn("Id");

				tbocorrencias.getColumnModel().getColumn(0).setPreferredWidth(80);
				tbocorrencias.getColumnModel().getColumn(1).setPreferredWidth(150);
				tbocorrencias.getColumnModel().getColumn(2).setPreferredWidth(40);
				tbocorrencias.getColumnModel().getColumn(3).setPreferredWidth(5);

				tbocorrencias.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tbocorrencias
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (OcorrenciasBean list : sql.BuscaOcorrenciasTotal()) {
						modelo.addRow(new Object[] { list.getNome(), list.getTexto(), list.getData(), list.getId() });

					}

				} catch (Exception e) {
					System.out.println(e);

				}

			}
		});
		btbuscartodas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btbuscartodas.setRolloverIcon(
						new ImageIcon(ChecarOcorrencias.class.getResource("/imagens/botaobuscarbig.png")));
			}
		});
		btbuscartodas.setIcon(new ImageIcon(ChecarOcorrencias.class.getResource("/imagens/botaobuscar.png")));
		btbuscartodas.setFocusable(false);
		btbuscartodas.setContentAreaFilled(false);
		btbuscartodas.setBorderPainted(false);
		btbuscartodas.setBounds(101, 84, 145, 55);
		contentPane.add(btbuscartodas);

		JButton btbuscardata = new JButton("");
		btbuscardata.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btbuscardata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();

				String datainicio, datafinal;

				datainicio = masc.convertDate(txtdatainicial.getText());
				datafinal = masc.convertDate(txtdatafinal.getText());

				tbocorrencias.setModel(modelo);

				modelo.addColumn("Responsável");
				modelo.addColumn("Ocorrencia");
				modelo.addColumn("data");
				modelo.addColumn("Id");

				tbocorrencias.getColumnModel().getColumn(0).setPreferredWidth(80);
				tbocorrencias.getColumnModel().getColumn(1).setPreferredWidth(50);
				tbocorrencias.getColumnModel().getColumn(2).setPreferredWidth(50);
				tbocorrencias.getColumnModel().getColumn(3).setPreferredWidth(5);

				tbocorrencias.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tbocorrencias
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (OcorrenciasBean list : sql.BuscaOcorrenciasTotal()) {
						modelo.addRow(new Object[] { list.getNome(), list.getTexto(), list.getData(), list.getId() });

					}
				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		btbuscardata.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btbuscardata.setRolloverIcon(
						new ImageIcon(ChecarOcorrencias.class.getResource("/imagens/botaobuscarbig.png")));
			}
		});
		btbuscardata.setIcon(new ImageIcon(ChecarOcorrencias.class.getResource("/imagens/botaobuscar.png")));
		btbuscardata.setFocusable(false);
		btbuscardata.setContentAreaFilled(false);
		btbuscardata.setBorderPainted(false);
		btbuscardata.setBounds(544, 84, 145, 55);
		contentPane.add(btbuscardata);

		JButton btsair = new JButton("");
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(ConsVisitantes.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btsair.setIcon(new ImageIcon(ChecarOcorrencias.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(640, 555, 145, 55);
		contentPane.add(btsair);

		JButton btexcluir = new JButton("");
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int id = 0;

				id = Integer.parseInt(String
						.valueOf(tbocorrencias.getModel().getValueAt(tbocorrencias.getSelectedRow(), 3).toString()));

				CRUD sql = new CRUD();

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir este Cadastro?", "Atenção",
							JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						sql.ExcluirOcorrencias(id);
						JOptionPane.showMessageDialog(null, "Cadastro Excluído com Sucesso");
						((DefaultTableModel) tbocorrencias.getModel()).removeRow(tbocorrencias.getSelectedRow());
						tbocorrencias.repaint();
						tbocorrencias.revalidate();
						txttexto.setText("");
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btexcluir.setRolloverIcon(
						new ImageIcon(ChecarOcorrencias.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setIcon(new ImageIcon(ChecarOcorrencias.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(135, 555, 145, 55);
		contentPane.add(btexcluir);
		
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(
						ChecarOcorrencias.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
				label.setBounds(0, 0, 854, 637);
				contentPane.add(label);
	}
}

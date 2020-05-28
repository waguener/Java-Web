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
import br.com.olgber.bean.DescartaveisBean;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.ParseException;
import java.awt.Color;
import javax.swing.JFormattedTextField;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class ConsDescartavel extends JFrame {

	private JPanel contentPane;
	private JTable tabdescartavel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsDescartavel frame = new ConsDescartavel();
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
	public ConsDescartavel() throws ParseException {
		setTitle("Consulta de Uso dos Descart\u00E1veis");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Mascara masc = new Mascara();

		JLabel label_1 = new JLabel("Busca Pela Data:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_1.setBounds(23, 13, 185, 35);
		contentPane.add(label_1);

		JLabel lbljaleco = new JLabel("");
		lbljaleco.setForeground(Color.WHITE);
		lbljaleco.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lbljaleco.setBounds(95, 491, 60, 35);
		contentPane.add(lbljaleco);

		JLabel lbltbranca = new JLabel("");
		lbltbranca.setForeground(Color.WHITE);
		lbltbranca.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lbltbranca.setBounds(331, 491, 60, 35);
		contentPane.add(lbltbranca);

		JLabel lbltazul = new JLabel("");
		lbltazul.setForeground(Color.WHITE);
		lbltazul.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lbltazul.setBounds(527, 491, 60, 35);
		contentPane.add(lbltazul);

		JLabel lbltverde = new JLabel("");
		lbltverde.setForeground(Color.WHITE);
		lbltverde.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lbltverde.setBounds(752, 491, 60, 35);
		contentPane.add(lbltverde);
		
		JLabel lbltvermelha = new JLabel("");
		lbltvermelha.setForeground(Color.WHITE);
		lbltvermelha.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lbltvermelha.setBounds(200, 539, 60, 35);
		contentPane.add(lbltvermelha);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 150, 777, 294);
		contentPane.add(scrollPane);

		tabdescartavel = new JTable();
		tabdescartavel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabdescartavel);

		JLabel label_2 = new JLabel("De:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_2.setBounds(155, 48, 45, 35);
		contentPane.add(label_2);

		JFormattedTextField txtdatainicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatainicio.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatainicio.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatainicio.setBounds(200, 48, 134, 35);
		contentPane.add(txtdatainicio);

		JLabel label_3 = new JLabel("At\u00E9:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_3.setBounds(346, 48, 45, 35);
		contentPane.add(label_3);

		JFormattedTextField txtdatafinal = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatafinal.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatafinal.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatafinal.setBounds(393, 48, 134, 35);
		contentPane.add(txtdatafinal);

		JButton btbuscar = new JButton("");
		btbuscar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btbuscar.setRolloverIcon(new ImageIcon(ConsDescartavel.class.getResource("/imagens/botaobuscarbig.png")));
			}
		});
		btbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();

				String datainicio, datafinal;

				datainicio = masc.convertDate(txtdatainicio.getText());
				datafinal = masc.convertDate(txtdatafinal.getText());

				tabdescartavel.setModel(modelo);

				modelo.addColumn("ID");
				modelo.addColumn("Jalecos");
				modelo.addColumn("Toucas Brancas");
				modelo.addColumn("Toucas Verdes");
				modelo.addColumn("Toucas Azuis");
				modelo.addColumn("Toucas Vermelhas");
				modelo.addColumn("Data");

				tabdescartavel.getColumnModel().getColumn(0).setPreferredWidth(10);
				tabdescartavel.getColumnModel().getColumn(1).setPreferredWidth(60);
				tabdescartavel.getColumnModel().getColumn(2).setPreferredWidth(40);
				tabdescartavel.getColumnModel().getColumn(3).setPreferredWidth(40);
				tabdescartavel.getColumnModel().getColumn(4).setPreferredWidth(40);
				tabdescartavel.getColumnModel().getColumn(5).setPreferredWidth(40);
				tabdescartavel.getColumnModel().getColumn(6).setPreferredWidth(40);
				
				tabdescartavel.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabdescartavel
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (DescartaveisBean list : sql.BuscaDataConsDescartaveis(datainicio, datafinal)) {
						modelo.addRow(new Object[] { list.getId(), list.getJaleco(), list.getTbranca(),
								list.getTverde(), list.getTazul(),list.getTvermelha(), list.getData() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
				try {
					for (DescartaveisBean list : sql.BuscaDataTotalDescartaveis(datainicio, datafinal)) {
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
		btbuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btbuscar.setIcon(new ImageIcon(ConsDescartavel.class.getResource("/imagens/botaobuscar.png")));
		btbuscar.setFocusable(false);
		btbuscar.setContentAreaFilled(false);
		btbuscar.setBorderPainted(false);
		btbuscar.setBounds(439, 96, 164, 54);
		contentPane.add(btbuscar);

		JButton btsair = new JButton("");
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(ConsDescartavel.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btsair.setIcon(new ImageIcon(ConsDescartavel.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(681, 539, 149, 71);
		contentPane.add(btsair);

		JButton btbuscartodos = new JButton("");
		btbuscartodos.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btbuscartodos.setRolloverIcon(new ImageIcon(ConsDescartavel.class.getResource("/imagens/botaopesquisarbig.png")));
			}
		});
		btbuscartodos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btbuscartodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();

				tabdescartavel.setModel(modelo);

				modelo.addColumn("ID");
				modelo.addColumn("Jalecos");
				modelo.addColumn("Toucas Brancas");
				modelo.addColumn("Toucas Verdes");
				modelo.addColumn("Toucas Azuis");
				modelo.addColumn("Toucas Vermelhas");
				modelo.addColumn("Data");

				tabdescartavel.getColumnModel().getColumn(0).setPreferredWidth(10);
				tabdescartavel.getColumnModel().getColumn(1).setPreferredWidth(60);
				tabdescartavel.getColumnModel().getColumn(2).setPreferredWidth(40);
				tabdescartavel.getColumnModel().getColumn(3).setPreferredWidth(40);
				tabdescartavel.getColumnModel().getColumn(4).setPreferredWidth(40);
				tabdescartavel.getColumnModel().getColumn(5).setPreferredWidth(40);
				tabdescartavel.getColumnModel().getColumn(6).setPreferredWidth(40);
				
				tabdescartavel.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabdescartavel
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (DescartaveisBean list : sql.BuscaTotalConsDescartaveis()) {
						modelo.addRow(new Object[] { list.getId(), list.getJaleco(), list.getTbranca(),
								list.getTverde(), list.getTazul(),list.getTvermelha(), list.getData() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
				try {
					for (DescartaveisBean list : sql.BuscaTotalUsoDescartaveis()) {
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
		btbuscartodos.setIcon(new ImageIcon(ConsDescartavel.class.getResource("/imagens/botaopesquisar.png")));
		btbuscartodos.setFocusable(false);
		btbuscartodos.setContentAreaFilled(false);
		btbuscartodos.setBorderPainted(false);
		btbuscartodos.setBounds(118, 96, 164, 54);
		contentPane.add(btbuscartodos);

		JLabel lblTotalDeUso = new JLabel("Total de Uso:");
		lblTotalDeUso.setForeground(Color.WHITE);
		lblTotalDeUso.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblTotalDeUso.setBounds(12, 457, 212, 35);
		contentPane.add(lblTotalDeUso);

		JLabel label_5 = new JLabel("Jaleco:");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_5.setBounds(17, 491, 83, 35);
		contentPane.add(label_5);

		JLabel label_7 = new JLabel("Touca Branca:");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_7.setBounds(180, 491, 157, 35);
		contentPane.add(label_7);

		JLabel label_9 = new JLabel("Touca Azul:");
		label_9.setForeground(Color.CYAN);
		label_9.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_9.setBounds(403, 491, 126, 35);
		contentPane.add(label_9);

		JLabel label_11 = new JLabel("Touca Verde:");
		label_11.setForeground(Color.GREEN);
		label_11.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_11.setBounds(614, 491, 142, 35);
		contentPane.add(label_11);
		
		JLabel lblToucaVermelha = new JLabel("Touca Vermelha:");
		lblToucaVermelha.setForeground(Color.RED);
		lblToucaVermelha.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblToucaVermelha.setBounds(23, 539, 176, 35);
		contentPane.add(lblToucaVermelha);
				
				
				
						JLabel label = new JLabel("");
						label.setIcon(new ImageIcon(
								ConsDescartavel.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
						label.setBounds(0, 0, 842, 623);
						contentPane.add(label);
		setLocationRelativeTo(null);
		
	}
}

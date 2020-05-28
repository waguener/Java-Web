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
import br.com.olgber.bean.CadUsuarioBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.ParseException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;

public class ConsAtraso extends JFrame {

	private JPanel frmConsultaAtraso;
	private JTextField txtnome;
	private JTable tabfuncionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsAtraso frame = new ConsAtraso();
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
	public ConsAtraso() throws ParseException {
		setResizable(false);
		setTitle("Consultar Atrasos ou Sa\u00EDdas Atencipadas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 658);
		frmConsultaAtraso = new JPanel();
		frmConsultaAtraso.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmConsultaAtraso);
		frmConsultaAtraso.setLayout(null);
		setLocationRelativeTo(null);
		Mascara masc = new Mascara();

		JLabel lblBuscaPeloNome = new JLabel("Busca Pelo Nome:");
		lblBuscaPeloNome.setForeground(Color.WHITE);
		lblBuscaPeloNome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblBuscaPeloNome.setBounds(100, 25, 185, 35);
		frmConsultaAtraso.add(lblBuscaPeloNome);

		JLabel lblBuscaPelaData = new JLabel("Busca Pela Data:");
		lblBuscaPelaData.setForeground(Color.WHITE);
		lblBuscaPelaData.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblBuscaPelaData.setBounds(519, 25, 185, 35);
		frmConsultaAtraso.add(lblBuscaPelaData);

		txtnome = new JTextField();
		txtnome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtnome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();
				String nome;
				nome = txtnome.getText();
				
				tabfuncionario.setModel(modelo);

				modelo.addColumn("Nome");
				modelo.addColumn("Turno");
				modelo.addColumn("data");
				modelo.addColumn("hora");
				modelo.addColumn("Tipo");
				modelo.addColumn("Motivo");
				modelo.addColumn("ID");

				tabfuncionario.getColumnModel().getColumn(0).setPreferredWidth(80);
				tabfuncionario.getColumnModel().getColumn(1).setPreferredWidth(50);
				tabfuncionario.getColumnModel().getColumn(2).setPreferredWidth(50);
				tabfuncionario.getColumnModel().getColumn(3).setPreferredWidth(50);
				tabfuncionario.getColumnModel().getColumn(4).setPreferredWidth(80);
				tabfuncionario.getColumnModel().getColumn(5).setPreferredWidth(100);
				tabfuncionario.getColumnModel().getColumn(6).setPreferredWidth(10);

				tabfuncionario.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabfuncionario
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadAtrasoSaidaBean list : sql.BuscaNomeAtraso(nome)) {
						modelo.addRow(new Object[] { list.getNome(), list.getTurno(), list.getData(), list.getHora(),
								list.getTipo(), list.getMotivo(),list.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		txtnome.setBounds(60, 73, 259, 35);
		frmConsultaAtraso.add(txtnome);
		txtnome.setColumns(10);

		JLabel lblDe = new JLabel("De:");
		lblDe.setForeground(Color.WHITE);
		lblDe.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblDe.setBounds(405, 73, 45, 35);
		frmConsultaAtraso.add(lblDe);

		JFormattedTextField txtdatainicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatainicio.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatainicio.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatainicio.setBounds(450, 73, 134, 35);
		frmConsultaAtraso.add(txtdatainicio);

		JLabel lblAt = new JLabel("At\u00E9:");
		lblAt.setForeground(Color.WHITE);
		lblAt.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblAt.setBounds(596, 73, 45, 35);
		frmConsultaAtraso.add(lblAt);

		JFormattedTextField txtdatafinal = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatafinal.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatafinal.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatafinal.setBounds(649, 73, 134, 35);
		frmConsultaAtraso.add(txtdatafinal);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 188, 758, 351);
		frmConsultaAtraso.add(scrollPane);

		tabfuncionario = new JTable();
		tabfuncionario.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabfuncionario);

		JButton btnBuscar = new JButton("");
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setFocusable(false);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setIcon(new ImageIcon(ConsAtraso.class.getResource("/imagens/botaobuscar.png")));
		btnBuscar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btnBuscar.setRolloverIcon(new ImageIcon(ConsAtraso.class.getResource("/imagens/botaobuscarbig.png")));
			}
		});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();

				String datainicio, datafinal;

				datainicio = masc.convertDate(txtdatainicio.getText());
				datafinal = masc.convertDate(txtdatafinal.getText());

				tabfuncionario.setModel(modelo);

				modelo.addColumn("Nome");
				modelo.addColumn("Turno");
				modelo.addColumn("data");
				modelo.addColumn("hora");
				modelo.addColumn("Tipo");
				modelo.addColumn("Motivo");
				modelo.addColumn("ID");

				tabfuncionario.getColumnModel().getColumn(0).setPreferredWidth(80);
				tabfuncionario.getColumnModel().getColumn(1).setPreferredWidth(50);
				tabfuncionario.getColumnModel().getColumn(2).setPreferredWidth(50);
				tabfuncionario.getColumnModel().getColumn(3).setPreferredWidth(50);
				tabfuncionario.getColumnModel().getColumn(4).setPreferredWidth(80);
				tabfuncionario.getColumnModel().getColumn(5).setPreferredWidth(100);
				tabfuncionario.getColumnModel().getColumn(6).setPreferredWidth(10);

				tabfuncionario.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabfuncionario
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadAtrasoSaidaBean list : sql.BuscaDataAtraso(datainicio, datafinal)) {
						modelo.addRow(new Object[] { list.getNome(), list.getTurno(), list.getData(), list.getHora(),
								list.getTipo(), list.getMotivo(),list.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		btnBuscar.setBounds(525, 121, 164, 54);
		frmConsultaAtraso.add(btnBuscar);

		JButton btsair = new JButton("");
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btsair.setRolloverIcon(new ImageIcon(ConsAtraso.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btsair.setIcon(new ImageIcon(ConsAtraso.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setBounds(622, 539, 149, 71);
		frmConsultaAtraso.add(btsair);
		
		JButton btexcluir = new JButton("");
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int num = 0;

				num = Integer.parseInt(String
						.valueOf(tabfuncionario.getModel().getValueAt(tabfuncionario.getSelectedRow(), 6).toString()));

				CRUD sql = new CRUD();

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir este Cadastro?",
							"Atenção", JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						sql.ExcluirAtraso(num);
						JOptionPane.showMessageDialog(null,"Cadastro Excluído com Sucesso");
						((DefaultTableModel) tabfuncionario.getModel()).removeRow(tabfuncionario.getSelectedRow());  
		                tabfuncionario.repaint();  
		                tabfuncionario.revalidate(); 
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btexcluir.setRolloverIcon(new ImageIcon(ConsAtraso.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setIcon(new ImageIcon(ConsAtraso.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(100, 539, 149, 71);
		frmConsultaAtraso.add(btexcluir);
		
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(
						ConsAtraso.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
				label.setBounds(0, 0, 862, 635);
				frmConsultaAtraso.add(label);
	}
}

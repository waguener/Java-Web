package br.com.olgber.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.CadChaveBean;
import br.com.olgber.bean.CadVisitanteBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadChave extends JFrame {

	private JPanel contentPane;
	private JTable tabcadchave;
	private JTextField txtchave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadChave frame = new CadChave();
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
	public CadChave() {
		setTitle("Cadastro de Chaves");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 833, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 161, 691, 334);
		contentPane.add(scrollPane);

		tabcadchave = new JTable();
		tabcadchave.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		tabcadchave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setViewportView(tabcadchave);

		txtchave = new JTextField();
		txtchave.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtchave.setColumns(10);
		txtchave.setBounds(165, 44, 527, 35);
		contentPane.add(txtchave);

		CRUD sql = new CRUD();

		DefaultTableModel modelo = new DefaultTableModel();

		tabcadchave.setModel(modelo);

		modelo.addColumn("ID");
		modelo.addColumn("Chave");

		tabcadchave.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabcadchave.getColumnModel().getColumn(1).setPreferredWidth(120);

		tabcadchave.setRowHeight(30);

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabcadchave.getDefaultRenderer(JLabel.class);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			for (CadChaveBean list : sql.BuscaChave()) {
				modelo.addRow(new Object[] { list.getId(), list.getNome() });

			}

		} catch (Exception e) {
			System.out.println(e);

		}
		JLabel label_1 = new JLabel("Chave:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_1.setBounds(94, 44, 73, 35);
		contentPane.add(label_1);

		JButton btsair = new JButton("");
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chaves tela;
				try {
					tela = new Chaves();
					tela.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				dispose();
			}
		});
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btsair.setRolloverIcon(new ImageIcon(CadChave.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setIcon(new ImageIcon(CadChave.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(529, 516, 149, 63);
		contentPane.add(btsair);

		JButton btexcluir = new JButton("");
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = 0;

				num = Integer.parseInt(
						String.valueOf(tabcadchave.getModel().getValueAt(tabcadchave.getSelectedRow(), 0).toString()));

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir o Usuário deste Armário",
							"Atenção", JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						CRUD sql = new CRUD();
						sql.ExcluirCadChave(num);
						JOptionPane.showMessageDialog(null, "Chave Excluida com Sucesso");
						((DefaultTableModel) tabcadchave.getModel()).removeRow(tabcadchave.getSelectedRow());  
		                tabcadchave.repaint();  
		                tabcadchave.revalidate();
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
				
			}
		});
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btexcluir.setRolloverIcon(new ImageIcon(CadChave.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setIcon(new ImageIcon(CadChave.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(333, 516, 146, 63);
		contentPane.add(btexcluir);

		JButton btcadastrar = new JButton("");
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtchave.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else {
					CRUD sql = new CRUD();

					CadChaveBean bean = new CadChaveBean();

					bean.setNome(txtchave.getText());
					try {
						sql.SalvarCadchave(bean);
						JOptionPane.showMessageDialog(null, "Chave Cadastrada com Sucesso");
						txtchave.setText("");
						txtchave.grabFocus();
						
						DefaultTableModel modelo = new DefaultTableModel();

						tabcadchave.setModel(modelo);

						modelo.addColumn("ID");
						modelo.addColumn("Chave");

						tabcadchave.getColumnModel().getColumn(0).setPreferredWidth(10);
						tabcadchave.getColumnModel().getColumn(1).setPreferredWidth(120);

						tabcadchave.setRowHeight(30);

						DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabcadchave
								.getDefaultRenderer(JLabel.class);
						renderer.setHorizontalAlignment(SwingConstants.CENTER);
						try {
							for (CadChaveBean list : sql.BuscaChave()) {
								modelo.addRow(new Object[] { list.getId(), list.getNome() });

							}

						} catch (Exception e3) {
							System.out.println(e3);

						}
					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
			}
		});
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btcadastrar.setRolloverIcon(new ImageIcon(CadChave.class.getResource("/imagens/botaocadgrande.png")));
			}
		});
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.setIcon(new ImageIcon(CadChave.class.getResource("/imagens/botaocadastrar.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(128, 516, 146, 63);
		contentPane.add(btcadastrar);

		JLabel lblChavesCadastradas = new JLabel("Chaves Cadastradas:");
		lblChavesCadastradas.setForeground(Color.WHITE);
		lblChavesCadastradas.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblChavesCadastradas.setBounds(30, 123, 215, 35);
		contentPane.add(lblChavesCadastradas);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(
				CadChave.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(0, 0, 837, 623);
		contentPane.add(label);
		setLocationRelativeTo(null);
	}
}

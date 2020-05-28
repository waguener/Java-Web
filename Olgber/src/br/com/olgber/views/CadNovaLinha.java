package br.com.olgber.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.CadAdmBean;
import br.com.olgber.bean.NovaLinhaBean;

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
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadNovaLinha extends JFrame {

	private JPanel contentPane;
	private JTextField txtlinha;
	private JTable tablinha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadNovaLinha frame = new CadNovaLinha();
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
	public CadNovaLinha() {
		setTitle("Cadastro de Linhas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblLinha = new JLabel("Linha:");
		lblLinha.setForeground(Color.WHITE);
		lblLinha.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblLinha.setBounds(52, 72, 75, 35);
		contentPane.add(lblLinha);

		txtlinha = new JTextField();
		txtlinha.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtlinha.setColumns(10);
		txtlinha.setBounds(125, 72, 472, 35);
		contentPane.add(txtlinha);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 137, 742, 313);
		contentPane.add(scrollPane);

		tablinha = new JTable();
		tablinha.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tablinha);
		CRUD sql = new CRUD();

		DefaultTableModel modelo = new DefaultTableModel();

		tablinha.setModel(modelo);

		modelo.addColumn("ID");
		modelo.addColumn("Linha");

		tablinha.getColumnModel().getColumn(0).setPreferredWidth(10);
		tablinha.getColumnModel().getColumn(1).setPreferredWidth(150);

		tablinha.setRowHeight(30);

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tablinha.getDefaultRenderer(JLabel.class);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			for (NovaLinhaBean list : sql.BuscaNovaLinha()) {
				modelo.addRow(new Object[] { list.getId(), list.getLinha() });
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}

		JButton btcadastrar = new JButton("");
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CRUD sql = new CRUD();

				NovaLinhaBean bean = new NovaLinhaBean();

				bean.setLinha(txtlinha.getText());
				try {
					sql.SalvarNovaLinha(bean);
					JOptionPane.showMessageDialog(null, "Linha Cadastrada com Sucesso");
					txtlinha.setText("");
					txtlinha.grabFocus();
					DefaultTableModel modelo = new DefaultTableModel();

					tablinha.setModel(modelo);

					modelo.addColumn("ID");
					modelo.addColumn("Linha");

					tablinha.getColumnModel().getColumn(0).setPreferredWidth(10);
					tablinha.getColumnModel().getColumn(1).setPreferredWidth(150);

					tablinha.setRowHeight(30);

					DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tablinha
							.getDefaultRenderer(JLabel.class);
					renderer.setHorizontalAlignment(SwingConstants.CENTER);
					try {
						for (NovaLinhaBean list : sql.BuscaNovaLinha()) {
							modelo.addRow(new Object[] { list.getId(), list.getLinha() });
						}
					} catch (Exception e1) {
						System.out.println(e1);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btcadastrar
						.setRolloverIcon(new ImageIcon(CadNovaLinha.class.getResource("/imagens/botaocadgrande.png")));
			}
		});
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.setIcon(new ImageIcon(CadNovaLinha.class.getResource("/imagens/botaocadastrar.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(609, 61, 141, 59);
		contentPane.add(btcadastrar);

		JButton btexcluir = new JButton("");
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = 0;

				id = Integer.parseInt(
						String.valueOf(tablinha.getModel().getValueAt(tablinha.getSelectedRow(), 0).toString()));

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir esta Linha?", "Atenção",
							JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						sql.ExcluirNovaLinha(id);
						JOptionPane.showMessageDialog(null, "Usuário Excluido com Sucesso");
						((DefaultTableModel) tablinha.getModel()).removeRow(tablinha.getSelectedRow());
						tablinha.repaint();
						tablinha.revalidate();
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btexcluir.setRolloverIcon(
						new ImageIcon(CadNovaLinha.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setIcon(new ImageIcon(CadNovaLinha.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(203, 484, 141, 59);
		contentPane.add(btexcluir);

		JButton btsair = new JButton("");
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControleVans tela;
				try {
					tela = new ControleVans();
					tela.setVisible(true);
					dispose();
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(CadNovaLinha.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setIcon(new ImageIcon(CadNovaLinha.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(515, 484, 141, 59);
		contentPane.add(btsair);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(
				CadNovaLinha.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(-24, 0, 866, 623);
		contentPane.add(label);
	}
}

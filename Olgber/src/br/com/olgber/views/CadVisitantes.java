package br.com.olgber.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.controle.util.Limpar;
import br.com.controle.util.Mascara;
import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.CadVisitanteBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class CadVisitantes extends JFrame {

	private JPanel frmCadVisitas;
	private JTextField txtnome;
	private JTextField txtempresa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadVisitantes frame = new CadVisitantes();
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
	public CadVisitantes() throws ParseException {
		setResizable(false);
		setTitle("Cadastro de Visitantes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 658);
		frmCadVisitas = new JPanel();
		frmCadVisitas.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmCadVisitas);
		frmCadVisitas.setLayout(null);
		setLocationRelativeTo(null);
		Mascara masc = new Mascara();
		Limpar limpar = new Limpar();

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblNome.setBounds(145, 112, 71, 35);
		frmCadVisitas.add(lblNome);

		JLabel lblRg = new JLabel("RG:");
		lblRg.setForeground(Color.WHITE);
		lblRg.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblRg.setBounds(145, 171, 50, 35);
		frmCadVisitas.add(lblRg);

		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setForeground(Color.WHITE);
		lblEmpresa.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblEmpresa.setBounds(145, 233, 92, 35);
		frmCadVisitas.add(lblEmpresa);

		JLabel lblMotivoDaVisita = new JLabel("Autoriza\u00E7\u00E3o:");
		lblMotivoDaVisita.setForeground(Color.WHITE);
		lblMotivoDaVisita.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblMotivoDaVisita.setBounds(142, 349, 167, 35);
		frmCadVisitas.add(lblMotivoDaVisita);

		JTextArea txttexto = new JTextArea();
		txttexto.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txttexto.setBounds(273, 351, 406, 35);
		frmCadVisitas.add(txttexto);

		txtnome = new JTextField();
		txtnome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtnome.setBounds(251, 115, 472, 35);
		frmCadVisitas.add(txtnome);
		txtnome.setColumns(10);

		JFormattedTextField txtrg = new JFormattedTextField();
		txtrg.setText("");
		txtrg.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtrg.setHorizontalAlignment(SwingConstants.LEFT);
		txtrg.setBounds(251, 171, 246, 35);
		frmCadVisitas.add(txtrg);

		txtempresa = new JTextField();
		txtempresa.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtempresa.setBounds(251, 233, 347, 35);
		frmCadVisitas.add(txtempresa);
		txtempresa.setColumns(10);

		JFormattedTextField txtdata = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdata.setHorizontalAlignment(SwingConstants.CENTER);
		txtdata.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdata.setBounds(251, 290, 139, 35);
		frmCadVisitas.add(txtdata);

		String hoje;
		hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
		txtdata.setText(hoje);

		JButton btcadastrar = new JButton("");
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();

				if (txtnome.getText().equals("") || txtrg.getText().equals("") || txtempresa.getText().equals("")
						|| txttexto.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else {

					boolean testeLogico = false;
					try {
						testeLogico = sql.BuscaVisitante(txtnome.getText());
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
						System.out.println("erro 1 " + e1);
					}
					if (testeLogico == false) {
						String data;
						data = masc.convertDate(txtdata.getText());

						CadVisitanteBean bean = new CadVisitanteBean();

						bean.setNome(txtnome.getText());
						bean.setRg(txtrg.getText());
						bean.setEmpresa(txtempresa.getText());
						bean.setData(data);
						bean.setMotivo(txttexto.getText());
						Date dia = null;
						String dataTexto = new String(txtdata.getText());
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

						try {
							format.setLenient(false);
							dia = format.parse(dataTexto);
							try {
								sql.Visitante(bean);
								JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
								txtnome.setText("");
								txtrg.setText("");
								txtempresa.setText("");
								txttexto.setText("");
								txtnome.grabFocus();
							} catch (Exception e) {
								System.out.println(e);
							}
						} catch (ParseException e) {
							JOptionPane.showMessageDialog(null, "Data inválida. Tente novamente!", "AVISO",
									JOptionPane.WARNING_MESSAGE);
							txtdata.setText("");
							txtdata.grabFocus();
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Este Visitante já está Cadastrado no Sistema \n Verifique em VISITANTES CADASTRADOS ");
					}
				}
			}
		});
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btcadastrar
						.setRolloverIcon(new ImageIcon(CadVisitantes.class.getResource("/imagens/botaocadgrande.png")));

			}
		});
		btcadastrar.setIcon(new ImageIcon(CadVisitantes.class.getResource("/imagens/botaocadastrar.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(75, 520, 139, 51);
		frmCadVisitas.add(btcadastrar);

		JButton btsair = new JButton("");
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btsair.setRolloverIcon(new ImageIcon(CadVisitantes.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setIcon(new ImageIcon(CadVisitantes.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(595, 523, 149, 48);
		frmCadVisitas.add(btsair);

		JLabel lblData = new JLabel("Data:");
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblData.setBounds(145, 290, 62, 35);
		frmCadVisitas.add(lblData);

		JLabel lblAntesDeCadastrar = new JLabel("Verifique antes se o Cadastro j\u00E1 foi feito.");
		lblAntesDeCadastrar.setForeground(Color.WHITE);
		lblAntesDeCadastrar.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblAntesDeCadastrar.setBounds(216, 25, 427, 35);
		frmCadVisitas.add(lblAntesDeCadastrar);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(
				CadVisitantes.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(0, 0, 853, 623);
		frmCadVisitas.add(label);
	}
}

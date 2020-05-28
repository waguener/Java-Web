package br.com.olgber.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.com.controle.util.Conexao;
import br.com.olgber.bean.AdmBean;
import br.com.olgber.bean.CadAdmBean;
import br.com.olgber.bean.CadArmarioBean;
import br.com.olgber.bean.CadArmarioFuncBean;
import br.com.olgber.bean.CadAtrasoSaidaBean;
import br.com.olgber.bean.CadChaveBean;
import br.com.olgber.bean.CadEntrevista;
import br.com.olgber.bean.CadUsuarioBean;
import br.com.olgber.bean.CadVansBean;
import br.com.olgber.bean.CadVeiDocumentoBean;
import br.com.olgber.bean.CadVeiculoBean;
import br.com.olgber.bean.CadVisitanteBean;
import br.com.olgber.bean.ChavesBean;
import br.com.olgber.bean.DescartaveisBean;
import br.com.olgber.bean.LoginBean;
import br.com.olgber.bean.MovVeiculoBean;
import br.com.olgber.bean.NovaLinhaBean;
import br.com.olgber.bean.OcorrenciasBean;
import br.com.olgber.bean.VisitasCadastradasBean;

public class CRUD {

	private String INSERT_CADUSUARIO = ("INSERT INTO cadusuario (nome,senha,tipo) values (?,?,?)");

	private String INSERT_HISTVISITAS = ("INSERT INTO histvisitas ( nome, rg, empresa, data, datavisita, horaentrada, horasaida, motivo) values(?,?,?,?,?,?,?,?)");

	private String INSERT_OCORRENCIAS = ("INSERT INTO ocorrencias (nome,texto,data) values (?,?,?)");

	private String INSERT_CADARMARIOFUNCIONARIO = ("INSERT INTO cadamariofuncionario (Narmario,nome,data,tipo) values (?,?,?,?)");

	private String INSERT_ARMARIO = ("INSERT INTO armario (Narmario,status,Chavereserva,tipo,data,Situacao) values (?,?,?,?,?,?)");

	private String INSERT_VEICULO = ("INSERT INTO cadveiculo (marca,modelo,placa,ano,gasolina,gas) values (?,?,?,?,?,?)");

	private String INSERT_DOCVEICULO = ("INSERT INTO cadveidocumento (veiculo,placa,tipo,numdocumento) values (?,?,?,?)");

	private String INSERT_MOVVEICULO = ("INSERT INTO movveiculo (veiculo,funcionario,destino,horasaida,horaretorno,datasaida,dataretorno,kminicio,kmfinal,gasolina,gas) values (?,?,?,?,?,?,?,?,?,?,?)");

	private String INSERT_VISITANTE = ("INSERT INTO cadvisitantes (nome,rg,empresa,data,motivo) values (?,?,?,?,?)");

	private String INSERT_LOGIN = ("INSERT INTO login (nome,data,hora) values (?,?,?)");

	private String INSERT_ATRASO = ("INSERT INTO cadatrso (nome, turno, data, hora, tipo, motivo) values (?,?,?,?,?,?)");

	private String INSERT_VANS = ("INSERT INTO cadvans (motorista, data, linha, tipo, hora, qtdpassageiro, responsavel) values (?,?,?,?,?,?,?)");

	private String INSERT_CHAVE = ("INSERT INTO chaves (funcionario,data,retirada,entrega,tipo,observacao) values (?,?,?,?,?,?)");

	private String INSERT_ADM = ("INSERT INTO adm (nome,entrada,saida,data) values (?,?,?,?)");

	private String INSERT_CADCHAVE = ("INSERT INTO cadchave (nome) values (?)");

	private String INSERT_DESCARTAVEIS = ("INSERT INTO descartaveis (jaleco,tbranca,tverde,tazul,tvermelha,data) values (?,?,?,?,?,?)");

	private String INSERT_FUNCADM = ("INSERT INTO cadadm (nome,cargo) values (?,?)");
	
	private String INSERT_NOVALINHA = ("INSERT INTO novalinha (linha) values (?)");
	
	private String INSERT_ENTREVISTA = ("INSERT INTO cadentrevista (nome,rg,data,entrada,saida) values (?,?,?,?,?)");

	private String SELECT_USUARIO = ("SELECT * FROM cadusuario WHERE nome = ? and senha = ? ");
	
	private String SELECT_VISITANTE = ("SELECT * FROM cadvisitantes WHERE nome = ");

	private String SELECT_NUMARMARIO = ("SELECT * FROM armario WHERE Narmario = ?");

	private String SELECT_SENHA = ("SELECT * FROM cadusuario WHERE senha = ");

	private String SELECT_ARMARIO = ("SELECT * FROM armario ORDER BY tipo,Narmario");

	private String SELECT_FUNCADM = ("SELECT * FROM cadadm ORDER BY nome");

	private String SELECT_USUARIOCAD = ("SELECT * FROM cadusuario");
	
	private String SELECT_NOVALINHA = (" SELECT * FROM novalinha ORDER BY linha");

	private String SELECT_ARMDISPONIVEL = ("SELECT * FROM armario WHERE status = ? AND tipo = ? ORDER BY Narmario ");

	private String SELECT_VEICULO = ("SELECT * FROM cadveiculo ");

	private String SELECT_MOVTOTAL = ("SELECT * FROM movveiculo ORDER BY id desc");

	private String SELECT_PLACA = ("SELECT * FROM cadveiculo WHERE modelo = ");

	private String SELECT_VEIPLACA = ("SELECT * FROM cadveiculo WHERE modelo = ");

	private String SELECT_KM = ("SELECT * FROM movveiculo WHERE veiculo = ");

	private String SELECT_MOVVEICULO = ("SELECT * FROM movveiculo WHERE veiculo = ? ORDER BY id desc");

	private String SELECT_TIPOCHAVE = (" SELECT * FROM chaves WHERE tipo = ? ORDER BY id desc");

	private String SELECT_GASOLINA = ("SELECT * FROM cadveiculo WHERE modelo =");

	private String SELECT_GAS = ("SELECT * FROM cadveiculo WHERE modelo =");

	private String SELECT_DATAATRASO = ("SELECT * FROM cadatrso WHERE data BETWEEN ? AND ? ORDER BY id desc");

	private String SELECT_DATACONSDESCARTAVEIS = ("SELECT * FROM descartaveis WHERE data BETWEEN ? AND ? ORDER BY id desc");

	private String SELECT_ADMDATA = ("SELECT * FROM adm WHERE data BETWEEN ? AND ? ORDER BY id desc");

	private String SELECT_DATAVANS = ("SELECT * FROM cadvans WHERE data BETWEEN ? AND ? ORDER BY id desc");
	
	private String SELECT_DATAENTREVISTA = ("SELECT * FROM cadentrevista WHERE data BETWEEN ? AND ? ORDER BY id desc");

	private String SELECT_DATAOCORRENCIAS = ("SELECT * FROM ocorrencias WHERE data BETWEEN ? AND ?");

	private String SELECT_MOVDATA = ("SELECT * FROM movveiculo WHERE datasaida BETWEEN ? AND ? ORDER BY id desc");

	private String SELECT_NOMEATRASO = ("SELECT * FROM cadatrso WHERE nome LIKE  ? ORDER BY id desc");

	private String SELECT_DATASVISITAS = ("SELECT * FROM histvisitas WHERE data = ? ORDER BY id desc");

	private String SELECT_NOMEADM = ("SELECT * FROM adm WHERE nome LIKE ? ORDER BY id desc");

	private String SELECT_VANMOTORISTA = ("SELECT * FROM cadvans WHERE motorista LIKE ? ORDER BY id desc");
	
	private String SELECT_NOMEENTREVISTA = ("SELECT * FROM cadentrevista WHERE nome LIKE ? ORDER BY id desc");
	
	private String SELECT_MOVFUNCIONARIO = ("SELECT DISTINCT funcionario FROM movveiculo order by funcionario");
	
	private String SELECT_MOVDESTINO2 = ("SELECT DISTINCT destino FROM movveiculo ORDER BY destino");

	private String SELECT_VANLINHA = ("SELECT * FROM cadvans WHERE linha = ? ORDER BY id desc");

	private String SELECT_NOMEVISITA = ("SELECT * FROM histvisitas WHERE nome LIKE  ? ORDER BY id desc");

	private String SELECT_NOMEEMPRESA = ("SELECT * FROM histvisitas WHERE empresa LIKE  ? ORDER BY id desc");

	private String SELECT_NOMEARMARIO = ("SELECT * FROM cadamariofuncionario WHERE nome LIKE  ? ORDER BY tipo  ");
	
	private String SELECT_NOMEVISITACADASTRO = ("SELECT * FROM cadvisitantes WHERE nome LIKE ? ORDER BY nome");

	private String SELECT_NUMBERARMARIO = ("SELECT * FROM cadamariofuncionario WHERE Narmario LIKE ?");

	private String SELECT_TIPOARMARIO = ("SELECT * FROM cadamariofuncionario WHERE tipo = ? ORDER BY Narmario");

	private String SELECT_DOCPLACA = ("SELECT * FROM cadveidocumento ORDER BY veiculo");
	
	private String SELECT_NOMECHAVE = (" SELECT * FROM chaves WHERE funcionario LIKE ? ORDER BY id desc");

	private String SELECT_NUMEROARMARIO = ("SELECT * FROM cadamariofuncionario WHERE Narmario = ? ");

	private String SELECT_DATAADM = ("SELECT * FROM adm WHERE data = ");

	private String SELECT_DATACHAVE = ("SELECT * FROM chaves WHERE data = ? ORDER BY id desc ");
	
	private String SELECT_ENTREVISTA = ("SELECT * FROM cadentrevista WHERE data = ? ORDER BY id desc ");
	
	private String SELECT_BUSCARTODAS = ("SELECT * FROM chaves ORDER BY ID DESC");

	private String SELECT_VISITAHOJE = ("SELECT * FROM histvisitas WHERE datavisita = ? ORDER BY id desc");
	
	private String SELECT_MOVHOJE = ("SELECT * FROM movveiculo WHERE datasaida = ? ORDER BY id desc");

	private String SELECT_DATADESCARTAVEIS = ("SELECT * FROM descartaveis WHERE data =");

	private String SELECT_TOTALDESCARTAVEIS = ("SELECT SUM(jaleco), SUM(tbranca), SUM(tverde), SUM(tazul) FROM descartaveis WHERE data = ");

	private String SELECT_DATATOTALDESCARTAVEIS = ("SELECT SUM(jaleco), SUM(tbranca), SUM(tverde), SUM(tazul), SUM(tvermelha) FROM descartaveis WHERE data BETWEEN ? AND ? ");

	private String SELECT_TOTALDEUSODESCARATVEIS = ("SELECT SUM(jaleco), SUM(tbranca), SUM(tverde), SUM(tazul), SUM(tvermelha) FROM descartaveis ORDER BY id desc");

	private String SELECT_FUNCARMARIOTOTAL = ("SELECT * FROM cadamariofuncionario ORDER BY tipo");

	private String SELECT_DATAVISITA = ("SELECT * FROM histvisitas WHERE datavisita BETWEEN ? AND ? ORDER BY id desc");

	private String SELECT_VISITAS = ("SELECT * FROM cadvisitantes ORDER BY nome");

	private String SELECT_VISITANTES = ("SELECT * FROM histvisitas ORDER BY id desc");

	private String SELECT_OCORRENCIAS = ("SELECT * FROM ocorrencias ORDER BY id desc");

	private String SELECT_IDUSUARIO = ("SELECT MAX(ID) FROM cadusuario ");

	private String SELECT_LASTARMARIO = ("SELECT MAX(Narmario) FROM armario where tipo =");

	private String SELECT_LOGIN = ("SELECT * FROM login ORDER BY id ");

	private String SELECT_CHAVETIPO = ("SELECT * FROM chaves");

	private String SELECT_USUARIOTIPO = ("SELECT Tipo FROM cadusuario WHERE Nome = ");

	private String SELECT_CADCHAVE = ("SELECT * FROM cadchave");

	private String SELECT_CHAVE = ("SELECT * FROM cadchave");

	private String SELECT_TOTALCONSDESCARTAVEIS = ("SELECT * FROM descartaveis ORDER BY id desc");

	private String UPDATE_COMBUSTIVEL = ("UPDATE cadveiculo SET gasolina = ? ,gas = ? WHERE modelo = ? ");

	private String UPDATE_USUSARIO = ("UPDATE cadusuario SET Nome = ? ,Senha = ? ,Tipo = ? WHERE ID = ?");

	private String UPDATE_FUNCIARMARIO = ("UPDATE armario SET status = ? WHERE Narmario = ? AND tipo = ?");
	
	private String UPDATE_FUNCIARMARIOCADASTRO = ("UPDATE armario SET status = ? WHERE id = ? ");

	private String UPDATE_ARMARIO = ("UPDATE armario SET status = ?,Chavereserva = ?,tipo = ?,Data = ?, Situacao = ? WHERE id = ?");

	private String UPDATE_ADM = ("UPDATE adm SET nome = ?, data = ?, entrada = ?, saida = ? WHERE id = ? ");
	
	private String UPDATE_MOVVEICULO = ("UPDATE movveiculo SET horaretorno = ?, dataretorno = ?, kmfinal = ?, gasolina = ?, gas = ? WHERE id = ?");

	private String UPDATE_VISITAS = ("UPDATE histvisitas SET horasaida = ? where id = ? ");

	private String UPDATE_CHAVE = ("UPDATE chaves SET funcionario = ?, data = ?, retirada = ?, entrega = ?, tipo = ?, observacao = ? where id = ?");
	
	private String UPDATE_ENTREVISTA = ("UPDATE cadentrevista SET nome = ?, rg = ?, data = ?, entrada = ?, saida = ? where id = ?");

	private String DELETE_USUARIO = ("DELETE FROM cadusuario WHERE id = ");

	private String DELETE_ARMARIO = ("DELETE FROM armario WHERE id = ");

	private String DELETE_FUNCARMARIO = ("DELETE FROM cadamariofuncionario WHERE id = ");

	private String DELETE_VAN = (" DELETE FROM cadvans WHERE id = ");

	private String DELETE_VISITAS = (" DELETE FROM cadvisitantes WHERE nome = ");

	private String DELETE_CADCHAVE = ("DELETE FROM cadchave WHERE id = ");

	private String DELETE_HISTVISITAS = ("DELETE FROM histvisitas WHERE id = ");
	
	private String DELETE_DOCUMENTO = ("DELETE FROM cadveidocumento WHERE id = ");
	
	private String DELETE_ENTREVISTA = ("DELETE FROM cadentrevista WHERE id = ");

	private String DELETE_ADM = ("DELETE FROM adm WHERE id =");

	private String DELETE_DESCARTAVEIS = ("DELETE FROM descartaveis WHERE id =");

	private String DELETE_CONSCHAVES = ("DELETE FROM chaves WHERE id = ");

	private String DELETE_FUNCADM = ("DELETE FROM cadadm WHERE id =");

	private String DELETE_ATRASO = ("DELETE FROM cadatrso WHERE id =");
	
	private String DELETE_NOVALINHA = ("DELETE FROM novalinha WHERE id =");
	
	private String DELETE_MOVVEICULO = ("DELETE FROM movveiculo WHERE id = ");
	
	private String DELETE_OCORRENCIAS = ("DELETE FROM ocorrencias WHERE id = ");

	/**
	 * private Connection getConnection() throws ClassNotFoundException,
	 * SQLException {
	 * 
	 * ConnectionFactory con = new ConnectionFactory(); return
	 * con.getConnection(); }
	 **/

	private Connection getConexao() {

		Conexao con = new Conexao();

		return con.getConexao();

	}

	public void SalvaUsuario(CadUsuarioBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_CADUSUARIO);

		stmt.setString(1, bean.getNome());
		stmt.setString(2, bean.getSenha());
		stmt.setString(3, bean.getTipo());
		stmt.execute();
		stmt.close();

	}

	public void SalvaHistVisita(VisitasCadastradasBean bean)
			throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_HISTVISITAS);

		stmt.setString(1, bean.getNome());
		stmt.setString(2, bean.getRg());
		stmt.setString(3, bean.getEmpresa());
		stmt.setString(4, bean.getData());
		stmt.setString(5, bean.getDatavisita());
		stmt.setString(6, bean.getHoraentrada());
		stmt.setString(7, bean.getHorasaida());
		stmt.setString(8, bean.getMotivo());
		stmt.execute();
		stmt.close();
	}
	
	public void SalvaEntrevista(CadEntrevista bean)
			throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_ENTREVISTA);

		stmt.setString(1, bean.getNome());
		stmt.setString(2, bean.getRg());
		stmt.setString(3, bean.getData());
		stmt.setString(4, bean.getEntrada());
		stmt.setString(5, bean.getSaida());
		stmt.execute();
		stmt.close();
	}

	public void SalvaArmFuncionario(CadArmarioFuncBean bean)
			throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_CADARMARIOFUNCIONARIO);

		stmt.setInt(1, bean.getNarmario());
		stmt.setString(2, bean.getNome());
		stmt.setString(3, bean.getData());
		stmt.setString(4, bean.getTipo());
		stmt.execute();
		stmt.close();
	}

	public void SalvaArmario(CadArmarioBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_ARMARIO);

		stmt.setInt(1, bean.getNarmario());
		stmt.setString(2, bean.getStatus());
		stmt.setString(3, bean.getChavereserva());
		stmt.setString(4, bean.getTipo());
		stmt.setString(5, bean.getData());
		stmt.setString(6, bean.getSituacao());
		stmt.execute();
		stmt.close();
	}

	public void SalvaVeiculo(CadVeiculoBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_VEICULO);

		stmt.setString(1, bean.getMarca());
		stmt.setString(2, bean.getModelo());
		stmt.setString(3, bean.getPlaca());
		stmt.setInt(4, bean.getAno());
		stmt.setString(5, bean.getGasolina());
		stmt.setString(6, bean.getGas());
		stmt.execute();
		stmt.close();
	}

	public void SalvaDocumento(CadVeiDocumentoBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_DOCVEICULO);

		stmt.setString(1, bean.getVeiculo());
		stmt.setString(2, bean.getPlaca());
		stmt.setString(3, bean.getTipo());
		stmt.setString(4, bean.getNumdocumento());
		
		stmt.execute();
		stmt.close();
	}

	public void SalvaMovVeiculo(MovVeiculoBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_MOVVEICULO);

		stmt.setString(1, bean.getVeiculo());
		stmt.setString(2, bean.getFuncionario());
		stmt.setString(3, bean.getDestino());
		stmt.setString(4, bean.getHorasaida());
		stmt.setString(5, bean.getHoraretorno());
		stmt.setString(6, bean.getDatasaida());
		stmt.setString(7, bean.getDataretorno());
		stmt.setString(8, bean.getKminicio());
		stmt.setString(9, bean.getKmfinal());
		stmt.setString(10, bean.getGasolina());
		stmt.setString(11, bean.getGas());
		
		stmt.execute();
		stmt.close();
	}

	public void Visitante(CadVisitanteBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_VISITANTE);

		stmt.setString(1, bean.getNome());
		stmt.setString(2, bean.getRg());
		stmt.setString(3, bean.getEmpresa());
		stmt.setString(4, bean.getData());
		stmt.setString(5, bean.getMotivo());
		stmt.execute();
		stmt.close();
	}

	public void Atraso(CadAtrasoSaidaBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_ATRASO);

		stmt.setString(1, bean.getNome());
		stmt.setString(2, bean.getTurno());
		stmt.setString(3, bean.getData());
		stmt.setString(4, bean.getHora());
		stmt.setString(5, bean.getTipo());
		stmt.setString(6, bean.getMotivo());
		stmt.execute();
		stmt.close();
	}

	public void Ocorrencias(OcorrenciasBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_OCORRENCIAS);

		stmt.setString(1, bean.getNome());
		stmt.setString(2, bean.getTexto());
		stmt.setString(3, bean.getData());
		stmt.execute();
		stmt.close();
	}

	public void Login(LoginBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_LOGIN);

		stmt.setString(1, bean.getNome());
		stmt.setString(2, bean.getData());
		stmt.setString(3, bean.getHora());
		stmt.execute();
		stmt.close();
	}

	public void Vans(CadVansBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_VANS);

		stmt.setString(1, bean.getMotorista());
		stmt.setString(2, bean.getData());
		stmt.setString(3, bean.getLinha());
		stmt.setString(4, bean.getTipo());
		stmt.setString(5, bean.getHora());
		stmt.setInt(6, bean.getQtdpassageiros());
		stmt.setString(7, bean.getResponsavel());
		stmt.execute();
		stmt.close();
	}

	public void SalvarChaves(ChavesBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_CHAVE);

		stmt.setString(1, bean.getFuncionario());
		stmt.setString(2, bean.getData());
		stmt.setString(3, bean.getRetirada());
		stmt.setString(4, bean.getEntrega());
		stmt.setString(5, bean.getTipo());
		stmt.setString(6, bean.getObservacao());

		stmt.execute();
		stmt.close();
	}

	public void SalvarAdm(AdmBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_ADM);

		stmt.setString(1, bean.getNome());
		stmt.setString(2, bean.getEntrada());
		stmt.setString(3, bean.getSaida());
		stmt.setString(4, bean.getData());
		stmt.execute();
		stmt.close();
	}

	public void SalvarCadchave(CadChaveBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_CADCHAVE);

		stmt.setString(1, bean.getNome());
		stmt.execute();
		stmt.close();
	}

	public void SalvarDescartaveis(DescartaveisBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_DESCARTAVEIS);

		stmt.setInt(1, bean.getJaleco());
		stmt.setInt(2, bean.getTbranca());
		stmt.setInt(3, bean.getTverde());
		stmt.setInt(4, bean.getTazul());
		stmt.setInt(5, bean.getTvermelha());
		stmt.setString(6, bean.getData());
		stmt.execute();
		stmt.close();
	}

	public void SalvarFuncAdm(CadAdmBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_FUNCADM);

		stmt.setString(1, bean.getNome());
		stmt.setString(2, bean.getCargo());
		stmt.execute();
		stmt.close();
	}
	
	public void SalvarNovaLinha(NovaLinhaBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(INSERT_NOVALINHA);

		stmt.setString(1, bean.getLinha());
		
		stmt.execute();
		stmt.close();
	}

	// Passar como parametro NOME E SENHA
	public boolean BuscaUsuario(String nome, String senha) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_USUARIO);
		stmt.setString(1, nome);
		stmt.setString(2, senha);
		ResultSet rs = stmt.executeQuery();

		Boolean teste = null;

		if (rs.next()) {
			teste = true;
			rs.close();
		} else {
			teste = false;

		}
		rs.close();
		return teste;
	}
	
	public boolean BuscaVisitante(String nome) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_VISITANTE + "'" + nome + "'");
		
		
		ResultSet rs = stmt.executeQuery();

		Boolean teste = null;

		if (rs.next()) {
			teste = true;
			rs.close();
		} else {
			teste = false;

		}
		rs.close();
		return teste;
	}

	public boolean BuscaNumArmario(int numero) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_NUMARMARIO);
		stmt.setInt(1, numero);

		ResultSet rs = stmt.executeQuery();

		Boolean teste = null;

		if (rs.next()) {
			teste = true;

		} else {
			teste = false;

		}
		rs.close();
		return teste;

	}

	public boolean BuscaUser(String user) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_SENHA + "'" + user + "'");
		ResultSet rs = stmt.executeQuery();

		Boolean teste = true;

		while (!rs.next()) {
			teste = false;
			System.out.println("falso");

		}
		rs.close();
		return teste;
	}

	public String Resultnome(String usuario) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_USUARIO + "'" + usuario + "'");
		ResultSet rs = stmt.executeQuery();
		String nome = "";

		int cod = 0;
		while (rs.next()) {
			cod = rs.getInt(1);
			nome = rs.getString(2);

		}
		rs.close();
		return nome;
	}

	public String Resultado(String usuario) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_USUARIO + "'" + usuario + "'");
		ResultSet rs = stmt.executeQuery();
		String senha = "";

		int cod = 0;
		while (rs.next()) {
			cod = rs.getInt(1);
			senha = rs.getString(3);

		}
		rs.close();
		return senha;
	}

	public String Resultipo(String usuario) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_USUARIO + "'" + usuario + "'");
		ResultSet rs = stmt.executeQuery();
		String tipo = "";

		int cod = 0;
		while (rs.next()) {
			cod = rs.getInt(1);
			tipo = rs.getString(4);

		}
		rs.close();
		return tipo;
	}

	public List<CadArmarioBean> BuscaArm() throws ClassNotFoundException, SQLException {

		List<CadArmarioBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_ARMARIO);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CadArmarioBean bean = new CadArmarioBean();

			bean.setId(rs.getInt(1));
			bean.setNarmario(rs.getInt(2));
			bean.setStatus(rs.getString(3));
			bean.setTipo(rs.getString(4));
			bean.setChavereserva(rs.getString(5));
			bean.setData(rs.getString(6));
			bean.setSituacao(rs.getString(7));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<CadAdmBean> BuscaFuncAdm() throws ClassNotFoundException, SQLException {

		List<CadAdmBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_FUNCADM);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CadAdmBean bean = new CadAdmBean();

			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setCargo(rs.getString(3));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<CadChaveBean> BuscaChave() throws ClassNotFoundException, SQLException {

		List<CadChaveBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_CADCHAVE);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CadChaveBean bean = new CadChaveBean();

			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<CadVeiculoBean> BuscaVeiculo() throws ClassNotFoundException, SQLException {
		List<CadVeiculoBean> lista = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_VEICULO);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			CadVeiculoBean bean = new CadVeiculoBean();
			bean.setId(rs.getInt(1));
			bean.setMarca(rs.getString(2));
			bean.setModelo(rs.getString(3));
			bean.setPlaca(rs.getString(4));
			bean.setAno(rs.getInt(5));
			lista.add(bean);

		}
		rs.close();
		return lista;

	}

	public List<ChavesBean> BuscaTipoChave() throws ClassNotFoundException, SQLException {
		List<ChavesBean> lista = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_CHAVETIPO);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ChavesBean bean = new ChavesBean();
			bean.setId(rs.getInt(1));
			bean.setTipo(rs.getString(6));

			lista.add(bean);

		}
		rs.close();
		return lista;

	}

	public List<CadUsuarioBean> BuscaUsuarioTotal() throws ClassNotFoundException, SQLException {

		List<CadUsuarioBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_USUARIOCAD);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			CadUsuarioBean bean = new CadUsuarioBean();
			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setSenha(rs.getString(3));
			bean.setTipo(rs.getString(4));
			list.add(bean);

		}
		rs.close();
		return list;

	}
	
	public List<NovaLinhaBean> BuscaNovaLinha() throws ClassNotFoundException, SQLException {

		List<NovaLinhaBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_NOVALINHA);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			NovaLinhaBean bean = new NovaLinhaBean();
			bean.setId(rs.getInt(1));
			bean.setLinha(rs.getString(2));
			
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<MovVeiculoBean> BuscaMovTotal() throws ClassNotFoundException, SQLException {
		List<MovVeiculoBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_MOVTOTAL);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			MovVeiculoBean bean = new MovVeiculoBean();

			bean.setId(rs.getInt(1));
			bean.setVeiculo(rs.getString(2));
			bean.setFuncionario(rs.getString(3));
			bean.setDestino(rs.getString(4));
			bean.setHorasaida(rs.getString(5));
			bean.setHoraretorno(rs.getString(6));
			bean.setDatasaida(rs.getString(7));
			bean.setDataretorno(rs.getString(8));
			bean.setKminicio(rs.getString(9));
			bean.setKmfinal(rs.getString(10));
			bean.setGasolina(rs.getString(11));

			list.add(bean);

		}
		rs.close();
		return list;
	}

	public List<CadArmarioBean> Buscadisponivel(String status, String tipo)
			throws ClassNotFoundException, SQLException {

		List<CadArmarioBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_ARMDISPONIVEL);
		stmt.setString(1, status);
		stmt.setString(2, tipo);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			CadArmarioBean bean = new CadArmarioBean();

			bean.setId(rs.getInt(1));
			bean.setNarmario(rs.getInt(2));
			bean.setStatus(rs.getString(3));
			bean.setTipo(rs.getString(4));
			bean.setChavereserva(rs.getString(5));
			bean.setData(rs.getString(6));
			bean.setSituacao(rs.getString(7));
			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public List<CadAtrasoSaidaBean> BuscaDataAtraso(String datainicio, String datafinal)
			throws ClassNotFoundException, SQLException {

		List<CadAtrasoSaidaBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_DATAATRASO);
		stmt.setString(1, datainicio);
		stmt.setString(2, datafinal);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			CadAtrasoSaidaBean bean = new CadAtrasoSaidaBean();

			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setTurno(rs.getString(3));
			bean.setData(rs.getString(4));
			bean.setHora(rs.getString(5));
			bean.setTipo(rs.getString(6));
			bean.setMotivo(rs.getString(7));
			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public List<DescartaveisBean> BuscaDataConsDescartaveis(String datainicio, String datafinal)
			throws ClassNotFoundException, SQLException {

		List<DescartaveisBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_DATACONSDESCARTAVEIS);
		stmt.setString(1, datainicio);
		stmt.setString(2, datafinal);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			DescartaveisBean bean = new DescartaveisBean();

			bean.setId(rs.getInt(1));
			bean.setJaleco(rs.getInt(2));
			bean.setTbranca(rs.getInt(3));
			bean.setTverde(rs.getInt(4));
			bean.setTazul(rs.getInt(5));
			bean.setTvermelha(rs.getInt(6));
			bean.setData(rs.getString(7));

			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public List<AdmBean> BuscaDataAdm(String datainicio, String datafinal) throws ClassNotFoundException, SQLException {

		List<AdmBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_ADMDATA);
		stmt.setString(1, datainicio);
		stmt.setString(2, datafinal);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			AdmBean bean = new AdmBean();

			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setData(rs.getString(3));
			bean.setEntrada(rs.getString(4));
			bean.setSaida(rs.getString(5));

			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public List<AdmBean> BuscaDataAdm(String hoje) throws ClassNotFoundException, SQLException {

		List<AdmBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_DATAADM + "'" + hoje + "'");

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			AdmBean bean = new AdmBean();

			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setData(rs.getString(3));
			bean.setEntrada(rs.getString(4));
			bean.setSaida(rs.getString(5));

			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public List<ChavesBean> BuscaDataChave(String data) throws ClassNotFoundException, SQLException {

		List<ChavesBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_DATACHAVE);
		stmt.setString(1, data);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			ChavesBean bean = new ChavesBean();

			bean.setId(rs.getInt(1));
			bean.setFuncionario(rs.getString(2));
			bean.setData(rs.getString(3));
			bean.setRetirada(rs.getString(4));
			bean.setEntrega(rs.getString(5));
			bean.setTipo(rs.getString(6));
			bean.setObservacao(rs.getString(7));

			lista.add(bean);

		}
		rs.close();
		return lista;
	}
	
	public List<CadEntrevista> BuscaDataEntrevista(String data) throws ClassNotFoundException, SQLException {

		List<CadEntrevista> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_ENTREVISTA);
		stmt.setString(1, data);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			CadEntrevista bean = new CadEntrevista();

			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setRg(rs.getString(3));
			bean.setData(rs.getString(4));
			bean.setEntrada(rs.getString(5));
			bean.setSaida(rs.getString(6));
			

			lista.add(bean);

		}
		rs.close();
		return lista;
	}
	
	public List<ChavesBean> BuscarTodas() throws ClassNotFoundException, SQLException {

		List<ChavesBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_BUSCARTODAS);
		
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			ChavesBean bean = new ChavesBean();

			bean.setId(rs.getInt(1));
			bean.setFuncionario(rs.getString(2));
			bean.setData(rs.getString(3));
			bean.setRetirada(rs.getString(4));
			bean.setEntrega(rs.getString(5));
			bean.setTipo(rs.getString(6));
			bean.setObservacao(rs.getString(7));

			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public List<VisitasCadastradasBean> BuscaVisitaHoje(String data) throws ClassNotFoundException, SQLException {

		List<VisitasCadastradasBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_VISITAHOJE);
		stmt.setString(1, data);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			VisitasCadastradasBean bean = new VisitasCadastradasBean();

			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setRg(rs.getString(3));
			bean.setEmpresa(rs.getString(4));
			bean.setData(rs.getString(5));
			bean.setDatavisita(rs.getString(6));
			bean.setHoraentrada(rs.getString(7));
			bean.setHorasaida(rs.getString(8));
			bean.setMotivo(rs.getString(9));

			lista.add(bean);

		}
		rs.close();
		return lista;
	}
	public List<MovVeiculoBean> BuscaMovHoje(String data) throws ClassNotFoundException, SQLException {
		List<MovVeiculoBean> lista = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_MOVHOJE);
		stmt.setString(1, data);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			MovVeiculoBean bean = new MovVeiculoBean();

			bean.setId(rs.getInt(1));
			bean.setVeiculo(rs.getString(2));
			bean.setFuncionario(rs.getString(3));
			bean.setDestino(rs.getString(4));
			bean.setHorasaida(rs.getString(5));
			bean.setHoraretorno(rs.getString(6));
			bean.setDatasaida(rs.getString(7));
			bean.setDataretorno(rs.getString(8));
			bean.setKminicio(rs.getString(9));
			bean.setKmfinal(rs.getString(10));
			bean.setGasolina(rs.getString(11));
			bean.setGas(rs.getString(12));
			lista.add(bean);

		}
		rs.close();
		return lista;
	}
	
	public List<CadVeiDocumentoBean> BuscaDoc() throws ClassNotFoundException, SQLException {
		List<CadVeiDocumentoBean> lista = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_DOCPLACA);
		
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			CadVeiDocumentoBean bean = new CadVeiDocumentoBean();

			bean.setId(rs.getInt(1));
			bean.setVeiculo(rs.getString(2));
			bean.setPlaca(rs.getString(3));
			bean.setTipo(rs.getString(4));
			bean.setNumdocumento(rs.getString(5));
			
			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public List<DescartaveisBean> BuscaDataDescartaveis(String hoje) throws ClassNotFoundException, SQLException {

		List<DescartaveisBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_DATADESCARTAVEIS + "'" + hoje + "'");

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			DescartaveisBean bean = new DescartaveisBean();

			bean.setId(rs.getInt(1));
			bean.setJaleco(rs.getInt(2));
			bean.setTbranca(rs.getInt(3));
			bean.setTverde(rs.getInt(4));
			bean.setTazul(rs.getInt(5));
			bean.setTvermelha(rs.getInt(6));
			bean.setData(rs.getString(7));

			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public List<DescartaveisBean> BuscaTotalDescartaveis(String hoje) throws ClassNotFoundException, SQLException {

		List<DescartaveisBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_TOTALDESCARTAVEIS + "'" + hoje + "'");

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			DescartaveisBean bean = new DescartaveisBean();

			bean.setJaleco(rs.getInt(1));
			bean.setTbranca(rs.getInt(2));
			bean.setTverde(rs.getInt(3));
			bean.setTazul(rs.getInt(4));

			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public List<DescartaveisBean> BuscaTotalUsoDescartaveis() throws ClassNotFoundException, SQLException {

		List<DescartaveisBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_TOTALDEUSODESCARATVEIS);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			DescartaveisBean bean = new DescartaveisBean();

			bean.setJaleco(rs.getInt(1));
			bean.setTbranca(rs.getInt(2));
			bean.setTverde(rs.getInt(3));
			bean.setTazul(rs.getInt(4));
			bean.setTvermelha(rs.getInt(5));
			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public List<CadVansBean> BuscaDataVans(String datainicio, String datafinal)
			throws ClassNotFoundException, SQLException {

		List<CadVansBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_DATAVANS);
		stmt.setString(1, datainicio);
		stmt.setString(2, datafinal);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			CadVansBean bean = new CadVansBean();

			bean.setId(rs.getInt(1));
			bean.setMotorista(rs.getString(2));
			bean.setData(rs.getString(3));
			bean.setLinha(rs.getString(4));
			bean.setTipo(rs.getString(5));
			bean.setHora(rs.getString(6));
			bean.setQtdpassageiros(rs.getInt(7));
			bean.setResponsavel(rs.getString(8));
			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public List<CadEntrevista> BuscaDataEntrevista2(String datainicio, String datafinal)
			throws ClassNotFoundException, SQLException {

		List<CadEntrevista> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_DATAENTREVISTA);
		stmt.setString(1, datainicio);
		stmt.setString(2, datafinal);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			CadEntrevista bean = new CadEntrevista();

			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setRg(rs.getString(3));
			bean.setData(rs.getString(4));
			bean.setEntrada(rs.getString(5));
			bean.setSaida(rs.getString(6));
			
			lista.add(bean);

		}
		rs.close();
		return lista;
	}
	
	public List<OcorrenciasBean> BuscaDataOcorrencias(String datainicio, String datafinal)
			throws ClassNotFoundException, SQLException {

		List<OcorrenciasBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_DATAOCORRENCIAS);
		stmt.setString(1, datainicio);
		stmt.setString(2, datafinal);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			OcorrenciasBean bean = new OcorrenciasBean();

			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setTexto(rs.getString(3));
			bean.setData(rs.getString(4));
			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public List<MovVeiculoBean> BuscaDataMov(String datainicio, String datafinal)
			throws ClassNotFoundException, SQLException {

		List<MovVeiculoBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_MOVDATA);
		stmt.setString(1, datainicio);
		stmt.setString(2, datafinal);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			MovVeiculoBean bean = new MovVeiculoBean();

			bean.setId(rs.getInt(1));
			bean.setVeiculo(rs.getString(2));
			bean.setFuncionario(rs.getString(3));
			bean.setDestino(rs.getString(4));
			bean.setHorasaida(rs.getString(5));
			bean.setHoraretorno(rs.getString(6));
			bean.setDatasaida(rs.getString(7));
			bean.setDataretorno(rs.getString(8));
			bean.setKminicio(rs.getString(9));
			bean.setKmfinal(rs.getString(10));
			bean.setGasolina(rs.getString(11));

			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public String BuscaPlaca(String model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_PLACA + "'" + model + "'");

		ResultSet rs = stmt.executeQuery();
		String result = "";
		int num;
		while (rs.next()) {
			num = rs.getInt(1);
			result = rs.getString(4);

		}
		rs.close();
		return result;
	}

	public String BuscaGasolina(String model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_GASOLINA + "'" + model + "'");

		ResultSet rs = stmt.executeQuery();
		String result = "";
		int num;
		while (rs.next()) {
			num = rs.getInt(1);
			result = rs.getString(6);

		}
		rs.close();
		return result;
	}

	public String BuscaGas(String model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_GAS + "'" + model + "'");

		ResultSet rs = stmt.executeQuery();
		String result = "";
		int num;
		while (rs.next()) {
			num = rs.getInt(1);
			result = rs.getString(7);

		}
		rs.close();
		return result;
	}

	public boolean Veiculo(String model) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_PLACA + "'" + model + "'");
		ResultSet rs = stmt.executeQuery();

		boolean teste = true;

		if (!rs.next()) {
			teste = false;
			System.out.println("falso");

		}
		System.out.println("true");
		rs.close();
		return teste;
	}

	public String BuscaKM(String model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_KM + "'" + model + "'");

		ResultSet rs = stmt.executeQuery();
		String result = "";
		int num;
		while (rs.next()) {
			num = rs.getInt(1);
			result = rs.getString(10);

		}
		rs.close();
		return result;
	}

	public String BuscaUsuarioTipo(String model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_USUARIOTIPO + "'" + model + "'");

		ResultSet rs = stmt.executeQuery();
		int num;
		String tipo = "";
		while (rs.next()) {

			// num = rs.getInt(1);
			tipo = rs.getString(1);

		}
		rs.close();
		return tipo;
	}

	public int IDUsuario() throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_IDUSUARIO);

		ResultSet rs = stmt.executeQuery();

		int num = 0;
		while (rs.next()) {
			num = rs.getInt(1);

		}
		rs.close();
		return num;
	}

	public String BuscaLogin() throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_LOGIN);

		ResultSet rs = stmt.executeQuery();

		String nome = null;
		int id;
		while (rs.next()) {
			id = rs.getInt(1);
			nome = rs.getString(2);

		}
		rs.close();
		return nome;
	}

	public String LastArmario(String model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_LASTARMARIO + "'" + model + "'");

		ResultSet rs = stmt.executeQuery();

		String num = "";

		while (rs.next()) {

			num = rs.getString(1);

		}
		rs.close();
		return num;
	}

	public List<CadAtrasoSaidaBean> BuscaNomeAtraso(String nome) throws ClassNotFoundException, SQLException {
		List<CadAtrasoSaidaBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_NOMEATRASO);
		stmt.setString(1, nome + "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CadAtrasoSaidaBean bean = new CadAtrasoSaidaBean();
			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setTurno(rs.getString(3));
			bean.setData(rs.getString(4));
			bean.setHora(rs.getString(5));
			bean.setTipo(rs.getString(6));
			bean.setMotivo(rs.getString(7));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<AdmBean> BuscaNomeAdm(String nome) throws ClassNotFoundException, SQLException {
		List<AdmBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_NOMEADM);
		stmt.setString(1, nome + "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			AdmBean bean = new AdmBean();
			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setData(rs.getString(3));
			bean.setEntrada(rs.getString(4));
			bean.setSaida(rs.getString(5));

			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<CadVansBean> BuscaMotorista(String nome) throws ClassNotFoundException, SQLException {
		List<CadVansBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_VANMOTORISTA);
		stmt.setString(1, nome + "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CadVansBean bean = new CadVansBean();
			bean.setId(rs.getInt(1));
			bean.setMotorista(rs.getString(2));
			bean.setData(rs.getString(3));
			bean.setLinha(rs.getString(4));
			bean.setTipo(rs.getString(5));
			bean.setHora(rs.getString(6));
			bean.setQtdpassageiros(rs.getInt(7));
			bean.setResponsavel(rs.getString(8));
			list.add(bean);

		}
		rs.close();
		return list;

	}
	
	public List<CadEntrevista> BuscaEntrevistaNome(String nome) throws ClassNotFoundException, SQLException {
		List<CadEntrevista> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_NOMEENTREVISTA);
		stmt.setString(1, nome + "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CadEntrevista bean = new CadEntrevista();
			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setRg(rs.getString(3));
			bean.setData(rs.getString(4));
			bean.setEntrada(rs.getString(5));
			bean.setSaida(rs.getString(6));
			
			list.add(bean);

		}
		rs.close();
		return list;

	}
	
	public List<MovVeiculoBean> BuscaMovFuncionario() throws ClassNotFoundException, SQLException {
		List<MovVeiculoBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_MOVFUNCIONARIO);
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			MovVeiculoBean bean = new MovVeiculoBean();
			
			
			bean.setFuncionario(rs.getString(1));
			list.add(bean);

		}
		rs.close();
		return list;

	}
	public List<MovVeiculoBean> BuscaMovDestino() throws ClassNotFoundException, SQLException {
		List<MovVeiculoBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_MOVDESTINO2);
		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			MovVeiculoBean bean = new MovVeiculoBean();
			
			
			bean.setDestino(rs.getString(1));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<CadVansBean> BuscaLinha(String linha) throws ClassNotFoundException, SQLException {
		List<CadVansBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_VANLINHA);
		stmt.setString(1, linha);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CadVansBean bean = new CadVansBean();
			bean.setId(rs.getInt(1));
			bean.setMotorista(rs.getString(2));
			bean.setData(rs.getString(3));
			bean.setLinha(rs.getString(4));
			bean.setTipo(rs.getString(5));
			bean.setHora(rs.getString(6));
			bean.setQtdpassageiros(rs.getInt(7));
			bean.setResponsavel(rs.getString(8));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<VisitasCadastradasBean> BuscaNomeVisita(String nome) throws ClassNotFoundException, SQLException {
		List<VisitasCadastradasBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_NOMEVISITA);
		stmt.setString(1, nome + "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			VisitasCadastradasBean bean = new VisitasCadastradasBean();

			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setRg(rs.getString(3));
			bean.setEmpresa(rs.getString(4));
			bean.setData(rs.getString(5));
			bean.setDatavisita(rs.getString(6));
			bean.setHoraentrada(rs.getString(7));
			bean.setHorasaida(rs.getString(8));
			bean.setMotivo(rs.getString(9));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<VisitasCadastradasBean> BuscaNomeEmpresa(String empresa) throws ClassNotFoundException, SQLException {
		List<VisitasCadastradasBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_NOMEEMPRESA);
		stmt.setString(1, empresa + "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			VisitasCadastradasBean bean = new VisitasCadastradasBean();

			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setRg(rs.getString(3));
			bean.setEmpresa(rs.getString(4));
			bean.setData(rs.getString(5));
			bean.setDatavisita(rs.getString(6));
			bean.setHoraentrada(rs.getString(7));
			bean.setHorasaida(rs.getString(8));
			bean.setMotivo(rs.getString(9));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<CadArmarioFuncBean> BuscaNomeArmario(String nome) throws ClassNotFoundException, SQLException {
		List<CadArmarioFuncBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_NOMEARMARIO);
		stmt.setString(1, nome + "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CadArmarioFuncBean bean = new CadArmarioFuncBean();
			bean.setId(rs.getInt(1));
			bean.setNarmario(rs.getInt(2));
			bean.setNome(rs.getString(3));
			bean.setData(rs.getString(4));
			bean.setTipo(rs.getString(5));
			list.add(bean);

		}
		rs.close();
		return list;

	}
	public List<CadVisitanteBean> BuscaNomeVisitacadastrada(String nome) throws ClassNotFoundException, SQLException {
		List<CadVisitanteBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_NOMEVISITACADASTRO);
		stmt.setString(1, nome + "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CadVisitanteBean bean = new CadVisitanteBean();
			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setRg(rs.getString(3));
			bean.setEmpresa(rs.getString(4));
			bean.setData(rs.getString(5));
			bean.setMotivo(rs.getString(6));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<CadArmarioFuncBean> BuscaTipoArmario(String tipo) throws ClassNotFoundException, SQLException {
		List<CadArmarioFuncBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_TIPOARMARIO );
		stmt.setString(1, tipo);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CadArmarioFuncBean bean = new CadArmarioFuncBean();
			bean.setId(rs.getInt(1));
			bean.setNarmario(rs.getInt(2));
			bean.setNome(rs.getString(3));
			bean.setData(rs.getString(4));
			bean.setTipo(rs.getString(5));
			list.add(bean);

		}
		rs.close();
		return list;

	}
	
	public List<CadArmarioFuncBean> BuscaFunc(String placa) throws ClassNotFoundException, SQLException {
		List<CadArmarioFuncBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_TIPOARMARIO );
		stmt.setString(1, placa);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CadArmarioFuncBean bean = new CadArmarioFuncBean();
			bean.setId(rs.getInt(1));
			bean.setNarmario(rs.getInt(2));
			bean.setNome(rs.getString(3));
			bean.setData(rs.getString(4));
			bean.setTipo(rs.getString(5));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<CadArmarioFuncBean> BuscaNumberArmario(int number) throws ClassNotFoundException, SQLException {
		List<CadArmarioFuncBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_NUMBERARMARIO);
		stmt.setInt(1, number);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CadArmarioFuncBean bean = new CadArmarioFuncBean();
			bean.setId(rs.getInt(1));
			bean.setNarmario(rs.getInt(2));
			bean.setNome(rs.getString(3));
			bean.setData(rs.getString(4));
			bean.setTipo(rs.getString(5));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<ChavesBean> BuscaNomeChave(String nome) throws ClassNotFoundException, SQLException {
		List<ChavesBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_NOMECHAVE);
		stmt.setString(1, nome + "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ChavesBean bean = new ChavesBean();
			bean.setId(rs.getInt(1));
			bean.setFuncionario(rs.getString(2));
			bean.setData(rs.getString(3));
			bean.setRetirada(rs.getString(4));
			bean.setEntrega(rs.getString(5));
			bean.setTipo(rs.getString(6));
			bean.setObservacao(rs.getString(7));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<CadArmarioFuncBean> BuscaFuncArmarioTotal() throws ClassNotFoundException, SQLException {
		List<CadArmarioFuncBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_FUNCARMARIOTOTAL);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CadArmarioFuncBean bean = new CadArmarioFuncBean();
			bean.setId(rs.getInt(1));
			bean.setNarmario(rs.getInt(2));
			bean.setNome(rs.getString(3));
			bean.setData(rs.getString(4));
			bean.setTipo(rs.getString(5));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<CadVisitanteBean> BuscaVisitantesTotal() throws ClassNotFoundException, SQLException {

		List<CadVisitanteBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_VISITAS);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			CadVisitanteBean bean = new CadVisitanteBean();
			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setRg(rs.getString(3));
			bean.setEmpresa(rs.getString(4));
			bean.setData(rs.getString(5));
			bean.setMotivo(rs.getString(6));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<VisitasCadastradasBean> BuscaVisitasTotal() throws ClassNotFoundException, SQLException {

		List<VisitasCadastradasBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_VISITANTES);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			VisitasCadastradasBean bean = new VisitasCadastradasBean();

			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setRg(rs.getString(3));
			bean.setEmpresa(rs.getString(4));
			bean.setData(rs.getString(5));
			bean.setDatavisita(rs.getString(6));
			bean.setHoraentrada(rs.getString(7));
			bean.setHorasaida(rs.getString(8));
			bean.setMotivo(rs.getString(9));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<OcorrenciasBean> BuscaOcorrenciasTotal() throws ClassNotFoundException, SQLException {

		List<OcorrenciasBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_OCORRENCIAS);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			OcorrenciasBean bean = new OcorrenciasBean();
			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setTexto(rs.getString(3));
			bean.setData(rs.getString(4));
			list.add(bean);

		}
		rs.close();
		return list;

	}

	public List<VisitasCadastradasBean> BuscaDataVisitas(String datainicio, String datafinal)
			throws ClassNotFoundException, SQLException {

		List<VisitasCadastradasBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_DATAVISITA);
		stmt.setString(1, datainicio);
		stmt.setString(2, datafinal);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			VisitasCadastradasBean bean = new VisitasCadastradasBean();

			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			bean.setRg(rs.getString(3));
			bean.setEmpresa(rs.getString(4));
			bean.setData(rs.getString(5));
			bean.setDatavisita(rs.getString(6));
			bean.setHoraentrada(rs.getString(7));
			bean.setHorasaida(rs.getString(8));
			bean.setMotivo(rs.getString(9));
			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public List<DescartaveisBean> BuscaDataTotalDescartaveis(String datainicio, String datafinal)
			throws ClassNotFoundException, SQLException {

		List<DescartaveisBean> lista = new ArrayList<>();

		PreparedStatement stmt = getConexao().prepareStatement(SELECT_DATATOTALDESCARTAVEIS);
		stmt.setString(1, datainicio);
		stmt.setString(2, datafinal);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			DescartaveisBean bean = new DescartaveisBean();

			bean.setJaleco(rs.getInt(1));
			bean.setTbranca(rs.getInt(2));
			bean.setTverde(rs.getInt(3));
			bean.setTazul(rs.getInt(4));
			bean.setTvermelha(rs.getInt(5));

			lista.add(bean);

		}
		rs.close();
		return lista;
	}

	public List<MovVeiculoBean> BuscaMovVeiculo(String veiculo) throws ClassNotFoundException, SQLException {
		List<MovVeiculoBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_MOVVEICULO);
		stmt.setString(1, veiculo);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			MovVeiculoBean bean = new MovVeiculoBean();

			bean.setId(rs.getInt(1));
			bean.setVeiculo(rs.getString(2));
			bean.setFuncionario(rs.getString(3));
			bean.setDestino(rs.getString(4));
			bean.setHorasaida(rs.getString(5));
			bean.setHoraretorno(rs.getString(6));
			bean.setDatasaida(rs.getString(7));
			bean.setDataretorno(rs.getString(8));
			bean.setKminicio(rs.getString(9));
			bean.setKmfinal(rs.getString(10));
			bean.setGasolina(rs.getString(11));
			bean.setGas(rs.getString(12));
			list.add(bean);

		}
		rs.close();
		return list;
	}

	public List<ChavesBean> BuscaTipochave(String tipo) throws ClassNotFoundException, SQLException {
		List<ChavesBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_TIPOCHAVE);
		stmt.setString(1, tipo);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			ChavesBean bean = new ChavesBean();

			bean.setId(rs.getInt(1));
			bean.setFuncionario(rs.getString(2));
			bean.setData(rs.getString(3));
			bean.setRetirada(rs.getString(4));
			bean.setEntrega(rs.getString(5));
			bean.setTipo(rs.getString(6));
			bean.setObservacao(rs.getString(7));

			list.add(bean);

		}
		rs.close();
		return list;
	}

	public List<CadChaveBean> Buscachave() throws ClassNotFoundException, SQLException {
		List<CadChaveBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_CHAVE);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			CadChaveBean bean = new CadChaveBean();

			bean.setId(rs.getInt(1));
			bean.setNome(rs.getString(2));
			list.add(bean);

		}
		rs.close();
		return list;
	}

	public List<DescartaveisBean> BuscaTotalConsDescartaveis() throws ClassNotFoundException, SQLException {
		List<DescartaveisBean> list = new ArrayList<>();
		PreparedStatement stmt = getConexao().prepareStatement(SELECT_TOTALCONSDESCARTAVEIS);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			DescartaveisBean bean = new DescartaveisBean();

			bean.setId(rs.getInt(1));
			bean.setJaleco(rs.getInt(2));
			bean.setTbranca(rs.getInt(3));
			bean.setTverde(rs.getInt(4));
			bean.setTazul(rs.getInt(5));
			bean.setData(rs.getString(6));
			list.add(bean);

		}
		rs.close();
		return list;
	}

	public void AtualizaCombustivel(CadVeiculoBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(UPDATE_COMBUSTIVEL);

		stmt.setString(1, bean.getGasolina());
		stmt.setString(2, bean.getGas());
		stmt.setString(3, bean.getModelo());
		stmt.execute();
		stmt.close();
	}

	public void AtualizaUsuario(CadUsuarioBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(UPDATE_USUSARIO);

		stmt.setString(1, bean.getNome());
		stmt.setString(2, bean.getSenha());
		stmt.setString(3, bean.getTipo());
		stmt.setInt(4, bean.getId());
		stmt.executeUpdate();
		stmt.close();
	}

	public void AtualizaFunciArmario(CadArmarioBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(UPDATE_FUNCIARMARIO);

		stmt.setInt(2, bean.getNarmario());
		stmt.setString(3, bean.getTipo());
		stmt.setString(1, bean.getStatus());
		stmt.executeUpdate();
		stmt.close();
	}
	
	public void AtualizaFunciArmarioCad(CadArmarioBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(UPDATE_FUNCIARMARIOCADASTRO);

		stmt.setInt(2, bean.getId());
		stmt.setString(1, bean.getStatus());
		stmt.executeUpdate();
		stmt.close();
	}

	public void AtualizaArmario(CadArmarioBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(UPDATE_ARMARIO);

		stmt.setString(1, bean.getStatus());
		stmt.setString(2, bean.getChavereserva());
		stmt.setString(3, bean.getTipo());
		stmt.setString(4, bean.getData());
		stmt.setString(5, bean.getSituacao());
		stmt.setInt(6, bean.getId());
		stmt.executeUpdate();
		stmt.close();
	}

	public void AtualizaADM(AdmBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(UPDATE_ADM);

		stmt.setString(1, bean.getNome());
		stmt.setString(2, bean.getData());
		stmt.setString(3, bean.getEntrada());
		stmt.setString(4, bean.getSaida());
		stmt.setInt(5, bean.getId());
		stmt.executeUpdate();
		stmt.close();
	}
	public void AtualizaMovVeiculo(MovVeiculoBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(UPDATE_MOVVEICULO);

		stmt.setString(1, bean.getHoraretorno());
		stmt.setString(2, bean.getDataretorno());
		stmt.setString(3, bean.getKmfinal());
		stmt.setString(4, bean.getGasolina());
		stmt.setString(5, bean.getGas());
		stmt.setInt(6, bean.getId());
		stmt.executeUpdate();
		stmt.close();
	}

	public void AtualizaVisitas(VisitasCadastradasBean bean)
			throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(UPDATE_VISITAS);

		stmt.setString(1, bean.getHorasaida());
		stmt.setInt(2, bean.getId());
		stmt.executeUpdate();
		stmt.close();
	}

	public void AtualizaChave(ChavesBean bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(UPDATE_CHAVE);

		stmt.setString(1, bean.getFuncionario());
		stmt.setString(2, bean.getData());
		stmt.setString(3, bean.getRetirada());
		stmt.setString(4, bean.getEntrega());
		stmt.setString(5, bean.getTipo());
		stmt.setString(6, bean.getObservacao());
		stmt.setInt(7, bean.getId());
		stmt.executeUpdate();
		stmt.close();
	}
	
	public void AtualizaEntrevista(CadEntrevista bean) throws SQLException, ClassNotFoundException, ParseException {
		PreparedStatement stmt = getConexao().prepareStatement(UPDATE_ENTREVISTA);

		stmt.setString(1, bean.getNome());
		stmt.setString(2, bean.getRg());
		stmt.setString(3, bean.getData());
		stmt.setString(4, bean.getEntrada());
		stmt.setString(5, bean.getSaida());
		stmt.setInt(6, bean.getId());
		stmt.executeUpdate();
		stmt.close();
	}

	public void ExcluirUsuario(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_USUARIO + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}

	public void ExcluirArmario(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_ARMARIO + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}

	public void ExcluirFuncArmario(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_FUNCARMARIO + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}

	public void ExcluirVan(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_VAN + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}

	public void ExcluirVisitas(String model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_VISITAS + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}

	public void ExcluirCadChave(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_CADCHAVE + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}

	public void ExcluirVisitas(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_HISTVISITAS + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}
	
	public void ExcluirDocumento(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_DOCUMENTO + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}
	
	public void ExcluirEntrevista(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_ENTREVISTA + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}

	public void ExcluirAdm(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_ADM + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}

	public void ExcluirDescartaveis(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_DESCARTAVEIS + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}

	public void ExcluirChaves(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_CONSCHAVES + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}

	public void ExcluirFunAdm(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_FUNCADM + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}

	public void ExcluirAtraso(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_ATRASO + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}
	
	public void ExcluirNovaLinha(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_NOVALINHA + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}
	
	public void ExcluirMovVeiculo(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_MOVVEICULO + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}
	public void ExcluirOcorrencias(int model) throws ClassNotFoundException, SQLException {

		PreparedStatement stmt = getConexao().prepareStatement(DELETE_OCORRENCIAS + "'" + model + "'");
		stmt.execute();

		stmt.close();
	}
}

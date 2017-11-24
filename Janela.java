package JDBC;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mysql.jdbc.Statement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Janela extends JFrame {
	
	private JLabel nomeAluno;
	private JLabel turmaAluno;
	private JLabel enderecoAluno;
	private JLabel idadeAluno;
	private TextField nomeResultado;
	private TextField turmaResultado;
	private TextField enderecoResultado;
	private TextField idadeResultado;
	private TextField entrada;
	private JButton pesquisa;
	private JButton limpar;
	private JButton cadastro;
	
	public Janela() throws SQLException{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(535,210);
		setResizable(false);
		setTitle("Buscador de Alunos");
		setup();
	}	

	private void setup() throws SQLException{
		JPanel painel = new JPanel();
		painel.setLayout(null);
		JLabel matriculaAluno = new JLabel("Digite a matricula do Aluno:",JLabel.RIGHT);
		matriculaAluno.setFont(new Font("Arial",Font.BOLD,12));
		matriculaAluno.setBounds(15,22,165,15);
		matriculaAluno.setOpaque(true);
		painel.add(matriculaAluno);
		
		entrada = new TextField();
		entrada.setBounds(190,20,180,20);	
		painel.add(entrada);

		pesquisa = new JButton("Pesquisar");
		pesquisa.setFont(new Font("Arial",Font.BOLD,11));
		pesquisa.setBounds(390,20,100,20);
		pesquisa.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String texto;
				texto=entrada.getText();
				if(texto.length()!=11){
					entrada.setText("Matrícula Inválida!!");
					try {
						new Thread().sleep(500);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					entrada.setText("");
				}else{
					try{
						Conexao con = new Conexao();
						Statement st = (Statement) con.conexao.createStatement();
																	
						st.executeQuery("select * from alunos where idAluno =" + texto);
						ResultSet rs = st.getResultSet();
																														
						while(rs.next()){							
							nomeResultado.setText(rs.getString("nomeAluno"));
							turmaResultado.setText(rs.getString("turmaAluno"));
							enderecoResultado.setText(rs.getString("enderecoAluno"));
							idadeResultado.setText(rs.getString("idadeAluno"));
						}
					}catch(Exception erro){}					
				}				
			}								
		});
		
		limpar = new JButton("Limpar");
		limpar.setFont(new Font("Arial",Font.BOLD,11));
		limpar.setBounds(390,52,100,20);
		
		limpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeResultado.setText("");
				turmaResultado.setText("");
				enderecoResultado.setText("");
				idadeResultado.setText("");
				entrada.setText("");
			}
			
		});
		
		cadastro = new JButton("Novo Cadastro");
		cadastro.setFont(new Font("Arial",Font.BOLD,11));
		cadastro.setBounds(390,82,120,20);
		
		cadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Conexao con = new Conexao();
					Statement st = (Statement) con.conexao.createStatement();
					String id = entrada.getText();
					String nome = nomeResultado.getText();
					String turma = turmaResultado.getText();
					String endereco = enderecoResultado.getText();
					String idade = idadeResultado.getText();					
					
					st.executeUpdate("insert into alunos(idAluno, nomeAluno, turmaAluno, enderecoAluno, idadeAluno)"
							+ "values('"+id+"','"+nome+"','"+turma+"','"+endereco+"','"+idade+"')");
					
					nomeResultado.setText("");
					turmaResultado.setText("");
					enderecoResultado.setText("");
					idadeResultado.setText("");
					entrada.setText("");
				
				}catch(Exception erro){
					erro.printStackTrace();
				}
				
			}
						
		});
		
		painel.add(pesquisa);
		painel.add(limpar);
		painel.add(cadastro);
		
		JLabel nomeAluno = new JLabel("Nome do Aluno:",JLabel.RIGHT);
		nomeAluno.setBounds(15,52,165,15);
		painel.add(nomeAluno);
		
		JLabel turmaAluno = new JLabel("Turma do Aluno:",JLabel.RIGHT);
		turmaAluno.setBounds(15,82,165,15);
		painel.add(turmaAluno);
		
		JLabel enderecoAluno = new JLabel("Endereço do Aluno:",JLabel.RIGHT);
		enderecoAluno.setBounds(15,112,165,15);
		painel.add(enderecoAluno);
		
		JLabel idadeAluno = new JLabel("Idade do Aluno:",JLabel.RIGHT);
		idadeAluno.setBounds(15,142,165,15);
		painel.add(idadeAluno);
		
		nomeResultado = new TextField();
		nomeResultado.setBounds(190,50,180,20);
		nomeResultado.setEditable(true);
		nomeResultado.setBackground(Color.WHITE);
		painel.add(nomeResultado);
		
		turmaResultado = new TextField();
		turmaResultado.setBounds(190,80,180,20);
		turmaResultado.setEditable(true);
		turmaResultado.setBackground(Color.WHITE);
		painel.add(turmaResultado);
		
		enderecoResultado = new TextField();
		enderecoResultado.setBounds(190,110,180,20);
		enderecoResultado.setEditable(true);
		enderecoResultado.setBackground(Color.WHITE);
		painel.add(enderecoResultado);
		
		idadeResultado = new TextField();
		idadeResultado.setBounds(190,140,180,20);
		idadeResultado.setEditable(true);
		idadeResultado.setBackground(Color.WHITE);
		painel.add(idadeResultado);
				
		add(painel);
	}

}


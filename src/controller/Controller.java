package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.google.gson.Gson;

import entity.Cliente;
import entity.Lembrete;
import entity.Login;
import entity.Pedido;
import entity.Prato;
import persistence.ClienteDao;
import persistence.LembreteDao;
import persistence.LoginDao;
import persistence.PedidoDao;
import persistence.PratoDao;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		
		if("deletarcliente".equalsIgnoreCase(cmd)) {
			deletarCliente(request, response);			
		}
		if("deletarpedido".equalsIgnoreCase(cmd)) {
			deletarPedido(request, response);			
		}
		if("deletarprato".equalsIgnoreCase(cmd)) {
			deletarPrato(request, response);			
		}		
		if("deletarlembrete".equalsIgnoreCase(cmd)) {
			deletarLembrete(request, response);			
		}
		if("sair".equalsIgnoreCase(cmd)) {
			request.getSession().invalidate();
			response.sendRedirect("login.jsp");
		}
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String todos = request.getParameter("todos");
		
		if("gravarcliente".equalsIgnoreCase(cmd)) {
			gravarCliente(request, response);
		}
		if("gravarprato".equalsIgnoreCase(cmd)) {
			gravarPrato(request, response);
		}
		if("gravarpedido".equalsIgnoreCase(cmd)) {
			gravarPedido(request, response);
			System.out.println("gravarpedido");
		}
		if("alterarCliente".equalsIgnoreCase(cmd)) {
			System.out.println("Id do modal: " + request.getParameter("idcliente"));
			atualizarCliente(request, response);
		}
		if("lembrete".equalsIgnoreCase(cmd)) {
			gravarLembrete(request, response);
		}
		if("todosospratos".equalsIgnoreCase(todos)) {
			System.out.println("Entrou aqui.");
			listarPratos(request, response);
		}
		if("criarlogin".equalsIgnoreCase(cmd)) {
			criarLogin(request, response);
		}
		if("consultarloginNome".equalsIgnoreCase(cmd)) {
			consultarLoginNome(request, response);
		}
		if("logar".equalsIgnoreCase(cmd)) {
			login(request, response);
		}
		
	}

	private void listarPratos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Prato p = new Prato();
		response.setContentType("application/json");
		PrintWriter print = response.getWriter();
		String json = new Gson().toJson(p.getListaPratos());
		print.print(json);		
	}
	
	private void gravarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pedido p = new Pedido();
		PedidoDao pd = new PedidoDao();
		ClienteDao cd = new ClienteDao();
		
		p.setIdCliente(Integer.parseInt(request.getParameter("idcliente")));
		p.setNomeCliente(cd.findById(Integer.parseInt(request.getParameter("idcliente"))).getNome().toString());
		p.setEnderecoCliente(cd.findById(Integer.parseInt(request.getParameter("idcliente"))).getEnderecoCliente());
		p.setPrato(request.getParameter("prato"));
		p.setObs(request.getParameter("observacao"));
		p.setValor(request.getParameter("preco"));
		p.setIdDono(Integer.parseInt(request.getParameter("idDono")));
		
		request.setAttribute("msg", pd.gravarPedido(p));
		request.getRequestDispatcher("pedido.jsp").forward(request, response);
	}

	protected void gravarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			ClienteDao cdao = new ClienteDao();
			Cliente cliente = new Cliente();
			
			cliente.setEnderecoCliente(request.getParameter("endereco"));
			cliente.setNome(request.getParameter("nome"));
			cliente.setTelefoneCliente(request.getParameter("telefone"));
			cliente.setObservacao(request.getParameter("obs"));
			cliente.setIdDono(Integer.parseInt(request.getParameter("idDono")));
			
			String mensagem = cdao.gravarCliente(cliente);
			
			request.setAttribute("msg", mensagem);
			request.getRequestDispatcher("cliente.jsp").forward(request, response);
		
	}
	
	protected void gravarPrato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			PratoDao pdao = new PratoDao();
			Prato prato = new Prato();
			
			prato.setNome(request.getParameter("nome"));
			prato.setDescricaoPrato(request.getParameter("descricao"));
			prato.setCategoria(request.getParameter("categoria"));
			prato.setIdDono(Integer.parseInt(request.getParameter("idDono")));
			
			String mensagem = pdao.gravarPrato(prato);
			
			request.setAttribute("msg", mensagem);
			request.getRequestDispatcher("prato.jsp").forward(request, response);
		
	}
	
	protected void deletarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			ClienteDao cdao = new ClienteDao();
			String mensagem = cdao.deletarCliente(Integer.parseInt(request.getParameter("id")));
			
			request.setAttribute("msg", mensagem);
			request.getRequestDispatcher("cliente.jsp").forward(request, response);
			
	}
	
	private void deletarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PedidoDao pd = new PedidoDao();
			String mensagem = pd.deletarPedido(Integer.parseInt(request.getParameter("id")));
		
			request.setAttribute("msg", mensagem);
			request.getRequestDispatcher("pedido.jsp").forward(request, response);
	}
	
	protected void deletarPrato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PratoDao pdao = new PratoDao();
		String mensagem = pdao.deletarPrato(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("msg", mensagem);
		request.getRequestDispatcher("prato.jsp").forward(request, response);
		
	}
	private void atualizarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteDao cdao = new ClienteDao();
		Cliente cliente = new Cliente();
		
		cliente.setId(Long.parseLong(request.getParameter("idcliente")));
		cliente.setEnderecoCliente(request.getParameter("enderecocliente"));
		cliente.setNome(request.getParameter("nomecliente"));
		cliente.setTelefoneCliente(request.getParameter("telefonecliente"));
		cliente.setObservacao(request.getParameter("obscliente"));
		
		System.out.println("cliente: " + cliente);
		
		String mensagem = cdao.atualizarCliente(cliente);
	
		request.setAttribute("msg", mensagem);
		request.getRequestDispatcher("cliente.jsp").forward(request, response);
}
	
	private void gravarLembrete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Lembrete lembrete = new Lembrete();
		LembreteDao ld = new LembreteDao();
		String[] mes = {"Jan", "Feb", "Mar", "April", "May", "Jun", "Jul", "Aug", "Sep", "Out", "Nov", "Dec"};
		
		lembrete.setTitulo(request.getParameter("titulolembrete"));
		lembrete.setTexto(request.getParameter("textolembrete"));
		lembrete.setDia(request.getParameter("dialembrete"));
		lembrete.setMes(mes[Integer.parseInt(request.getParameter("meslembrete"))]);
		lembrete.setAno(request.getParameter("anolembrete"));
		lembrete.setHora(request.getParameter("horalembrete"));
		lembrete.setMin(request.getParameter("minlembrete"));
		lembrete.setIdDono(Integer.parseInt(request.getParameter("idDono")));
		
		String mensagem = ld.gravarLembrete(lembrete);
		request.setAttribute("msg", mensagem);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	private void deletarLembrete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LembreteDao ld = new LembreteDao();
		String mensagem = ld.deletarLembrete(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("msg", mensagem);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	private void criarLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		Login login = new Login();
		LoginDao ldao = new LoginDao();
		
		login.setNome(request.getParameter("nome"));
		login.setSenha(request.getParameter("senha"));
		login.setAdmin(request.getParameter("admin"));
		
		String mensagem = ldao.criarLogin(login);
		
		out.print(mensagem);	
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		
		Login login = new Login();
		LoginDao ldao = new LoginDao();
				
		login = ldao.logar(nome, senha);
		
		if(login != null) {
			HttpSession session = request.getSession();
			session.setAttribute("usuario", login.getNome());
			session.setAttribute("idusuario", login.getId());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "Login inválido!!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}
	
	private void consultarLoginNome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		String nome = request.getParameter("nome");
		LoginDao ldao = new LoginDao();
				
		String mensagem = ldao.consultarLoginPorNome(nome);
		
		out.print(mensagem);	
	}
}

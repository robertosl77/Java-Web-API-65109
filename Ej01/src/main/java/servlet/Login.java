package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usr = request.getParameter("mail");
		String pass = request.getParameter("clave");
		
		Usuario usuarioIngresado = validarUsuario(usr, pass);
		if (usuarioIngresado != null) {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("sesionActiva", "1");
			httpSession.setAttribute("usuario", usuarioIngresado.getNombreUsuario());
			httpSession.setAttribute("nombre", usuarioIngresado.getNombreApellido());
			System.out.println("usuario logueado!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else {
			request.setAttribute("mensajeError", "Usuario o clave incorrectos");
			request.getRequestDispatcher("formulario.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//simular acceso a origen de datos
	private Usuario validarUsuario(String mail, String clave) {
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("Lionel Messi", "lionel@gmail.com", "1234"));
		usuarios.add(new Usuario("Jose Gomez", "jose@gmail.com", "123456"));
		usuarios.add(new Usuario("Maria Sarez", "maria@gmail.com", "12345"));
		
		for (Usuario usuario : usuarios) {
			if((usuario.getNombreUsuario().equals(mail)) && (usuario.getClave().equals(clave))) {
				return usuario;
			}
		}
		return null;
	}

}

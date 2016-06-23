package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.skife.jdbi.v2.Handle;

import com.google.gson.Gson;

import dao.LinhaDao;
import model.Linha;
import singleton.DatabaseConnectionManager;

/**
 * Servlet implementation class ServletRetornaLinhas
 */
@WebServlet("/listaLinhas")
public class ServletRetornaLinhas extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Handle h;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRetornaLinhas() {
        super();
        h = DatabaseConnectionManager.getInstance().getH();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinhaDao dao = h.attach(LinhaDao.class);
		List<Linha> linhas = h.createQuery("select * from cadeobusao_linha").map(Linha.class).list();
		response.getWriter().append(new Gson().toJson(linhas).toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}

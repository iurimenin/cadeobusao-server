package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;

import com.google.gson.Gson;

import dao.PosicaoBusaoDao;
import model.PosicaoBusao;
import model.PosicaoBusaoSerializable;
import singleton.DatabaseConnectionManager;

/**
 * Servlet implementation class ServletGerenciaPosicoesBusao
 */
@WebServlet("/gerenciaPosicoesBusao")
public class ServletGerenciaPosicoesBusao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	private Handle h;
	
    /**
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public ServletGerenciaPosicoesBusao() throws ClassNotFoundException {
        super();
        h = DatabaseConnectionManager.getInstance().getH();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		h.begin(); 
		String codigoLinha = request.getParameter("codigoLinha");
		Query<Map<String, Object>> q = h.createQuery("select * from cadeobusao_posicao_busao "
				+ " where codigo_linha=" 
				+ codigoLinha
				+ " order by codigo desc");
		Query<PosicaoBusao> q2 = q.map(PosicaoBusao.class);
		List<PosicaoBusao> rs = q2.list();
		h.commit();

		if(rs != null && !rs.isEmpty()) {
			response.getWriter().append(new Gson().toJson(PosicaoBusaoSerializable.from(rs).get(0)).toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body = request.getReader().readLine();
		if(body.charAt(0) != '{') {
			body = "{" + body;
		}
		PosicaoBusao posicao = new Gson().fromJson(body, PosicaoBusao.class);
		posicao.setDataHoraRegistro(new Date());
		
		h.begin();
		PosicaoBusaoDao dao = h.attach(PosicaoBusaoDao.class);
		dao.registraNovaPosicaoBusao(posicao, posicao.getLinha());
		h.commit();
		
	}

}

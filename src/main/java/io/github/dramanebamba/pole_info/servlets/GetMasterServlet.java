package main.java.io.github.dramanebamba.pole_info.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.java.io.github.dramanebamba.pole_info.service.*;
import main.java.io.github.dramanebamba.pole_info.model.*;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Masters
 */
@WebServlet("/GetMasterServlet")
public class GetMasterServlet extends HttpServlet {
	public static final String ATT_MESSAGES = "master";
	public static final String VUE          = "/WEB-INF/GetMaster.jsp";
	private static final long serialVersionUID = 1L;
	@Inject
	private MasterDAO masterDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetMasterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		List<Master> listeDesMasters = new ArrayList<>();
		listeDesMasters = masterDAO.listeDesMasters();
		session.setAttribute("listMaster", listeDesMasters);


		String operation = request.getParameter("operation");
		System.out.println(operation);

	if(operation != null && operation.equals("remove")){
		int id = Integer.parseInt(request.getParameter("id"));
		masterDAO.supprimerMaster(id);
	}


		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

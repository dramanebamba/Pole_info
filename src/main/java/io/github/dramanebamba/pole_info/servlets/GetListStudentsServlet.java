package main.java.io.github.dramanebamba.pole_info.servlets;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.io.github.dramanebamba.pole_info.service.PersonneDAO;

/**
 * Servlet implementation class Identification
 */
@WebServlet("/listStudents")
public class GetListStudentsServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	PersonneDAO personne;

	public GetListStudentsServlet()
	{
		/**
		 * @see HttpServlet#HttpServlet()
		 */
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		int id_master = (Integer.parseInt(request.getParameter("id")));

		session.setAttribute("listStudent", personne.getListStudentMaster(id_master));
		RequestDispatcher dispatch = this.getServletContext().getRequestDispatcher("/WEB-INF/GetStudent.jsp");
		dispatch.forward(request, response);
	}
}

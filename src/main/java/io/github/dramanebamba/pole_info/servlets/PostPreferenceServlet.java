package main.java.io.github.dramanebamba.pole_info.servlets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.io.github.dramanebamba.pole_info.model.Contenu;
import main.java.io.github.dramanebamba.pole_info.model.Master;
import main.java.io.github.dramanebamba.pole_info.model.Preference;
import pole_info.ContenuDAO;
import pole_info.MasterDAO;
import pole_info.PreferenceDAO;

/**
 * Servlet implementation class PostPreferenceServlet
 */
@WebServlet("/PostPreferenceServlet")
public class PostPreferenceServlet extends HttpServlet {
	public static final String VUE = "/WEB-INF/PostPreference.jsp";
	private static final long serialVersionUID = 1L;

	@Inject
	private PreferenceDAO preferenceDao;
	@Inject
	private MasterDAO masterDao;
	@Inject
	private ContenuDAO contenuDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostPreferenceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		List<Master> listMaster = masterDao.listeDesMasters();
		List<Contenu> listContenu = contenuDao.listeDesContenus();
		
		session.setAttribute("listMaster", listMaster);
		session.setAttribute("listContenu", listContenu);
		
		RequestDispatcher dispatch = this.getServletContext().getRequestDispatcher(VUE);
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String operation = request.getParameter("operation");
		
		if(operation.equals("masterChoice")){
			
			int idMaster = Integer.parseInt(request.getParameter("id_master"));
			System.out.println("idMaster : " + idMaster);
			
			List<Contenu> listContenu = contenuDao.getCoursesFromMaster(idMaster);
			
			//id Master
			session.setAttribute("idMaster", idMaster);
			
			//Contenu linked to master
			session.removeAttribute("listContenu");
			session.setAttribute("listContenu", listContenu);
		}
		if(operation.equals("postPreference")){
			//Retrieve all the needed parameters for the preference
			int idMaster = Integer.parseInt((String) session.getAttribute("idMaster"));
			int idContenu = Integer.parseInt(request.getParameter("id_contenu"));
			int niveau = Integer.parseInt(request.getParameter("niveau"));
			int idPersonne = Integer.parseInt((String) session.getAttribute("id"));
			
			preferenceDao.createPreference(new Preference(idMaster,idContenu,idPersonne,niveau));
			System.out.println("Création d'une préférence : " + " id_master : " + idMaster + " id_contenu : " + idContenu + " id_personne : " + idPersonne + " niveau : " + niveau);		

		}		
		
		RequestDispatcher dispatch = this.getServletContext().getRequestDispatcher("/WEB-INF/PostPreference.jsp");
		dispatch.forward(request, response);
	}

}

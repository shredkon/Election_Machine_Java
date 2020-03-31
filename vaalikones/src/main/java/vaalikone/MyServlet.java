package vaalikone;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persist.Ehdokkaat;
import persist.Kysymykset;

public class MyServlet extends HttpServlet{
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		List<Ehdokkaat> ehdokkaat=null;
		//получаем коллекцию кандидатов из базы данных 
				EntityManagerFactory emf=null;
			        EntityManager em = null;
			        try {
			  	      emf=Persistence.createEntityManagerFactory("vaalikones");
			  	      em = emf.createEntityManager();
			        }
			        catch(Exception e) {
			          	response.getWriter().println("EMF+EM EI Onnistu");
			          	
			          	e.printStackTrace(response.getWriter());
			          	
			          	return;
			        }
			        
			        try {
		                //Hae haluttu kysymys tietokannasta
		                Query q = em.createQuery(
		                        "SELECT e FROM Ehdokkaat e");
		               // q.setParameter(1, kysymys_id);
		                //Lue haluttu kysymys listaan
		                ehdokkaat = q.getResultList();
		                //передаем коолекцию в запрос
		               

		            } finally {
		                // Sulje tietokantayhteys
		                if (em.getTransaction().isActive()) {
		                    em.getTransaction().rollback();
		                }
		               
		            }
			        
			        
		if( request.getParameter("Edit")!=null) {
			Integer id=Integer.parseInt(request.getParameter("Id"));
			String Sukunimi=request.getParameter("Sukunimi");
			String Etunimi=request.getParameter("Etunimi");
			Ehdokkaat e=new Ehdokkaat(id);
			for(Ehdokkaat eh: ehdokkaat) {
				if(eh.getEhdokasId().equals(id)) {e=eh;}
			}
			e.setEtunimi(Etunimi);
			e.setSukunimi(Sukunimi);
			
			
			//e.
			em.merge(e);
			
			 em.close();
			
			
			//Query q1 = em.createQuery("update Ehdokkaat e set e.sukunimi='"+Sukunimi+"' where e.ehdokasId="+id);
			//q1.executeUpdate(e);
			
			//em.getTransaction().commit();
			
			System.out.println("Edit edit");	
		}
		if( request.getParameter("Del")!=null) {
			System.out.println("Delete delete");	
		}
		
	        
	        request.setAttribute("Ehdokkaat", ehdokkaat);
            //пеердаем запрос в страницу jsp
            request.getRequestDispatcher("ehdokkaats.jsp")
                    .forward(request, response);
	        
		
		
	}
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fol

}

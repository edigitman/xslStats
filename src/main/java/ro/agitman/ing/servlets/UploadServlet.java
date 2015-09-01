package ro.agitman.ing.servlets;

import ro.agitman.ing.model.Transaction;
import ro.agitman.ing.model.XslProcessing;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by edi on 31.08.15.
 */
@MultipartConfig
@WebServlet(name = "/upload")
public class UploadServlet extends HttpServlet {

    private EntityManagerFactory emf;

    public void init() {
        emf = Persistence.createEntityManagerFactory("ing");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();

        List<Transaction> list = XslProcessing.getInstance().parseFile(fileContent);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        for(Transaction t : list){
            em.persist(t);
        }

        em.getTransaction().commit();

        response.sendRedirect("/index.html");

    }

}

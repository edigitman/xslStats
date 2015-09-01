package ro.agitman.ing.servlets;

import ro.agitman.ing.model.Transaction;
import ro.agitman.ing.model.XslProcessing;

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
public class UploadServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();
        // ... (do your job here)

        List<Transaction> list = XslProcessing.getInstance().parseFile(fileContent);

        System.out.println(list.size());

    }

}

package algo;

import com.oreilly.servlet.MultipartRequest;
import static db.dbconn.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eiosys
 */
@WebServlet(name = "fileUpload", urlPatterns = {"/fileUpload"})
public class fileUpload extends HttpServlet {
//Statement st; 
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        File outputfile = null;
        String filename = null;
        String path1="",path2="";
        try {
            MultipartRequest multipartRequest = new MultipartRequest(request, getServletContext().getRealPath("/") + "files");
            File file = multipartRequest.getFile("file");
            File image = multipartRequest.getFile("image");
            String class_feature = multipartRequest.getParameter("text");
            file.getName();
            Retrieve.setClassFeature(class_feature);
            String file_name = file.getName();
            String pic_image = image.getName();
            Statement st1 = db.dbconn.connect();
            String sql = "INSERT INTO files(name,type)VALUES('" + file_name + "','List'),('" + pic_image + "','Dataset')";
            System.out.println("SQL: "+sql);
            st1.executeUpdate(sql);
            path1 = getServletContext().getRealPath("/")+"files/"+file_name;
            path2 = getServletContext().getRealPath("/")+"files/"+pic_image;
            ReadFile.reading(path1, path2, class_feature);
//            st1.close();
            response.sendRedirect("successful_upload.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
    }
    // </editor-fold>
}

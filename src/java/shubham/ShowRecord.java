package shubham;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "ShowRecord", urlPatterns = {"/sr"})
public class ShowRecord extends HttpServlet {
    int i=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession hs;
        hs=request.getSession(false);
        PrintWriter out = response.getWriter();
        try  {
            if(hs!=null){
                if(!hs.getAttribute("type").toString().equalsIgnoreCase("admin")){
                    RequestDispatcher rd = request.getRequestDispatcher("ErrorPage.jsp");
                    request.setAttribute("msg", "Login as an admin");
                    rd.forward(request, response);
                }
            }
            else {
                response.sendRedirect("LoginPage.html");
            }
               Connection con=DBService.getConnection();
               PreparedStatement pst=con.prepareStatement
        ("select * from student");
            ResultSet rs=pst.executeQuery();
            out.println("<center><table><tr bgcolor=#abdddd>"
                    + "<th>ID</th>"
                    + "<th>NAME</th>"
                    + "<th>AGE</th>"
                    + "<th>ADDRESS</th>"
                    + "<th>BRANCH</th>"
                    + "<th>SECTION</th>"
                    + "</tr>");
            while(rs.next())
            {   if(i%2==0){
                out.println("<tr bgcolor=#ffaaaa>"
                        + "<td>"+rs.getInt("id")+"</td>"
                        + "<td>"+rs.getString("name")+"</td>"
                        + "<td>"+rs.getInt("age")+"</td>"
                        + "<td>"+rs.getString("address")+"</td>"                        
                        + "<td>"+rs.getString("branch")+"</td>"
                        + "<td>"+rs.getString("section")+"</td>"
                        + "</tr>");
            }
                else
                {
                out.println("<tr bgcolor=#ffffff>"
                        + "<td>"+rs.getInt("id")+"</td>"
                        + "<td>"+rs.getString("name")+"</td>"
                        + "<td>"+rs.getInt("age")+"</td>"
                        + "<td>"+rs.getString("address")+"</td>"
                        + "<td>"+rs.getString("branch")+"</td>"
                        + "<td>"+rs.getString("section")+"</td>"
                        + "</tr>");
            }
            i++;
            }
            
            out.println("</table></center>");
        }
        catch(Exception e)
        {
            out.println(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
    }// </editor-fold>

}

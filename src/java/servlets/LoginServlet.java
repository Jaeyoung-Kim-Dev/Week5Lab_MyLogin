package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AccountService;
import models.User;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String logout = request.getParameter("logout");

        if (logout != null) {
            session.invalidate();
            request.setAttribute("message", "The user has successfully logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

        User user = (User) session.getAttribute("user");

        if (user != null) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("home");
            return;
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        /*
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
        }
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AccountService accountService = new AccountService();
        User user = accountService.login(username, password);

        if (user == null) {
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("message", "Ivalid login");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            session.setAttribute("user", user);
            response.sendRedirect("home");
        }

        /*
        for (int i =0 ; i < accountServlet.size(); i++ ) {
            String matchUsername = accountServlet.get(i).getUsername();
            String matchPassword = accountServlet.get(i).getPassword();
                                    
            login (matchUsername, matchPassword);
            
            if (matchUsername && matchPassword) {
                //foundAccount = true;
                request.setAttribute("username", accountServlet.get(i).getUsername());
                getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
            }
        }
        
        if (!foundAccount) {
            
        }
        
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AccountService accountService = new AccountService(username, password);
        request.setAttribute("accountService", accountService);

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

        /*
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");        
        
        Note note = new Note(title, contents);
        request.setAttribute("note", note);
        
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");

        try (
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)))) {
            pw.println(title);
            pw.println(contents);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
         */
    }
}

package br.com.fintech.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        // Simples validação (substituir por consulta ao banco no futuro)
        if ("admin".equals(usuario) && "1234".equals(senha)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioAutenticado", usuario);
            response.sendRedirect("dashboard.jsp"); // ou outra página restrita
        } else {
            request.setAttribute("erroLogin", "Usuário ou senha inválidos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }
}

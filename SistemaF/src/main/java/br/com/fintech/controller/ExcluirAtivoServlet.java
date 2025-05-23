package br.com.fintech.controller;

import br.com.fintech.dao.AtivoFinanceiroDAO;
import br.com.fintech.dao.impl.OracleAtivoFinanceiroDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/excluir-ativo")
public class ExcluirAtivoServlet extends HttpServlet {
    private AtivoFinanceiroDAO ativoDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.ativoDAO = new OracleAtivoFinanceiroDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("Tentando excluir ativo com ID: " + id);
            ativoDAO.remover(id);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Redireciona para a listagem atualizada
        response.sendRedirect("ativos");
    }
}

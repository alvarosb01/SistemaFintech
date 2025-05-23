package br.com.fintech.controller;

import br.com.fintech.dao.AtivoFinanceiroDAO;
import br.com.fintech.dao.impl.OracleAtivoFinanceiroDao;
import br.com.fintech.exception.DBException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

        HttpSession session = request.getSession();

        try {
            // Validação do parâmetro ID
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                session.setAttribute("erro", "ID do ativo não fornecido");
                response.sendRedirect("ativos");
                return;
            }

            int id = Integer.parseInt(idParam);

            // Validação se ID é positivo
            if (id <= 0) {
                session.setAttribute("erro", "ID do ativo inválido");
                response.sendRedirect("ativos");
                return;
            }

            System.out.println("Tentando excluir ativo com ID: " + id);

            // Verificar se o ativo existe antes de tentar excluir
            if (!ativoDAO.existe(id)) {
                session.setAttribute("erro", "Ativo não encontrado");
                response.sendRedirect("ativos");
                return;
            }

            // Tentar excluir o ativo
            boolean excluido = ativoDAO.remover(id);

            if (excluido) {
                session.setAttribute("sucesso", "Ativo excluído com sucesso!");
                System.out.println("Ativo com ID " + id + " excluído com sucesso");
            } else {
                session.setAttribute("erro", "Falha ao excluir o ativo");
            }

        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter ID para número: " + e.getMessage());
            session.setAttribute("erro", "ID do ativo deve ser um número válido");

        } catch (DBException e) {
            System.err.println("Erro de banco de dados ao excluir ativo: " + e.getMessage());
            e.printStackTrace();
            session.setAttribute("erro", "Erro ao acessar banco de dados: " + e.getMessage());

        } catch (Exception e) {
            System.err.println("Erro inesperado ao excluir ativo: " + e.getMessage());
            e.printStackTrace();
            session.setAttribute("erro", "Erro interno do servidor. Tente novamente.");
        }

        // Redireciona para a listagem atualizada
        response.sendRedirect("ativos");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
                "Método GET não permitido para exclusão");
    }
}
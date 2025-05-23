package br.com.fintech.controller;

import br.com.fintech.dao.AtivoFinanceiroDAO;
import br.com.fintech.dao.DAOFactory;
import br.com.fintech.dao.impl.AtivoFinanceiroImpl;
import br.com.fintech.exception.DBException;
import br.com.fintech.model.AtivoFinanceiro;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/ativos")
public class AtivoServlet extends HttpServlet {

    private AtivoFinanceiroDAO dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = DAOFactory.getAtivoFinanceiroDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String nome = req.getParameter("nomeAtivo");
            int quantidade = Integer.parseInt(req.getParameter("quantidade"));
            double valorCota = Double.parseDouble(req.getParameter("valorCota"));
            LocalDate dataCompra = LocalDate.parse(req.getParameter("dataCompra"));

            AtivoFinanceiro ativo = new AtivoFinanceiroImpl(nome, quantidade, valorCota, dataCompra);
            dao.cadastrar(ativo);

            req.setAttribute("mensagem", "Ativo financeiro cadastrado!");
        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar o ativo.");
        } catch (NumberFormatException | DateTimeParseException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, preencha os dados corretamente.");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro inesperado. Tente novamente.");
        }

        req.getRequestDispatcher("cadastro-ativo.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<AtivoFinanceiro> lista = dao.listar();
        req.setAttribute("ativo", lista);
        req.getRequestDispatcher("lista-ativos.jsp").forward(req, resp);
    }
}

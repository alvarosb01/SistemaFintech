<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>Adicionar Ações - CriptoOrganize</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0,0,0,0.05);
        }
        .btn-primary {
            background-color: #343a40;
            border: none;
        }
        .btn-primary:hover {
            background-color: #23272b;
        }
    </style>
</head>

<body>
<%@include file="header.jsp"%>

<div class="container py-5">
    <div class="card">
        <div class="card-header bg-dark text-white">
            <h5 class="mb-0">Comprar Novo Ativo</h5>

            <c:if test="${not empty mensagem}">
                <div class="alert alert-success ms-2 me-2 m-auto mt-2">${mensagem}</div>
            </c:if>

            <c:if test="${not empty erro}">
                <div class="alert alert-danger ms-2 me-2 m-auto mt-2">${erro}</div>
            </c:if>

        </div>
        <div class="card-body">
            <form action="ativos" method="post">
                <div class="mb-3">
                    <label for="nomeAtivo" class="form-label">Nome do Ativo</label>
                    <input type="text" class="form-control" id="nomeAtivo" name="nomeAtivo" placeholder="Ex: PETR4" required>
                </div>

                <div class="mb-3">
                    <label for="quantidade" class="form-label">Quantidade</label>
                    <input type="number" class="form-control" id="quantidade" name="quantidade" min="1" required>
                </div>

                <div class="mb-3">
                    <label for="valorCota" class="form-label">Valor da Cota (R$)</label>
                    <input type="number" step="0.01" class="form-control" id="valorCota" name="valorCota" required>
                </div>

                <div class="mb-3">
                    <label for="dataCompra" class="form-label">Data de Compra</label>
                    <input type="date" class="form-control" id="dataCompra" name="dataCompra" required>
                </div>

                <div class="d-flex justify-content-between">
                    <a href="index.jsp" class="btn btn-outline-secondary">Cancelar</a>
                    <button type="submit" class="btn btn-primary">Comprar Ativo</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


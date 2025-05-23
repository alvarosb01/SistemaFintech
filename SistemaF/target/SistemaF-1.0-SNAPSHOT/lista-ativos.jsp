<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>CriptoOrganize</title>
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
        .table th {
            background-color: #343a40;
            color: white;
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
    <div class="card mb-4">
        <div class="card-header bg-dark text-white d-flex justify-content-between align-items-center">
            <h5 class="mb-0">Lista de Ativos</h5>
            <div class="d-flex gap-2">
                <a href="cadastro-ativo.jsp" class="btn btn-sm btn-light">+ Adicionar Ações</a>

            </div>

        </div>
        <div class="card-body">
            <p class="text-muted mb-4">Visualize e gerencie os ativos da sua carteira.</p>
            <div class="table-responsive">
                <table class="table table-hover align-middle">

                    <tbody>
                    <thead>
                    <tr>
                        <th>Ativos</th>
                        <th class="text-end">Quantidade</th>
                        <th class="text-end">Valor da cota (R$)</th>
                        <th class="text-center">Data de compra</th>
                        <th class="text-center">Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${ativo}">
                    <tr>
                        <td>${item.nome}</td>
                        <td class="text-end">${item.quantidade}</td>
                        <td class="text-end">${item.valor}</td>
                        <td class="text-center">${item.dataCompra}</td>
                        <td class="text-center">
                            <form action="excluir-ativo" method="post" onsubmit="return confirm('Tem certeza que deseja excluir este ativo?');">
                                <input type="hidden" name="id" value="${item.id}" />
                                <button type="submit" class="btn btn-sm btn-outline-danger">Excluir</button>
                            </form>
                        </td>
                    </tr>
                    </c:forEach>
                    <thead>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
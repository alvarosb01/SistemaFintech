<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>CriptoOrganize - Dashboard</title>
    <a href="login.jsp">Login</a>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0,0,0,0.05);
        }
        .section-title {
            font-size: 1.5rem;
            font-weight: bold;
            color: #f8f9fa;
            margin-bottom: 1rem;
        }
        .news-img {
            max-height: 150px;
            object-fit: cover;
        }
    </style>
</head>

<body>
<%@include file="header.jsp"%>

<div class="container py-5">

    <!-- Seção de Gráficos -->
    <div class="mb-5">
        <div class="section-title">Resumo de Mercado</div>
        <div class="row g-4">
            <div class="col-md-6">
                <div class="card p-3">
                    <h6 class="mb-3">Comparativo Ações x Cripto</h6>
                    <canvas id="graficoComparativo"></canvas>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card p-3">
                    <h6 class="mb-3">Histórico do Bitcoin</h6>
                    <canvas id="graficoBitcoin"></canvas>
                </div>
            </div>
        </div>
    </div>

    <!-- Seção de Notícias -->
    <div class="mb-5">
        <h2 class="mb-4">Últimas Notícias Financeiras</h2>
        <div class="row row-cols-1 row-cols-md-3 g-4">

            <!-- Notícia 1 -->
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <img src="https://images.unsplash.com/photo-1569025690938-a00729c9e1b9?auto=format&fit=crop&w=800&q=80"
                         class="card-img-top" alt="Alta no mercado de ações">
                    <div class="card-body">
                        <h5 class="card-title">Alta no mercado de ações</h5>
                        <p class="card-text">B3 registra alta com otimismo sobre inflação e corte de juros nos EUA.</p>
                    </div>
                </div>
            </div>

            <!-- Notícia 2 -->
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <img src="https://images.unsplash.com/photo-1611078489935-0cb964de46b1?auto=format&fit=crop&w=800&q=80"
                         class="card-img-top" alt="Bitcoin ultrapassa $70 mil">
                    <div class="card-body">
                        <h5 class="card-title">Bitcoin ultrapassa $70 mil</h5>
                        <p class="card-text">Criptomoeda dispara após nova regulamentação favorável nos EUA.</p>
                    </div>
                </div>
            </div>

            <!-- Notícia 3 -->
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <img src="https://images.unsplash.com/photo-1605902711622-cfb43c44367e?auto=format&fit=crop&w=800&q=80"
                         class="card-img-top" alt="Investidores migram para renda fixa">
                    <div class="card-body">
                        <h5 class="card-title">Investidores migram para renda fixa</h5>
                        <p class="card-text">CDBs e LCIs tornam-se mais atrativos com altas taxas de juros em 2025.</p>
                    </div>
                </div>
            </div>

        </div>
    </div>


<%@include file="footer.jsp"%>

<script>
    // Gráfico 1 - Comparativo Ações x Cripto
    const ctx1 = document.getElementById('graficoComparativo').getContext('2d');
    new Chart(ctx1, {
        type: 'bar',
        data: {
            labels: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai'],
            datasets: [
                {
                    label: 'Ações',
                    data: [10, 12, 9, 14, 15],
                    backgroundColor: 'rgba(52, 58, 64, 0.7)'
                },
                {
                    label: 'Criptos',
                    data: [8, 9, 11, 13, 16],
                    backgroundColor: 'rgba(25, 135, 84, 0.7)'
                }
            ]
        },
        options: {
            responsive: true,
            scales: {
                y: { beginAtZero: true }
            }
        }
    });

    // Gráfico 2 - Histórico Bitcoin
    const ctx2 = document.getElementById('graficoBitcoin').getContext('2d');
    new Chart(ctx2, {
        type: 'line',
        data: {
            labels: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai'],
            datasets: [{
                label: 'Preço BTC (em mil USD)',
                data: [45, 50, 48, 60, 70],
                borderColor: 'rgba(255, 99, 132, 0.9)',
                backgroundColor: 'rgba(255, 99, 132, 0.1)',
                fill: true,
                tension: 0.4
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: { beginAtZero: false }
            }
        }
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

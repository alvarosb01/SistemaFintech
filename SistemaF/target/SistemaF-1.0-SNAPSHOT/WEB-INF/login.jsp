<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="../resources/css/estilo.css">
</head>
<body>
<h1>Login</h1>
<form action="usuario?action=login" method="post">
    <div>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    </div>
    <div>
        <label for="senha">Senha:</label>
        <input type="password" id="senha" name="senha" required>
    </div>
    <button type="submit">Entrar</button>
    <p>Ainda não tem conta? <a href="cadastro.jsp">Cadastre-se</a></p>
    <% if (request.getAttribute("erro") != null) { %>
    <p class="erro"><%= request.getAttribute("erro") %></p>
    <% } %>
</form>
</body>
</html>

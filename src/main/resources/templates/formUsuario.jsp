<!DOCTYPE html>

<html xmlns:th="http://thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Usuario</title>
</head>

<body>

    <h3> Cadastrar Usuário </h3>

    <form method="get">

        ID: <input type="text" name="id" placeholder="ID"> <br>
        Nome: <input type="text" name="nome" placeholder="NAME"> <br>
        Login: <input type="text" name="login" placeholder="LOGIN"> <br>
        Senha: <input type="password" name="senha" placeholder="PASSWORD"> <br>
        <button type="submit">Enviar</button>

    </form>

</body>

</html>
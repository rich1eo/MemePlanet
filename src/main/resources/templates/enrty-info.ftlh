<!DOCTYPE html>
<html>
<head>
    <title>MemePlanet</title>
</head>
<body>
<h1>MemePlanet</h1>
<h4>Подробная информация о посте</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"/> <br><br>
</#list>
<b>Название: </b>${entry.title} <br>
<b>Описание: </b>${entry.description} <br>
<b>Автор: </b><a href="/user/${entry.user.id}">${user.}</a><br>
</body>
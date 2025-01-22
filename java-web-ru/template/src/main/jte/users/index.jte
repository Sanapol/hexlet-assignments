@import exercise.dto.users.UsersPage

@param UsersPage users

<html lang="ru">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Пользователи</title>
    </head>
    <body>
        @for (var user : users.getUsers())
            <div>
                <h1><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h1>
            </div>
        @endfor
    </body>
</html>
@import exercise.dto.users.UserPage
@param UserPage users

<html lang="ru">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>${users.getUser().getFirstName()} ${users.getUser().getLastName()}</title>
    </head>
    <body>
        <h1>${users.getUser().getFirstName()} ${users.getUser().getLastName()}</h1>
        <p>email = ${users.getUser().getEmail()}</p>
        <p>id = ${users.getUser().getId()}</p>
    </body>
</html>

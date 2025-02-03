@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@import gg.jte.Content
@param Content content
@param MainPage page

<html lang="ru">
    <head>
        <title>Сессия</title>
    </head>
    <body>
        @if(page.getName() != null)
            <h1>${String.valueOf(page.getName())}</h1>
            <a href="${NamedRoutes.logoutPath()}">Выйти</a>
        @else
            <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
        @endif
        ${content}
    </body>
</html>



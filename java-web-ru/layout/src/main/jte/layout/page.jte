@import gg.jte.Content
@param Content content

<http lang="ru">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Страница</title>
    </head>
    <body>
        <header>
            <ul>
                <li>
                    <a href="/">Главная</a>
                </li>
                <li>
                    <a href="/users">Пользователи</a>
                </li>
            </ul>
        </header>
        @if(content != null)
            ${content}
        @endif
        <footer>
            Привет, я футер
            <a href="https://ru.hexlet.io/">А это ссылка на хекслет</a>
        </footer>
    </body>
</http>
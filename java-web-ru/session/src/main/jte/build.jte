@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page

@template.layout.page(content = @`
    <div>
        <form action="${NamedRoutes.loginPath()}" method="post">
            <div>
                <label>
                    Имя
                    <input type="text" name="name" value="${page.getName()}">
                </label>
            </div>
            <div>
                <label>
                    Пароль
                    <input type="password" name="password">
                </label>
                @if(page.getError() != null)
                    <p>${page.getError()}</p>
                @endif
            </div>
            <input type="submit" value="Войти">
        </form>
    </div>
`)

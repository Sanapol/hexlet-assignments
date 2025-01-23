@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(content = @`
        <p>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</p>
        <p>id = ${page.getUser().getId()}</p>
        <p>email = ${page.getUser().getEmail()}</p>
`)

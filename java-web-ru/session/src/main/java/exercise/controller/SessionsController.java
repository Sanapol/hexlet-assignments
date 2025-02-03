package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;

import java.security.Security;

public class SessionsController {

    // BEGIN
    public static void getLogin(Context ctx) {
        var page = new LoginPage();
        ctx.render("build.jte", model("page", page));
    }

    public static void postLogin(Context ctx) {
        try {
            var name = ctx.formParam("name");
            var check = UsersRepository.findByName(name)
                    .orElseThrow(() -> new NotFoundResponse("Wrong"));
            var password = ctx.formParamAsClass("password", String.class)
                    .check(pas -> check.getPassword().equals(encrypt(pas)), "Wrong username or password")
                    .get();
            ctx.sessionAttribute("currentUser", name);
            ctx.redirect("/");
        } catch (ValidationException e) {
            var page = new LoginPage(ctx.formParam("name"), "Wrong username or password");
            ctx.render("build.jte", model("page", page));
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/");
    }

    public static void index(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", model("page", page));
    }
    // END
}

package exercise;

import io.javalin.Javalin;
import exercise.controller.PostsController;
import exercise.controller.RootController;
import exercise.util.NamedRoutes;
import io.javalin.rendering.template.JavalinJte;

import io.javalin.testtools.JavalinTest;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get(NamedRoutes.rootPath(), RootController::index);

        app.get(NamedRoutes.buildPostPath(), PostsController::build);
        app.post(NamedRoutes.postsPath(), PostsController::create);

        app.get(NamedRoutes.postsPath(), PostsController::index);
        app.get(NamedRoutes.postPath("{id}"), PostsController::show);

        app.get(NamedRoutes.editPostPath("{id}"), PostsController::edit);
        app.post(NamedRoutes.postPath("{id}"), PostsController::update);

        // BEGIN
        app.after(ctx -> {
                var body = ctx.result();
                var hash = DigestUtils.sha256Hex(body);
                ctx.header("X-Response-Digest", hash);
            });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}




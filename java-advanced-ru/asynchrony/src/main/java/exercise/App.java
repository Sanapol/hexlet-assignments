package exercise;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String path1, String path2, String resultPath) {
        CompletableFuture<String> futureFile1 = CompletableFuture.supplyAsync(() -> {
            try {
                Path path = Paths.get(path1);
                String content = Files.readString(path);
                return content;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        });;

        CompletableFuture<String> futureFile2 = CompletableFuture.supplyAsync(() -> {
           try {
               Path path = Paths.get(path2);
               String content = Files.readString(path);
               return content;
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        });;

        CompletableFuture<String> futureUnion = futureFile1.thenCombine(futureFile2, (file1, file2) -> {
            try {
                String content = file1 + file2;
                Path path = Paths.get(resultPath);
                File file = new File(String.valueOf(path));
                if (!file.exists()) {
                    Files.createFile(file.toPath());
                }
                Path result = Files.write(path, content.getBytes());
                return result.toString();
            } catch (Exception e) {
               throw new RuntimeException(e);
           }
        });
        return futureUnion;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles("src/main/resources/file1.txt", "src/main/resources/file2.txt", "result.txt");
        // END
    }
}


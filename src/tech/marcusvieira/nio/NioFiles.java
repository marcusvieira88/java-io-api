package tech.marcusvieira.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class NioFiles {

    public static void main(String[] args) {
        Path path = Paths.get("logging.properties");

        // Files.exists()
        boolean pathExists =
            Files.exists(path,LinkOption.NOFOLLOW_LINKS);

        System.out.println(pathExists);


        // Files.createDirectory()
        Path path2 = Paths.get("testdir");
        try {
            Path newDir = Files.createDirectory(path2);
        } catch(FileAlreadyExistsException e){
            // the directory already exists.
        } catch (IOException e) {
            //something else went wrong
            e.printStackTrace();
        }


        // Files.copy()
        Path sourcePath      = Paths.get("logging.properties");
        Path destinationPath = Paths.get("logging-copy.properties");

        try {
            Files.copy(sourcePath, destinationPath);
        } catch(FileAlreadyExistsException e) {
            //destination file already exists
        } catch (IOException e) {
            //something else went wrong
            e.printStackTrace();
        }

        // Files.move()
        Path sourcePath2      = Paths.get("logging-copy.properties");
        Path destinationPath2 = Paths.get("testdir/logging-moved.properties");

        try {
            Files.move(sourcePath2, destinationPath2,
                StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            //moving file failed.
            e.printStackTrace();
        }

        // Files.delete()
        Path path3 = Paths.get("testdir/logging-moved.properties");

        try {
            Files.delete(path3);
        } catch (IOException e) {
            //deleting file failed
            e.printStackTrace();
        }

        // Files.walkFileTree() - Searching For Files
        Path rootPath = Paths.get("testdir");
        String fileToFind = File.separator + "test4.txt";

        try {
            Files.walkFileTree(rootPath, new SimpleFileVisitor<>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    String fileString = file.toAbsolutePath().toString();
                    System.out.println("pathString = " + fileString);

                    if(fileString.endsWith(fileToFind)){
                        System.out.println("file found at path: " + file.toAbsolutePath());
                        return FileVisitResult.TERMINATE;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch(IOException e){
            e.printStackTrace();
        }

        // Files.walkFileTree() - Deleting Directories Recursively
        Path dirToBeDeleted = Paths.get("testdir/to-delete");
        try {
            Files.createDirectory(dirToBeDeleted); // create a dir to be deleted
        } catch (IOException e) {
            e.printStackTrace();
        }

        // delete the dir
        try {
            Files.walkFileTree(dirToBeDeleted, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("delete file: " + file.toString());
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    System.out.println("delete dir: " + dir.toString());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

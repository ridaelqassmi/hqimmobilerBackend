package com.HQimmobillier.fpbm.utility;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CommenFunctions {
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get("frontend/src/assets/"+uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
    public static byte[]  retriveFileById(String subDir) throws IOException {
        File f = new File(subDir);
        String path = f.getAbsolutePath();
        byte[] picInBytes = new byte[(int) f.length()];
        FileInputStream fileInputStream = new FileInputStream(f);
        fileInputStream.read(picInBytes);
        fileInputStream.close();
        return picInBytes;
    }
}

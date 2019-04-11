package kr.hs.dgsw.web0325.Controller;

import kr.hs.dgsw.web0325.Domain.User;
import kr.hs.dgsw.web0325.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web0325.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController {

    private UserService userService;

    @Autowired
    public AttachmentController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/attachment/user/{id}")
    public AttachmentProtocol upload(@PathVariable int id, @RequestPart MultipartFile srcFile) throws IOException {
        String fileName = System.getProperty("user.dir") + "\\upload\\profile\\" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy\\MM\\dd")) + UUID.randomUUID().toString() + "_" + srcFile.getOriginalFilename();
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        srcFile.transferTo(file);

        String originalPath = srcFile.getOriginalFilename();

        User user = new User();
        user.setStoredPath(fileName);
        user.setOriginalPath(originalPath);
        userService.update(id, user);
        return new AttachmentProtocol(fileName, originalPath);
    }

    @PostMapping("/attachment")
    public AttachmentProtocol upload(@RequestPart MultipartFile srcFile) throws IOException {
        try{
            String fileName = System.getProperty("user.dir") + "\\upload\\" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy\\MM\\dd")) + UUID.randomUUID().toString() + "_" + srcFile.getOriginalFilename();
            File file = new File(fileName);
            file.getParentFile().mkdirs();
            srcFile.transferTo(file);
            return new AttachmentProtocol(fileName, srcFile.getOriginalFilename());
        }catch (Exception ex){
            return null;
        }
    }

    @GetMapping("/attachment/{type}/{id}")
    public void download(@PathVariable String type,@PathVariable Long id, HttpServletRequest req, HttpServletResponse resp){
        try{
            String filePath = "";
            String fileName = "";

            File file = new File(filePath);
            if(file.exists() == false) return;

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if(mimeType == null) mimeType = "application/octet-stream";

            resp.setContentType(mimeType);
            resp.setHeader("Content-Disposition","inline : filename=\"" + fileName + "\"");
            resp.setContentLength((int)file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, resp.getOutputStream());
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
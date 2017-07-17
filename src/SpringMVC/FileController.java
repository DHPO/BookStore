package SpringMVC;

import AppService.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by jimmy on 17-7-17.
 */
@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "file/{fileId}", method = RequestMethod.GET)
    public void getPicture(@PathVariable("fileId")String fileId, HttpServletRequest request, HttpServletResponse response){
        System.out.println(fileId);
        response.setContentType("application/octet-stream;charset=UTF-8");
        InputStream in=fileService.getFile(fileId);
        try {
            OutputStream outputStream=response.getOutputStream();
            int len=0;
            byte[]buf=new byte[1024];
            while((len=in.read(buf,0,1024))!=-1){
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "file", method = RequestMethod.POST)
    public @ResponseBody String savePicture(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println("here");
        System.out.println(file);
        InputStream fileContent =file.getInputStream();
        return fileService.saveFile(fileContent);
    }
}

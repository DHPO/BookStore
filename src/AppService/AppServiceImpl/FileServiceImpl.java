package AppService.AppServiceImpl;

import AppService.FileService;
import Dao.FileDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

/**
 * Created by jimmy on 17-7-17.
 */
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao fileDao;

    public InputStream getFile(String id){
        return fileDao.getFile(id);
    }

    public String saveFile(InputStream file){
        return fileDao.saveFile(file);
    }
}

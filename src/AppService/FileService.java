package AppService;

import java.io.InputStream;

/**
 * Created by jimmy on 17-7-17.
 */
public interface FileService {
    InputStream getFile(String id);

    String saveFile(InputStream file);
}

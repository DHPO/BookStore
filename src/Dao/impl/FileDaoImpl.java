package Dao.impl;

import Dao.FileDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.InputStream;

public class FileDaoImpl implements FileDao{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String saveFile(InputStream fileContent) {
        DB db = mongoTemplate.getDb();

        GridFS gfsPhoto = new GridFS(db, "questionnaireFiles");
        DBObject query1 = new BasicDBObject();
        GridFSDBFile imageForOutput = gfsPhoto.findOne(query1);
        if (imageForOutput!=null){gfsPhoto.remove(imageForOutput);}

        GridFSInputFile gfsFile = gfsPhoto.createFile(fileContent);
        gfsFile.save();
        return gfsFile.getId().toString();
    }

    @Override
    public InputStream getFile(String id) {
        System.out.println(id);
        DB db = mongoTemplate.getDb();
        GridFS gfsPhoto = new GridFS(db, "questionnaireFiles");
        GridFSDBFile imageForOutput = gfsPhoto.find(new ObjectId(id));
        return imageForOutput.getInputStream();
    }

}

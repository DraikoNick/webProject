package by.gsu.epamlab.model.utils;

import org.apache.commons.fileupload.FileItem;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import static java.lang.System.out;

public class FileUpDownLoadUtils {
    public static String doUpload(List<FileItem> items, String filePath) throws Exception {
        Iterator<FileItem> iter = items.iterator();
        String fileName = "";
        while (iter.hasNext()) {
            FileItem item = iter.next();
            if (!item.isFormField()) {
                fileName = item.getName();
                item.write(new File(filePath + fileName));
            }
        }
        return fileName;
    }

    public static void doDownLoad(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        int i;
        while ((i = fis.read()) != -1){
            out.write(i);
        }
        fis.close();
    }

}

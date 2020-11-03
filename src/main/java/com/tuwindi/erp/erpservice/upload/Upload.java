package com.tuwindi.erp.erpservice.upload;

import com.tuwindi.erp.erpservice.config.Config;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Upload {
    private static Upload instance = null;
    private Matcher matcherImage;
    private final Pattern patternImage;
    private final static String EXTENSION = "([^\\s]+(\\.(?i)(jpg|JPG|PNG|png|GIF|gif|pdf|PDF))$)";

    public Upload() {
        patternImage = Pattern.compile(EXTENSION);
    }

    /**
     *
     * @return
     */
    public static Upload getInstance() {
        if (instance == null) {
            instance = new Upload();
        }
        return instance;
    }

    public boolean isValideFile(String filename) {
        return patternImage.matcher(filename).matches();
    }

    public String getFileExt(String file) {
        int pos = file.lastIndexOf(".");
        if (pos > -1) {
            return file.substring(pos);
        } else {
            return file;
        }
    }

    public String getFileName(String name) {
        String fileName = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date d = new Date();
        String tab[] = name.split("\\.");
        if (tab != null && tab.length > 1) {
            String extension = tab[tab.length - 1];
            fileName = sdf.format(d) + generateCode(5) + "." + extension;
        }
        return fileName;
    }

    public String generateCode(int length){
        String chars = "0123456789";
        StringBuilder pass = new StringBuilder(chars.length());
        for(int x = 0; x < length; x++){
            int i = (int)(Math.random() * chars.length());
            pass.append((chars.charAt(i)));
        }
        return pass.toString();
    }

    public String getPath(String cle) {
        try {
            return Config.getInstance().getValue(cle);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return "";
    }

    public String uploadFile(String fileName, InputStream inputStream, String path){
        if (inputStream != null) {
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                File f = new File(getPath(path) + "/" + fileName);
                bis = new BufferedInputStream(inputStream);
                FileOutputStream fos = new FileOutputStream(f);
                bos = new BufferedOutputStream(fos);
                int x;
                while ((x = bis.read()) != -1) {
                    bos.write(x);
                }
                return "OK";
            } catch (IOException ex) {
                Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    bos.flush();
                    bos.close();
                    bis.close();
                } catch (IOException ex) {
                    Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "NOK";
    }
}

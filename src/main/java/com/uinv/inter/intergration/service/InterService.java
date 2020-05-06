package com.uinv.inter.intergration.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uinv.inter.intergration.dao.InterDao;
import com.uinv.inter.intergration.dao.InterImageDao;
import com.uinv.inter.intergration.dao.InterTagDao;
import com.uinv.inter.intergration.pojo.InterDoc;
import com.uinv.inter.intergration.pojo.InterImage;
import com.uinv.inter.intergration.pojo.InterTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GQ.Yin
 * @version 1.0
 * @title
 * @date 2020/05/01 10:53
 */
@Service
public class InterService {
    private static final Logger logger = LoggerFactory.getLogger(InterService.class);
    @Autowired
    private InterDao interDao;
    @Autowired
    private InterTagDao interTagDao;
    @Autowired
    private InterImageDao interImageDao;
//    private String filePath = "D:/";
    private String filePath = System.getProperty("user.dir") + "/upload/";

    public String uploadFile(String docId, String uid, MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        fileName = fileName.substring(fileName.lastIndexOf("."));
        fileName = uid + fileName;
        String filePH = filePath + docId + "_" + fileName;
        File image = new File(filePH);
        file.transferTo(image);
        logger.info("upload image >>>, {}",filePath + docId + "_" + fileName);

        InterImage interImage = interImageDao.findByInterId(docId);
        if (interImage == null) {
            JSONArray images = new JSONArray();
            images.add(docId + "_" + fileName);
            interImageDao.saveAndFlush(new InterImage(docId, JSON.toJSONString(images)));
        } else {
            JSONArray images = JSON.parseArray(interImage.getInterImage());
            images.add(docId + "_" + fileName);
            interImage.setInterImage(JSON.toJSONString(images));
            interImageDao.saveAndFlush(interImage);
        }
        return "0";
    }


    public String uploadTextFile(String docId, String uid, MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        fileName = fileName.substring(fileName.lastIndexOf("."));
        fileName = uid + fileName;
        File text = new File(filePath + docId + fileName);
        file.transferTo(text);
        logger.info("upload text file >>>, {}",filePath + docId + fileName);
        String code = readFileContent(filePath + docId + fileName, null);
        if (text.exists()) {
            text.delete();
        }
        if (code!=null) {
            return code;
        }else {
            return "";
        }
    }



    public String removeImage(String interId, String uid, String fileName) throws Exception {
        fileName = fileName.substring(fileName.lastIndexOf("."));
        fileName = uid + fileName;
        File file = new File(filePath + interId + "_" + fileName);
        if (file.exists()) {
            file.delete();
            logger.info("delete image >>>, {}",filePath + interId + "_" + fileName);
            InterImage interImage = interImageDao.findByInterId(interId);
            if (interImage != null) {
                JSONArray images = JSON.parseArray(interImage.getInterImage());
                images.remove(interId + "_" + fileName);
                interImage.setInterImage(JSON.toJSONString(images));
                interImageDao.saveAndFlush(interImage);
                return "0";
            }
        }
        return "1";
    }


    public byte[] getImage(String fileName) throws IOException {
        File file = new File(filePath + fileName);
        if (file.exists()) {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
        } else {
            return new byte[0];
        }
    }


    public String docSave(String param) {
        try {
            InterDoc interDoc = JSON.parseObject(param, InterDoc.class);
            interDao.saveAndFlush(interDoc);
            logger.info("save interDoc id >>>, {}",interDoc.getInterId());
            JSONArray tags = JSON.parseArray(interDoc.getInterTag());
            tags.forEach(tag -> {
                interTagDao.save(new InterTag(tag.toString(), tag.toString()));
            });
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    public String queryDoc(String param) {
        JSONArray paramArr = JSON.parseArray(param);
        Specification querySpeciByTag = (Specification<InterDoc>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            paramArr.forEach(tag -> {
                predicates.add(criteriaBuilder.like(root.get("interTag"), "%" + tag + "%"));
            });
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        Specification querySpeciByName = (Specification<InterDoc>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            paramArr.forEach(tag -> {
                predicates.add(criteriaBuilder.like(root.get("interTag"), "%" + tag + "%"));
                predicates.add(criteriaBuilder.like(root.get("interName"), "%" + tag + "%"));
                predicates.add(criteriaBuilder.like(root.get("interDesc"), "%" + tag + "%"));
            });
            return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
        };
        Sort sort = Sort.by(Sort.Direction.DESC, "interUpdateTime");
        PageRequest pageRequest = PageRequest.of(0, 200, sort);
        Page<InterDoc> all = interDao.findAll(querySpeciByTag, pageRequest);
        List<InterDoc> interDocs = all.getContent();
        if (interDocs.size() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            interDocs.forEach(doc -> {
                doc.setInterCreateTime(sdf.format(doc.getInterUpdateTime()));
            });
            return JSON.toJSONString(interDocs);
        } else {
            Page<InterDoc> allSec = interDao.findAll(querySpeciByName, pageRequest);
            List<InterDoc> interDocsSec = allSec.getContent();
            if (interDocsSec.size() > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                interDocsSec.forEach(doc -> {
                    doc.setInterCreateTime(sdf.format(doc.getInterUpdateTime()));
                });
                return JSON.toJSONString(interDocsSec);
            } else {
                return JSON.toJSONString(new JSONArray());
            }
        }
    }

    public String queryTag() {
        List<InterTag> interTags = interTagDao.findAll();
        if (interTags.size() > 0) {
            JSONArray retTags = new JSONArray();
            interTags.forEach(singleTag -> {
                JSONObject newTag = new JSONObject();
                newTag.put("value", singleTag.getTagName());
                retTags.add(newTag);
            });
            return JSON.toJSONString(retTags);
        } else {
            return JSON.toJSONString(new JSONArray());
        }
    }

    public String queryDocById(String interId) {
        InterDoc interDoc = interDao.findByInterId(interId);
        if (interDoc != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            interDoc.setInterCreateTime(sdf.format(interDoc.getInterUpdateTime()));
            InterImage interImage = interImageDao.findByInterId(interId);
            if (interImage != null) {
                interDoc.setInterImage(interImage.getInterImage());
            }
            return JSON.toJSONString(interDoc);
        } else {
            return JSON.toJSONString(new JSONObject());
        }
    }

    public String modifyDocById(String interId) {
        InterDoc interDoc = interDao.findByInterId(interId);
        if (interDoc != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            interDoc.setInterCreateTime(sdf.format(interDoc.getInterUpdateTime()));
            InterImage interImage = interImageDao.findByInterId(interId);
            if (interImage != null) {
                JSONArray interImageSec = new JSONArray();
                JSONArray imagePath = JSON.parseArray(interImage.getInterImage());
                imagePath.forEach(img ->{
                    JSONObject imgObj = new JSONObject();
                    imgObj.put("url", img);
                    String tmImg = img.toString();
                    imgObj.put("uid", tmImg.substring(tmImg.lastIndexOf("_")+1, tmImg.lastIndexOf(".")));
                    interImageSec.add(imgObj);
                });
                interDoc.setInterImage(JSON.toJSONString(interImageSec));
            }
            return JSON.toJSONString(interDoc);
        } else {
            return JSON.toJSONString(new JSONObject());
        }
    }

    public int countDoc() {
        return interDao.countInterDocBy();
    }

    /**
     * 读取文件内容
     * @param filename
     * @param charset
     * @return
     */
    public static String readFileContent(String filename, String charset){
        if (charset == null || charset.length() < 1) {
            charset = "utf-8";
        }
        InputStream fis=null;
        try{
            fis=new FileInputStream(new File(filename));
            byte[] buff = new byte[1024*1024];
            List<Byte> list = new ArrayList<Byte>();
            while(true){
                int index = fis.read(buff);
                if(index>=0){
                    for(int i=0;i<index;i++){
                        list.add(Byte.valueOf(buff[i]));
                    }
                    buff = new byte[1024*1024];
                }else{
                    break;
                }
            }
            byte[] buff_final = new byte[list.size()];
            for(int i=0;i<list.size();i++){
                buff_final[i] = list.get(i).byteValue();
            }
            return new String(buff_final, charset).trim();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fis = null;
        }
    }
}

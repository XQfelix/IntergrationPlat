package com.uinv.inter.intergration.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.uinv.inter.intergration.service.InterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author GQ.Yin
 * @version 1.0
 * @title
 * @date 2020/05/01 10:53
 */
@RestController
@CrossOrigin
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private InterService interService;


    /**
     * 上传图片
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/inter/upload", method = RequestMethod.POST)
    public String uploadFlie(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file) throws Exception {
        return interService.uploadFile(request.getParameter("interId"), request.getParameter("uid"), file);
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/inter/uploadtext", method = RequestMethod.POST)
    public String uploadTextFlie(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file) throws Exception {
        return interService.uploadTextFile(request.getParameter("interId"), request.getParameter("uid"), file);
    }

    /**
     * 获取图片
     *
     * @param filename
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/inter/getimage/{filename}",produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    public byte[] getImage(@PathVariable String filename) throws IOException {
        return interService.getImage(filename);
    }


    /**
     * 删除图片
     *
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/inter/removeimage", method = RequestMethod.POST)
    public String removeImage(@RequestBody byte[] body) throws Exception {
        JSONObject bodyObj = JSON.parseObject(new String(body, "utf-8"));
        return interService.removeImage(bodyObj.getString("interId"), bodyObj.getString("uid"), bodyObj.getString("fileName"));
    }


    /**
     * 保存文档
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/inter/save", method = RequestMethod.POST)
    public String docSave(@RequestBody byte[] body) throws Exception {
        return interService.docSave(new String(body, "utf-8"));
    }


    /**
     * 查询文档
     * @param body
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/inter/querydoc", method = RequestMethod.POST)
    public String queryDoc(@RequestBody byte[] body) throws Exception {
        logger.info("query tags >>>, {}", new String(body, "utf-8"));
        return interService.queryDoc(new String(body, "utf-8"));
    }

    /**
     * 查询关键词列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/inter/querytag", method = RequestMethod.GET)
    public String queryTag() throws Exception {
        return interService.queryTag();
    }


    /**
     * 查询单个文档
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/inter/querydocbyid", method = RequestMethod.POST)
    public String queryDocById(@RequestBody byte[] body) throws Exception {
        return interService.queryDocById(new String(body, "utf-8"));
    }


    /**
     * 查询单个文档
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/inter/modifydocbyid", method = RequestMethod.POST)
    public String modifyDocById(@RequestBody byte[] body) throws Exception {
        return interService.modifyDocById(new String(body, "utf-8"));
    }

    /**
     * 优化建议
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/inter/docsuggest", method = RequestMethod.POST)
    public String docSuggest(@RequestBody byte[] body) throws Exception {
        return "";
    }


    /**
     * 总数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/inter/countdoc", method = RequestMethod.GET)
    public int countDoc() throws Exception {
        return interService.countDoc();
    }

}

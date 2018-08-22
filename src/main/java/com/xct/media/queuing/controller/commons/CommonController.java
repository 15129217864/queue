package com.xct.media.queuing.controller.commons;

import com.xct.media.queuing.storage.StorageService;
import com.xct.media.queuing.util.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Chris on 2018/5/11.
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    private Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private StorageService storageService;

    /**
     * 服务端加载图片
     *
     * @param type     图片类型
     * @param filename 图片名称
     * @return
     */
    @GetMapping("/biz/{type}/{filename:.+}")
    public ResponseEntity<Resource> getBizStorageImage(@PathVariable int type, @PathVariable String filename) {
        logger.info("get photo png, type [{}] and filename [{}]", type, filename);
        Resource storageImageResource = storageService.loadAsResource(type, filename);
        return ResponseEntity.ok(storageImageResource);
    }


    /**
     * 业务员图片加载
     *
     * @param filename
     * @return
     */
    @GetMapping("/biz/{filename:.+}")
    public ResponseEntity<Resource> getJobUserStorageImage(@PathVariable String filename) {
        Resource storageImageResource = storageService.loadAsResource(filename);
        return ResponseEntity.ok(storageImageResource);
    }

    /**
     * 背景图上传
     *
     * @param multipartFile
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/biz/upload")
    public String uploadBackground(@RequestParam("type") String imgType, @RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes) {

        String originalFileName = multipartFile.getOriginalFilename();
        String fileName = storageService.store(imgType, multipartFile);
        logger.info("up load images, image name {}", fileName);
        // 传递参数会存放在session里
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + originalFileName + "!");
        return "upload";
    }


    @RequestMapping("/load")
    @ResponseBody
    public ApiResult<List<String>> listUploadedFiles(@RequestParam("type") String imgType) throws IOException {

        logger.info("get list upload files type param value {}", imgType);
        List<String> stringList = storageService.loadAll(imgType)
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(CommonController.class, "getBizStorageImage", imgType, path.getFileName().toString())
                                .build()
                                .toString()
                )
                .collect(Collectors.toList());
        ApiResult<List<String>> apiResult = new ApiResult<>();
        apiResult.setData(stringList);
        return apiResult;
    }
}

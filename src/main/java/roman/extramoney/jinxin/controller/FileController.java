package roman.extramoney.jinxin.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Api("文件管理接口")
@RequestMapping("file")
@RestController
public class FileController {

    @Value("${file.location}")
    private String filePath;

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @ApiOperation(value="文件上传")
    private Map<String,Object> upload(@RequestParam("file") MultipartFile file) throws IOException {
        HashMap<String, Object> result = new HashMap<>();
        String fileName = file.getOriginalFilename();
        FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(new File(filePath,fileName)));
        result.put("URI",fileName);
        return result;
    }

    @RequestMapping(value="/upload/multifile", method = RequestMethod.POST)
    @ApiOperation(value="多文件上传")
    private List<Map<String, Object>> uploadMultifile(@RequestParam("files") MultipartFile[] files) throws IOException {
        return Arrays.stream(files).map(file->{
            try {
                return upload(file);
            } catch (IOException e) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @RequestMapping(value="/download/{file:.+}", method = RequestMethod.GET)
    @ApiOperation(value="文件下载")
    private void download(HttpServletResponse response, @PathVariable String file) throws IOException {
        File downloadFile = new File(filePath, file);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(file.getBytes("utf-8"),"iso-8859-1") + "\"");
        response.addHeader("Content-Length", "" + downloadFile.length());
        response.setContentType("application/octet-stream;charset=UTF-8");
        FileCopyUtils.copy(new FileInputStream(downloadFile),response.getOutputStream());

    }
}

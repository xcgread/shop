package com.xuzhihao.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xuzhihao.shop.dto.FileSystem;

import cn.hutool.core.io.IoUtil;

@Controller
@RequestMapping("/fastdfs")
public class FastdfsController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FastdfsController.class);

	@Value("${fastdfs.upload_location}")
	private String upload_location;

	@Value("${fastdfs.priview_url}")
	private String priview_url;

	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		return "index";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public FileSystem upload(@RequestParam("file") MultipartFile file) {
		// 将文件先存储在web服务器上（本机），再调用fastDFS的client将文件上传到 fastDSF服务器
		FileSystem fileSystem = new FileSystem();
		String originalFilename = file.getOriginalFilename();// 得到 文件的原始名称
		String extention = originalFilename.substring(originalFilename.lastIndexOf("."));// 扩展名
		String fileNameNew = UUID.randomUUID() + extention;
		String filePath = upload_location + fileNameNew;
		File file1 = new File(filePath);
		if (!file1.getParentFile().exists())
			file1.getParentFile().mkdirs();
		// 获取新上传文件的物理路径
		String newFilePath = file1.getAbsolutePath();
		// 创建tracker的客户端
		TrackerServer trackerServer = null;
		StorageServer storageServer = null;
		try {
			file.transferTo(file1);
			// 加载fastDFS客户端的配置 文件
			ClientGlobal.initByProperties("config/fastdfs-client.properties");
			TrackerClient tracker = new TrackerClient();
			trackerServer = tracker.getConnection();
			// 定义storage的客户端
			StorageClient1 client = new StorageClient1(trackerServer, storageServer);
			// 文件元信息
			NameValuePair[] metaList = new NameValuePair[1];
			metaList[0] = new NameValuePair("fileName", originalFilename);
			// 执行上传，将上传成功的存放在web服务器（本机）上的文件上传到 fastDFS
			String fileId = client.upload_file1(newFilePath, null, metaList);
			LOGGER.info("upload success. file id is: {}", fileId);
			fileSystem.setFileId(fileId);
			fileSystem.setFilePath(priview_url + fileId);
			fileSystem.setFileName(originalFilename);
			// todo
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("上传发生错误: {}！", e.getMessage());
		} finally {
			try {
				if (null != trackerServer)
					trackerServer.close();
				if (null != storageServer)
					storageServer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return fileSystem;
	}

	/**
	 * 
	 * @param fileId
	 */
	@RequestMapping("/download")
	@ResponseBody
	public void download(@RequestParam(required = true) String fileId, HttpServletResponse response) {
		TrackerServer trackerServer = null;
		StorageServer storageServer = null;
		try {
			// 加载fastDFS客户端的配置 文件
			ClientGlobal.initByProperties("config/fastdfs-client.properties");
			TrackerClient tracker = new TrackerClient();
			trackerServer = tracker.getConnection();
			StorageClient1 client = new StorageClient1(trackerServer, storageServer);
			byte[] bytes = client.download_file1(fileId);
			// 设置返回内容格式
			response.setContentType("application/octet-stream");
			// 把文件名按UTF-8取出并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码
			// 中文不要太多，最多支持17个中文，因为header有150个字节限制。
			// 这一步一定要在读取文件之后进行，否则文件名会乱码，找不到文件
			String fileName = "这是一个测试文件名称！@#%￥ZA.jpg";
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			// 设置下载弹窗的文件名和格式（文件名要包括名字和文件格式）
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			// 创建输出流对象
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(bytes);
			// 关闭流对象
			IoUtil.close(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != storageServer)
					storageServer.close();
				if (null != trackerServer)
					trackerServer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

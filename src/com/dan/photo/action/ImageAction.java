package com.dan.photo.action;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dan.photo.domain.Image;
import com.dan.photo.domain.User;
import com.dan.photo.service.ImageService;

/**
 * 图片控制器
 * @author gd
 */
@WebServlet(value = "/ImageAction")
@MultipartConfig
public class ImageAction extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Integer type = Integer.valueOf(request.getParameter("type"));
		ImageService imageService = new ImageService();

		if (type == 1) { // 上传图片
			String imageName = request.getParameter("image_name");
			Part image = request.getPart("image");
			Image img = new Image();
			img.setDate(new Date());
			img.setName(imageName);
			img.setUser((User) request.getSession().getAttribute("user"));
			img.setUrl(img.getUser().getUsername() + "/" + UUID.randomUUID());
			imageService.addImage(img, image.getInputStream());
			request.getSession().setAttribute("imageList", imageService.getByUserId(img.getUser().getId()));
			response.sendRedirect(request.getContextPath() + "/home.jsp");
		} else if (type == 2) { // 删除图片
			String ids = request.getParameter("ids");
			String urls = request.getParameter("urls");
			imageService.delByIdsAndUrls(ids, urls);
			request.getSession().setAttribute("imageList",
					imageService.getByUserId(((User) request.getSession().getAttribute("user")).getId()));
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

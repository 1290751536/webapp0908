package demo0908.servlet;

import demo0908.dao.UserDao;
import demo0908.entity.User;
import demo0908.utils.EncryptUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(value = "/UpdataInfoServlet")
public class UpdataInfoServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDao userDao = new UserDao();
        User user = (User) session.getAttribute("user");

        String uploadPath = getServletContext().getRealPath("/WEB-INF/user-avatar");
        System.out.println(uploadPath);
        File uploadFile = new File(uploadPath);
        String tmpPath = getServletContext().getRealPath("/tmp");
        File tmpFile = new File(tmpPath);

        DiskFileItemFactory factory = getDiskFileItemFactory(tmpFile);
        ServletFileUpload servletFileUpload = getServletFileUpload(factory);
        try {
            Map<String, String> mp = uploadRequest(request, servletFileUpload, uploadPath);
            String userId = mp.get("userid");
            String userName = mp.get("username");
            String password = mp.get("password");
            String avatar = mp.get("avatar");
            if (userDao.updateUserName(userId, userName)) {
                user.setUserName(userName);
            }
            if (!password.equals(user.getPassword())) {
                if (userDao.updatePassword(userId, EncryptUtils.digest(password))) {
                    user.setPassword(EncryptUtils.digest(password));
                }
            }
            System.out.println(avatar);
            userDao.updateAvatar(userId, avatar);
            session.setAttribute("user", user);

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> uploadRequest(HttpServletRequest request, ServletFileUpload servletFileUpload, String uploadPath) throws FileUploadException, IOException {
        List<FileItem> fileItems = servletFileUpload.parseRequest(request);
        Map<String, String> mp = new HashMap<>();
        for (FileItem fileItem : fileItems) {
            if (fileItem.isFormField()) {
                String filedName = fileItem.getFieldName(); // 获取字段名：表单项name属性的值
                String value = fileItem.getString("UTF-8"); // FileItem对象中保存的数据流内容：即表单项为普通文本的输入值
                System.out.println(filedName + ":" + value);
                mp.put(filedName, value);
            } else {
                String fileName = fileItem.getName();
                if (fileItem == null || fileName.isEmpty()) {
                    continue;
                }
                String fileExtName = fileName.substring(fileName.lastIndexOf("."));
                String avatar = UUID.randomUUID().toString() + fileExtName;
                mp.put("avatar", uploadPath + "/" + avatar);
                InputStream in = fileItem.getInputStream();
                FileOutputStream fos = new FileOutputStream(uploadPath + "/" + avatar);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fileItem.delete(); // 上传成功清除临时文件
                fos.close();
                in.close();
            }
        }
        return mp;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private DiskFileItemFactory getDiskFileItemFactory(File tmpFile) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024); // 缓冲区大小1M
        factory.setRepository(tmpFile); // 临时文件夹
        return factory;
    }

    private ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
        ServletFileUpload servletFileUpload = new ServletFileUpload();
        servletFileUpload.setFileItemFactory(factory);
        servletFileUpload.setHeaderEncoding("UTF-8");
        servletFileUpload.setFileSizeMax(1024 * 1024 * 5); // 5M
        return servletFileUpload;
    }
}

package com.hoainam.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hoainam.entity.User;
import com.hoainam.service.UploadService;
import com.hoainam.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UploadService uploadService;

    @GetMapping("")
    public String index(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/users/list";
    }

    @GetMapping("/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/users/create";
    }

    @PostMapping("/create")
    public String createUserPage(@ModelAttribute("newUser") User user,
            @RequestParam("avatarFile") MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                // Lưu file vào thư mục /resources/images/avatars
                String fileName = uploadService.handleSaveUploadFile(file, "avatars");
                user.setImages(fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        userService.create(user);
        return "redirect:/admin/users";
    }

    @RequestMapping("/update/{username}")
    public String getUpdateUserPage(Model model, @PathVariable String username) {
        User currentUser = this.userService.findById(username);
        model.addAttribute("newUser", currentUser);
        return "admin/users/update";
    }

    @PostMapping("/update")
    public String updateUser(Model model,
            @ModelAttribute("newUser") User user,
            @RequestParam("avatarFile") MultipartFile file) {

        User currentUser = this.userService.findById(user.getUsername());
        if (currentUser != null) {
            currentUser.setPhone(user.getPhone());
            currentUser.setEmail(user.getEmail());
            currentUser.setFullname(user.getFullname());
            currentUser.setActive(user.getActive());
            currentUser.setAdmin(user.getAdmin());

            try {
                if (file != null && !file.isEmpty()) {
                    String fileName = uploadService.handleSaveUploadFile(file, "avatars");
                    currentUser.setImages(fileName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.userService.update(currentUser);
        }

        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{username}")
    public String getDeleteUserPage(Model model, @PathVariable String username) {
        User user = userService.findById(username);
        model.addAttribute("newUser", user);
        return "admin/users/delete";
    }

    @PostMapping("/delete")
    public String getDeleteUser(Model model, @ModelAttribute("newUser") User user) {
        this.userService.delete(user.getUsername());
        return "redirect:/admin/users";
    }
    
    @GetMapping("/searchpaginated")
    public String search(Model model,
                         @RequestParam(name = "name", required = false) String name, // Dùng biến 'name' đại diện cho username
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        // Sắp xếp theo Username
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("username"));
        Page<User> resultPage = null;

        if (StringUtils.hasText(name)) {
            resultPage = userService.search(name, pageable);
            model.addAttribute("name", name);
        } else {
            resultPage = userService.findAll(pageable);
        }

        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);

            if (totalPages > 5) {
                if (end == totalPages) start = end - 5;
                else if (start == 1) end = start + 5;
            }

            List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("userPage", resultPage);
        return "admin/users/searchpaging";
    }
}

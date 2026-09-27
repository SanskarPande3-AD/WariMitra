package anudip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import anudip.entity.Admin;
import anudip.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Add Admin
    @PostMapping
    public Admin addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    // Get All Admins
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    // Get Admin By ID
    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    // Update Admin
    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        return adminService.updateAdmin(id, admin);
    }

    // Delete Admin
    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        return adminService.deleteAdmin(id);
    }
}